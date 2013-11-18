package db;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import person.Client;
import person.Employee;
import person.Person;
import vehicle.Bus;
import vehicle.Car;
import vehicle.MicroBus;
import vehicle.Motorcycle;
import vehicle.Truck;
import vehicle.Vehicle;

import com.ibm.icu.util.Calendar;

public class Database
{

	private ArrayList<Person> userList;
	private ArrayList<Vehicle> vehicleList;

	public Database()
	{
		// criar clientes para a base de dados de exemplo
		userList = new ArrayList<Person>();

		Client client1 = new Client();

		client1.setId("c1");
		client1.setPassword("c1");
		client1.setName("Cliente 1");

		this.addUser(client1);

		Client client2 = new Client();

		client2.setId("c2");
		client2.setPassword("c2");
		client2.setName("Cliente 2");

		this.addUser(client2);

		// criar funcionários para a base de dados de exemplo
		Employee employee1 = new Employee();

		employee1.setId("e1");
		employee1.setPassword("e1");
		employee1.setName("Funcionário 1");

		this.addUser(employee1);

		Employee employee2 = new Employee();

		employee2.setId("e2");
		employee2.setPassword("e2");
		employee2.setName("Funcionário 2");

		this.addUser(employee2);
		
		// criar veículos para a base de dados de exemplo
		vehicleList = new ArrayList<Vehicle>();
		
		GregorianCalendar motorcycleDate = new GregorianCalendar();
		motorcycleDate.set(2009,01,01);
		Motorcycle motorcycle = new Motorcycle(1000, motorcycleDate.get(Calendar.YEAR), 5000, 1.2,
											   0.8, "Honda", "Biz", "ABC1234", 35);		
		this.addVehicle(motorcycle);
		
		GregorianCalendar carDate = new GregorianCalendar();
		carDate.set(2010,01,02 );
		Vehicle car = new Car(2000, carDate.get(Calendar.YEAR), 15000,
							  2.8, 1.9, "Volkswagen", "Gol", "ABC1235", 60);
		this.addVehicle(car);
		
		GregorianCalendar busDate = new GregorianCalendar();
		busDate.set(2011, 01, 03);
		Vehicle bus = new Bus(3000, busDate.get(Calendar.YEAR), 42000,
							  15, 3, "Marcopolo", "Audace", "ABC1236", 130);
		this.addVehicle(bus);

		GregorianCalendar truckDate = new GregorianCalendar();
		truckDate.set(2012, 01, 04);
		Vehicle truck = new Truck(4000, truckDate.get(Calendar.YEAR), 23000,
								  20, 3, "Ford", "Cargo", "ABC1237", 160);
		this.addVehicle(truck);
		
		GregorianCalendar microBusDate = new GregorianCalendar();
		microBusDate.set(2013, 01, 05);
		Vehicle microBus = new MicroBus(3500, microBusDate.get(Calendar.YEAR), 27000,
										10, 2.7, "Mercedez-Benz", "LO 915", "ABC1238", 110);	
		this.addVehicle(microBus);
		
		/*
		Vehicle lol = new Vehicle();
		
		lol.setAvailable(truck.IsAvailable());
		lol.setBrand(truck.getBrand());
		lol.setCategory(truck.getCategory());
		lol.setEnginePower(truck.getEnginePower());
		lol.setLength(truck.getLength());
		lol.setManufacturingDate(truck.getManufacturingDate());
		lol.setMileage(truck.getMileage());		
		lol.setModel(truck.getModel());
		lol.setPlate(truck.getPlate());
		lol.setWidth(truck.getWidth());

		this.addVehicle(lol); 
		*/
		
	}

	public Person getUser(String login)
	{
		for (int i = 0; i < userList.size(); i++)
			if (userList.get(i).getId().equals(login))
				return userList.get(i);

		return null;
	}

	public void addUser(Person person)
	{
		if (userList.contains(person))
			return;
		
		userList.add(person);
	}

	public Vehicle getVehicle(String plate)
	{
		// TODO: implementar método que retorna veículo através da placa.
		return vehicleList.get(0);
	}

	public void addVehicle(Vehicle vehicle)
	{
		if (vehicleList.contains(vehicle))
			return;
		
		vehicleList.add(vehicle);
	}
	
	public ArrayList<Vehicle> getVehicleList()
	{
		return vehicleList;
	}
	
	public boolean removeVehicleList(Vehicle vehicle)
	{
		if(vehicleList.contains(vehicle))
			if(vehicleList.remove(vehicle)) 
				return true;

		return false; 
	}

	public ArrayList<Person> getUserList()
	{
		return userList;
	}

	public void setUserList(ArrayList<Person> userList)
	{
		this.userList = userList;
	}
	
	public ArrayList<Client> getClientList()
	{
		ArrayList<Client> clientList = new ArrayList<Client>();
		
		for(int i = 0; i < userList.size(); i++)
		{
			if(userList.get(i).getClass().getName().equals("person.Client"))
				clientList.add((Client)userList.get(i));			
		}
		return clientList;
	}
}
