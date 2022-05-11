package app.handlers.impl;

import app.handlers.AbstractVehicleCommandHandler;

public class DefaultCommandHandler extends AbstractVehicleCommandHandler<String> {
    @Override
    protected boolean validateRequest(String command[]) {
        return false;
    }

    @Override
    protected String createObject(String[] command) {
        return null;
    }

    @Override
    protected String execute(String s) {
        return Boolean.FALSE.toString().toUpperCase();
    }

    @Override
    protected int getCommandArgumentsSize() {
        return 0;
    }

    @Override
    public String getCommandStartString() {
        return "ANY";
    }
}
