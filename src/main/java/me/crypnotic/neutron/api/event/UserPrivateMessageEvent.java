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

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.event.ResultedEvent;
import me.crypnotic.neutron.api.user.User;
import net.kyori.adventure.text.Component;

import java.util.Optional;

public final class UserPrivateMessageEvent implements ResultedEvent<UserPrivateMessageEvent.PrivateMessageResult> {

    private final Optional<User<? extends CommandSource>> sender;
    private final Optional<User<? extends CommandSource>> recipient;
    private final Component message;
    private final boolean reply;
    private PrivateMessageResult result;

    public UserPrivateMessageEvent(Optional<User<? extends CommandSource>> sender, Optional<User<? extends CommandSource>> recipient,
            Component message, boolean reply) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.reply = reply;

        this.result = PrivateMessageResult.create();
    }

    public Optional<User<? extends CommandSource>> getSender() {
        return this.sender;
    }

    public Optional<User<? extends CommandSource>> getRecipient() {
        return this.recipient;
    }

    public Component getMessage() {
        return this.message;
    }

    public boolean isReply() {
        return this.reply;
    }

    public PrivateMessageResult getResult() {
        return this.result;
    }

    public void setResult(PrivateMessageResult result) {
        this.result = result;
    }

    public static final class PrivateMessageResult implements ResultedEvent.Result {

        private Optional<Component> reason;
        private boolean allowed;

        public PrivateMessageResult(Optional<Component> reason, boolean allowed) {
            this.reason = reason;
            this.allowed = allowed;
        }

        public void allow() {
            this.allowed = true;
        }

        public void deny(Component reason) {
            this.reason = Optional.ofNullable(reason);
            this.allowed = false;
        }

        public static PrivateMessageResult create() {
            return new PrivateMessageResult(Optional.empty(), true);
        }

        public Optional<Component> getReason() {
            return this.reason;
        }

        public boolean isAllowed() {
            return this.allowed;
        }

        public void setReason(Optional<Component> reason) {
            this.reason = reason;
        }
    }
}
