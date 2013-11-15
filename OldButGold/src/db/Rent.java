//Classe que representa os aluguéis dos clientes

package db;

import java.util.Calendar;
import java.util.GregorianCalendar;

import vehicle.Vehicle;

public class Rent
{
	private GregorianCalendar withdrawalDate;
	private GregorianCalendar returnDate;
	private int withdrawalMileage; //quilometragem com que o carro sai da loja
	private int returnMileage;	  //quilometragem com que o carro retorna à loja
	private double withdrawalPayment;
	private double returnPayment;
	private Vehicle rentVehicle;
	private int rentDuration;
	private String rentCode;	//toda locação deve ter um código associado
	
	public Rent(Vehicle selectedVehicle, int rentDuration)
	{
		withdrawalDate = new GregorianCalendar();
		returnDate = new GregorianCalendar();
		returnDate.add(Calendar.DAY_OF_MONTH, rentDuration);
		withdrawalMileage = selectedVehicle.getMileage();
		this.rentVehicle = selectedVehicle;
		this.rentDuration = rentDuration;
		this.withdrawalPayment = rentDuration * selectedVehicle.getDailyPrice();
		
		//O código de cada locação é a sua data e hora de ocorrência.
		//Dia, mês, ano, hora, minutos, segundos.
		String code = "" + withdrawalDate.get(Calendar.DAY_OF_MONTH) + (withdrawalDate.get(Calendar.MONTH) + 1)
				  		 + withdrawalDate.get(Calendar.YEAR) + withdrawalDate.get(Calendar.HOUR_OF_DAY) 
				  		 + withdrawalDate.get(Calendar.MINUTE) + withdrawalDate.get(Calendar.SECOND);		
		this.setRentCode(code);
		
		//int comparison = returnDate.compareTo(withdrawalDate);
			//JOptionPane.showMessageDialog(null, comparison);
	}

	public GregorianCalendar getWithdrawalDate() 
	{
		return withdrawalDate;
	}

	public void setWithdrawalDate(GregorianCalendar withdrawalDate)
	{
		this.withdrawalDate = withdrawalDate;
	}

	public Calendar getReturnDate() 
	{
		return returnDate;
	}

	public void setReturnDate(GregorianCalendar returnDate)
	{
		this.returnDate = returnDate;
	}

	public int getWithdrawalMileage()
	{
		return withdrawalMileage;
	}

	public void setWithdrawalMileage(int withdrawalMileage)
	{
		this.withdrawalMileage = withdrawalMileage;
	}

	public int getReturnMileage() 
	{
		return returnMileage;
	}

	public void setReturnMileage(int returnMileage) 
	{
		this.returnMileage = returnMileage;
	}

	public double getWithdrawalPayment() {
		return withdrawalPayment;
	}

	public void setWithdrawalPayment(double withdrawalPayment) {
		this.withdrawalPayment = withdrawalPayment;
	}

	public double getReturnPayment() {
		return returnPayment;
	}

	public void setReturnPayment(double returnPayment) {
		this.returnPayment = returnPayment;
	}

	public Vehicle getRentVehicle() {
		return rentVehicle;
	}

	public void setRentVehicle(Vehicle rentVehicle) {
		this.rentVehicle = rentVehicle.clone(); //novo objeto, cópia do veículo original
	}

	public int getRentTime() {
		return rentDuration;
	}

	public void setRentTime(int rentTime) {
		this.rentDuration = rentTime;
	}

	public int getRentDuration() {
		return rentDuration;
	}

	public void setRentDuration(int rentDuration) {
		this.rentDuration = rentDuration;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}
}
