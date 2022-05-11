package app.services.strategy;

import app.config.ApplicationConfig;
import app.dal.entity.VehicleEntity;
import app.dto.BookVehicleDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractVehicleSelectionForBookingStrategy extends AbstractAvailableVehiclesSelectionStrategy{
    public VehicleEntity selectVehicleToBook(List<VehicleEntity> vehicles,
                                      Map<String, Set<Integer>> existingBookingDetails,
                                      BookVehicleDto bookVehicleDto){
        List<VehicleEntity> availableVehicles = getAllAvailableVehicles(vehicles,existingBookingDetails,bookVehicleDto.getStartTime(),bookVehicleDto.getEndTime());
        VehicleEntity selectedVehicle = selectVehicle(availableVehicles, vehicles);
        return applyDynamicPricingIfApplicable(selectedVehicle,vehicles,availableVehicles);
    }

    private boolean isDynamicPricingApplicable(List<VehicleEntity> allVehicles , List<VehicleEntity> availableVehicles){
        if(!ApplicationConfig.isDynamicPricingEnabled()) return false;

        int occupiedVehicles = allVehicles.size()-availableVehicles.size();
        double occupancyRation = occupiedVehicles / (availableVehicles.size() * 1.0);

        return occupancyRation>= ApplicationConfig.getDynamicPricingThreshold();

    }

    protected boolean isVehicleAvailble(VehicleEntity vehicleEntity , Map<String,Set<Integer>> bookingDetails, BookVehicleDto bookVehicleDto){
        for(int i = bookVehicleDto.getStartTime();i<bookVehicleDto.getEndTime();i++){
            if(bookingDetails.get(vehicleEntity.getVehicleId()).contains(i)) return false;
        }
        return true;
    }

    private VehicleEntity applyDynamicPricingIfApplicable(VehicleEntity vehicleEntity , List<VehicleEntity> allVehicles , List<VehicleEntity> availableVehicles){
        VehicleEntity vehicle = null;
        if(vehicleEntity==null) return vehicleEntity;
        vehicle=new VehicleEntity();
        vehicle.setVehicleId(vehicleEntity.getVehicleId());
        vehicle.setVehicleType(vehicleEntity.getVehicleType());
        vehicle.setPrice(vehicleEntity.getPrice());
        vehicle.setVehicleId(vehicleEntity.getVehicleId());
        vehicle.setBranchId(vehicleEntity.getBranchId());
        if(isDynamicPricingApplicable(allVehicles,availableVehicles)){
            vehicle.setPrice(vehicle.getPrice()*ApplicationConfig.getDynamicPricingMultiplier());
        }
        return vehicle;
    }

    protected abstract VehicleEntity selectVehicle(List<VehicleEntity> availableVehicles , List<VehicleEntity> allVehicles);
}
