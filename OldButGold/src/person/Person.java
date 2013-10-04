package person;

import java.sql.Date;

public class Person
{

	private
	
	String name, id, password;
	long cpf;
	Date birthDate;
	
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	
	public long getCpf() 
	{
		return cpf;
	}
	
	public void setCpf(long cpf) 
	{
		this.cpf = cpf;
	}

	public Date getBirthDate() 
	{
		return birthDate;
	}

	public void setBirthDate(Date birthDate) 
	{
		this.birthDate = birthDate;
	}		

}
