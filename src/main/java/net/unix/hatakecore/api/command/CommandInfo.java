package net.unix.hatakecore.api.command;

import java.lang.annotation.*;

/**
 * @author Unix
 * 14:07, 11.05.2019
 **/

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {

    String value() default "";

    String usage() default "";

    String permission() default "";

    String[] aliases() default "";

}