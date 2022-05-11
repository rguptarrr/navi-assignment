package app.handlers.impl;

import app.dal.entity.VehicleTypeEnum;
import app.dto.AddVehicleDto;
import app.handlers.AbstractVehicleCommandHandler;
import app.services.VehicleService;
import app.services.impl.InMememoryVehicleService;

public class AddVehicleCommandHandler extends AbstractVehicleCommandHandler<AddVehicleDto> {

    private static final String COMMAND_START_STRING = "ADD_VEHICLE";

    private VehicleService vehicleService = InMememoryVehicleService.getInstance();

    @Override
    protected boolean validateRequest(String command[]) {
        return true;
    }

    @Override
    protected AddVehicleDto createObject(String command[]) {
        AddVehicleDto vehicleDto = new AddVehicleDto();
        vehicleDto.setBranchId(command[1]);
        vehicleDto.setVehicleType(VehicleTypeEnum.valueOf(command[2]));
        vehicleDto.setVehicleId(command[3]);
        vehicleDto.setPrice(Integer.parseInt(command[4]));
        return vehicleDto;
    }

    @Override
    protected String execute(AddVehicleDto vehicleDto) {
        return (vehicleService.addVehicle(vehicleDto)?Boolean.TRUE.toString():Boolean.FALSE.toString()).toUpperCase();
    }

    @Override
    protected int getCommandArgumentsSize() {
        return 4;
    }

    @Override
    public String getCommandStartString() {
        return COMMAND_START_STRING;
    }
}
