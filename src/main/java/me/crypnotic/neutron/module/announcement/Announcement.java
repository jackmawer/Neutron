/*
* This file is part of Neutron, licensed under the MIT License
*
* Copyright (c) 2019 Crypnotic <crypnoticofficial@gmail.com>
*
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/
package me.crypnotic.neutron.module.announcement;

import com.velocitypowered.api.scheduler.ScheduledTask;
import me.crypnotic.neutron.NeutronPlugin;
import me.crypnotic.neutron.util.StringHelper;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Announcement {

    private final NeutronPlugin plugin;
    private final String id;
    private final AnnouncementData data;

    private ScheduledTask task;
    private List<Component> localMessages;
    private Iterator<Component> iterator;

    public Announcement(NeutronPlugin plugin, String id, me.crypnotic.neutron.module.announcement.AnnouncementData data) {
        this.plugin = plugin;
        this.id = id;
        this.data = data;
    }

    public void init() {
        if (localMessages == null) {
            this.localMessages = data.getMessages().stream().map(message -> StringHelper.append(data.getPrefix(), message))
                    .collect(Collectors.toList());
        }

        if (!data.isMaintainOrder()) {
            Collections.shuffle(localMessages);
        }

        this.iterator = localMessages.iterator();
    }

    private void broadcast() {
        if (!iterator.hasNext()) {
            init();
        }

        plugin.getProxy().sendMessage(Identity.nil(), iterator.next());
    }

    public static Announcement create(NeutronPlugin neutron, String id, AnnouncementData data) {
        Announcement announcement = new Announcement(neutron, id, data);

        announcement.init();

        if (announcement.getData().isEnabled()) {
            announcement.task = neutron.getProxy().getScheduler().buildTask(neutron, announcement::broadcast)
                    .repeat(announcement.data.getInterval(), TimeUnit.SECONDS).schedule();
        }

        return announcement;
    }

    public String getId() {
        return this.id;
    }

    public me.crypnotic.neutron.module.announcement.AnnouncementData getData() {
        return this.data;
    }

    public ScheduledTask getTask() {
        return this.task;
    }
}
