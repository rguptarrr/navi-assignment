package app.dal.entity;

import java.util.Set;

public class BranchEntity {
    private String branchId;
    private Set<VehicleTypeEnum> allowedVehicles;

    public Set<VehicleTypeEnum> getAllowedVehicles() {
        return allowedVehicles;
    }

    public void setAllowedVehicles(Set<VehicleTypeEnum> allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
