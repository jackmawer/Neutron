package me.crypnotic.neutron.module.command.options;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import me.crypnotic.neutron.api.command.CommandContext;
import me.crypnotic.neutron.api.command.CommandWrapper;
import me.crypnotic.neutron.api.locale.LocaleMessage;
import me.crypnotic.neutron.api.user.User;
import net.kyori.text.Component;

public class KickCommand extends CommandWrapper {
    @Override
    public void handle(CommandSource source, CommandContext context) throws CommandExitException {
        assertPermission(source, "neutron.command.kick");
        assertUsage(source, context.size() > 0);

        Player player = getNeutron().getProxy().getPlayer(context.get(0)).orElse(null);
        assertNotNull(source, player, LocaleMessage.UNKNOWN_PLAYER);

        String sourceName = getUser(source).map(User::getName).orElse("unknown");
        Component kickMessage;

        if (context.size() > 1) {
            String reason = context.join(" ", 1);
            kickMessage = getMessage(player, LocaleMessage.KICK_MESSAGE_WITH_REASON, sourceName, reason);
            message(source, LocaleMessage.KICK_RESPONSE_WITH_REASON, player.getUsername(), reason);
        } else {
            kickMessage = getMessage(player, LocaleMessage.KICK_MESSAGE_NO_REASON, sourceName);
            message(source, LocaleMessage.KICK_RESPONSE_NO_REASON, player.getUsername());
        }

        player.disconnect(kickMessage);
    }

    @Override
    public String getUsage() {
        return "/kick (player) [reason]";
    }
}
