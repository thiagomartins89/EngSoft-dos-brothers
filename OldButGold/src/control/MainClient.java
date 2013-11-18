package control;

import org.eclipse.swt.widgets.Display;

import GUI.WindowClientMenu;
import GUI.WindowClientVehicleRent;
import GUI.WindowRentHistory;
import db.Database;

public class MainClient
{
	private CurrentState mainClientCurrentState;
	private Database mainClientDatabase;
	
	public MainClient(Database mainDatabase, CurrentState mainCurrentState)
	{
		mainClientDatabase = mainDatabase;
		mainClientCurrentState = mainCurrentState;
		BeginClientActions();
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
					
				case "Loca��o":
					MakeClientRent();
					break;
					
				case "Verificar Hist�rico":
					OpenRentHistory();
					break;
			}
			
		//Volta para o in�cio do menu de cliente
		}while(mainClientCurrentState.getChosenAction() == "Voltar");		
	}
	
	//fun��o que abre a janela de aluguel de ve�culos para o cliente
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
	
	//fun��o que abre a janela do hist�rico de loca��es do cliente
	private void OpenRentHistory()
	{
		try
		{
			WindowRentHistory windowRentHistory = new WindowRentHistory(mainClientCurrentState, mainClientDatabase);
			windowRentHistory.setBlockOnOpen(true);
			windowRentHistory.open();
			Display.getCurrent().dispose();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
}
