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
package me.crypnotic.neutron.api.locale;

public enum LocaleMessage {

    ALERT_MESSAGE("&7&l[&c&lALERT&7&l] &e{0}"),

    CONNECT_JOIN_MESSAGE("&b{0} &7joined the network"),
    CONNECT_QUIT_MESSAGE("&b{0} &7left the network"),
    SWITCH_SERVER_MESSAGE("&b{0} &7switched to {1}"),

    FIND_MESSAGE("&b{0} &7is connected to &b{1}"),

    IGNORE_AMBIGUOUS_PLAYER("&cPlayer '{0}' is ambiguous; did you mean: {1}"),
    IGNORE_LIST_EMPTY("&aYou are not ignoring anyone."),
    IGNORE_LIST_HEAD("&aYou are ignoring the following players:\n"),
    IGNORE_LIST_ITEM("&f{0}&7, "),
    IGNORE_LIST_ITEM_UNKNOWN("&f&ounknown&7, "),
    IGNORE_NOW_IGNORING("&aYou are now ignoring {0}."),
    IGNORE_NOW_NOT_IGNORING("&aYou are no longer ignoring {0}."),

    INFO_HEADER("&l&7==> Information for player: &b{0}"),
    INFO_LOCALE("&7Locale: &b{0}"),
    INFO_PING("&7Ping: &b{0}"),
    INFO_SERVER("&7Current Server: &b{0}"),
    INFO_UUID("&7Unique Id: &b{0}"),
    INFO_VERSION("&7Minecraft Version: &b{0}"),

    INVALID_USAGE("&cUsage: {0}"),

    KICK_MESSAGE_NO_REASON("&cYou were kicked from the server by {0}!"),
    KICK_MESSAGE_WITH_REASON("&cYou were kicked from the server by {0}!\n\n&cReason: &f{1}"),
    KICK_RESPONSE_NO_REASON("&a{0} has been kicked."),
    KICK_RESPONSE_WITH_REASON("&a{0} has been kicked. Reason: &f{1}"),

    LIST_HEADER("&aThere are currently &b{0} &aplayers online\n&7&oHover over a server to see the players online"),
    LIST_MESSAGE("&a[{0}] &e{1} player{2} online"),

    MESSAGE_IGNORED_BY_TARGET("&cYou can't message {0} right now."),
    MESSAGE_IGNORING_TARGET("&cYou can't message {0} because you are ignoring them."),
    MESSAGE_SENDER("&b&lme \u00bb {0} &7> &o"),
    MESSAGE_RECEIVER("&b&l{0} \u00bb me &7> &o"),

    NO_PERMISSION("&cYou don't have permission to execute this command."),
    NOT_CONNECTED_TO_SERVER("&cYou must be connected to a server to use this subcommand."),

    REPLY_NO_RECIPIENT("&cYou don't have anyone to reply to."),

    SEND_ALL("&aAll players have been sent to &b{0}"),
    SEND_CURRENT("&aAll players from your current server have been sent to &b{0}"),
    SEND_MESSAGE("&aYou have been sent to &b{0}"),
    SEND_PLAYER("&b{0} &ahas been sent to &b{1}"),

    PLAYER_OFFLINE("&c{0} is currently offline."),
    PLAYER_ONLY_COMMAND("&cOnly players can use this subcommand."),
    PLAYER_ONLY_SUBCOMMAND("&cOnly players can use this subcommand."),

    UNKNOWN_PLAYER("&cUnknown player: {0}"),
    UNKNOWN_SERVER("&cUnknown server: {0}");

    private final String defaultMessage;
    private final String name = toString().toLowerCase();

    LocaleMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public static LocaleMessage match(String key) {
        try {
            return LocaleMessage.valueOf(key.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }

    public String getName() {
        return this.name;
    }
}
