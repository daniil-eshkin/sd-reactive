package sd.lab4.util;

import sd.lab4.model.RegionalPriceMark;

public class PriceUtils {
    public static double convertPrice(RegionalPriceMark from, RegionalPriceMark to, double price) {
        switch (from) {
            case RU -> {
                return convertRuPrice(to, price);
            }
            case USD -> {
                return convertUsdPrice(to, price);
            }
            case EUR -> {
                return convertEurPrice(to, price);
            }
            default -> throw new IllegalArgumentException();
        }
    }

    public static double convertRuPrice(RegionalPriceMark to, double price) {
        switch (to) {
            case RU -> {
                return price;
            }
            case USD -> {
                return price * 2;
            }
            case EUR -> {
                return price * 4;
            }
            default -> throw new IllegalArgumentException();
        }
    }

    public static double convertUsdPrice(RegionalPriceMark to, double price) {
        switch (to) {
            case RU -> {
                return price / 2;
            }
            case USD -> {
                return price;
            }
            case EUR -> {
                return price * 2;
            }
            default -> throw new IllegalArgumentException();
        }
    }

    public static double convertEurPrice(RegionalPriceMark to, double price) {
        switch (to) {
            case RU -> {
                return price / 4;
            }
            case USD -> {
                return price / 2;
            }
            case EUR -> {
                return price;
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
