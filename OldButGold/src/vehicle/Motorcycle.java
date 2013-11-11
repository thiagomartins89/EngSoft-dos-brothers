package vehicle;

public class Motorcycle extends Vehicle
{
	
	public Motorcycle(int enginePower, int manufacturingDate, int mileage, double length,
			double width, String brand, String model, String plate, double dailyPrice)
	{
		super(enginePower, manufacturingDate, mileage, length, width, brand, model, plate, dailyPrice);
		this.category = "A";
	}

}
