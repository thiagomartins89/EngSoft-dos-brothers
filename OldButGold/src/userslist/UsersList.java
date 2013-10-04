package userslist;

import person.Person;

public class UsersList
{
	private UsersList next;
	private Person data;
	
	public UsersList(Person data)
	{
		this.data = data;
		this.next = null;
	}
	
	public UsersList getNext()
	{
		return next;
	}
	
	public void setNext(UsersList next)
	{
		this.next = next;
	}
	
	public Person getData()
	{
		return data;
	}
	
	public void setData(Person data)
	{
		this.data = data;
	}
}
