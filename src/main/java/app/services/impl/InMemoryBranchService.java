package app.services.impl;

import app.dto.AddBranchDto;
import app.services.BranchService;
import app.util.BranchEntityConversionUtil;
import app.dal.repo.inmemory.BranchRepo;

public class InMemoryBranchService implements BranchService {

    private static InMemoryBranchService instance = new InMemoryBranchService();

    public static InMemoryBranchService getInstance() {
        return instance;
    }

    private BranchRepo branchRepo = BranchRepo.getInstance();

    @Override
    public boolean addNewBranch(AddBranchDto branchDto) {
        return branchRepo.add(BranchEntityConversionUtil.get(branchDto));
    }



    private InMemoryBranchService() {
    }
}
