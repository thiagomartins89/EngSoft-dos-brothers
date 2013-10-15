package control;

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
			//abre-se a janela de login
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
			
			Class userClass = mainCurrentState.getCurrentUser().getClass();
			
			//--------------------------------------------------------------------------
			//In�cio das fun��es do funcion�rio.
			//Se ficar muito grande, considerar colocar em outra classe.
			
			if(userClass.getName() == "person.Employee")	//n�o sei como fazer retornar apenas "Employee",
															//mas isso j� quebra o galho
			{
				do
				{
					//se � funcion�rio, abre janela de menu de funcion�rio
					try 
					{	
						WindowEmployeeMenu employeeMenuWindow = new WindowEmployeeMenu(mainCurrentState, mainDatabase);
						employeeMenuWindow.setBlockOnOpen(true);
						employeeMenuWindow.open();
						Display.getCurrent().dispose();			
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					
					switch(mainCurrentState.getChosenAction()) //op��o escolhida pelo funcion�rio no menu
					{
						
						case "Cadastrar Cliente":
							//abre a janela de cadastro de cliente
							try 
							{	
								WindowSubscribeClient subscribeClientWindow = new WindowSubscribeClient(mainCurrentState, mainDatabase);
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
								WindowSubscribeVehicle subscribeVehicleWindow = new WindowSubscribeVehicle(mainCurrentState, mainDatabase);
								subscribeVehicleWindow.setBlockOnOpen(true);
								subscribeVehicleWindow.open();
								Display.getCurrent().dispose();			
							} 
							catch (Exception e) 
							{
								e.printStackTrace();
							}
							break;
							
						case "Sair":
							break;
					}
					
				//Volta para o in�cio do menu de funcion�rio caso o ve�culo seja
				//adicionado com sucesso, ou se o funcion�rio pressionar "voltar".
				}while(mainCurrentState.getChosenAction() == "Voltar" 
				  || mainCurrentState.getChosenAction() == "Adicionar Ve�culo");
			}
				
			
			//----------------------------------------------------------------------------
			//Fim das fun��es do funcion�rio
			
			//In�cio das fun��es de cliente
			else
			{
				do
				{
					//se � cliente, abre janela de menu de cliente
					try 
					{	
						WindowClientMenu clientMenuWindow = new WindowClientMenu(mainCurrentState, mainDatabase);
						clientMenuWindow.setBlockOnOpen(true);
						clientMenuWindow.open();
						Display.getCurrent().dispose();			
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					
					if(mainCurrentState.getChosenAction() == "Sair")
						continue;
					
					switch(mainCurrentState.getChosenAction()) //op��o escolhida pelo cliente no menu
					{
						case "Pesquisar Ve�culo":
							try 
							{	
								WindowSearchVehicle searchVehicleWindow = new WindowSearchVehicle(mainCurrentState, mainDatabase);
								searchVehicleWindow.setBlockOnOpen(true);
								searchVehicleWindow.open();
								Display.getCurrent().dispose();			
							} 
							catch (Exception e) 
							{
								e.printStackTrace();
							}
				
					}
					
				//Volta para o in�cio do menu de cliente
				}while(mainCurrentState.getChosenAction() == "Voltar");
			}
			
		}while(mainCurrentState.getCurrentUser() != null);
	}
	

}
