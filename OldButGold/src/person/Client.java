package person;

import java.util.ArrayList;

import db.Rent;


public class Client extends Person 
{
	
	private	long cnh;	//carteira nacional de habilitação
	private ArrayList<Rent> rentList;

	public Client()
	{
		rentList = new ArrayList<Rent>();
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
}
