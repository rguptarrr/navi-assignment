package app.services.impl;

import app.dal.entity.BranchEntity;
import app.dal.entity.VehicleEntity;
import app.dal.repo.inmemory.BranchRepo;
import app.dal.repo.inmemory.VehicleRepo;
import app.dto.AddVehicleDto;
import app.dto.BookVehicleDto;
import app.dto.DisplayVehiclesDto;
import app.services.strategy.AbstractVehicleDisplayStrategy;
import app.services.strategy.AbstractVehicleSelectionForBookingStrategy;
import app.services.VehicleService;
import app.services.strategy.selection.LowestPriceVehicleSelectionForBookingStrategy;
import app.services.strategy.sorting.AscendingOrderPriceBasedSortingStrategy;
import app.util.VehicleEntityConversionUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMememoryVehicleService implements VehicleService {
    private static InMememoryVehicleService instance = new InMememoryVehicleService();

    public static InMememoryVehicleService getInstance() {
        return instance;
    }

    private AbstractVehicleSelectionForBookingStrategy vehicleSelectionStrategy = new LowestPriceVehicleSelectionForBookingStrategy();
    private AbstractVehicleDisplayStrategy vehicleDisplayStrategy = new AscendingOrderPriceBasedSortingStrategy();

    private VehicleRepo vehicleRepo = VehicleRepo.getInstance();

    private BranchRepo branchRepo = BranchRepo.getInstance();

    @Override
    public boolean addVehicle(AddVehicleDto vehicleDto) {
        return vehicleRepo.add(VehicleEntityConversionUtil.get(vehicleDto));
    }

    @Override
    public VehicleEntity bookVehicle(BookVehicleDto bookVehicleDto) {
        //Fetch all Vehicles of the branch
        BranchEntity branchEntity = branchRepo.get(bookVehicleDto.getBranchId());
        if(branchEntity==null || !branchEntity.getAllowedVehicles().contains(bookVehicleDto.getVehicleTypeEnum()))
            return null;
        //Fetch all vehicles of desired types against this branch id
        List<VehicleEntity> vehicles = vehicleRepo.getVehiclesByBranchIdAndType(bookVehicleDto.getBranchId(), bookVehicleDto.getVehicleTypeEnum());
        if(vehicles==null || vehicles.size()==0) return null;

        //Fetch all bookings against this branch and vehicle type
        Map<String, Set<Integer>> bookingDetails = branchRepo.getBookingDetailsByBranchIdAndVehicleType
                (bookVehicleDto.getBranchId(), bookVehicleDto.getVehicleTypeEnum());

        //Select Vehicle to book as per strategy
        VehicleEntity vehicleEntity = vehicleSelectionStrategy.selectVehicleToBook(vehicles, bookingDetails, bookVehicleDto);

        //If no vehicle was selected, then return null
        if(vehicleEntity==null) return null;

        //Book Vehicle
        boolean b = vehicleRepo.bookVehicle(vehicleEntity, bookVehicleDto.getStartTime(), bookVehicleDto.getEndTime());
        return b?vehicleEntity:null;
    }

    @Override
    public List<VehicleEntity> displayAllAvailableVehicles(DisplayVehiclesDto displayVehiclesDto) {
        BranchEntity branchEntity = branchRepo.get(displayVehiclesDto.getBranchId());
        if(branchEntity==null)
            return null;
        return vehicleDisplayStrategy.getAllAvailableVehicles(branchEntity,displayVehiclesDto);
    }

    private InMememoryVehicleService() {
    }
}
