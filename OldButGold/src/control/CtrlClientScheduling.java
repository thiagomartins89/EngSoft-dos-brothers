//Classe que serve de controle à janela WindowScheduleRent,
//a janela de agendamento.

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

public class CtrlClientScheduling extends CtrlSearchVehicle
{
	private Database ctrlSearchVehicleDatabase;

	public CtrlClientScheduling(Database mainDatabase)
	{
		super(mainDatabase);
		this.ctrlSearchVehicleDatabase = mainDatabase;
	}
	
	public Rent makeClientScheduling(int selectionIndex, CurrentState rentCurrentState, int rentTime)
	{
		Vehicle selectedVehicle = resultVehiclesList.get(selectionIndex);
		
		if(selectedVehicle.IsAvailable())
		{
			GregorianCalendar newCalendar = new GregorianCalendar();
			Person currentUser = rentCurrentState.getCurrentUser();
			Rent clientRent = new Rent(selectedVehicle, rentTime);
			selectedVehicle.setAvailable(false);
			JOptionPane.showMessageDialog(null, "Veículo locado com sucesso!");
			Client currentClient = (Client) currentUser;
			currentClient.AddRent(clientRent);
			return clientRent;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Veículo indisponível para locação no momento.");
			return null;
		}
		
	}
}
