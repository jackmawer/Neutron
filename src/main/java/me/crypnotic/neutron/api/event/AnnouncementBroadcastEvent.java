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
package me.crypnotic.neutron.api.event;

import com.velocitypowered.api.event.ResultedEvent;
import me.crypnotic.neutron.module.announcement.Announcement;
import net.kyori.adventure.text.Component;

public final class AnnouncementBroadcastEvent implements ResultedEvent<AnnouncementBroadcastEvent.BroadcastResult> {

    private final Announcement announcement;
    private final Component message;
    private BroadcastResult result;

    public AnnouncementBroadcastEvent(Announcement announcement, Component message) {
        this.announcement = announcement;
        this.message = message;
        this.result = BroadcastResult.create(message);
    }

    public Announcement getAnnouncement() {
        return this.announcement;
    }

    public Component getMessage() {
        return this.message;
    }

    public BroadcastResult getResult() {
        return this.result;
    }

    public void setResult(BroadcastResult result) {
        this.result = result;
    }

    public static final class BroadcastResult implements ResultedEvent.Result {

        private Component message;
        private boolean allowed;

        public BroadcastResult(Component message, boolean allowed) {
            this.message = message;
            this.allowed = allowed;
        }

        public static BroadcastResult create(Component message) {
            return new BroadcastResult(message, true);
        }

        public Component getMessage() {
            return this.message;
        }

        public boolean isAllowed() {
            return this.allowed;
        }

        public void setMessage(Component message) {
            this.message = message;
        }

        public void setAllowed(boolean allowed) {
            this.allowed = allowed;
        }
    }
}
