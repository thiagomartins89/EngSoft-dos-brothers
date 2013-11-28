package person;

import java.util.ArrayList;

import vehicle.Vehicle;

import db.Rent;


public class Client extends Person 
{
	
	private	long cnh;	//carteira nacional de habilitação
	private ArrayList<Rent> rentList;
	private ArrayList<Vehicle> rentedVehiclesList;

	public Client()
	{
		rentList = new ArrayList<Rent>();
		rentedVehiclesList = new ArrayList<Vehicle>();
	}
	
	public long getCnh() 
	{
		return cnh;
	}

	public void setCnh(long cnh) 
	{
		this.cnh = cnh;
	}
	
	public void AddRent(Rent rent)
	{
		rentList.add(rent);
	}
	
	public ArrayList<Rent> getRentList() 
	{
		return rentList;
	}

	public ArrayList<Vehicle> getRentedVehiclesList() 
	{
		return rentedVehiclesList;
	}

	public void AddRentedVehicle(Vehicle vehicle)
	{
		rentedVehiclesList.add(vehicle);
	}
	
	public void RemoveRentedVehicle(Vehicle vehicle)
	{
		rentedVehiclesList.remove(vehicle);
	}
}
