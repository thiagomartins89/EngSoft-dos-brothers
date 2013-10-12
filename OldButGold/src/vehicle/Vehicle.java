package vehicle;

import java.util.Date;

public class Vehicle
{

	private
	String enginePower;
	Date manufacturingDate;
	double length, width;
	String brand, model,  plate;
	Boolean isAvailable;
	String category;
	
	public String getEnginePower()
	{
		return enginePower;
	}

	public void setEnginePower(String enginePower)
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String string) {
		this.category = string;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

}
