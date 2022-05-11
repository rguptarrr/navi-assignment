package app.dal.repo.inmemory;

import app.dal.entity.BranchEntity;
import app.dal.entity.VehicleEntity;
import app.dal.entity.VehicleTypeEnum;

import java.util.*;

public class InMemoryDataStorage {
    private static InMemoryDataStorage instance = new InMemoryDataStorage();

    /*
    * Direct Mapping of branch Id with branch entity
    * */
    private Map<String,BranchEntity> branchEntityMap = new HashMap<>();


    /* Map of BranchId:VehicleType #Concat branch id with vehicle type
     * with VehicleEntity
     * */
    private Map<String, List<VehicleEntity>> branchToVehicleMapping = new HashMap<>();

    /*
    * Structure Followed
    * Branch ID:Vehicle Type // i.e. concat branch id with vehicle type
    *   Vehicle ID
    *       Slots occupied on hourly basis
    * */
    private Map<String,Map<String, Set<Integer>>> bookingDetails = new HashMap<>();

    public Map<String,Set<Integer>> getBookingDetails(String branchId, VehicleTypeEnum vehicleTypeEnum){
        return bookingDetails.get(branchAndVehicleTypeCachedKey(branchId,vehicleTypeEnum));
    }

    public boolean bookVehicle(VehicleEntity vehicleEntity , int startTime, int endTime){
        //Base start end check
        if(startTime>=endTime) return false;
        //If key is not available, return false
        if(branchToVehicleMapping.get(branchAndVehicleTypeCachedKey(vehicleEntity.getBranchId(),vehicleEntity.getVehicleType()))==null) return false;
        for(int i = startTime;i<endTime;i++){
            bookingDetails.get(branchAndVehicleTypeCachedKey(vehicleEntity.getBranchId(),vehicleEntity.getVehicleType())).get(vehicleEntity.getVehicleId()).add(i);
        }
        return true;
    }

    //Validate for non existing id and then insert
    public boolean addBranch(BranchEntity branchEntity){
        if(branchEntityMap.get(branchEntity.getBranchId())!=null) return false;
        branchEntityMap.put(branchEntity.getBranchId(),branchEntity);
        //Creating mapping for vehicle mapping
        for (VehicleTypeEnum allowedVehicle : branchEntity.getAllowedVehicles()) {
            String key = branchAndVehicleTypeCachedKey(branchEntity.getBranchId(), allowedVehicle);
            branchToVehicleMapping.put(key,new ArrayList<VehicleEntity>());
            bookingDetails.put(key,new HashMap<String, Set<Integer>>());
        }
        return true;
    }

    public boolean addVehicle(VehicleEntity vehicleEntity){
        BranchEntity mappedBranch = branchEntityMap.get(vehicleEntity.getBranchId());
        if(mappedBranch==null) return false;

        if(!mappedBranch.getAllowedVehicles().contains(vehicleEntity.getVehicleType())) return false;

        String key = branchAndVehicleTypeCachedKey(vehicleEntity.getBranchId(), vehicleEntity.getVehicleType());
        for (VehicleEntity vehicle : branchToVehicleMapping.get(key)) {
            if(vehicle.getVehicleId().equals(vehicleEntity.getVehicleId())) return false;
        }

        branchToVehicleMapping.get(branchAndVehicleTypeCachedKey(vehicleEntity.getBranchId(), vehicleEntity.getVehicleType())).add(vehicleEntity);
        bookingDetails.get(key).put(vehicleEntity.getVehicleId(),new HashSet<Integer>());
        return true;
    }

    public List<VehicleEntity> getAllVehiclesOfABranch(String branchId, VehicleTypeEnum vehicleTypeEnum){
        return branchToVehicleMapping.get(branchAndVehicleTypeCachedKey(branchId,vehicleTypeEnum));
    }

    public BranchEntity getBranchById(String id){
        return branchEntityMap.get(id);
    }

    public static InMemoryDataStorage getInstance(){
        return instance;
    }

    private String branchAndVehicleTypeCachedKey(String branchId, VehicleTypeEnum vte){
        return branchId+":"+vte.name();
    }
    private InMemoryDataStorage() {
    }
}
