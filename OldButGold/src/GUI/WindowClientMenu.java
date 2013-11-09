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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import control.CurrentState;
import db.Database;

public class WindowClientMenu extends ApplicationWindow {

	private CurrentState currentState;
	@SuppressWarnings("unused")
	private Database clientMenuDatabase;
	/**
	 * Create the application window.
	 */
	public WindowClientMenu(CurrentState mainCurrentState, Database mainDatabase) {
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		clientMenuDatabase = mainDatabase;
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NONE);
		{
			Button btnCarRental = new Button(container, SWT.NONE);
			btnCarRental.addSelectionListener(new SelectionAdapter()
			{
				@Override
				//função de ação quando botão "Locação" é pressionado
				public void widgetSelected(SelectionEvent e) 
				{
					currentState.setChosenAction("Locação");
					close();
				}
			});
			btnCarRental.setBounds(150, 52, 138, 30);
			btnCarRental.setText("Locação");
		}
		
		{
			Button btnScheduling = new Button(container, SWT.NONE);
			btnScheduling.setBounds(150, 88, 138, 30);
			btnScheduling.setText("Agendamento");
		}
				
		{
			Button btnVerifyHistory = new Button(container, SWT.NONE);
			btnVerifyHistory.setBounds(150, 124, 138, 30);
			btnVerifyHistory.setText("Verificar Histórico");
		}		
		
		{
			Button btnExit = new Button(container, SWT.NONE);
			btnExit.addSelectionListener(new SelectionAdapter()
			{
				@Override
				//função de ação quando botão "Sair" é pressionado
				public void widgetSelected(SelectionEvent e)
				{
					currentState.setChosenAction("Sair");
					close();
				}
			});
			btnExit.setBounds(150, 160, 138, 30);
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
		//Image imgOldButGold = new Image(null, "C:/oldbutgold.png");
		//newShell.setImage(imgOldButGold);
		//newShell.setBackgroundImage(imgOldButGold);
		newShell.setBackgroundMode(SWT.INHERIT_DEFAULT);
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 313);
	}

}
