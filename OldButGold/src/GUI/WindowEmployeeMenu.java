package GUI;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import control.CurrentState;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import db.Database;

public class WindowEmployeeMenu extends ApplicationWindow
{

	private CurrentState currentState;
	private Database employeeMenuDatabase;
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
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(null);
		{
			Button btnSubscribeClient = new Button(container, SWT.NONE);
			btnSubscribeClient.setBounds(101, 28, 196, 30);
			btnSubscribeClient.addSelectionListener(new SelectionAdapter() {
				@Override
				//função de ação quando botão "Cadastrar Cliente" é pressionado
				public void widgetSelected(SelectionEvent e) 
				{
					currentState.setChosenAction("Cadastrar Cliente");
					close();
					
				}
			});
			btnSubscribeClient.setText("Cadastrar Cliente");
		}
		
		Button btnAddVehicle = new Button(container, SWT.NONE);
		btnAddVehicle.setBounds(101, 64, 196, 30);
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
			Button btnRemoveVehicle = new Button(container, SWT.NONE);
			btnRemoveVehicle.setBounds(101, 100, 196, 30);
			btnRemoveVehicle.setText("Remover Veículo");
		}
		
		{
			Button btnExit = new Button(container, SWT.NONE);
			btnExit.addSelectionListener(new SelectionAdapter() {
				@Override
				//função de ação quando botão "Sair" é pressionado
				public void widgetSelected(SelectionEvent e) {
					currentState.setChosenAction("Sair");
					close();
				}
			});
			btnExit.setBounds(103, 138, 194, 30);
			btnExit.setText("Sair");
		}


		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
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
	protected void configureShell(Shell newShell) {
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
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
