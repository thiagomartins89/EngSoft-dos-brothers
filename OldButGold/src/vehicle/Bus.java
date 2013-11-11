package vehicle;

public class Bus extends Vehicle
{
	public Bus(int enginePower, int manufacturingDate, int mileage, double length,
			double width, String brand, String model, String plate, double dailyPrice)
	{
		super(enginePower, manufacturingDate, mileage, length, width, brand, model, plate, dailyPrice);
		this.category = "D";
	}

}
