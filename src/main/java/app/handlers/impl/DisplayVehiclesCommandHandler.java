package app.handlers.impl;

import app.dal.entity.VehicleEntity;
import app.dto.DisplayVehiclesDto;
import app.handlers.AbstractVehicleCommandHandler;
import app.services.VehicleService;
import app.services.impl.InMememoryVehicleService;

import java.util.List;

public class DisplayVehiclesCommandHandler extends AbstractVehicleCommandHandler<DisplayVehiclesDto> {

    private static final String COMMAND_START_STRING = "DISPLAY_VEHICLES";

    private VehicleService vehicleService = InMememoryVehicleService.getInstance();

    @Override
    protected boolean validateRequest(String command[]) {
        return true;
    }

    @Override
    protected DisplayVehiclesDto createObject(String[] command) {
        DisplayVehiclesDto displayVehiclesDto = new DisplayVehiclesDto();
        displayVehiclesDto.setBranchId(command[1]);
        displayVehiclesDto.setStartTime(Integer.parseInt(command[2]));
        displayVehiclesDto.setEndTime(Integer.parseInt(command[3]));
        return displayVehiclesDto;
    }

    @Override
    protected String execute(DisplayVehiclesDto displayVehicleDto) {
        List<VehicleEntity> vehicleEntities = vehicleService.displayAllAvailableVehicles(displayVehicleDto);
        if(vehicleEntities==null || vehicleEntities.size()==0) return getDefaultResponse();

        StringBuilder sb = new StringBuilder();
        for (VehicleEntity vehicleEntity : vehicleEntities) {
            sb.append(vehicleEntity.getVehicleId()).append(",");
        }
        return sb.substring(0,sb.length()-1).toString().toUpperCase();
    }

    @Override
    protected int getCommandArgumentsSize() {
        return 3;
    }

    @Override
    public String getCommandStartString() {
        return COMMAND_START_STRING;
    }
}
