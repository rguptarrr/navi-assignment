package app.dal.repo.inmemory;

import app.dal.entity.BranchEntity;
import app.dal.entity.VehicleTypeEnum;
import app.dal.repo.IRepo;

import java.util.Map;
import java.util.Set;

public class BranchRepo implements IRepo<BranchEntity,String> {
    private static BranchRepo instance = new BranchRepo();

    public static BranchRepo getInstance() {
        return instance;
    }

    private InMemoryDataStorage inMemoryDataStorage = InMemoryDataStorage.getInstance();

    @Override
    public boolean add(BranchEntity branchEntity) {
        return inMemoryDataStorage.addBranch(branchEntity);
    }

    public BranchEntity get(String s) {
        return inMemoryDataStorage.getBranchById(s);
    }

    public Map<String, Set<Integer>> getBookingDetailsByBranchIdAndVehicleType(String branchId , VehicleTypeEnum vehicleTypeEnum){
        return inMemoryDataStorage.getBookingDetails(branchId,vehicleTypeEnum);
    }

    private BranchRepo() {
    }
}
