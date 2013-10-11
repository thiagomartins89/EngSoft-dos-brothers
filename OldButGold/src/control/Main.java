package control;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import GUI.WindowEmployeeMenu;
import GUI.WindowLogin;
import GUI.WindowSubscribeClient;
import GUI.WindowSubscribeVehicle;

//classe em que o programa inicia.
public class Main
{
	private static CurrentState mainCurrentState = new CurrentState();
	
	public static void main(String[] args)
	{
		//abre-se a janela de login
		try 
		{	
			WindowLogin loginWindow = new WindowLogin(mainCurrentState);
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
		
		Class userClass = mainCurrentState.getCurrentUser().getClass();
		
		//--------------------------------------------------------------------------
		//In�cio das fun��es do funcion�rio.
		//Se ficar muito grande, considerar colocar em outra classe.
		
		if(userClass.getName() == "person.Employee")	//n�o sei como fazer retornar apenas "Employee",
														//mas isso j� quebra o galho
		{
			//se � funcion�rio, abre janela de menu de funcion�rio
			try 
			{	
				WindowEmployeeMenu employeeMenuWindow = new WindowEmployeeMenu(mainCurrentState);
				employeeMenuWindow.setBlockOnOpen(true);
				employeeMenuWindow.open();
				Display.getCurrent().dispose();			
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			switch(mainCurrentState.getChosenAction())
			{
				//abre a janela de cadastro de cliente
				case "Cadastrar Cliente":
					try 
					{	
						WindowSubscribeClient subscribeClientWindow = new WindowSubscribeClient(mainCurrentState);
						subscribeClientWindow.setBlockOnOpen(true);
						subscribeClientWindow.open();
						Display.getCurrent().dispose();			
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					break;
					
				//abre a janela de cadastro de ve�culo
				case "Adicionar Ve�culo":
					try 
					{	
						WindowSubscribeVehicle subscribeVehicleWindow = new WindowSubscribeVehicle(mainCurrentState);
						subscribeVehicleWindow.setBlockOnOpen(true);
						subscribeVehicleWindow.open();
						Display.getCurrent().dispose();			
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					break;
				
			}
		}
		
		//----------------------------------------------------------------------------
		//Fim das fun��es do funcion�rio
		
	}
	

}
