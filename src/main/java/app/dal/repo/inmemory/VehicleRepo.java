package app.dal.repo.inmemory;

import app.dal.entity.VehicleEntity;
import app.dal.entity.VehicleTypeEnum;
import app.dal.repo.IRepo;

import java.util.List;

public class VehicleRepo implements IRepo<VehicleEntity,String> {
    private static VehicleRepo instance = new VehicleRepo();
    private InMemoryDataStorage inMemoryDataStorage = InMemoryDataStorage.getInstance();

    public static VehicleRepo getInstance() {
        return instance;
    }

    @Override
    public boolean add(VehicleEntity vehicleEntity) {
        return inMemoryDataStorage.addVehicle(vehicleEntity);
    }

    public List<VehicleEntity> getVehiclesByBranchIdAndType(String branchId, VehicleTypeEnum vehicleTypeEnum){
        return inMemoryDataStorage.getAllVehiclesOfABranch(branchId,vehicleTypeEnum);
    }

    public boolean bookVehicle(VehicleEntity vehicleEntity, int startTime, int endTime){
        return inMemoryDataStorage.bookVehicle(vehicleEntity,startTime,endTime);
    }
    private VehicleRepo() {
    }
}
