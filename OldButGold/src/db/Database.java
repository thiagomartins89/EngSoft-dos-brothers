package db;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import person.*;
import vehicle.*;

public class Database
{
	
	private ArrayList<Person> userList;
	private ArrayList<Vehicle> vehicleList;
	
	public Database()
	{
		//criar clientes para a base de dados de exemplo
		userList = new ArrayList<Person>();
		
		Client client1 = new Client();
		
		client1.setId("c1");
		client1.setPassword("c1");
		
		this.addUser(client1);
		
		Client client2 = new Client();
		
		client2.setId("c2");
		client2.setPassword("c2");
		
		this.addUser(client2);
		
		//criar funcionários para a base de dados de exemplo
		Employee employee1 = new Employee();
		
		employee1.setId("e1");
		employee1.setPassword("e1");
		
		this.addUser(employee1);
		
		Employee employee2 = new Employee();
		
		employee2.setId("e2");
		employee2.setPassword("e2");
		
		this.addUser(employee2);
	}
	
	public Person getUser(String login)
	{
		for(int i = 0; i < userList.size(); i++)
			if(userList.get(i).getId().equals(login))
				return userList.get(i);
		
		return null;
	}
	
	public void addUser(Person person)
	{
		userList.add(person);
	}
}
