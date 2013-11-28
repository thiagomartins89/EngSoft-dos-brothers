package control;

import org.eclipse.swt.widgets.Display;

import GUI.WindowEmployeeMenu;
import GUI.WindowEmployeeRentHistory;
import GUI.WindowEmployeeVehicleRent;
import GUI.WindowRemoveVehicle;
import GUI.WindowReturnVehicle;
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
		this.beginEmployeeActions();
	}
	
	private void beginEmployeeActions()
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
					makeSubscribeClient();
					break;
					
				case "Adicionar Veículo":
					makeAddVehicle();
					break;
					
				case "Locação":
					makeEmployeeRent();
					break;
					
				case "Devolução":
					MakeReturnVehicle();
					break;
				
				case "Remover Veículo":
					makeRemoveVehicle();
					break;
					
				case "Verificar Histórico":
					openRentHistory();
					break;
			}
			
		//Volta para o início do menu de funcionário caso o veículo seja
		//adicionado com sucesso, ou se o funcionário pressionar "voltar".
		}while(mainEmployeeCurrentState.getChosenAction() == "Voltar" ||
		       mainEmployeeCurrentState.getChosenAction() == "Adicionar Veículo");
	}
	
	//função que abre a janela de devolução de veículos
	private void MakeReturnVehicle()
	{
		try 
		{	
			WindowReturnVehicle returnVehicleWindow = new WindowReturnVehicle(mainEmployeeCurrentState, mainEmployeeDatabase);
			returnVehicleWindow.setBlockOnOpen(true);
			returnVehicleWindow.open();
			Display.getCurrent().dispose();			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}

	//função que abre a janela de adição de veículos
	private void makeAddVehicle()
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
	private void makeEmployeeRent()
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
	private void makeSubscribeClient()
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
	
	//Função para a Remoção de um veículo (se não locado)
	private void makeRemoveVehicle()
	{
		try 
		{	
			WindowRemoveVehicle removeVehicleWindow = new WindowRemoveVehicle(mainEmployeeCurrentState, mainEmployeeDatabase);
			removeVehicleWindow.setBlockOnOpen(true);
			removeVehicleWindow.open();
			Display.getCurrent().dispose();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//função que abre a janela do histórico de locações
	private void openRentHistory()
	{
		try
		{
			WindowEmployeeRentHistory windowRentHistory = new WindowEmployeeRentHistory(mainEmployeeCurrentState, mainEmployeeDatabase);
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
