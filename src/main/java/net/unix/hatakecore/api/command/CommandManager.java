package net.unix.hatakecore.api.command;

import org.jetbrains.annotations.NotNull;

/**
 * @author Unix
 * 14:06, 11.05.2019
 **/
public interface CommandManager {

    void registerCommand(@NotNull Command command);

    void registerCommands(@NotNull Command... commands);

}