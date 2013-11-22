//Classe que serve de controle à janela WindowScheduleRent,
//a janela de agendamento.

package control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import person.Client;
import person.Person;

import vehicle.Vehicle;

import db.Database;
import db.Rent;

public class CtrlClientScheduling extends CtrlSearchVehicle
{
	private Database ctrlSearchVehicleDatabase;

	public CtrlClientScheduling(Database mainDatabase)
	{
		super(mainDatabase);
		this.ctrlSearchVehicleDatabase = mainDatabase;
	}
	
	public Rent makeClientScheduling(int selectionIndex, CurrentState rentCurrentState, int rentTime, GregorianCalendar chosenDate)
	{
		Vehicle selectedVehicle = resultVehiclesList.get(selectionIndex);
		
		if(selectedVehicle.isAvailableAt(chosenDate))
		{
			Person currentUser = rentCurrentState.getCurrentUser();
			Rent clientRent = new Rent(selectedVehicle, rentTime, chosenDate);
			
			GregorianCalendar availableDate = new GregorianCalendar();
			availableDate.setTime(chosenDate.getTime());	
			
			availableDate.add(Calendar.DAY_OF_MONTH, rentTime);
			selectedVehicle.setAvailableAt(availableDate);
			selectedVehicle.setAvailable(false);
			
			Client currentClient = (Client) currentUser;
			currentClient.AddRent(clientRent);
			selectedVehicle.setCurrentClient(currentClient);
			
			JOptionPane.showMessageDialog(null, "Veículo agendado com sucesso!");
			
			return clientRent;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Veículo indisponível para locação no momento.");
			return null;
		}
		
	}
}
