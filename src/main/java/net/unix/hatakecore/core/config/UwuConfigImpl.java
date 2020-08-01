package net.unix.hatakecore.core.config;

import net.unix.hatakecore.api.config.UwuConfig;
import net.unix.hatakecore.core.basic.HatakeCorePlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * @author Unix on 05.10.2019.
 */
public class UwuConfigImpl implements UwuConfig {

    private final FileConfiguration commandConfiguration;

    public UwuConfigImpl(@NotNull HatakeCorePlugin plugin) {
        this.commandConfiguration = plugin.createConfig("command.yml");
    }

    @Override
    public FileConfiguration getCommandConfiguration() {
        return this.commandConfiguration;
    }
}