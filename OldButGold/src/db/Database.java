package db;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import com.ibm.icu.util.Calendar;

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

		// criar funcion�rios para a base de dados de exemplo
		Employee employee1 = new Employee();

		employee1.setId("e1");
		employee1.setPassword("e1");
		employee1.setName("Funcion�rio 1");

		this.addUser(employee1);

		Employee employee2 = new Employee();

		employee2.setId("e2");
		employee2.setPassword("e2");
		employee2.setName("Funcion�rio 2");

		this.addUser(employee2);
		
		// criar ve�culos para a base de dados de exemplo
		vehicleList = new ArrayList<Vehicle>();
		
		GregorianCalendar motorcycleDate = new GregorianCalendar();
		motorcycleDate.set(2009,01,01);
		Motorcycle motorcycle = new Motorcycle(1000, motorcycleDate.get(Calendar.YEAR), 1.2,
											   0.8, "Honda", "Biz", "ABC1234");		
		this.addVehicle(motorcycle);
		
		GregorianCalendar carDate = new GregorianCalendar();
		carDate.set(2010,01,02 );
		Vehicle car = new Car(2000, carDate.get(Calendar.YEAR), 2.8,
							 1.9, "Volkswagen", "Gol", "ABC1235");
		this.addVehicle(car);
		
		GregorianCalendar busDate = new GregorianCalendar();
		busDate.set(2011, 01, 03);
		Vehicle bus = new Bus(3000, busDate.get(Calendar.YEAR), 15,
							  3, "Marcopolo", "Audace", "ABC1236");
		this.addVehicle(bus);

		GregorianCalendar truckDate = new GregorianCalendar();
		truckDate.set(2012, 01, 04);
		Vehicle truck = new Truck(4000, truckDate.get(Calendar.YEAR), 20,
								  3, "Ford", "Cargo", "ABC1237");
		this.addVehicle(truck);
		
		GregorianCalendar microBusDate = new GregorianCalendar();
		microBusDate.set(2013, 01, 05);
		Vehicle microBus = new MicroBus(3500, microBusDate.get(Calendar.YEAR), 10,
										2.7, "Mercedez-Benz", "LO 915", "ABC1238");	
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
		if (userList.contains(person))
			return;
		
		userList.add(person);
	}

	public Vehicle getVehicle(String plate)
	{
		// TODO: implementar m�todo que retorna ve�culo atrav�s da placa.
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
