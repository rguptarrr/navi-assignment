package app.services.strategy.selection;

import app.dal.entity.VehicleEntity;
import app.services.strategy.AbstractVehicleSelectionForBookingStrategy;

import java.util.List;

public class FirstAvailableVehicleSelectionForBookingStrategy extends AbstractVehicleSelectionForBookingStrategy {

    @Override
    protected VehicleEntity selectVehicle(List<VehicleEntity> availableVehicles, List<VehicleEntity> allVehicles) {
        if(availableVehicles!=null && availableVehicles.size()>0) return availableVehicles.get(0);
        return null;
    }
}
