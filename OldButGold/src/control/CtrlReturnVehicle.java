package control;

import java.util.ArrayList;

import person.Client;
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

}
