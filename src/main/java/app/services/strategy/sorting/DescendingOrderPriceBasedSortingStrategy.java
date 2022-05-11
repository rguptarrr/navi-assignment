package app.services.strategy.sorting;

import app.dal.entity.VehicleEntity;
import app.services.strategy.AbstractVehicleDisplayStrategy;

public class DescendingOrderPriceBasedSortingStrategy extends AbstractVehicleDisplayStrategy {
    @Override
    protected int compareVehicles(VehicleEntity v1, VehicleEntity v2) {
        return Double.compare(v2.getPrice(),v1.getPrice());
    }
}
