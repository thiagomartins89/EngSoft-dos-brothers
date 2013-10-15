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
			//Início das funções do funcionário.
			//Se ficar muito grande, considerar colocar em outra classe.
			
			if(userClass.getName() == "person.Employee")	//não sei como fazer retornar apenas "Employee",
															//mas isso já quebra o galho
			{
				do
				{
					//se é funcionário, abre janela de menu de funcionário
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
					
					switch(mainCurrentState.getChosenAction()) //opção escolhida pelo funcionário no menu
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
							
						//abre a janela de cadastro de veículo
						case "Adicionar Veículo":
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
					
				//Volta para o início do menu de funcionário caso o veículo seja
				//adicionado com sucesso, ou se o funcionário pressionar "voltar".
				}while(mainCurrentState.getChosenAction() == "Voltar" 
				  || mainCurrentState.getChosenAction() == "Adicionar Veículo");
			}
				
			
			//----------------------------------------------------------------------------
			//Fim das funções do funcionário
			
			//Início das funções de cliente
			else
			{
				do
				{
					//se é cliente, abre janela de menu de cliente
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
					
					switch(mainCurrentState.getChosenAction()) //opção escolhida pelo cliente no menu
					{
						case "Pesquisar Veículo":
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
					
				//Volta para o início do menu de cliente
				}while(mainCurrentState.getChosenAction() == "Voltar");
			}
			
		}while(mainCurrentState.getCurrentUser() != null);
	}
	

}
