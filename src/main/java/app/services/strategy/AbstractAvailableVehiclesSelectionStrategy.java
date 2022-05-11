package app.services.strategy;

import app.dal.entity.VehicleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractAvailableVehiclesSelectionStrategy {
    protected List<VehicleEntity> getAllAvailableVehicles(List<VehicleEntity> allVehicles,
                                             Map<String, Set<Integer>> existingBookingDetails, int startTime, int endTime){
        List<VehicleEntity> availableVehicles = new ArrayList<>();
        if(startTime>=endTime) return availableVehicles;
        if(existingBookingDetails==null || existingBookingDetails.size()==0) return allVehicles;
        for(VehicleEntity vehicleEntity:allVehicles){
            if(isVehicleAvailble(vehicleEntity,existingBookingDetails,startTime,endTime))
                availableVehicles.add(vehicleEntity);
        }
        return availableVehicles;
    }

    protected boolean isVehicleAvailble(VehicleEntity vehicleEntity , Map<String,Set<Integer>> bookingDetails, int startTime, int endTime){
        for(int i = startTime;i<endTime;i++){
            if(bookingDetails.get(vehicleEntity.getVehicleId()).contains(i)) return false;
        }
        return true;
    }

}
