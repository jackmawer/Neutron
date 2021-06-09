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
package me.crypnotic.neutron.api.command;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import me.crypnotic.neutron.NeutronPlugin;
import me.crypnotic.neutron.api.Neutron;
import me.crypnotic.neutron.api.locale.LocaleMessage;
import me.crypnotic.neutron.api.user.User;
import me.crypnotic.neutron.util.StringHelper;
import net.kyori.adventure.text.Component;

import java.util.Optional;

public abstract class CommandWrapper implements Command {

    private boolean enabled;
    private String[] aliases;

    @Override
    public void execute(CommandSource source, String[] args) {
        try {
            handle(source, new CommandContext(args));
        } catch (CommandExitException exception) {
            /* Catch silently */
        }
    }

    public void assertUsage(CommandSource source, boolean assertion) {
        assertCustom(source, assertion, LocaleMessage.INVALID_USAGE, getUsage());
    }

    public void assertPlayer(CommandSource source, LocaleMessage message, Object... values) {
        assertCustom(source, source instanceof Player, message, values);
    }

    public void assertNull(CommandSource source, Object value, LocaleMessage message, Object... values) {
        assertCustom(source, value == null, message, values);
    }

    public void assertNotNull(CommandSource source, Object value, LocaleMessage message, Object... values) {
        assertCustom(source, value != null, message, values);
    }

    public void assertNotIgnoring(CommandSource source, CommandSource ignoreSource, Player target, LocaleMessage message, Object... values) {
        getUser(ignoreSource).ifPresent(u -> assertCustom(source, !u.isIgnoringPlayer(target), message, values));
    }

    public void assertPermission(CommandSource source, String permission) {
        assertCustom(source, source.hasPermission(permission), LocaleMessage.NO_PERMISSION);
    }

    public void assertCustom(CommandSource source, boolean assertion, LocaleMessage message, Object... values) {
        if (!assertion) {
            message(source, message, values);

            throw new CommandExitException();
        }
    }

    public void message(CommandSource source, LocaleMessage message, Object... values) {
        StringHelper.message(source, message, values);
    }

    public Component getMessage(CommandSource source, LocaleMessage message, Object... values) {
        return StringHelper.getMessage(source, message, values);
    }

    public Optional<User<? extends CommandSource>> getUser(CommandSource source) {
        return getNeutron().getUserManager().getUser(source);
    }

    public abstract void handle(CommandSource source, CommandContext context) throws CommandExitException;

    public abstract String getUsage();

    public boolean isEnabled() {
        return this.enabled;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public class CommandExitException extends RuntimeException {
        private static final long serialVersionUID = -1299193476106186693L;
    }

    public NeutronPlugin getNeutron() {
        return Neutron.getNeutron();
    }
}
