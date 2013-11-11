package control;

import org.eclipse.swt.widgets.Display;

import GUI.WindowClientVehicleRent;
import GUI.WindowEmployeeVehicleRent;
import GUI.WindowEmployeeMenu;
import GUI.WindowSubscribeClient;
import GUI.WindowSubscribeVehicle;
import db.Database;

public class MainEmployee
{
	
	private CurrentState mainEmployeeCurrentState;
	private Database mainEmployeeDatabase;
	
	public MainEmployee(Database mainDatabase, CurrentState mainCurrentState)
	{
		mainEmployeeDatabase = mainDatabase;
		mainEmployeeCurrentState = mainCurrentState;
		this.BeginEmployeeActions();
	}
	
	private void BeginEmployeeActions()
	{
		do
		{
			try 
			{	
				WindowEmployeeMenu employeeMenuWindow = new WindowEmployeeMenu(mainEmployeeCurrentState, mainEmployeeDatabase);
				employeeMenuWindow.setBlockOnOpen(true);
				employeeMenuWindow.open();
				Display.getCurrent().dispose();			
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			switch(mainEmployeeCurrentState.getChosenAction()) //opção escolhida pelo funcionário no menu
			{
			
				case "Sair":
					mainEmployeeCurrentState.setCurrentUser(null);
					break;
				
				case "Cadastrar Cliente":
					MakeSubscribeClient();
					break;
					
				//abre a janela de cadastro de veículo
				case "Adicionar Veículo":
					MakeAddVehicle();
					break;
					
				case "Locação":
					MakeEmployeeRent();
					break;		
					
			}
			
		//Volta para o início do menu de funcionário caso o veículo seja
		//adicionado com sucesso, ou se o funcionário pressionar "voltar".
		}while(mainEmployeeCurrentState.getChosenAction() == "Voltar" ||
		       mainEmployeeCurrentState.getChosenAction() == "Adicionar Veículo");
	}
	
	//função que abre a janela de adição de veículos
	private void MakeAddVehicle()
	{
		try 
		{	
			WindowSubscribeVehicle subscribeVehicleWindow = new WindowSubscribeVehicle(mainEmployeeCurrentState, mainEmployeeDatabase);
			subscribeVehicleWindow.setBlockOnOpen(true);
			subscribeVehicleWindow.open();
			Display.getCurrent().dispose();			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//função que abre a janela de aluguel de veículos para o funcionário
	private void MakeEmployeeRent()
	{
		try 
		{	
			WindowEmployeeVehicleRent employeeCarRentWindow = new WindowEmployeeVehicleRent(mainEmployeeCurrentState, mainEmployeeDatabase);
			employeeCarRentWindow.setBlockOnOpen(true);
			employeeCarRentWindow.open();
			Display.getCurrent().dispose();			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	//função que abre a janela de adição de veículos
	private void MakeSubscribeClient()
	{
		try 
		{	
			WindowSubscribeClient subscribeClientWindow = new WindowSubscribeClient(mainEmployeeCurrentState, mainEmployeeDatabase);
			subscribeClientWindow.setBlockOnOpen(true);
			subscribeClientWindow.open();
			Display.getCurrent().dispose();			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
