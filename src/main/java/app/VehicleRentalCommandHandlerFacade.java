package app;

import app.handlers.impl.*;
import app.handlers.IVehicleRentalHandler;

import java.util.*;

public class VehicleRentalCommandHandlerFacade {
    private static VehicleRentalCommandHandlerFacade instance = new VehicleRentalCommandHandlerFacade();

    private static IVehicleRentalHandler defaultHandler = new DefaultCommandHandler();
    private static Map<String,IVehicleRentalHandler> commandToHandlerMap;

    public static VehicleRentalCommandHandlerFacade getInstance() {
        return instance;
    }

    static {
        List<IVehicleRentalHandler> allHandlers = getAllHandlers();
        commandToHandlerMap = new HashMap<>();
        for (IVehicleRentalHandler handler : allHandlers) {
            commandToHandlerMap.put(handler.getCommandStartString(),handler);
        }
    }

    public IVehicleRentalHandler getHandler(String command){
        IVehicleRentalHandler iVehicleRentalHandler = commandToHandlerMap.get(command.split(" ")[0]);
        return iVehicleRentalHandler==null?defaultHandler:iVehicleRentalHandler;
    }

    private static List<IVehicleRentalHandler> getAllHandlers(){
        List<IVehicleRentalHandler> allHandlers = new ArrayList<>();
        allHandlers.add(new AddBranchCommandHandler());
        allHandlers.add(new AddVehicleCommandHandler());
        allHandlers.add(new BookVehicleCommandHandler());
        allHandlers.add(new DisplayVehiclesCommandHandler());
        allHandlers.add(new DynamicPricingCommandHandler());
        allHandlers.add(new DynamicPricingThresholdCommandHandler());
        return allHandlers;
    }

    private VehicleRentalCommandHandlerFacade() {
    }
}
