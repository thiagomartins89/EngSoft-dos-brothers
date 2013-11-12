package GUI;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import control.CurrentState;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import db.Rent;

import person.Client;
import person.Person;

public class WindowWithdrawalReceipt extends ApplicationWindow {

	
	private CurrentState receiptCurrentState;
	private Text txtWithdrawalDate;
	private Text txtWithdrawalTime;
	private Text txtMileage;
	
	/**
	 * Create the application window.
	 * @param rentCurrentState 
	 */
	public WindowWithdrawalReceipt(CurrentState receiptCurrentState) {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		this.receiptCurrentState = receiptCurrentState;
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		Person currentPerson = receiptCurrentState.getCurrentUser();
		Client currentClient = (Client) currentPerson;
		ArrayList<Rent> rentList = currentClient.getRentList();
		Rent lastRent = rentList.get(rentList.size() -1);		
		
		Group grpWithdrawalReceipt = new Group(container, SWT.NONE);
		grpWithdrawalReceipt.setBounds(10, 12, 434, 215);
		grpWithdrawalReceipt.setText("Comprovante de retirada");
		grpWithdrawalReceipt.setLayout(null);
		
		Label lblWithdrawalDate = new Label(grpWithdrawalReceipt, SWT.NONE);
		lblWithdrawalDate.setBounds(36, 44, 100, 15);
		lblWithdrawalDate.setText("Data da retirada:");
		
		Label lblWithdrawalTime = new Label(grpWithdrawalReceipt, SWT.NONE);
		lblWithdrawalTime.setBounds(36, 74, 100, 15);
		lblWithdrawalTime.setText("Hora da retirada:");
		
		Label lblMileage = new Label(grpWithdrawalReceipt, SWT.NONE);
		lblMileage.setBounds(36, 103, 100, 15);
		lblMileage.setText("Quilometragem:");
		
		txtWithdrawalDate = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtWithdrawalDate.setBounds(138, 38, 126, 21);
		txtWithdrawalDate.setEnabled(false);
		txtWithdrawalDate.setEditable(false);
		txtWithdrawalDate.setText("" + lastRent.getWithdrawalDate().getTime().toString());
		//JOptionPane.showMessageDialog(null, lastRent.getWithdrawalDate().getTime().toString());
		
		txtWithdrawalTime = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtWithdrawalTime.setBounds(138, 68, 126, 21);
		txtWithdrawalTime.setEnabled(false);
		txtWithdrawalTime.setEditable(false);
		
		txtMileage = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtMileage.setBounds(138, 97, 126, 21);
		txtMileage.setEnabled(false);
		txtMileage.setEditable(false);
		
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
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

}