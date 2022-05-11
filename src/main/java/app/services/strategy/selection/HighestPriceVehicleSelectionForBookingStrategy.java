package app.services.strategy.selection;

import app.dal.entity.VehicleEntity;
import app.services.strategy.AbstractVehicleSelectionForBookingStrategy;

import java.util.List;

public class HighestPriceVehicleSelectionForBookingStrategy extends AbstractVehicleSelectionForBookingStrategy {
    @Override
    protected VehicleEntity selectVehicle(List<VehicleEntity> availableVehicles, List<VehicleEntity> allVehicles) {
        double maxPrice = Double.MIN_VALUE;
        VehicleEntity maxCostVehicle = null;
        for(VehicleEntity vehicle : availableVehicles){
            if(vehicle.getPrice()>maxPrice){
                maxPrice=vehicle.getPrice();
                maxCostVehicle=vehicle;
            }
        }
        return maxCostVehicle;
    }
}
