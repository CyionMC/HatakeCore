package net.unix.hatakecore.core.command.admin;

import net.unix.hatakecore.api.command.Command;
import net.unix.hatakecore.api.command.CommandInfo;
import net.unix.hatakecore.api.helper.ChatHelper;
import net.unix.hatakecore.api.helper.GameModeHelper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author Unix on 05.10.2019.
 */
@CommandInfo(
        value = "gamemode",
        usage = "(tryb) (gracz)",
        permission = "core.gamemode",
        aliases = { "gm" }
)
public class GameModeCommand extends Command {
    @Override
    public void onCommand(Player player, String... args) {
        if (args.length < 1 || args.length > 2) {
            this.sendUsage(player);
            return;
        }

        final Optional<GameMode> gameMode = GameModeHelper.parseGameMode(args[0]);
        final Runnable runnable = () -> ChatHelper.sendMessage(player, "&4Blad: &cNie znaleziono takiego trybu!");

        gameMode.ifPresentOrElse(g -> {
            player.setGameMode(g);
            ChatHelper.sendMessage(player, "&8>> &aTwoj tryb gry zostal zmieniony na &6" + g.name() + "&a!");
        }, runnable);
        if (args.length == 1) return;

        final Player other = Bukkit.getPlayerExact(args[1]);

        if (other == null) {
            ChatHelper.sendMessage(player, "&4Blad: &cNie znaleziono gracza!");
            return;
        }

        gameMode.ifPresentOrElse(g -> {
            other.setGameMode(g);
            ChatHelper.sendMessage(player, "&8>> &aZmieniles tryb gry graczowi &6" + other.getName() + " &ana &6" + g.name() + "&a!");
            ChatHelper.sendMessage(other, "&8>> &aTwoj tryb gry zostal zmieniony na &6" + g.name() + " &aprzez &6" + player.getName() + "&a!");
        }, runnable);
    }
}