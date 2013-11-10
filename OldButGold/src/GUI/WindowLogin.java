package GUI;

import javax.swing.JOptionPane;

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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

import person.Person;

import db.Database;
import control.CtrlUserLogin;
import control.CurrentState;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class WindowLogin extends ApplicationWindow
{
	private Text txtUserLogin;
	private Text txtUserPassword;
	private Database loginDatabase;
	private CurrentState currentState;
	private CtrlUserLogin userLoginCtrl;

	public WindowLogin(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
		currentState = mainCurrentState;
		loginDatabase = mainDatabase;
		userLoginCtrl = new CtrlUserLogin(mainDatabase);
	}

	@Override
	protected Control createContents(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.BORDER | SWT.NO_REDRAW_RESIZE);
		container.setLayout(null);
		
		Label lblNomeDeUsurio = new Label(container, SWT.NONE);
		lblNomeDeUsurio.setBounds(139, 86, 50, 20);
		lblNomeDeUsurio.setText("Usuário");
		
		txtUserLogin = new Text(container, SWT.BORDER);
		txtUserLogin.setBounds(211, 83, 78, 26);
		
		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setBounds(144, 126, 40, 20);
		lblPassword.setText("Senha");
		
		txtUserPassword = new Text(container, SWT.BORDER | SWT.PASSWORD);
		txtUserPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR)
				{
					login();
				}
			}
		});
		txtUserPassword.setBounds(211, 120, 78, 26);
		
		Button btnLogin = new Button(container, SWT.NONE);
		btnLogin.setBounds(134, 164, 55, 29);
		
		btnLogin.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				login();
			}			
			
		});
		
		btnLogin.setText("Logar");
		
		Button btnExit = new Button(container, SWT.NONE);
		btnExit.setBounds(223, 163, 55, 30);
		btnExit.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				//função de ação quando botão Sair é pressionado
				JOptionPane.showMessageDialog(null, "Obrigado, volte sempre!");
				currentState.setChosenAction("Sair");
				close();
			}
		});
		btnExit.setText("Sair");		
		
		return container;
	}
	
	//função de ação quando botão logar é pressionado
	private void login()
	{	
		CurrentState auxState = null;
		
		String username = txtUserLogin.getText(),
			   password = txtUserPassword.getText();
		
		if(username.equals(""))
			JOptionPane.showMessageDialog(null, "Um nome de usuário deve ser informado.");
		
		else if(password.equals(""))
			JOptionPane.showMessageDialog(null, "Uma senha deve ser informada.");
				
		else 
			auxState = userLoginCtrl.setCurrentState(username, password, currentState);
		
		if(auxState != null)
		{
			currentState = auxState;
			
			close();
		}
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
		return new Point(450, 329);
	}

	public CurrentState getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState(CurrentState currentState) 
	{
		this.currentState = currentState;
	}
}
