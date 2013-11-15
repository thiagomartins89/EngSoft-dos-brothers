package control;

import org.eclipse.swt.widgets.Display;

import GUI.WindowEmployeeMenu;
import GUI.WindowEmployeeVehicleRent;
import GUI.WindowRemoveVehicle;
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
			
			switch(mainEmployeeCurrentState.getChosenAction()) //op��o escolhida pelo funcion�rio no menu
			{
			
				case "Sair":
					mainEmployeeCurrentState.setCurrentUser(null);
					break;
				
				case "Cadastrar Cliente":
					MakeSubscribeClient();
					break;
					
				case "Adicionar Ve�culo":
					MakeAddVehicle();
					break;
					
				case "Loca��o":
					MakeEmployeeRent();
					break;
				
				case "Remover Ve�culo":
					MakeRemoveVehicle();
					break;
			}
			
		//Volta para o in�cio do menu de funcion�rio caso o ve�culo seja
		//adicionado com sucesso, ou se o funcion�rio pressionar "voltar".
		}while(mainEmployeeCurrentState.getChosenAction() == "Voltar" ||
		       mainEmployeeCurrentState.getChosenAction() == "Adicionar Ve�culo");
	}
	
	//fun��o que abre a janela de adi��o de ve�culos
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
	
	//fun��o que abre a janela de aluguel de ve�culos para o funcion�rio
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
	
	//fun��o que abre a janela de adi��o de ve�culos
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
	
	//Fun��o para a Remo��o de um ve�culo (se n�o locado)
	private void MakeRemoveVehicle()
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

}
