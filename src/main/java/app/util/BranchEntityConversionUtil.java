package app.util;

import app.dto.AddBranchDto;
import app.dal.entity.BranchEntity;

public class BranchEntityConversionUtil {
    public static BranchEntity get(AddBranchDto branchDto){
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setBranchId(branchDto.getBranchId());
        branchEntity.setAllowedVehicles(branchDto.getAllowedVehicles());
        return branchEntity;
    }
}
