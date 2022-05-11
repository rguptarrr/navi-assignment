package app.handlers.impl;

import app.config.ApplicationConfig;
import app.handlers.AbstractVehicleCommandHandler;

public class DynamicPricingThresholdCommandHandler extends AbstractVehicleCommandHandler<Double> {

    private static final String COMMAND_START_STRING = "DYNAMIC_PRICING_THRESHOLD";

    @Override
    protected boolean validateRequest(String[] command) {
        return true;
    }

    @Override
    protected Double createObject(String[] command) {
        return Double.parseDouble(command[1]);
    }

    @Override
    protected String execute(Double s) {
        ApplicationConfig.setDynamicPricingThreshold(s);
        return Boolean.TRUE.toString().toUpperCase();
    }

    @Override
    protected int getCommandArgumentsSize() {
        return 1;
    }

    @Override
    public String getCommandStartString() {
        return COMMAND_START_STRING;
    }
}
