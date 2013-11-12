package control;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import person.Client;
import person.Person;
import vehicle.Vehicle;
import db.Database;
import db.Rent;

public class CtrlRemoveVehicle extends CtrlSearchVehicle 
{
	private Database ctrlSearchVehicleDatabase;

	public CtrlRemoveVehicle(Database mainDatabase) 
	{
		super(mainDatabase);
		this.ctrlSearchVehicleDatabase = mainDatabase;
	}

	public boolean RemoveVehicle(int selectionIndex)
	{
		Vehicle selectedVehicle = resultVehiclesList.get(selectionIndex);
		
		if(!selectedVehicle.IsAvailable())
			return false;
		
		else
		{
			if(ctrlSearchVehicleDatabase.removeVehicleList(selectedVehicle))
				return true;
			else
				return false;
		}
	}
}
