package controller;

import vehicle.Vehicle;
import db.Database;

public class VehicleController
{
	Database database;

	/**
	 * Verifica se o ve�culo j� existe no banco de dados. Se n�o existir,
	 * adiciona o ve�culo e retorna "true". Se existir, retorna false.
	 * 
	 * @param vehicle
	 */
	public boolean subscribe(Vehicle vehicle)
	{
		String plate = vehicle.getPlate();
		if (database.getVehicle(plate) == null)
		{
			database.addVehicle(vehicle);
			return true;
		}

		return false;
	}
}
