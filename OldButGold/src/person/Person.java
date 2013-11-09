package person;

import java.sql.Date;
import java.util.Calendar;

public class Person
{

	private
	
	String name, id, password;
	long cpf;
	Calendar birthDate;
	
	
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

	public Calendar getBirthDate() 
	{
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) 
	{
		this.birthDate = birthDate;
	}		

}
