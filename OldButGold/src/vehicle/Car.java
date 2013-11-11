package vehicle;

public class Car extends Vehicle 
{
	public Car(int enginePower, int manufacturingDate, int mileage, double length,
			double width, String brand, String model, String plate, double dailyPrice)
	{
		super(enginePower, manufacturingDate, mileage, length, width, brand, model, plate, dailyPrice);
		this.category = "B";
	}

}
