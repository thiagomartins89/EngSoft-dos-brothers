//Classe que serve de controle à janela WindowSearchVehicle

package control;

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
	
	public Rent makeVehicleRent(int selectionIndex, CurrentState rentCurrentState, int rentTime)
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
			selectedVehicle.setCurrentClient(currentClient);
			return clientRent;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Veículo indisponível para locação no momento.");
			return null;
		}
		
	}
}
