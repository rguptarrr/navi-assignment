package app.services;

import app.dal.entity.VehicleEntity;
import app.dto.AddVehicleDto;
import app.dto.BookVehicleDto;
import app.dto.DisplayVehiclesDto;

import java.util.List;

public interface VehicleService {
    public boolean addVehicle(AddVehicleDto vehicleDto);
    public VehicleEntity bookVehicle(BookVehicleDto bookVehicleDto);
    public List<VehicleEntity> displayAllAvailableVehicles(DisplayVehiclesDto displayVehiclesDto);
}
