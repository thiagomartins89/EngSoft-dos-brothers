package control;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import person.Person;
import vehicle.Vehicle;
import db.Database;
import db.Rent;

public class CtrlEmployeeCarRent extends CtrlSearchVehicle
{
	private Database ctrlSearchVehicleDatabase;

	public CtrlEmployeeCarRent(Database mainDatabase)
	{
		super(mainDatabase);
		this.ctrlSearchVehicleDatabase = mainDatabase;
	}
	
	public void MakeCarRent(int selectionIndex, CurrentState rentCurrentState)
	{
		//JOptionPane.showMessageDialog(null, vehicleList.size());
		Vehicle selectedVehicle = vehicleList.get(selectionIndex);
		
		if(selectedVehicle.IsAvailable())
		{
			Calendar newCalendar = new GregorianCalendar();
			Person currentUser = rentCurrentState.getCurrentUser();
			Rent clientRent = new Rent(newCalendar, selectedVehicle.getMileage());
			selectedVehicle.setIsAvailable(false);
			JOptionPane.showMessageDialog(null, "Veículo locado com sucesso!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Veículo indisponível para locação no momento.");
		}
		
	}
}


