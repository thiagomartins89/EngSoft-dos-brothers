package db;

import javax.swing.JOptionPane;

import person.*;
import userslist.UsersList;

public class Database {

	public UsersList list;	//mudar para private após testes

	public Database()
	{
		//criar clientes para a base de dados de exemplo
		list = new UsersList(null);
		
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
		UsersList auxList = list;
		
		while(auxList != null)
		{
			if(auxList.getData().getId().equals(login))
				return auxList.getData();
			else
				auxList = auxList.getNext();
		}
		
		return null;
	}
	
	public void addUser(Person person)
	{
		if(list.getData() == null)
			list.setData(person);
		
		else
		{
			UsersList auxList = list;
			UsersList newNode = new UsersList(person);
			
			while(auxList.getNext() != null)
				auxList = auxList.getNext();
			
			auxList.setNext(newNode);
		}
	}
}
