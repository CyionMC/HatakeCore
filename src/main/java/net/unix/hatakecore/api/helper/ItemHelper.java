package net.unix.hatakecore.api.helper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Unix on 05.10.2019.
 */
public final class ItemHelper {

    private ItemHelper() {
    }

    @NotNull
    public static Material findMaterial(String mat) {
        final Material material = Material.matchMaterial(mat);

        if (material == null) {
            Logger.getLogger("ItemHelper").log(Level.SEVERE, mat + " not exist!");
            return Material.AIR;
        }

        return material;
    }

    public static void giveOrDrop(@NotNull Player player, ItemStack itemStack, Location location) {
        player.getInventory()
                .addItem(itemStack)
                .values()
                .forEach(item -> location.getWorld().dropItem(location, item));

        player.updateInventory();
    }

    @NotNull
    public static Map<Enchantment, Integer> getEnchantments(@NotNull ConfigurationSection configurationSection) {
        final List<String> enchants = configurationSection.getStringList("enchants");

        if (enchants.isEmpty()) {
            return new HashMap<>();
        }

        final Map<Enchantment, Integer> enchantments = new HashMap<>();

        enchants.stream()
                .map(enchantment -> enchantment.split(":"))
                .forEach(split -> {

                    if (split.length == 0) {
                        return;
                    }

                    final Enchantment enchant = Enchantment.getByName(split[0].toUpperCase());

                    if (enchant == null) {
                        return;
                    }

                    final int level;

                    try {
                        level = Integer.parseInt(split[1]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Can't serialize enchantment! Level is not integer!");
                        return;
                    }

                    enchantments.put(enchant, level);
                });

        return enchantments;
    }
}