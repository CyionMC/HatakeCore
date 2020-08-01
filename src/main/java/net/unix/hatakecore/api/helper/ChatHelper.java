package net.unix.hatakecore.api.helper;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Unix
 * 14:10, 11.05.2019
 **/
public final class ChatHelper {

    private ChatHelper() {
    }

    @NotNull
    public static String fixColor(@NotNull String text) {
        return ChatColor.translateAlternateColorCodes('&', text)
                .replace(">>", "»")
                .replace("<<", "«");
    }

    @NotNull
    public static List<String> fixColor(@NotNull List<String> messages) {
        return messages.stream()
                .map(ChatHelper::fixColor)
                .collect(Collectors.toList());
    }

    public static void sendMessage(@NotNull Player player, String message) {
        player.sendMessage(fixColor(message));
    }

    public static void sendMessage(@NotNull CommandSender sender, String message) {
        sender.sendMessage(fixColor(message));
    }

    public static void sendMessage(@NotNull Collection<Player> players, @NotNull String message) {
        players.forEach(p -> sendMessage(p, message));
    }

    @NotNull
    public static String toString(@NotNull Collection<String> messages) {
        return fixColor(messages.toString().replace(", ", "\n")
                .replace("[", "")
                .replace("]", ""));
    }

    public static void sendActionBar(@NotNull Player player, @NotNull String message) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;

        craftPlayer.getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + fixColor(message) + "\"}"), (byte)2));
    }

    public static void sendAction(@NotNull Collection<Player> players, @NotNull String message) {
        players.forEach(p -> sendActionBar(p, message));
    }

    public static void sendTitle(@NotNull Player player, @NotNull String title, @NotNull String subTitle) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        final PlayerConnection playerConnection = craftPlayer.getHandle().playerConnection;

        playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{'text': '" + fixColor(title) + "'}"), 30, 30, 30));
        playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{'text': '" + fixColor(subTitle) + "'}"), 30, 30, 30));
    }

    public static void sendTitle(@NotNull Collection<Player> players, @NotNull String title, @NotNull String subTitle) {
        players.forEach(p -> sendTitle(p, title, subTitle));
    }
}