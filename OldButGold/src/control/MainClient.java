package control;

import org.eclipse.swt.widgets.Display;

import GUI.WindowClientScheduling;
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
		beginClientActions();
	}

	private void beginClientActions() 
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
				
				case "Agendamento":
					makeClientScheduling();
			}
			
		//Volta para o início do menu de cliente
		}while(mainClientCurrentState.getChosenAction() == "Voltar");		
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
	
}
