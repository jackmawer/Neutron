package me.crypnotic.neutron.manager.user.holder;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import com.velocitypowered.api.proxy.Player;
import me.crypnotic.neutron.api.Neutron;
import me.crypnotic.neutron.api.user.User;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

// TODO: Consider whether this should support *any* CommandSource that isn't a player (ie plugin-provided CommandSources)
public class ConsoleUser implements User<ConsoleCommandSource> {

    private final String name;

    private CommandSource replyRecipient;

    public ConsoleUser(String name) {
        this.name = name;
    }

    @Override
    public Optional<ConsoleCommandSource> getBase() {
        return Optional.of(Neutron.getNeutron().getProxy().getConsoleCommandSource());
    }

    @Override
    public void load() throws Exception {
        /* noop */
    }

    @Override
    public void save() throws Exception {
        /* noop */
    }

    @Override
    public Optional<UUID> getUUID() {
        return Optional.empty();
    }

    @Override
    public void setIgnoringPlayer(Player target, boolean ignore) {
        /* noop */
    }

    @Override
    public boolean isIgnoringPlayer(Player target) {
        return false;
    }

    @Override
    public Set<UUID> getIgnoredPlayers() {
        return Collections.emptySet();
    }

    public String getName() {
        return this.name;
    }

    public CommandSource getReplyRecipient() {
        return this.replyRecipient;
    }

    public void setReplyRecipient(CommandSource replyRecipient) {
        this.replyRecipient = replyRecipient;
    }
}
