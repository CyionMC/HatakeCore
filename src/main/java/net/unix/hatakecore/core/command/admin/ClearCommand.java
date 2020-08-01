package net.unix.hatakecore.core.command.admin;

import net.unix.hatakecore.api.command.Command;
import net.unix.hatakecore.api.command.CommandInfo;
import net.unix.hatakecore.api.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

/**
 * @author Unix on 05.10.2019.
 */
@CommandInfo(
        value = "clear",
        usage = "(gracz)",
        permission = "core.clear",
        aliases = { "clearinv", "wyczysc" }
        )
public class ClearCommand extends Command {
    @Override
    public void onCommand(Player player, String... args) {
        if (args.length > 1) {
            this.sendUsage(player);
            return;
        }

        final String name;

        try {
            name = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.clearInventory(player.getInventory());
            ChatHelper.sendMessage(player, "&8>> &aWyczyszczono ekwipunek!");
            return;
        }

        final Player other = Bukkit.getPlayerExact(name);

        if (other == null) {
            ChatHelper.sendMessage(player, "&4Blad: &cNie znaleziono gracza!");
            return;
        }

        this.clearInventory(other.getInventory());
        ChatHelper.sendMessage(other, "&8>> &aAdministrator &6" + player.getName() + " &awyczyscil Tobie ekwipunek!");
        ChatHelper.sendMessage(player, "&8>> &aWyczysciles ekwipunek graczowi &6" + name + "&a!");
    }

    private void clearInventory(@NotNull PlayerInventory inventory) {
        inventory.setHeldItemSlot(0);
        inventory.setHelmet(null);
        inventory.setChestplate(null);
        inventory.setLeggings(null);
        inventory.setBoots(null);
        inventory.clear();
    }
}