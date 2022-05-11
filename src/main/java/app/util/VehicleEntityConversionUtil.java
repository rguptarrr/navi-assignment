package app.util;

import app.dto.AddVehicleDto;
import app.dal.entity.VehicleEntity;

import java.util.ArrayList;
import java.util.List;

public class VehicleEntityConversionUtil {
    public static VehicleEntity get(AddVehicleDto vehicleDto){
        if(vehicleDto==null) return null;
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setVehicleId(vehicleDto.getVehicleId());
        vehicleEntity.setVehicleType(vehicleDto.getVehicleType());
        vehicleEntity.setPrice(vehicleDto.getPrice());
        vehicleEntity.setBranchId(vehicleDto.getBranchId());
        return vehicleEntity;
    }

    public static List<VehicleEntity> get(List<AddVehicleDto> vehicleDtos){
        if(vehicleDtos==null || vehicleDtos.size()==0) return null;
        List<VehicleEntity> vehicleEntities = new ArrayList<>();
        for (AddVehicleDto vehicleDto : vehicleDtos) {
            vehicleEntities.add(get(vehicleDto));
        }
        return vehicleEntities;
    }
}
