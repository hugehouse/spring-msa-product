package com.msa.product.util;

public class PageLimitNormalizer {
    public static int normalize(int limit) {
        switch(limit) {
            case 10:
            case 20:
            case 30:
            case 50: return limit;
            default: return 10;
        }
    }
}
