//Classe que representa os aluguéis dos clientes

package db;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Rent
{
	private GregorianCalendar withdrawalDate;
	private GregorianCalendar returnDate;
	private int withdrawalMileage; //quilometragem com que o carro sai da loja
	private int returnMileage;	  //quilometragem com que o carro retorna à loja
	private double withdrawalPayment;
	private double returnPayment;
	
	public Rent(GregorianCalendar clientWithdrawalDate, int clientWithdrawalMileage)
	{
		withdrawalDate = clientWithdrawalDate;
		returnDate = null;
		withdrawalMileage = clientWithdrawalMileage;		
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
}
