package app;

import java.io.*;

public class DriverClass {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting application");
        String filePath = args[0];
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
        String command = null;
        while ((command=bufferedReader.readLine())!=null){
            if(command.equalsIgnoreCase("exit")) break;
            System.out.println(VehicleRentalCommandHandlerFacade.getInstance().getHandler(command).handle(command));
        }
        System.out.println("exiting application");
    }
}