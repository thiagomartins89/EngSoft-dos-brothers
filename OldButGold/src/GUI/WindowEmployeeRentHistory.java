package GUI;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import person.Client;
import vehicle.Vehicle;
import control.CtrlRentHistory;
import control.CurrentState;
import db.Database;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class WindowEmployeeRentHistory extends ApplicationWindow {
	private Table tblRentHistory;

	private CurrentState rentCurrentState;
	private CtrlRentHistory ctrlRentHistory;
	private Database mainDatabase;

	/**
	 * Create the application window.
	 */
	public WindowEmployeeRentHistory(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		rentCurrentState = mainCurrentState;
		ctrlRentHistory = new CtrlRentHistory(mainCurrentState, mainDatabase);
		this.mainDatabase = mainDatabase; 
	}
	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		{
			Composite composite = new Composite(container, SWT.NONE);
			composite.setBounds(0, 0, 434, 291);
			{
				Group group = new Group(composite, SWT.NONE);
				final Combo comboClientList = new Combo(group, SWT.READ_ONLY);
				group.setText("Hist\u00F3rico de loca\u00E7\u00F5es");
				group.setBounds(10, 10, 414, 250);
				{
					tblRentHistory = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
					tblRentHistory.setLinesVisible(true);
					tblRentHistory.setHeaderVisible(true);
					tblRentHistory.setBounds(10, 60, 394, 179);
					{
						TableColumn tableColumn = new TableColumn(tblRentHistory, SWT.NONE);
						tableColumn.setWidth(100);
						tableColumn.setText("Retirada");
					}
					{
						TableColumn tableColumn = new TableColumn(tblRentHistory, SWT.NONE);
						tableColumn.setWidth(100);
						tableColumn.setText("Devolu\u00E7\u00E3o");
					}
					{
						TableColumn tableColumn = new TableColumn(tblRentHistory, SWT.NONE);
						tableColumn.setWidth(173);
						tableColumn.setText("Ve\u00EDculo");
					}
				}
				
				
				comboClientList.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent arg0) {
						// botão verificar:
						tblRentHistory.removeAll();
						fillRentHistoryTable(comboClientList.getText());
					}
				});
				comboClientList.setBounds(119, 24, 100, 23);
				comboClientList.select(0);
					
			    final ArrayList<Client> clientList = mainDatabase.getClientList();
			        
			    for(int i = 0; i < clientList.size(); i++)
			    {
			    	comboClientList.add(clientList.get(i).getId());
			    }
				
				Label lblClientUsername = new Label(group, SWT.NONE);
				lblClientUsername.setBounds(10, 27, 103, 15);
				lblClientUsername.setText("Cliente (username):");
			
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						int tableIndex = tblRentHistory.getSelectionIndex();
						if(tableIndex >= 0)
						{
							Vehicle vehicle = ctrlRentHistory.getRentVehicle(comboClientList.getText(), tableIndex);
							WindowVehicleDetails windowVehicleDetails = new WindowVehicleDetails(vehicle, false);
							windowVehicleDetails.open();
						}
					}
				});
				button.setText("Detalhes");
				button.setBounds(268, 266, 75, 25);
			}
			{
				Button button1 = new Button(composite, SWT.NONE);
				button1.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						rentCurrentState.setChosenAction("Voltar");
						close();
					}
				});
				button1.setText("Voltar");
				button1.setBounds(349, 266, 75, 25);
			}
		}

		return container;
	}
	
	/**
	 * Preenche a tabela com o histórico de locações do cliente.
	 */
	private void fillRentHistoryTable(String clientUsername)
	{
		if(mainDatabase.getUser(clientUsername) == null)
		{
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado.");
			return;
		}
		
		int rentListSize = ctrlRentHistory.getRentListSize(clientUsername);
		
		for(int i = 0; i < rentListSize; i++)
		{
			String rentWithdrawalDate = ctrlRentHistory.getRentWithdrawalDate(clientUsername, i);
			String rentReturnDate = ctrlRentHistory.getRentReturnDate(clientUsername, i);
			String rentVehicleModel = ctrlRentHistory.getRentVehicleModel(clientUsername, i);
			
			String[] rentData = { rentWithdrawalDate, rentReturnDate, rentVehicleModel };
			
			TableItem tableItem = new TableItem(tblRentHistory, SWT.NONE);
			tableItem.setText(rentData);
		}
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
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Old but Gold");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(440, 386);
	}
}
