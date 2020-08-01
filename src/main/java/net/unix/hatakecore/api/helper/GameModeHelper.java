package net.unix.hatakecore.api.helper;

import org.apache.commons.lang.StringUtils;
import org.bukkit.GameMode;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Unix on 05.10.2019.
 */
public final class GameModeHelper {

    private GameModeHelper() {
    }

    @NotNull
    private static Optional<GameMode> getGameMode(String string) {
        return Arrays.stream(GameMode.values())
                .filter(m -> m.name().toLowerCase().contains(string.toLowerCase()))
                .findFirst();
    }

    public static Optional<GameMode> parseGameMode(String string) {
        if (StringUtils.isNumeric(string))
            return Optional.ofNullable(GameMode.getByValue(Integer.parseInt(string)));
        else
            return getGameMode(string);
    }
}