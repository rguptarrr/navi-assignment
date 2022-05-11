package app.dto;

import app.dal.entity.VehicleTypeEnum;

import java.util.Set;

public class AddBranchDto {
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
