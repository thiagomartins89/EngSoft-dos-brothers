//Classe que serve de controle � janela WindowSearchVehicle

package control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import person.Client;
import person.Person;

import vehicle.Vehicle;

import db.Database;
import db.Rent;

public class CtrlClientVehicleRent extends CtrlSearchVehicle
{
	private Database ctrlSearchVehicleDatabase;

	public CtrlClientVehicleRent(Database mainDatabase)
	{
		super(mainDatabase);
		this.ctrlSearchVehicleDatabase = mainDatabase;
	}
	
	public boolean MakeCarRent(int selectionIndex, CurrentState rentCurrentState)
	{
		Vehicle selectedVehicle = resultVehiclesList.get(selectionIndex);
		
		if(selectedVehicle.IsAvailable())
		{
			GregorianCalendar newCalendar = new GregorianCalendar();
			Person currentUser = rentCurrentState.getCurrentUser();
			Rent clientRent = new Rent(newCalendar, selectedVehicle.getMileage());
			selectedVehicle.setAvailable(false);
			JOptionPane.showMessageDialog(null, "Ve�culo locado com sucesso!");
			Client currentClient = (Client) currentUser;
			currentClient.AddRent(clientRent);
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Ve�culo indispon�vel para loca��o no momento.");
			return false;
		}
		
	}
}