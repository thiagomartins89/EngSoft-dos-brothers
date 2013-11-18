package control;

import org.eclipse.swt.widgets.Display;

import GUI.WindowLogin;
import db.Database;

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
			
			//Verifica qual � o tipo do usu�rio logado
			Class userClass = mainCurrentState.getCurrentUser().getClass();
						
			if(userClass.getName() == "person.Employee")
			{
				//D� in�cio � sess�o como funcion�rio
				MainEmployee mainEmployee = new MainEmployee(mainDatabase, mainCurrentState);
			}
			
			else
			{
				//D� in�cio � sess�o como cliente
				MainClient mainClient = new MainClient(mainDatabase, mainCurrentState);
			}
			
		}while(mainCurrentState.getCurrentUser() == null);
	}
	

}
