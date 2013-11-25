package GUI;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;

import control.CtrlSubscribeClient;
import control.CurrentState;
import db.Database;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridData;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;

public class WindowSubscribeClient extends ApplicationWindow
{
	private DataBindingContext m_bindingContext;
	private Text txtUserName;
	private Text txtUserCPF;
	private Text textUserBirth;
	private Text textUserCNH;
	private Text txtUserId;
	private Text txtUserPassword1;
	
	private CurrentState currentState;
	private Database subscribeClientDatabase;
	private CtrlSubscribeClient ctrlSubscribeClient;
	private Text txtUserPassword2;
	
	
	/**
	 * Create the application window.
	 */
	public WindowSubscribeClient(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		subscribeClientDatabase = mainDatabase;
		ctrlSubscribeClient = new CtrlSubscribeClient(mainDatabase);
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		
		Group grpCadastroDeCliente = new Group(container, SWT.NONE);
		GridData gd_grpCadastroDeCliente = new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 5);
		gd_grpCadastroDeCliente.widthHint = 428;
		gd_grpCadastroDeCliente.heightHint = 178;
		grpCadastroDeCliente.setLayoutData(gd_grpCadastroDeCliente);
		grpCadastroDeCliente.setText("Cadastro de Cliente");
		
		Button btnReturn = new Button(grpCadastroDeCliente, SWT.NONE);
		btnReturn.setBounds(351, 157, 43, 25);
		btnReturn.addSelectionListener(new SelectionAdapter()
		{
			@Override
			// botão "Voltar"
			public void widgetSelected(SelectionEvent e)
			{
				currentState.setChosenAction("Voltar");
				close();
			}
		});
		btnReturn.setText("Voltar");
		
		Group grpInformaesPessoais = new Group(grpCadastroDeCliente, SWT.NONE);
		grpInformaesPessoais.setText("Informa\u00E7\u00F5es Pessoais");
		grpInformaesPessoais.setBounds(10, 27, 235, 155);
		
		txtUserName = new Text(grpInformaesPessoais, SWT.BORDER);
		txtUserName.setBounds(49, 29, 176, 21);
		
		Label lblUserName = new Label(grpInformaesPessoais, SWT.NONE);
		lblUserName.setBounds(10, 32, 33, 15);
		lblUserName.setText("Nome");
		
		Label lblUserCPF = new Label(grpInformaesPessoais, SWT.NONE);
		lblUserCPF.setBounds(10, 59, 21, 15);
		lblUserCPF.setText("CPF");
		
		txtUserCPF = new Text(grpInformaesPessoais, SWT.BORDER);
		txtUserCPF.setBounds(128, 56, 97, 21);
		
		textUserBirth = new Text(grpInformaesPessoais, SWT.BORDER);
		textUserBirth.setBounds(128, 110, 97, 21);
		
		Label lblBirthDate = new Label(grpInformaesPessoais, SWT.NONE);
		lblBirthDate.setBounds(10, 113, 112, 15);
		lblBirthDate.setText("Data de Nascimento");
		
		textUserCNH = new Text(grpInformaesPessoais, SWT.BORDER);
		textUserCNH.setBounds(128, 83, 97, 21);
		
		Label lblUserCNH = new Label(grpInformaesPessoais, SWT.NONE);
		lblUserCNH.setBounds(10, 86, 55, 15);
		lblUserCNH.setText("CNH");
		grpInformaesPessoais.setTabList(new Control[]{txtUserName, txtUserCPF, textUserCNH, textUserBirth});
		
		Group grpInformaesCadastrais = new Group(grpCadastroDeCliente, SWT.NONE);
		grpInformaesCadastrais.setText("Informa\u00E7\u00F5es Cadastrais");
		grpInformaesCadastrais.setBounds(251, 27, 173, 124);
		
		txtUserId = new Text(grpInformaesCadastrais, SWT.BORDER);
		txtUserId.setBounds(87, 26, 76, 21);
		
		Label lblUserId = new Label(grpInformaesCadastrais, SWT.NONE);
		lblUserId.setBounds(33, 29, 40, 15);
		lblUserId.setText("Usuário");
		
		Label lblUserPassword = new Label(grpInformaesCadastrais, SWT.NONE);
		lblUserPassword.setBounds(41, 56, 32, 15);
		lblUserPassword.setText("Senha");
		
		txtUserPassword1 = new Text(grpInformaesCadastrais, SWT.BORDER | SWT.PASSWORD);
		txtUserPassword1.setBounds(87, 53, 76, 21);
		
		txtUserPassword2 = new Text(grpInformaesCadastrais, SWT.BORDER | SWT.PASSWORD);
		txtUserPassword2.setBounds(87, 82, 76, 21);
		
		Label lblConfirmarSenha = new Label(grpInformaesCadastrais, SWT.NONE);
		lblConfirmarSenha.setBounds(5, 85, 76, 15);
		lblConfirmarSenha.setText("Repetir Senha");
		
		Button btnSubscribe = new Button(grpCadastroDeCliente, SWT.NONE);
		btnSubscribe.addSelectionListener(new SelectionAdapter()
		{
			@Override
			// Botão Cadastrar
			public void widgetSelected(SelectionEvent e)
			{				
				String returnMessage = ctrlSubscribeClient.subscribeClient(
						txtUserName.getText(),
						txtUserCPF.getText(),
						textUserCNH.getText(),
						textUserBirth.getText(),
						txtUserId.getText(),
						txtUserPassword1.getText(),
						txtUserPassword2.getText());

				JOptionPane.showMessageDialog(null, returnMessage);
			}
		});
		btnSubscribe.setBounds(270, 157, 75, 25);
		btnSubscribe.setText("Cadastrar");
		grpCadastroDeCliente.setTabList(new Control[]{grpInformaesPessoais, grpInformaesCadastrais, btnSubscribe, btnReturn});

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
		return new Point(448, 303);
	}
}
