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
				
				case "Remover Veículo":
					makeRemoveVehicle();
					break;
			}
			
		//Volta para o início do menu de funcionário caso o veículo seja
		//adicionado com sucesso, ou se o funcionário pressionar "voltar".
		}while(mainEmployeeCurrentState.getChosenAction() == "Voltar" ||
		       mainEmployeeCurrentState.getChosenAction() == "Adicionar Veículo");
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

}
