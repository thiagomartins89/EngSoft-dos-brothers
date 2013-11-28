package GUI;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import control.CurrentState;
import db.Database;

public class WindowEmployeeMenu extends ApplicationWindow
{

	private CurrentState currentState;
	@SuppressWarnings("unused")
	private Database employeeMenuDatabase;
	private Button btnRemoveVehicle;
	private Button btnExit;
	/**
	 * Create the application window.
	 */
	public WindowEmployeeMenu(CurrentState mainCurrentState, Database mainDatabase)
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		employeeMenuDatabase = mainDatabase;
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(null);
		
		Group grpEmployeeMenu = new Group(container, SWT.NONE);
		grpEmployeeMenu.setBounds(10, 0, 434, 191);
		grpEmployeeMenu.setText("Selecione uma opção");
		grpEmployeeMenu.setLayout(null);
		
		Button btnSubscribeClient = new Button(grpEmployeeMenu, SWT.NONE);
		btnSubscribeClient.setBounds(10, 32, 170, 28);
		btnSubscribeClient.addSelectionListener(new SelectionAdapter()
		{
			@Override
			//função de ação quando botão "Cadastrar Cliente" é pressionado
			public void widgetSelected(SelectionEvent e) 
			{
				currentState.setChosenAction("Cadastrar Cliente");
				close();
				
			}
		});
		btnSubscribeClient.setText("Cadastrar Cliente");
		
		Button btnAddVehicle = new Button(grpEmployeeMenu, SWT.NONE);
		btnAddVehicle.setBounds(10, 112, 170, 28);
		btnAddVehicle.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			//função de ação quando botão "Adicionar Veículo" é pressionado
			public void widgetSelected(SelectionEvent e)
			{
				currentState.setChosenAction("Adicionar Veículo");
				close();
			}
		});
		btnAddVehicle.setText("Adicionar Veículo");
		
		{
			btnRemoveVehicle = new Button(grpEmployeeMenu, SWT.NONE);
			btnRemoveVehicle.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) 
				{
					currentState.setChosenAction("Remover Veículo");
					close();		
				}
			});
			btnRemoveVehicle.setBounds(204, 112, 170, 28);
			btnRemoveVehicle.setText("Remover Veículo");
		}
		
		{
			btnExit = new Button(grpEmployeeMenu, SWT.NONE);
			btnExit.addSelectionListener(new SelectionAdapter() {
				@Override
				//função de ação quando botão "Sair" é pressionado
				public void widgetSelected(SelectionEvent e) {
					currentState.setChosenAction("Sair");
					close();
				}
			});
			btnExit.setBounds(204, 153, 170, 28);
			btnExit.setText("Sair");
		}
		
		Button btnRent = new Button(grpEmployeeMenu, SWT.NONE);
		btnRent.addSelectionListener(new SelectionAdapter()
		{
			@Override
			//função de ação quando botão "Locação" é pressionado
			public void widgetSelected(SelectionEvent e) 
			{
				currentState.setChosenAction("Locação");
				close();
			}
		});
		btnRent.setBounds(10, 72, 170, 28);
		btnRent.setText("Locação");
		
		Button btnReturn = new Button(grpEmployeeMenu, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			//função de ação quando botão "Devolução" é pressionado
			public void widgetSelected(SelectionEvent e) 
			{
				currentState.setChosenAction("Devolução");
				close();				
			}
		});
		btnReturn.setBounds(204, 72, 170, 28);
		btnReturn.setText("Devolução");
		
		Button btnNewButton = new Button(grpEmployeeMenu, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				
			}
		});
		btnNewButton.setBounds(204, 32, 170, 28);
		btnNewButton.setText("Bloquear/Desbloquear Cliente");
		
		Button btnVerifyHistory = new Button(grpEmployeeMenu, SWT.NONE);
		btnVerifyHistory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//verificar histórico
				currentState.setChosenAction("Verificar Histórico");
				close();
			}
		});
		btnVerifyHistory.setText("Verificar Histórico");
		btnVerifyHistory.setBounds(10, 153, 170, 28);
		grpEmployeeMenu.setTabList(new Control[]{btnSubscribeClient, btnRent, btnAddVehicle, btnNewButton, btnReturn, btnRemoveVehicle, btnExit});

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
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("Old but Gold");
		//Image imgOldButGold = new Image(null, "C:/oldbutgold.png");
		//newShell.setImage(imgOldButGold);
		//newShell.setBackgroundImage(imgOldButGold);
		newShell.setBackgroundMode(SWT.INHERIT_DEFAULT);
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize()
	{
		return new Point(450, 256);
	}
}
