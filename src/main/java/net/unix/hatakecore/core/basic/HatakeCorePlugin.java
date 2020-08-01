package net.unix.hatakecore.core.basic;

import lombok.Getter;
import net.unix.hatakecore.api.command.CommandManager;
import net.unix.hatakecore.api.config.UwuConfig;
import net.unix.hatakecore.core.command.basic.CommandManagerImpl;
import net.unix.hatakecore.core.command.admin.ClearCommand;
import net.unix.hatakecore.core.command.admin.GameModeCommand;
import net.unix.hatakecore.core.command.admin.HealCommand;
import net.unix.hatakecore.core.command.admin.OnlineCommand;
import net.unix.hatakecore.core.command.player.SvipCommand;
import net.unix.hatakecore.core.command.player.VipCommand;
import net.unix.hatakecore.core.config.UwuConfigImpl;
import net.unix.hatakecore.core.command.player.HelpCommand;
import net.unix.hatakecore.core.listener.PlayerJoinListener;
import net.unix.hatakecore.core.listener.PlayerQuitListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

/**
 * @author Unix
 * 14:07, 11.05.2019
 */

@Getter
public class HatakeCorePlugin extends JavaPlugin {

    private final CommandManager commandManager;

    private UwuConfig            uwuConfig;

    public HatakeCorePlugin() {
        this.commandManager = new CommandManagerImpl(this);
    }

    @Override
    public void onLoad() {
        HatakeCore.setInstance(this);
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.uwuConfig = new UwuConfigImpl(this);

        final FileConfiguration commandConfiguration = this.uwuConfig.getCommandConfiguration();

        this.commandManager.registerCommands(
                new HelpCommand(commandConfiguration),
                new VipCommand(commandConfiguration),
                new SvipCommand(commandConfiguration),
                new GameModeCommand(),
                new HealCommand(),
                new OnlineCommand(),
                new ClearCommand()
        );

        this.registerListeners(
                new PlayerJoinListener(),
                new PlayerQuitListener()
        );

//        Arrays.stream(
//                new IRecipe[] {
//
//                }
//        ).forEachOrdered(recipe -> this.getServer().addRecipe(recipe.createRecipe()));
    }

    @Override
    public void onDisable() {
    }

    private void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).forEachOrdered(this::registerListener);
    }

    private void registerListener(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    public FileConfiguration createConfig(String fileName) {
        final File file = new File(this.getDataFolder(), fileName);
        final FileConfiguration fileConfiguration = new YamlConfiguration();

        if (!file.exists()) {
            this.saveResource(fileName, false);
        }

        try {
            fileConfiguration.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileConfiguration;
    }
}