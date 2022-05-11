package app.handlers;

/*@see app.VehicleRentalCommandHandlerFacade#addAllHandlersToList
* */
public abstract class AbstractVehicleCommandHandler<REQUEST> implements IVehicleRentalHandler {
    @Override
    public String handle(String command) {
        try{
            if(command==null || command.length()==0) return Boolean.FALSE.toString();
            String[] commandArray = command.split(" ");
            if(!commandArray[0].equals(getCommandStartString())
                    || !validateRequest(commandArray)
                    || commandArray.length!=getCommandArgumentsSize()+1) return getDefaultResponse();
            REQUEST object = createObject(commandArray);
            return execute(object);
        }catch (Exception e){
            //e.printStackTrace();
        }
        return getDefaultResponse();
    }
    protected abstract boolean validateRequest(String command[]);
    protected abstract REQUEST createObject(String command[]);
    protected abstract String execute(REQUEST request);
    protected abstract int getCommandArgumentsSize();
    @Override
    public abstract String getCommandStartString();
    protected String getDefaultResponse(){
        return Boolean.FALSE.toString().toUpperCase();
    }
}
