package db;

import java.util.ArrayList;
import java.util.Date;

import person.Client;
import person.Employee;
import person.Person;
import vehicle.Bus;
import vehicle.Car;
import vehicle.MicroBus;
import vehicle.Motorcycle;
import vehicle.Truck;
import vehicle.Vehicle;

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
		
		Date motorcycleDate = new Date(01, 01, 2009);
		Motorcycle motorcycle = new Motorcycle("1000", motorcycleDate, 1.2,
				0.8, "Honda", "Biz", "ABC1234");		
		this.addVehicle(motorcycle);
		
		Date carDate = new Date(02, 01, 2010);
		Vehicle car = new Car("2000", carDate, 2.8,
				1.9, "Volkswagen", "Gol", "ABC1235");
		this.addVehicle(car);
		
		Date busDate = new Date(03, 01, 2011);
		Vehicle bus = new Bus("3000", busDate, 15,
				3, "Mercedez-Benz", "bus1", "ABC1236");
		this.addVehicle(bus);

		Date truckDate = new Date(04, 01, 2012);
		Vehicle truck = new Truck("4000", truckDate, 20,
				3, "TruckBrand", "truck1", "ABC1237");
		this.addVehicle(truck);
		
		Date microBusDate = new Date(05, 01, 2013);
		Vehicle microBus = new MicroBus("3500", microBusDate, 10,
				2.7, "MicroBusBrand", "microbus1", "ABC1238");	
		this.addVehicle(microBus);
		
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
}
