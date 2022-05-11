package app.handlers.impl;

import app.dal.entity.VehicleEntity;
import app.dal.entity.VehicleTypeEnum;
import app.dto.BookVehicleDto;
import app.handlers.AbstractVehicleCommandHandler;
import app.services.VehicleService;
import app.services.impl.InMememoryVehicleService;

public class BookVehicleCommandHandler extends AbstractVehicleCommandHandler<BookVehicleDto> {

    private static final String COMMAND_START_STRING = "BOOK";

    private VehicleService vehicleService = InMememoryVehicleService.getInstance();

    @Override
    protected boolean validateRequest(String command[]) {
        return true;
    }

    @Override
    protected BookVehicleDto createObject(String command[]) {
        BookVehicleDto bookVehicleDto = new BookVehicleDto();
        bookVehicleDto.setBranchId(command[1]);
        bookVehicleDto.setVehicleTypeEnum(VehicleTypeEnum.valueOf(command[2]));
        bookVehicleDto.setStartTime(Integer.parseInt(command[3]));
        bookVehicleDto.setEndTime(Integer.parseInt(command[4]));
        return bookVehicleDto;
    }

    @Override
    protected String execute(BookVehicleDto bookVehicleDto) {
        VehicleEntity vehicleEntity = vehicleService.bookVehicle(bookVehicleDto);
        return vehicleEntity==null?"-1":Integer.toString((int)(vehicleEntity.getPrice()*(bookVehicleDto.getEndTime()-bookVehicleDto.getStartTime())*1.0));
    }

    @Override
    protected int getCommandArgumentsSize() {
        return 4;
    }

    @Override
    public String getCommandStartString() {
        return COMMAND_START_STRING;
    }

    @Override
    protected String getDefaultResponse() {
        return "-1";
    }
}
