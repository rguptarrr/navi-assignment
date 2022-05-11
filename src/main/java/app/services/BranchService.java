package app.services;

import app.dto.AddBranchDto;

public interface BranchService {
    public boolean addNewBranch(AddBranchDto branchDto);
}
