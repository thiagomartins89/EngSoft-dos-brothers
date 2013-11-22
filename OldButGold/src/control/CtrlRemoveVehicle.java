package control;

import vehicle.Vehicle;
import db.Database;

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
		
		if(!selectedVehicle.isAvailable())
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
