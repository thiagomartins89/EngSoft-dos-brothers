//Classe que representa os aluguéis dos clientes

package db;

import java.util.Calendar;

public class Rent
{
	private Calendar withdrawalDate;
	private Calendar returnDate;
	private int withdrawalMileage; //quilometragem com que o carro sai da loja
	private int returnMileage;	  //quilometragem com que o carro retorna à loja
	
	public Rent(Calendar clientWithdrawalDate, int clientWithdrawalMileage)
	{
		withdrawalDate = clientWithdrawalDate;
		returnDate = null;
		withdrawalMileage = clientWithdrawalMileage;		
	}

	public Calendar getWithdrawalDate() 
	{
		return withdrawalDate;
	}

	public void setWithdrawalDate(Calendar withdrawalDate)
	{
		this.withdrawalDate = withdrawalDate;
	}

	public Calendar getReturnDate() 
	{
		return returnDate;
	}

	public void setReturnDate(Calendar returnDate)
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
}
