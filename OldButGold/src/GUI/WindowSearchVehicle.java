package GUI;

import java.util.ArrayList;
import java.util.Collections;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;

import control.CurrentState;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

import vehicle.Vehicle;

import db.Database;

public class WindowSearchVehicle extends ApplicationWindow {

	private CurrentState currentState;
	private Database searchVehicleDatabase;

	/**
	 * Create the application window.
	 */
	public WindowSearchVehicle(CurrentState mainCurrentState, Database mainDatabase) {
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		searchVehicleDatabase = mainDatabase;
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);
		
		final Combo comboSearchOptions = new Combo(container, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
		final Combo comboSearchOptionsResults = new Combo(container, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
		final List listSearchResults = new List(container, SWT.BORDER);
		listSearchResults.setBounds(291, 39, 148, 104);	
		
		final Label lblUnity = new Label(container, SWT.NONE);
		lblUnity.setBounds(300, 54, 24, 15);
		
		
		//função que executa o que acontece quando o usuário
		//seleciona as opções da primeira combo box.
		comboSearchOptions.addModifyListener(new ModifyListener() 
		{
			//função que verifica a opção escolhida pelo usuário e coloca na outra
			//combo as opções disponíveis do tipo escolhido, de forma ordenada.
			//Ex: se tiver apenas uma moto e um carro e o usuário selecionar categoria,
			//a combo de resultados apresentará A e B.
			public void modifyText(ModifyEvent arg0)
			{
				lblUnity.setText("");
				comboSearchOptionsResults.removeAll(); //limpa os resultados anteriores
				listSearchResults.removeAll();
				
				final ArrayList<Vehicle> vehicleList = searchVehicleDatabase.getVehicleList();
				int optionIndex = comboSearchOptions.getSelectionIndex();
				String optionName = comboSearchOptions.getItem(optionIndex);
								
				switch(optionName)
				{
					case "Categoria":
						//lista auxiliar para ordenar os itens
						ArrayList<String> sortCategoryList = new ArrayList<String>();
						
						for(int i=0; i < vehicleList.size(); i++)
						{
							if(!sortCategoryList.contains(vehicleList.get(i).getCategory()))
									sortCategoryList.add(vehicleList.get(i).getCategory());
						}
						
						Collections.sort(sortCategoryList);
						
						//insere no combo a lista após ser ordenada
						for(int i=0; i < sortCategoryList.size(); i++)
						{
								String vehicleCategory = sortCategoryList.get(i);
								
								if(comboSearchOptionsResults.indexOf(vehicleCategory) == -1)
									comboSearchOptionsResults.add(vehicleCategory);
													
						}
						break; //fim do Categoria
						
					case "Potência do motor":
						
						ArrayList<Integer> sortEnginePowerList = new ArrayList<Integer>();
						
						for(int i=0; i < vehicleList.size(); i++)
						{
							if(!sortEnginePowerList.contains(vehicleList.get(i).getEnginePower()))
								sortEnginePowerList.add(vehicleList.get(i).getEnginePower());
						}
						
						Collections.sort(sortEnginePowerList);
						
						for(int i=0; i < sortEnginePowerList.size(); i++)
						{
							String strEnginePower = sortEnginePowerList.get(i).toString();
							
							if(comboSearchOptionsResults.indexOf(strEnginePower) == -1)
								comboSearchOptionsResults.add(strEnginePower);
							
						}		
						lblUnity.setText("Cv");
						
						break; //fim do Potência
						
					case "Ano":
						ArrayList<Integer> sortYearList = new ArrayList<Integer>();
						
						for(int i=0; i < vehicleList.size(); i++)
						{
							if(!sortYearList.contains(vehicleList.get(i).getManufacturingDate()))
								sortYearList.add(vehicleList.get(i).getManufacturingDate());
						}						
						Collections.sort(sortYearList);
						
						for(int i=0; i < sortYearList.size(); i++)
						{
							String strVehicleYear = sortYearList.get(i).toString();
							if(comboSearchOptionsResults.indexOf(strVehicleYear) == -1)
								comboSearchOptionsResults.add(strVehicleYear);
							
						}				
						break;
						
					case "Comprimento máximo":
						ArrayList<Double> sortMaxLengthList = new ArrayList<Double>();
						
						for(int i=0; i < vehicleList.size(); i++)
						{
							if(!sortMaxLengthList.contains(vehicleList.get(i).getLength()))
								sortMaxLengthList.add(vehicleList.get(i).getLength());
						}						
						Collections.sort(sortMaxLengthList);
						
						for(int i=0; i < vehicleList.size(); i++)
						{
							String strMaxLength = sortMaxLengthList.get(i).toString();
							if(comboSearchOptionsResults.indexOf(strMaxLength) == -1)
								comboSearchOptionsResults.add(strMaxLength);
							
						}	
						lblUnity.setText("m");
						break;
						
					case "Marca":
						ArrayList<String> sortBrandList = new ArrayList<String>();
						
						for(int i=0; i < vehicleList.size(); i++)
						{
							if(!sortBrandList.contains(vehicleList.get(i).getBrand()))
								sortBrandList.add(vehicleList.get(i).getBrand());
						}						
						Collections.sort(sortBrandList);
						
						for(int i=0; i < sortBrandList.size(); i++)
						{
							String vehicleBrand = sortBrandList.get(i);
								
							if(comboSearchOptionsResults.indexOf(vehicleBrand) == -1)
								comboSearchOptionsResults.add(vehicleBrand);
													
						}				
						break;
						
					case "Modelo":
						ArrayList<String> sortModelList = new ArrayList<String>();
						
						for(int i=0; i < vehicleList.size(); i++)
						{
							if(!sortModelList.contains(vehicleList.get(i).getModel()))
								sortModelList.add(vehicleList.get(i).getModel());
						}						
						Collections.sort(sortModelList);
						
						for(int i=0; i < sortModelList.size(); i++)
						{
								String vehicleModel = sortModelList.get(i);
								
								if(comboSearchOptionsResults.indexOf(vehicleModel) == -1)
									comboSearchOptionsResults.add(vehicleModel);
													
						}		
						break;
				}
			}
		});
		comboSearchOptions.setBounds(87, 11, 192, 28);
		
		//Adicionando as opções à ComboBox
		comboSearchOptions.add("Categoria");	
		comboSearchOptions.add("Potência do motor");	
		comboSearchOptions.add("Ano");	
		comboSearchOptions.add("Comprimento máximo");
		comboSearchOptions.add("Marca");
		comboSearchOptions.add("Modelo");		
		comboSearchOptions.select(0); //Coloca a primeira opção como default		
		
		
		comboSearchOptionsResults.addModifyListener(new ModifyListener() 
		{
			//função que preenche a lista com as opções de veículos
			//disponíveis do parâmetro solicitado pelo usuário.
			public void modifyText(ModifyEvent arg0)
			{
				if(comboSearchOptionsResults.getItemCount() == 0)
					return;
				
				//limpa a lista para inserir os novos resultados				
				listSearchResults.removeAll();
				
				int optionIndex = comboSearchOptions.getSelectionIndex();				
				String chosenOption = comboSearchOptions.getItem(optionIndex);
				
				final ArrayList<Vehicle> vehicleList = searchVehicleDatabase.getVehicleList();
				int optionResultIndex = comboSearchOptionsResults.getSelectionIndex();
				String chosenOptionResult = comboSearchOptionsResults.getItem(optionResultIndex);
				
				switch(chosenOption)
				{
					case "Categoria":						
						for(int i = 0; i < vehicleList.size(); i++)
						{
							if(vehicleList.get(i).getCategory().equals(chosenOptionResult))
							{
								listSearchResults.add(vehicleList.get(i).getModel());
							}
						}
										
						break; //fim do Categoria
						
					case "Potência do motor":
						for(int i = 0; i < vehicleList.size(); i++)
						{
							int vehicleEnginePower = vehicleList.get(i).getEnginePower();
							String strEnginePower = ("" + vehicleEnginePower);
							if(strEnginePower.equals(chosenOptionResult))
							{
								listSearchResults.add(vehicleList.get(i).getModel());
							}
							
						}
						break; //fim do Potência
						
					case "Ano":
						for(int i = 0; i < vehicleList.size(); i++)
						{
							int vehicleManufacturingYear = vehicleList.get(i).getManufacturingDate();
							String strManufacturingYear = ("" + vehicleManufacturingYear);
							if(strManufacturingYear.equals(chosenOptionResult))
							{
								listSearchResults.add(vehicleList.get(i).getModel());
							}
						}
						break;
						
					case "Comprimento máximo":
						for(int i = 0; i < vehicleList.size(); i++)
						{
							double vehicleMaxLength = vehicleList.get(i).getLength();
							String strMaxLength = ("" + vehicleMaxLength);
							if(strMaxLength.equals(chosenOptionResult))
							{
								listSearchResults.add(vehicleList.get(i).getModel());
							}
						}
						break;
						
					case "Marca":
						for(int i = 0; i < vehicleList.size(); i++)
						{
							String vehicleBrand = vehicleList.get(i).getBrand();
							if(vehicleBrand.equals(chosenOptionResult))
							{
								listSearchResults.add(vehicleList.get(i).getModel());
							}
						}			
						break;
						
					case "Modelo":
						for(int i = 0; i < vehicleList.size(); i++)
						{
							String vehicleModel = vehicleList.get(i).getModel();
							if(vehicleModel.equals(chosenOptionResult))
							{
								listSearchResults.add(vehicleModel);
							}
						}	
						break;
					}
					 
			}
		});
		
		Label lblSearchOptions = new Label(container, SWT.NONE);
		lblSearchOptions.setBounds(4, 14, 77, 25);
		lblSearchOptions.setText("Pesquisar por :");
		
		
		comboSearchOptionsResults.setBounds(87, 40, 192, 28);	
		
		Button btnReturn = new Button(container, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			//função de ação quando botão "Voltar" é pressionado
			public void widgetSelected(SelectionEvent e) 
			{
				currentState.setChosenAction("Voltar");
				close();
			}
		});
		btnReturn.setBounds(285, 172, 75, 25);
		btnReturn.setText("Voltar");
		
		Label lblAvailableVehicles = new Label(container, SWT.NONE);
		lblAvailableVehicles.setBounds(300, 14, 126, 15);
		lblAvailableVehicles.setText("Veículos disponíveis:");


		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions()
	{
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager()
	{
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style)
	{
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() 
	{
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("Old but Gold");
		Image imgOldButGold = new Image(null, "images/oldbutgold.png");
		newShell.setImage(imgOldButGold);
		newShell.setBackgroundImage(imgOldButGold);
		newShell.setBackgroundMode(SWT.INHERIT_DEFAULT);
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() 
	{
		return new Point(490, 381);
	}
}
