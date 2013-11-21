package control;

import org.eclipse.swt.widgets.Display;

import GUI.WindowClientMenu;
import GUI.WindowClientScheduling;
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
			
			switch(mainClientCurrentState.getChosenAction()) //opção escolhida pelo cliente no menu
			{
				case "Sair":
					mainClientCurrentState.setCurrentUser(null);
					break;
					
				case "Locação":
					makeClientRent();
					break;
					
				case "Verificar Histórico":
					openRentHistory();
					break;
					
				case "Agendamento":
					makeClientScheduling();
					break;
			}
			
		//Volta para o início do menu de cliente
		}while(mainClientCurrentState.getChosenAction() == "Voltar");		
	}
	
	private void makeClientScheduling() 
	{
		try 
		{	
			WindowClientScheduling clientSchedulingWindow = new WindowClientScheduling(mainClientCurrentState, mainClientDatabase);
			clientSchedulingWindow.setBlockOnOpen(true);
			clientSchedulingWindow.open();
			Display.getCurrent().dispose();			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
				
	}

	//função que abre a janela de aluguel de veículos para o cliente
	private void makeClientRent()
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
	
	//função que abre a janela do histórico de locações do cliente
	private void openRentHistory()
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
