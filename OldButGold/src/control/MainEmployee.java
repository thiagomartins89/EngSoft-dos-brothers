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
			
			switch(mainEmployeeCurrentState.getChosenAction()) //op��o escolhida pelo funcion�rio no menu
			{
			
				case "Sair":
					mainEmployeeCurrentState.setCurrentUser(null);
					break;
				
				case "Cadastrar Cliente":
					makeSubscribeClient();
					break;
					
				case "Adicionar Ve�culo":
					makeAddVehicle();
					break;
					
				case "Loca��o":
					makeEmployeeRent();
					break;
					
				case "Devolu��o":
					MakeReturnVehicle();
					break;
				
				case "Remover Ve�culo":
					makeRemoveVehicle();
					break;
					
				case "Verificar Hist�rico":
					openRentHistory();
					break;
			}
			
		//Volta para o in�cio do menu de funcion�rio caso o ve�culo seja
		//adicionado com sucesso, ou se o funcion�rio pressionar "voltar".
		}while(mainEmployeeCurrentState.getChosenAction() == "Voltar" ||
		       mainEmployeeCurrentState.getChosenAction() == "Adicionar Ve�culo");
	}
	
	//fun��o que abre a janela de devolu��o de ve�culos
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

	//fun��o que abre a janela de adi��o de ve�culos
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
	
	//fun��o que abre a janela de aluguel de ve�culos para o funcion�rio
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
	
	//fun��o que abre a janela de adi��o de ve�culos
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
	
	//Fun��o para a Remo��o de um ve�culo (se n�o locado)
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
	
	//fun��o que abre a janela do hist�rico de loca��es
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
