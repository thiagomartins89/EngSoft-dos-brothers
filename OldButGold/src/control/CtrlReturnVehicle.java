package control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import person.Client;
import vehicle.Vehicle;
import db.Database;
import db.Rent;

public class CtrlReturnVehicle extends CtrlSearchVehicle
{
	private Database ctrlSearchVehicleDatabase;
	
	public CtrlReturnVehicle(Database mainDatabase) 
	{
		super(mainDatabase);
		this.ctrlSearchVehicleDatabase = mainDatabase;
	}

	public void makeVehicleReturn(int selectedVehicleIndex, Client selectedClient)
	{                        	
        Vehicle selectedVehicle = selectedClient.getRentedVehiclesList().get(selectedVehicleIndex);
        selectedVehicle.setCurrentClient(null);
        selectedVehicle.setAvailable(true);
        selectedClient.RemoveRentedVehicle(selectedVehicle);
        JOptionPane.showMessageDialog(null, "Veículo devolvido com sucesso!");
		
	}

}
