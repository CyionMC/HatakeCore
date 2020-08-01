package net.unix.hatakecore.api.command;

import net.unix.hatakecore.api.helper.ChatHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Unix
 * 14:07, 11.05.2019
 **/
public class BukkitCommand extends org.bukkit.command.Command {

    private final Command command;

    public BukkitCommand(@NotNull Command command) {
        super(command.getName(), "", "", command.getAliases());
        this.command = command;
    }

    @Override
    public boolean execute(CommandSender sender, String string, String[] args) {
        if (!(sender instanceof Player)) {
            ChatHelper.sendMessage(sender, "&8>> &cTej komendy nie mozna wywolac z poziomu konsoli!");
            return true;
        }

        final String permission = this.command.getPermission();

        if (!permission.isEmpty() && !sender.hasPermission(permission)) {
            ChatHelper.sendMessage(sender, "&8>> &cNie posiadasz permisji &4(" + permission + "&4) &cdo uzycia tej komendy!");
            return true;
        }

        this.command.onCommand((Player) sender, args);
        return true;
    }
}