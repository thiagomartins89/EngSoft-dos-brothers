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
	private int rentTime;
	
	public Rent(Vehicle selectedVehicle, int rentTime)
	{
		withdrawalDate = new GregorianCalendar();
		returnDate = null;
		withdrawalMileage = selectedVehicle.getMileage();
		this.rentVehicle = selectedVehicle;
		this.rentTime = rentTime;
		this.withdrawalPayment = rentTime * selectedVehicle.getDailyPrice();
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
		this.rentVehicle = rentVehicle;
	}

	public int getRentTime() {
		return rentTime;
	}

	public void setRentTime(int rentTime) {
		this.rentTime = rentTime;
	}
}
