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

import net.kyori.adventure.text.Component;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.Collections;
import java.util.List;

@ConfigSerializable
public class AnnouncementData {

    @Setting("enabled")
    private boolean enabled = true;
    @Setting("interval")
    private long interval = 300;
    @Setting("maintain-order")
    private boolean maintainOrder = true;
    @Setting("messages")
    private List<Component> messages = Collections.emptyList();
    @Setting("prefix")
    private Component prefix = Component.empty();

    public AnnouncementData() {
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public long getInterval() {
        return this.interval;
    }

    public boolean isMaintainOrder() {
        return this.maintainOrder;
    }

    public List<Component> getMessages() {
        return this.messages;
    }

    public Component getPrefix() {
        return this.prefix;
    }
}
