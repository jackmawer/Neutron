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
package me.crypnotic.neutron.module.command;

import java.util.function.Supplier;

import me.crypnotic.neutron.api.command.CommandWrapper;
import me.crypnotic.neutron.module.command.options.*;

public enum Commands {
    ALERT("alert", AlertCommand::new),
    FIND("find", FindCommand::new),
    GLIST("glist", GlistCommand::new),
    IGNORE("ignore", IgnoreCommand::new),
    INFO("info", InfoCommand::new),
    KICK("kick", KickCommand::new),
    MESSAGE("message", MessageCommand::new),
    REPLY("reply", ReplyCommand::new),
    SEND("send", SendCommand::new);

    private final String key;
    private final Supplier<CommandWrapper> supplier;

    Commands(String key, Supplier<CommandWrapper> supplier) {
        this.key = key;
        this.supplier = supplier;
    }

    public String getKey() {
        return this.key;
    }

    public Supplier<CommandWrapper> getSupplier() {
        return this.supplier;
    }
}
