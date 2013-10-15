package vehicle;

public class MicroBus extends Vehicle
{

	public MicroBus(int enginePower, int manufacturingDate, double length,
			double width, String brand, String model, String plate)
	{
		this.enginePower = enginePower;
		this.manufacturingDate = manufacturingDate;
		this.length = length;
		this.width = width;
		this.brand = brand;
		this.model = model;
		this.plate = plate;
		this.category = "D";
		this.isAvailable = true; //todos os veículos já vêm adicionados como disponíveis
	}
	
}
