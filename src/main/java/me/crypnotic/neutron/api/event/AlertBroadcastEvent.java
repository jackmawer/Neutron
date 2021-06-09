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
import me.crypnotic.neutron.api.user.User;
import net.kyori.adventure.text.Component;

import java.util.Optional;

public final class AlertBroadcastEvent {

    private final Optional<User<? extends CommandSource>> author;
    private final String unformattedText;
    private final Component message;

    public AlertBroadcastEvent(Optional<User<? extends CommandSource>> author, String unformattedText, Component message) {
        this.author = author;
        this.unformattedText = unformattedText;
        this.message = message;
    }

    public Optional<User<? extends CommandSource>> getAuthor() {
        return this.author;
    }

    public String getUnformattedText() {
        return this.unformattedText;
    }

    public Component getMessage() {
        return this.message;
    }
}
