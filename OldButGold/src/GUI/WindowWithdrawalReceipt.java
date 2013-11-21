package GUI;

import java.util.Calendar;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import db.Rent;

public class WindowWithdrawalReceipt extends ApplicationWindow
{
	private Text txtWithdrawalDate;
	private Text txtWithdrawalTime;
	private Text txtMileage;
	private Text txtChargedValue;
	private Rent receiptRent;
	private Text txtCode;
	private Text txtReturnDate;
	
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
		lblWithdrawalDate.setBounds(10, 41, 100, 21);
		lblWithdrawalDate.setText("Data da retirada:");
		
		Label lblWithdrawalTime = new Label(grpWithdrawalReceipt, SWT.NONE);
		lblWithdrawalTime.setBounds(10, 68, 100, 15);
		lblWithdrawalTime.setText("Hora da retirada:");
		
		Label lblMileage = new Label(grpWithdrawalReceipt, SWT.NONE);
		lblMileage.setBounds(10, 100, 100, 15);
		lblMileage.setText("Quilometragem:");
		
		txtWithdrawalDate = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtWithdrawalDate.setBounds(116, 38, 80, 21);
		txtWithdrawalDate.setEnabled(false);
		txtWithdrawalDate.setEditable(false);
		String strDate = receiptRent.getWithdrawalDate().get(Calendar.DAY_OF_MONTH) + 
						 "/" + (receiptRent.getWithdrawalDate().get(Calendar.MONTH) + 1) +  //soma-se um porque janeiro = 0
						 "/" + receiptRent.getWithdrawalDate().get(Calendar.YEAR);						 
		txtWithdrawalDate.setText(strDate);
		
		//Arrumar isso aqui pra setar a hora em que a operação foi realizada,
		//seja ela agendamento ou locação.
		txtWithdrawalTime = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtWithdrawalTime.setBounds(116, 65, 80, 21);
		txtWithdrawalTime.setEnabled(false);
		txtWithdrawalTime.setEditable(false);
		String strWithdrawalTime = receiptRent.getWithdrawalDate().get(Calendar.HOUR_OF_DAY) + 
						 ":" + receiptRent.getWithdrawalDate().get(Calendar.MINUTE) + 
						 ":" + receiptRent.getWithdrawalDate().get(Calendar.SECOND);
		txtWithdrawalTime.setText(strWithdrawalTime);
		
		txtMileage = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtMileage.setBounds(116, 97, 80, 21);
		txtMileage.setEnabled(false);
		txtMileage.setEditable(false);
		txtMileage.setText("" + receiptRent.getRentVehicle().getMileage());
		
		String strDays = null;
		
		if(receiptRent.getRentTime() == 1)
			strDays = "dia";
		else
			strDays = "dias";
		
		Label lblChargedValue = new Label(grpWithdrawalReceipt, SWT.NONE);
		lblChargedValue.setBounds(10, 132, 100, 40);
		lblChargedValue.setText("Valor cobrado:  R$\n" +
				 receiptRent.getRentTime() + " " + strDays + " x " + "R$" + receiptRent.getRentVehicle().getDailyPrice()); 
		
		txtChargedValue = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtChargedValue.setEnabled(false);
		txtChargedValue.setEditable(false);
		txtChargedValue.setBounds(116, 140, 80, 21);
		txtChargedValue.setText("" + receiptRent.getWithdrawalPayment());
		
		Label lblCode = new Label(grpWithdrawalReceipt, SWT.NONE);
		lblCode.setBounds(248, 44, 60, 20);
		lblCode.setText("Código:");
		
		txtCode = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtCode.setEnabled(false);
		txtCode.setEditable(false);
		txtCode.setBounds(318, 38, 80, 21);
		txtCode.setText("" + receiptRent.getRentCode());
		
		Label lblReturnDate = new Label(grpWithdrawalReceipt, SWT.NONE);
		lblReturnDate.setBounds(218, 74, 90, 20);
		lblReturnDate.setText("Devolver dia:");
		
		txtReturnDate = new Text(grpWithdrawalReceipt, SWT.BORDER);
		txtReturnDate.setEnabled(false);
		txtReturnDate.setEditable(false);
		txtReturnDate.setBounds(318, 71, 80, 21);
		
		String strReturnTime = receiptRent.getReturnDate().get(Calendar.DAY_OF_MONTH) + 
				 "/" + (receiptRent.getReturnDate().get(Calendar.MONTH) + 1) + 
				 "/" + receiptRent.getReturnDate().get(Calendar.YEAR);
		txtReturnDate.setText(strReturnTime);
		
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
