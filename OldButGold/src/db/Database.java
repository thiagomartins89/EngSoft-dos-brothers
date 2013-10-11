package db;

import java.util.ArrayList;

import person.Client;
import person.Employee;
import person.Person;
import vehicle.Car;
import vehicle.Motorcycle;
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
		
		Vehicle motorcycle = new Motorcycle();
		motorcycle.setPlate("IQT2191");
		
		Vehicle car = new Car();
		
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
