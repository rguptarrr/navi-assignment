package app.services.strategy.selection;

import app.dal.entity.VehicleEntity;
import app.services.strategy.AbstractVehicleSelectionForBookingStrategy;

import java.util.List;

public class LowestPriceVehicleSelectionForBookingStrategy extends AbstractVehicleSelectionForBookingStrategy {
    @Override
    protected VehicleEntity selectVehicle(List<VehicleEntity> availableVehicles , List<VehicleEntity> allVehicles) {
        double minPrice = Double.MAX_VALUE;
        VehicleEntity lowestCostVehicle = null;
        for(VehicleEntity vehicle : availableVehicles){
            if(vehicle.getPrice()<minPrice){
                minPrice=vehicle.getPrice();
                lowestCostVehicle=vehicle;
            }
        }
        return lowestCostVehicle;
    }
}
