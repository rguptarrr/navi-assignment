package app.config;

public class ApplicationConfig {
    private static boolean dynamicPricingEnabled = false;
    private static double dynamicPricingThreshold = 0.8;
    private static double dynamicPricingMultiplier = 1.1;

    public static double getDynamicPricingMultiplier() {
        return dynamicPricingMultiplier;
    }

    public static void setDynamicPricingMultiplier(double dynamicPricingMultiplier) {
        ApplicationConfig.dynamicPricingMultiplier = dynamicPricingMultiplier;
    }

    public static boolean isDynamicPricingEnabled() {
        return dynamicPricingEnabled;
    }

    public static void setDynamicPricingEnabled(boolean dynamicPricingEnabled) {
        ApplicationConfig.dynamicPricingEnabled = dynamicPricingEnabled;
    }

    public static double getDynamicPricingThreshold() {
        return dynamicPricingThreshold;
    }

    public static void setDynamicPricingThreshold(double dynamicPricingThreshold) {
        ApplicationConfig.dynamicPricingThreshold = dynamicPricingThreshold;
    }
}
