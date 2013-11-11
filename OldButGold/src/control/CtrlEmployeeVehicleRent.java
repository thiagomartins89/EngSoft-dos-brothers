package control;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import person.Client;
import person.Person;
import vehicle.Vehicle;
import db.Database;
import db.Rent;

public class CtrlEmployeeVehicleRent extends CtrlSearchVehicle
{
	private Database ctrlSearchVehicleDatabase;

	public CtrlEmployeeVehicleRent(Database mainDatabase)
	{
		super(mainDatabase);
		this.ctrlSearchVehicleDatabase = mainDatabase;
	}
	
	public boolean MakeCarRent(int selectionIndex, String clientUsername)
	{
		
		boolean rentSucess = false;
		
		Vehicle selectedVehicle = resultVehiclesList.get(selectionIndex);		
		Person person = ctrlSearchVehicleDatabase.getUser(clientUsername);
		
		if(clientUsername.equals(""))
			JOptionPane.showMessageDialog(null, "Um nome de usuário deve ser informado.");
		
		else if(person == null)
			JOptionPane.showMessageDialog(null, "Usuário inválido.");
		
		else if(!person.getClass().getName().equals("person.Client"))
			JOptionPane.showMessageDialog(null, "Apenas clientes podem locar veículos!");
		
		
		else if(selectedVehicle.IsAvailable())
		{
			
			GregorianCalendar newCalendar = new GregorianCalendar();
			Rent clientRent = new Rent(newCalendar, selectedVehicle.getMileage());
			selectedVehicle.setAvailable(false);
			Client client = (Client) person;
			client.AddRent(clientRent);
			
			JOptionPane.showMessageDialog(null, "Veículo locado com sucesso!");
			rentSucess = true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Veículo indisponível para locação no momento.");
		}
		
		return rentSucess;
		
	}
}


