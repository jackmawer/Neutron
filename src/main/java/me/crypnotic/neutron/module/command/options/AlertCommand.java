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
package me.crypnotic.neutron.module.command.options;

import com.velocitypowered.api.command.CommandSource;
import me.crypnotic.neutron.api.command.CommandContext;
import me.crypnotic.neutron.api.command.CommandWrapper;
import me.crypnotic.neutron.api.event.AlertBroadcastEvent;
import me.crypnotic.neutron.api.locale.LocaleMessage;
import net.kyori.adventure.text.Component;

public class AlertCommand extends CommandWrapper {

    public AlertCommand() {
    }

    @Override
    public void handle(CommandSource source, CommandContext context) throws CommandExitException {
        assertPermission(source, "neutron.command.alert");
        assertUsage(source, context.size() > 0);

        String message = context.join(" ");

        getNeutron().getProxy().getAllPlayers().forEach(target -> message(target, LocaleMessage.ALERT_MESSAGE, message));

        Component consoleMessage = getMessage(getNeutron().getProxy().getConsoleCommandSource(), LocaleMessage.ALERT_MESSAGE, message);

        /* Fire event for API purposes */
        getNeutron().getProxy().getEventManager().fireAndForget(new AlertBroadcastEvent(getUser(source), message, consoleMessage));

        /* Log to console since ProxyServer#broadcast doesn't do so */
        getNeutron().getProxy().getConsoleCommandSource().sendMessage(consoleMessage);
    }

    @Override
    public String getUsage() {
        return "/alert (message)";
    }
}
