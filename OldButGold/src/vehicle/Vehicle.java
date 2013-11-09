package vehicle;

public class Vehicle
{

	public Vehicle(int enginePower, int manufacturingDate, double length,
			double width, String brand, String model, String plate, String category, int mileage)
	{
		this.enginePower = enginePower;
		this.manufacturingDate = manufacturingDate;
		this.length = length;
		this.width = width;
		this.brand = brand;
		this.model = model;
		this.plate = plate;
		this.category = category;
		this.isAvailable = true; //todos os veículos já vêm adicionados como disponíveis
		this.mileage = mileage;
	}
	
	
	public Vehicle() {
		// TODO Auto-generated constructor stub
	}


	protected String brand, model, plate, category;
	protected double length, width;
	protected int enginePower, manufacturingDate, mileage;
	protected Boolean isAvailable;

	
	public int getEnginePower()
	{
		return enginePower;
	}

	public void setEnginePower(int enginePower)
	{
		this.enginePower = enginePower;
	}

	public int getManufacturingDate()
	{
		return manufacturingDate;
	}

	public void setManufacturingDate(int manufacturingDate)
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

	public Boolean IsAvailable()
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

	public void setCategory(String category) {
		this.category = category;
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
	
	public int getMileage()
	{
		return mileage;
	}

	public void setMileage(int mileage)
	{
		this.mileage = mileage;
	}

}
