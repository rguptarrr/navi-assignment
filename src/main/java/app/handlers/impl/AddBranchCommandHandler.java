package app.handlers.impl;

import app.dto.AddBranchDto;
import app.handlers.AbstractVehicleCommandHandler;
import app.services.BranchService;
import app.services.impl.InMemoryBranchService;
import app.dal.entity.VehicleTypeEnum;

import java.util.HashSet;
import java.util.Set;

public class AddBranchCommandHandler extends AbstractVehicleCommandHandler<AddBranchDto> {

    private static final String COMMAND_START_STRING = "ADD_BRANCH";

    private BranchService branchService = InMemoryBranchService.getInstance();

    @Override
    protected boolean validateRequest(String command[]) {
        return true;
    }

    @Override
    protected AddBranchDto createObject(String command[]) {
        AddBranchDto branchDto = new AddBranchDto();
        branchDto.setBranchId(command[1]);
        Set<VehicleTypeEnum> allowedVehicleTypes = new HashSet<>();
        for(String allowedVehicles : command[2].split(",")){
            allowedVehicleTypes.add(VehicleTypeEnum.valueOf(allowedVehicles));
        }
        branchDto.setAllowedVehicles(allowedVehicleTypes);
        return branchDto;
    }

    @Override
    protected String execute(AddBranchDto branchDto) {
        boolean b = branchService.addNewBranch(branchDto);
        return Boolean.toString(b).toUpperCase();
    }

    @Override
    protected int getCommandArgumentsSize() {
        return 2;
    }

    @Override
    public String getCommandStartString() {
        return COMMAND_START_STRING;
    }
}
