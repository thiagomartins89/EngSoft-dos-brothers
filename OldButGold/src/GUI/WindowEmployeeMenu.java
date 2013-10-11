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

public class WindowEmployeeMenu extends ApplicationWindow {

	private CurrentState currentState;
	/**
	 * Create the application window.
	 */
	public WindowEmployeeMenu(CurrentState mainCurrentState) {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		{
			Button btnSubscribeClient = new Button(container, SWT.NONE);
			btnSubscribeClient.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					//função de ação quando botão Cadastrar é pressionado
					currentState.setChosenAction("Cadastrar Cliente");
					close();
					
				}
			});
			btnSubscribeClient.setBounds(124, 70, 192, 30);
			btnSubscribeClient.setText("Cadastrar Cliente");
		}
		
		Button btnAddRemoveVehicle = new Button(container, SWT.NONE);
		btnAddRemoveVehicle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnAddRemoveVehicle.setBounds(124, 107, 192, 30);
		btnAddRemoveVehicle.setText("Adicionar/Remover Veículo");

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
	
	/*
	public static void main(String args[]) {
		try {
			WindowEmployeeMenu employeeMenuWindow = new WindowEmployeeMenu();
			employeeMenuWindow.setBlockOnOpen(true);
			employeeMenuWindow.open();
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
