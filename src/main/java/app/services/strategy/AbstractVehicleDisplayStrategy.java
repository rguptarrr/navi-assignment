package app.services.strategy;

import app.dal.entity.BranchEntity;
import app.dal.entity.VehicleEntity;
import app.dal.entity.VehicleTypeEnum;
import app.dal.repo.inmemory.BranchRepo;
import app.dal.repo.inmemory.VehicleRepo;
import app.dto.DisplayVehiclesDto;

import java.util.*;

public abstract class AbstractVehicleDisplayStrategy extends AbstractAvailableVehiclesSelectionStrategy{

    private VehicleRepo vehicleRepo = VehicleRepo.getInstance();

    private BranchRepo branchRepo = BranchRepo.getInstance();


    public List<VehicleEntity> getAllAvailableVehicles(BranchEntity branchEntity , DisplayVehiclesDto displayVehiclesDto){
        if(branchEntity==null) return null;
        List<VehicleEntity> availableVehicles = new ArrayList<>();
        for (VehicleTypeEnum vehicleType : branchEntity.getAllowedVehicles()) {
            //Fetch all vehicles of desired types against this branch id
            List<VehicleEntity> vehicles = vehicleRepo.getVehiclesByBranchIdAndType(displayVehiclesDto.getBranchId(), vehicleType);
            //Fetch all bookings against this branch and vehicle type
            Map<String, Set<Integer>> bookingDetails = branchRepo.getBookingDetailsByBranchIdAndVehicleType(displayVehiclesDto.getBranchId(), vehicleType);
            //Getting all available vehicles of this type and in the time range
            List<VehicleEntity> allAvailableVehicles = getAllAvailableVehicles(vehicles, bookingDetails, displayVehiclesDto.getStartTime(), displayVehiclesDto.getEndTime());
            if(allAvailableVehicles!=null) availableVehicles.addAll(allAvailableVehicles);
        }
        sortInOrder(availableVehicles);
        return availableVehicles;
    }

    private void sortInOrder(List<VehicleEntity> vehicleEntities){
        Collections.sort(vehicleEntities,getComparator());
    }

    private Comparator<VehicleEntity> getComparator(){
        return new Comparator<VehicleEntity>() {
            @Override
            public int compare(VehicleEntity o1, VehicleEntity o2) {
                return compareVehicles(o1,o2);
            }
        };
    }

    protected abstract int compareVehicles(VehicleEntity v1, VehicleEntity v2);
}
