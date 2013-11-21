package control;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;

import GUI.WindowClientVehicleRent;
import GUI.WindowEmployeeVehicleRent;
import GUI.WindowEmployeeMenu;
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
				
				case "Remover Ve�culo":
					makeRemoveVehicle();
					break;
			}
			
		//Volta para o in�cio do menu de funcion�rio caso o ve�culo seja
		//adicionado com sucesso, ou se o funcion�rio pressionar "voltar".
		}while(mainEmployeeCurrentState.getChosenAction() == "Voltar" ||
		       mainEmployeeCurrentState.getChosenAction() == "Adicionar Ve�culo");
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

}
