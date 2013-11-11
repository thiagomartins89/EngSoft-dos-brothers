package vehicle;

public class Truck extends Vehicle 
{

	public Truck(int enginePower, int manufacturingDate, int mileage, double length,
			double width, String brand, String model, String plate, double dailyPrice)
	{
		super(enginePower, manufacturingDate, mileage, length, width, brand, model, plate, dailyPrice);
		this.category = "C";
	}
}
