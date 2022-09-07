package io.github.alprkeskin.common.util;

public final class OptionUtils {
    private OptionUtils() {

    }

    public static int addOption(int option, int value) {
         return option | value;
    }

    public static boolean checkOption(int option, int value) {
        if (value < 1)
            return false;
        return (value & option) == value;
    }
}
