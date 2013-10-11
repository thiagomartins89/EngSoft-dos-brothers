package GUI;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
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
		
		comboSearchOptions.addModifyListener(new ModifyListener() 
		{
			//função que verifica a opção escolhida pelo usuário
			//e coloca na outra combo as opções disponíveis do tipo escolhido.
			public void modifyText(ModifyEvent arg0)
			{
				comboSearchOptionsResults.removeAll(); //limpa os resultados anteriores
				
				int optionIndex = comboSearchOptions.getSelectionIndex();
				String optionName = comboSearchOptions.getItem(optionIndex);
				ArrayList<Vehicle> vehicleList = searchVehicleDatabase.getVehicleList();
				
				switch(optionName)
				{
					case "Categoria":	//Coloca todas as categorias disponíveis para o usuário selecionar uma.
						
						//Lista feita para manter o controle do que já foi adicionado à comboBox.
						ArrayList<String> categoryList = new ArrayList<String>(); 
						
						for(int i=0; i < vehicleList.size(); i++)
						{
							String vehicleCategory = vehicleList.get(i).getCategory();
							
							if(categoryList.isEmpty())
							{
								categoryList.add(vehicleCategory);
								comboSearchOptionsResults.add(categoryList.get(i));
							}		
						
							
							else if(!categoryList.contains(vehicleCategory))
							{
								categoryList.add(vehicleCategory);
								comboSearchOptionsResults.add(categoryList.get(i));
							}
													
						}						
						break; //fim do Categoria
						
					case "Potência do motor":
						
						//Lista feita para manter o controle do que já foi adicionado à comboBox.
						ArrayList<String> enginePowerList = new ArrayList<String>();
						for(int i=0; i < vehicleList.size(); i++)
						{
							String vehicleEnginePower = vehicleList.get(i).getEnginePower();
							
							if(enginePowerList.isEmpty())
							{
								enginePowerList.add(vehicleEnginePower);								
								comboSearchOptionsResults.add(vehicleEnginePower);
							}								
							else if(!enginePowerList.contains(vehicleEnginePower))
							{
								enginePowerList.add(vehicleEnginePower);
								comboSearchOptionsResults.add(vehicleEnginePower);
							}
													
						}						
						break; //fim do Potência
				}
			}
		});
		comboSearchOptions.setBounds(119, 7, 192, 28);
		
		//Adicionando as opções à ComboBox
		comboSearchOptions.add("Categoria");	
		comboSearchOptions.add("Potência do motor");	
		comboSearchOptions.add("Ano");	
		comboSearchOptions.add("Comprimento máximo");
		comboSearchOptions.add("Marca");
		comboSearchOptions.add("Modelo");		
		comboSearchOptions.select(0); //Coloca a primeira opção como default
		
		Label lblSearchOptions = new Label(container, SWT.NONE);
		lblSearchOptions.setBounds(10, 10, 103, 25);
		lblSearchOptions.setText("Pesquisar por :");
		
		
		comboSearchOptionsResults.setBounds(119, 55, 192, 28);
		
		List list = new List(container, SWT.BORDER);
		list.setBounds(338, 10, 134, 104);		
		
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
		btnReturn.setBounds(332, 155, 75, 25);
		btnReturn.setText("Voltar");


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
	
	/*
	public static void main(String args[]) {
		try {
			WindowSearchVehicle window = new WindowSearchVehicle();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("New Application");
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
