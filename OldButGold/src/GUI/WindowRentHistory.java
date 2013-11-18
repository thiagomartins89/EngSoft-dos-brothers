package GUI;

import java.util.ArrayList;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import control.CtrlRentHistory;
import control.CurrentState;
import db.Database;

public class WindowRentHistory extends ApplicationWindow
{
	private Table tblRentHistory;
	
	private CurrentState rentCurrentState;
	//private Database clientVehicleRentDatabase;
	private CtrlRentHistory ctrlRentHistory;
	
	private ArrayList<String> rentHistoryList;

	/**
	 * Create the application window.
	 */
	public WindowRentHistory(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		rentCurrentState = mainCurrentState;
		//clientVehicleRentDatabase = mainDatabase;
		//ctrlRentHistory = new CtrlRentHistory(clientVehicleRentDatabase);
		ctrlRentHistory = new CtrlRentHistory();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NONE);
		
		Group grpRentHistory = new Group(container, SWT.NONE);
		grpRentHistory.setText("Histórico de locações");
		grpRentHistory.setBounds(10, 10, 414, 221);
		
		tblRentHistory = new Table(grpRentHistory, SWT.BORDER | SWT.FULL_SELECTION);
		tblRentHistory.setBounds(10, 32, 394, 179);
		tblRentHistory.setHeaderVisible(true);
		tblRentHistory.setLinesVisible(true);
		
		TableColumn tblclmnWithdrawalDate = new TableColumn(tblRentHistory, SWT.NONE);
		tblclmnWithdrawalDate.setWidth(100);
		tblclmnWithdrawalDate.setText("Retirada");
		
		TableColumn tblclmnReturnDate = new TableColumn(tblRentHistory, SWT.NONE);
		tblclmnReturnDate.setWidth(100);
		tblclmnReturnDate.setText("Devolução");
		
		TableColumn tblclmnVehicle = new TableColumn(tblRentHistory, SWT.NONE);
		tblclmnVehicle.setWidth(173);
		tblclmnVehicle.setText("Veículo");
		
		Button btnDetails = new Button(container, SWT.NONE);
		btnDetails.setBounds(268, 237, 75, 25);
		btnDetails.setText("Detalhes");
		
		Button btnReturn = new Button(container, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rentCurrentState.setChosenAction("Voltar");
				close();
			}
		});
		btnReturn.setBounds(349, 237, 75, 25);
		btnReturn.setText("Voltar");
		
		fillRentHistoryTable();

		return container;
	}
	
	/**
	 * Preenche a tabela com o histórico de locações do cliente.
	 */
	private void fillRentHistoryTable()
	{
		//gambiarras de teste
/*		rentHistoryList = ctrlRentHistory.getRentHistoryList(rentCurrentState);
		
		for(int i = 0; i < rentHistoryList.size() / 3; i = i + 3)
		{
			String[] text = { rentHistoryList.get(i), rentHistoryList.get(i+1), rentHistoryList.get(i+2) };
			TableItem tableItem = new TableItem(tblRentHistory, SWT.NONE);
			tableItem.setText(text);
		}*/
		
		for(int i = 0; i < 3; i++)
		{
			String[] text = { "Isso é", "só um", "TESTE" };
			TableItem tableItem = new TableItem(tblRentHistory, SWT.NONE);
			tableItem.setText(text);
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
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("Old but Gold");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize()
	{
		return new Point(450, 373);
	}
}
