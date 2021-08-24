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

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import me.crypnotic.neutron.NeutronPlugin;
import me.crypnotic.neutron.api.Neutron;
import me.crypnotic.neutron.api.locale.LocaleMessage;
import me.crypnotic.neutron.api.user.User;
import me.crypnotic.neutron.util.StringHelper;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.Optional;

public abstract class CommandWrapper implements SimpleCommand {

    private boolean enabled;
    private String[] aliases;

    public abstract void handle(CommandSource source, CommandContext context) throws CommandExitException;

    /**
     * Get suggestions for the command.
     *
     * @param source The command source
     * @param args The current arguments
     * @return The suggestions for the current argument
     */
    protected List<String> suggest(CommandSource source, String[] args) {
        return List.of();
    }

    public abstract String getUsage();

    @Override
    public final void execute(Invocation invocation) {
        try {
            this.handle(invocation.source(), new CommandContext(invocation.arguments()));
        } catch (CommandExitException e) {
            // Catch silently
        }
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        return this.suggest(invocation.source(), invocation.arguments());
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

    @SuppressWarnings("squid:S1452")
    public Optional<User<? extends CommandSource>> getUser(CommandSource source) {
        return getNeutron().getUserManager().getUser(source);
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public NeutronPlugin getNeutron() {
        return Neutron.getNeutron();
    }

    public static class CommandExitException extends RuntimeException {
        private static final long serialVersionUID = -1299193476106186693L;
    }
}
