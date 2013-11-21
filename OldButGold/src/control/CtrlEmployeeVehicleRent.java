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
	
	public Rent makeCarRent(int selectionIndex, String clientUsername, int rentTime)
	{
		
		Vehicle selectedVehicle = resultVehiclesList.get(selectionIndex);		
		Person person = ctrlSearchVehicleDatabase.getUser(clientUsername);
		Rent employeeRent = null;
		
		if(clientUsername.equals(""))
			JOptionPane.showMessageDialog(null, "Um nome de usu�rio deve ser informado.");
		
		else if(person == null)
			JOptionPane.showMessageDialog(null, "Usu�rio inv�lido.");
		
		else if(!person.getClass().getName().equals("person.Client"))
			JOptionPane.showMessageDialog(null, "Apenas clientes podem locar ve�culos!");
		
		
		else if(selectedVehicle.IsAvailable())
		{
			
			GregorianCalendar newCalendar = new GregorianCalendar();
			employeeRent = new Rent(selectedVehicle, rentTime);
			selectedVehicle.setAvailable(false);
			Client client = (Client) person;
			client.AddRent(employeeRent);
			
			JOptionPane.showMessageDialog(null, "Ve�culo locado com sucesso!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Ve�culo indispon�vel para loca��o no momento.");
		}
		
		return employeeRent;
				
	}
}


