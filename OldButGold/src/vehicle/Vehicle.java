package vehicle;

import java.sql.Date;

public class Vehicle
{

	private int enginePower;
	Date manufacturingDate;
	double length, width;
	String brand, model,  plate;
	Boolean isAvailable;

	public int getEnginePower()
	{
		return enginePower;
	}

	public void setEnginePower(int enginePower)
	{
		this.enginePower = enginePower;
	}

	public Date getManufacturingDate()
	{
		return manufacturingDate;
	}

	public void setManufacturingDate(Date manufacturingDate)
	{
		this.manufacturingDate = manufacturingDate;
	}

	public double getLength()
	{
		return length;
	}

	public void setLength(double length)
	{
		this.length = length;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public Boolean getIsAvailable()
	{
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable)
	{
		this.isAvailable = isAvailable;
	}

	public String getPlate()
	{
		return plate;
	}

	public void setPlate(String plate)
	{
		this.plate = plate;
	}

}
