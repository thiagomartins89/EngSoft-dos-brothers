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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;

import control.CurrentState;
import db.Database;

public class WindowSubscribeClient extends ApplicationWindow
{
	private Text txtUserName;
	private Text txtUserCPF;
	private Text txtUserId;
	private Text txtUserPassword;
	private CurrentState currentState;
	private Database subscribeClientDatabase;
	
	/**
	 * Create the application window.
	 */
	public WindowSubscribeClient(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		subscribeClientDatabase = mainDatabase;
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblUserName = new Label(container, SWT.NONE);
		lblUserName.setText("Nome");
		
		txtUserName = new Text(container, SWT.BORDER);
		new Label(container, SWT.NONE);
		
		Label lblUserCPF = new Label(container, SWT.NONE);
		lblUserCPF.setText("CPF");
		
		txtUserCPF = new Text(container, SWT.BORDER);
		new Label(container, SWT.NONE);
		
		Label lblUserId = new Label(container, SWT.NONE);
		lblUserId.setText("Usuário");
		
		txtUserId = new Text(container, SWT.BORDER);
		new Label(container, SWT.NONE);
		
		Label lblUserPassword = new Label(container, SWT.NONE);
		lblUserPassword.setText("Senha");
		
		txtUserPassword = new Text(container, SWT.BORDER);

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
			WindowSubscribeClient window = new WindowSubscribeClient();
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
		return new Point(448, 299);
	}
}
