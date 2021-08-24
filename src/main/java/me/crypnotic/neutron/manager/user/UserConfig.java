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
package me.crypnotic.neutron.manager.user;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class UserConfig {

    @Setting(value = "cache", comment = "Advanced settings controlling how users are kept in memory")
    private Cache cache = new Cache();

    @Setting(value = "console", comment = "Default settings for the console user")
    private Console console = new Console();

    public me.crypnotic.neutron.manager.user.UserConfig.Cache getCache() {
        return this.cache;
    }

    public Console getConsole() {
        return this.console;
    }

    @ConfigSerializable
    public static class Cache {

        @Setting(value = "max-size", comment = "Maximum number of users to keep loaded")
        private int maxSize = 100;

        @Setting(value = "expiry", comment = "How long after its last access a user should stay loaded in minutes")
        private int expiryMins = 30;

        public int getMaxSize() {
            return this.maxSize;
        }

        public int getExpiryMins() {
            return this.expiryMins;
        }
    }

    @ConfigSerializable
    public static class Console {
        @Setting(value = "name", comment = "The console user's name")
        private String name = "Console";

        public String getName() {
            return this.name;
        }
    }
}
