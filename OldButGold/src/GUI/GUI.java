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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

import person.Person;

import db.Database;
import org.eclipse.swt.layout.GridLayout;

public class GUI extends ApplicationWindow {
	private Text guiLogin;
	private Text guiPassword;
	private Database db;

	public GUI() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
		db = new Database();
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite fundo = new Composite(parent, SWT.NONE);
		fundo.setLayout(new GridLayout(2, false));
		
		Label lblNomeDeUsurio = new Label(fundo, SWT.NONE);
		lblNomeDeUsurio.setText("Login");
		
		guiLogin = new Text(fundo, SWT.BORDER);
		
		Label lblSenha = new Label(fundo, SWT.NONE);
		lblSenha.setText("Senha");
		
		guiPassword = new Text(fundo, SWT.BORDER | SWT.PASSWORD);
		new Label(fundo, SWT.NONE);
		
		Button btnLogar = new Button(fundo, SWT.NONE);
		
		btnLogar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//fun��o de a��o quando bot�o logar � pressionado
				Person user = db.getUser(guiLogin.getText());
				
				if(guiLogin.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Um nome de usu�rio deve ser informado.");
				else if(guiPassword.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Uma senha deve ser informada.");
				else if(user != null)
				{
					if(user.getPassword().equals(guiPassword.getText()))
						JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

					else
						JOptionPane.showMessageDialog(null, "Senha incorreta!");
				}
				else
					JOptionPane.showMessageDialog(null, "Usuario n�o existe!");
			}
		});
		btnLogar.setText("Logar");

		return fundo;
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
	public static void main(String args[]) {
		try {
			GUI window = new GUI();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Old but Gold");
		Image imgOldButGold = new Image(null, "imgOldButGold.jpg");
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