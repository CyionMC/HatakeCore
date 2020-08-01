package net.unix.hatakecore.api.helper;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Unix on 05.10.2019.
 */
public class ItemBuilder {

    private final ItemStack                 itemStack;
    private final ItemMeta                  itemMeta;
    private final Map<Enchantment, Integer> enchantments;
    private final List<String>              lores;

    private ItemBuilder(@NotNull ItemStack itemStack) {
        this.itemStack    = itemStack;
        this.itemMeta     = itemStack.getItemMeta();
        this.enchantments = new HashMap<>();
        this.lores        = new ArrayList<>();
    }

    private ItemBuilder(Material material) {
        this(new ItemStack(material));
    }

    private ItemBuilder(Material material, int amount) {
        this(new ItemStack(material, amount));
    }

    private ItemBuilder(Material material, int amount, int data) {
        this(new ItemStack(material, amount, (short) data));
    }

    public ItemBuilder setName(String name) {
        this.itemMeta.setDisplayName(ChatHelper.fixColor(name));
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lores.clear();
        this.lores.addAll(lore);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        this.lores.add(ChatHelper.fixColor(lore));
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, Integer level) {
        this.enchantments.put(enchantment, level);
        return this;
    }

    public ItemStack build() {
        this.itemMeta.setLore(this.lores);
        this.itemStack.setItemMeta(this.itemMeta);
        this.itemStack.addUnsafeEnchantments(this.enchantments);

        return this.itemStack;
    }

    @NotNull
    @Contract("_ -> new")
    public static ItemBuilder of(Material material) {
        return new ItemBuilder(material);
    }

    @NotNull
    @Contract("_, _ -> new")
    public static ItemBuilder of(Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static ItemBuilder of(Material material, int amount, int data) {
        return new ItemBuilder(material, amount, data);
    }
}