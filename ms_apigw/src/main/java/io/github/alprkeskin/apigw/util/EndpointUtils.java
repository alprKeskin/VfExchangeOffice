package io.github.alprkeskin.apigw.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public final class EndpointUtils {
    private EndpointUtils() {

    }

    public static String getEndpoint(String... args) {
        StringJoiner joiner = new StringJoiner("-");

        Arrays.stream(args).filter(Objects::nonNull).forEachOrdered(joiner::add);

        return joiner.toString();
    }
}
