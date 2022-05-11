package app.handlers.impl;

import app.config.ApplicationConfig;
import app.handlers.AbstractVehicleCommandHandler;

public class DynamicPricingCommandHandler extends AbstractVehicleCommandHandler<String> {

    private static final String COMMAND_START_STRING = "DYNAMIC_PRICING";

    private enum DynamicPricingSwitch{
        ENABLE(true),DISABLE(false);

        private boolean value;

        DynamicPricingSwitch(boolean value) {
            this.value = value;
        }
    }

    @Override
    protected boolean validateRequest(String[] command) {
        DynamicPricingSwitch.valueOf(command[1]);
        return true;
    }

    @Override
    protected String createObject(String[] command) {
        return command[1];
    }

    @Override
    protected String execute(String s) {
        DynamicPricingSwitch dynamicPricingSwitch = DynamicPricingSwitch.valueOf(s);
        if(ApplicationConfig.isDynamicPricingEnabled() == dynamicPricingSwitch.value) return getDefaultResponse();
        ApplicationConfig.setDynamicPricingEnabled(dynamicPricingSwitch.value);
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
