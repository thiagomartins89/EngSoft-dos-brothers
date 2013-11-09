package control;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;

import db.Database;

import GUI.WindowClientMenu;
import GUI.WindowEmployeeMenu;
import GUI.WindowLogin;
import GUI.WindowSearchVehicle;
import GUI.WindowSubscribeClient;
import GUI.WindowSubscribeVehicle;

//classe em que o programa inicia.
public class Main
{
	private static CurrentState mainCurrentState = new CurrentState();
	private static Database mainDatabase = new Database();
	
	public static void main(String[] args)
	{
		do
		{
			//abre a janela de login
			try 
			{	
				WindowLogin loginWindow = new WindowLogin(mainCurrentState, mainDatabase);
				loginWindow.setBlockOnOpen(true);
				int ret = loginWindow.open();
				if (ret != 0) //se houve algum problema ao fechar a janela
					return;
				
				Display.getCurrent().dispose();			
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	
			if(mainCurrentState.getChosenAction() == "Sair")
				return;
			
			//Verifica qual é o tipo do usuário logado
			Class userClass = mainCurrentState.getCurrentUser().getClass();
						
			if(userClass.getName() == "person.Employee")
			{
				//Dá início à sessão como funcionário
				MainEmployee mainEmployee = new MainEmployee(mainDatabase, mainCurrentState);
			}
			
			else
			{
				//Dá início à sessão como cliente
				MainClient mainClient = new MainClient(mainDatabase, mainCurrentState);
			}
			
		}while(mainCurrentState.getCurrentUser() == null);
	}
	

}
