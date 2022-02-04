package com.bestofsky.red4java.lib;

import java.security.SecureRandom;


public class Utils {
    public static String randomHex(int n) {
        int digit = n * 2;
        final char[] seeds = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        SecureRandom random = new SecureRandom();
        StringBuilder stringBuffer = new StringBuilder();

        for (int i = 0; i < digit; i++) {
            stringBuffer.append(seeds[random.nextInt(seeds.length)]);
        }

        return stringBuffer.toString();
    }
}
