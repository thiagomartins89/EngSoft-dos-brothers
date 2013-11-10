package control;

import org.eclipse.swt.widgets.Display;

import GUI.WindowClientVehicleRent;
import GUI.WindowClientMenu;
import GUI.WindowSearchVehicle;
import db.Database;

public class MainClient
{
	private CurrentState mainClientCurrentState;
	private Database mainClientDatabase;
	
	public MainClient(Database mainDatabase, CurrentState mainCurrentState)
	{
		mainClientDatabase = mainDatabase;
		mainClientCurrentState = mainCurrentState;
		this.BeginClientActions();
	}

	private void BeginClientActions() 
	{
		do
		{
			try 
			{	
				WindowClientMenu clientMenuWindow = new WindowClientMenu(mainClientCurrentState, mainClientDatabase);
				clientMenuWindow.setBlockOnOpen(true);
				clientMenuWindow.open();
				Display.getCurrent().dispose();			
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			switch(mainClientCurrentState.getChosenAction()) //op��o escolhida pelo cliente no menu
			{
				case "Sair":
					mainClientCurrentState.setCurrentUser(null);
					break;
					
				/*				
				case "Pesquisar Ve�culo":
					try 
					{	
						WindowSearchVehicle searchVehicleWindow = new WindowSearchVehicle(mainClientCurrentState, mainClientDatabase);
						searchVehicleWindow.setBlockOnOpen(true);
						searchVehicleWindow.open();
						Display.getCurrent().dispose();			
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					break;
					*/
										
				case "Loca��o":
					MakeClientRent();
					break;				
		
			}
			
		//Volta para o in�cio do menu de cliente
		}while(mainClientCurrentState.getChosenAction() == "Voltar");		
	}
	
	private void MakeClientRent()
	{
		try 
		{	
			WindowClientVehicleRent clientCarRentWindow = new WindowClientVehicleRent(mainClientCurrentState, mainClientDatabase);
			clientCarRentWindow.setBlockOnOpen(true);
			clientCarRentWindow.open();
			Display.getCurrent().dispose();			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
}
