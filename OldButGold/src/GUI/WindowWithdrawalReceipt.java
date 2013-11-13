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

public class WindowWithdrawalReceipt extends ApplicationWindow
{
	private Text txtWithdrawalDate;
	private Text txtWithdrawalTime;
	private Text txtMileage;
	private Text txtChargedValue;
	private Rent receiptRent;
	
	/**
	 * Create the application window.
	 * @param rentCurrentState 
	 */
	public WindowWithdrawalReceipt(Rent newRent) 
	{
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		this.receiptRent = newRent;		
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);
		
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
		txtWithdrawalDate.setBounds(138, 38, 80, 21);
		txtWithdrawalDate.setEnabled(false);
		txtWithdrawalDate.setEditable(false);
		String strDate = receiptRent.getWithdrawalDate().getTime().getDay() + 
						 "/" + receiptRent.getWithdrawalDate().getTime().getMonth() + 
						 "/" + receiptRent.getWithdrawalDate().getTime().getYear();						 
		txtWithdrawalDate.setText(strDate);
			
		txtWithdrawalTime = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtWithdrawalTime.setBounds(138, 68, 80, 21);
		txtWithdrawalTime.setEnabled(false);
		txtWithdrawalTime.setEditable(false);
		String strTime = receiptRent.getWithdrawalDate().getTime().getHours() + 
						 ":" + receiptRent.getWithdrawalDate().getTime().getMinutes() + 
						 ":" + receiptRent.getWithdrawalDate().getTime().getSeconds();
		txtWithdrawalTime.setText(strTime.toString());
		
		txtMileage = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtMileage.setBounds(138, 97, 80, 21);
		txtMileage.setEnabled(false);
		txtMileage.setEditable(false);
		txtMileage.setText("" + receiptRent.getRentVehicle().getMileage());
		
		String strDays = null;
		
		if(receiptRent.getRentTime() == 1)
			strDays = "dia";
		else
			strDays = "dias";
		
		Label lblChargedValue = new Label(grpWithdrawalReceipt, SWT.NONE);
		lblChargedValue.setBounds(36, 138, 100, 40);
		lblChargedValue.setText("Valor cobrado:  R$\n" +
				 receiptRent.getRentTime() + " " + strDays + " x " + "R$" + receiptRent.getRentVehicle().getDailyPrice()); 
		
		txtChargedValue = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtChargedValue.setEnabled(false);
		txtChargedValue.setEditable(false);
		txtChargedValue.setBounds(138, 135, 80, 21);
		txtChargedValue.setText("" + receiptRent.getWithdrawalPayment());
		
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
