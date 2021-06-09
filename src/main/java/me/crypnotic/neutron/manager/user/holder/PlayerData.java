package me.crypnotic.neutron.manager.user.holder;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.velocitypowered.api.command.CommandSource;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@ConfigSerializable
class PlayerData {

    // Persistent data - this is saved to disk.

    @Setting(comment = "The version of this config. Don't change this!")
    private int configVersion = 1;

    @Setting(comment = "The player's last known username.")
    private String username;

    @Setting(comment = "Players that this player is ignoring")
    private List<UUID> ignoredPlayers = Collections.emptyList(); // Configurate includes a List TypeSerializer, so let's use that.

    public PlayerData() {
    }

    public Set<UUID> getIgnoredPlayers() {
        return Sets.newHashSet(ignoredPlayers);
    }

    public void setIgnoredPlayers(Set<UUID> ignoredPlayers) {
        this.ignoredPlayers = Lists.newArrayList(ignoredPlayers);
    }

    // Non-persisted data - this is not saved when the user is unloaded.

    private WeakReference<CommandSource> replyRecipient = null;

    public CommandSource getReplyRecipient() {
        return replyRecipient != null ? replyRecipient.get() : null;
    }

    public void setReplyRecipient(CommandSource replyRecipient) {
        this.replyRecipient = new WeakReference<>(replyRecipient);
    }

    public int getConfigVersion() {
        return this.configVersion;
    }

    public String getUsername() {
        return this.username;
    }

    public void setConfigVersion(int configVersion) {
        this.configVersion = configVersion;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerData that = (PlayerData) o;
        return configVersion == that.configVersion && Objects.equal(username, that.username) && Objects.equal(ignoredPlayers, that.ignoredPlayers) && Objects.equal(replyRecipient, that.replyRecipient);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(configVersion, username, ignoredPlayers, replyRecipient);
    }

    public String toString() {
        return "PlayerData(configVersion=" + this.getConfigVersion() + ", username=" + this.getUsername() + ", ignoredPlayers=" + this.getIgnoredPlayers() + ", replyRecipient=" + this.getReplyRecipient() + ")";
    }
}
