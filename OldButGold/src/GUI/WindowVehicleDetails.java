package GUI;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import vehicle.Vehicle;
import control.CtrlSubscribeVehicle;
import control.CurrentState;
import db.Database;

import org.eclipse.swt.widgets.Group;

public class WindowVehicleDetails extends ApplicationWindow
{
	private Text txtVehicleModel;
	private Text txtVehicleBrand;
	private Text txtVehiclePlate;
	private Text txtVehicleManufacturingDate;
	private Text txtVehicleEnginePower;
	private Text txtVehicleLength;
	private Text txtVehicleWidth;
	private Text txtCategory;
	private Text txtVehicleMileage;
	Vehicle selectedVehicle;

	/**
	 * Create the application window.
	 */
	public WindowVehicleDetails(Vehicle vehicle)
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		selectedVehicle = vehicle;
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(null);

		Button btnReturn = new Button(container, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter()
		{
			@Override
			// botão "Voltar"
			public void widgetSelected(SelectionEvent e)
			{
				close();
			}
		});
		btnReturn.setBounds(334, 181, 76, 25);
		btnReturn.setText("Voltar");

		Group grpVehicleDetails = new Group(container, SWT.NONE);
		grpVehicleDetails.setBounds(10, 10, 400, 165);
		grpVehicleDetails.setText("Detalhes do veículo");
		grpVehicleDetails.setLayout(null);

		Label lblVehicleBrand = new Label(grpVehicleDetails, SWT.NONE);
		lblVehicleBrand.setBounds(64, 25, 36, 15);
		lblVehicleBrand.setText("Marca:");

		txtVehicleBrand = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehicleBrand.setEnabled(false);
		txtVehicleBrand.setEditable(false);
		txtVehicleBrand.setBounds(106, 22, 110, 21);
		txtVehicleBrand.setText(selectedVehicle.getBrand());

		Label lblVehicleModel = new Label(grpVehicleDetails, SWT.NONE);
		lblVehicleModel.setBounds(56, 52, 44, 15);
		lblVehicleModel.setText("Modelo:");

		txtVehicleModel = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehicleModel.setEditable(false);
		txtVehicleModel.setEnabled(false);
		txtVehicleModel.setBounds(106, 49, 110, 21);
		txtVehicleModel.setText(selectedVehicle.getModel());

		Label lblVehicleManufacturingDate = new Label(grpVehicleDetails,
				SWT.NONE);
		lblVehicleManufacturingDate.setBounds(75, 79, 25, 15);
		lblVehicleManufacturingDate.setText("Ano:");

		txtVehicleManufacturingDate = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehicleManufacturingDate.setEnabled(false);
		txtVehicleManufacturingDate.setEditable(false);
		txtVehicleManufacturingDate.setBounds(106, 76, 76, 21);
		txtVehicleManufacturingDate.setText("" + selectedVehicle.getManufacturingDate());

		Label lblVehicleMileage = new Label(grpVehicleDetails, SWT.NONE);
		lblVehicleMileage.setBounds(13, 106, 87, 15);
		lblVehicleMileage.setText("Quilometragem:");

		txtVehicleMileage = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehicleMileage.setEnabled(false);
		txtVehicleMileage.setEditable(false);
		txtVehicleMileage.setBounds(106, 103, 76, 21);
		txtVehicleMileage.setText("" + selectedVehicle.getMileage());

		Label lblKm = new Label(grpVehicleDetails, SWT.NONE);
		lblKm.setBounds(188, 106, 17, 15);
		lblKm.setText("km");

		Label lblVehicleLength = new Label(grpVehicleDetails, SWT.NONE);
		lblVehicleLength.setBounds(22, 133, 78, 15);
		lblVehicleLength.setText("Comprimento:");

		txtVehicleLength = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehicleLength.setEnabled(false);
		txtVehicleLength.setEditable(false);
		txtVehicleLength.setBounds(106, 130, 76, 21);
		txtVehicleLength.setText("" + selectedVehicle.getLength());

		Label lblM = new Label(grpVehicleDetails, SWT.NONE);
		lblM.setBounds(188, 133, 11, 15);
		lblM.setText("m");

		Label lblVehicleCategory = new Label(grpVehicleDetails, SWT.NONE);
		lblVehicleCategory.setBounds(234, 25, 54, 15);
		lblVehicleCategory.setText("Categoria:");

		Label lblVehiclePlate = new Label(grpVehicleDetails, SWT.NONE);
		lblVehiclePlate.setBounds(257, 52, 31, 15);
		lblVehiclePlate.setText("Placa:");

		txtVehiclePlate = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehiclePlate.setEnabled(false);
		txtVehiclePlate.setEditable(false);
		txtVehiclePlate.setBounds(294, 49, 76, 21);
		txtVehiclePlate.setText(selectedVehicle.getPlate());

		Label lblVehicleEnginePower = new Label(grpVehicleDetails, SWT.NONE);
		lblVehicleEnginePower.setBounds(239, 79, 49, 15);
		lblVehicleEnginePower.setText("Potência:");

		txtVehicleEnginePower = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehicleEnginePower.setEnabled(false);
		txtVehicleEnginePower.setEditable(false);
		txtVehicleEnginePower.setBounds(294, 76, 76, 21);
		txtVehicleEnginePower.setText("" + selectedVehicle.getEnginePower());

		Label lblCv = new Label(grpVehicleDetails, SWT.NONE);
		lblCv.setBounds(376, 79, 12, 15);
		lblCv.setText("cv");

		Label lblVehicleWidth = new Label(grpVehicleDetails, SWT.NONE);
		lblVehicleWidth.setBounds(245, 106, 43, 15);
		lblVehicleWidth.setText("Largura:");

		txtVehicleWidth = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehicleWidth.setEnabled(false);
		txtVehicleWidth.setEditable(false);
		txtVehicleWidth.setBounds(294, 103, 76, 21);
		txtVehicleWidth.setText("" + selectedVehicle.getWidth());

		Label lblM_1 = new Label(grpVehicleDetails, SWT.NONE);
		lblM_1.setBounds(376, 106, 11, 15);
		lblM_1.setText("m");
		
		txtCategory = new Text(grpVehicleDetails, SWT.BORDER);
		txtCategory.setEditable(false);
		txtCategory.setEnabled(false);
		txtCategory.setBounds(294, 22, 76, 21);
		txtCategory.setText(selectedVehicle.getCategory());
		
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
	 * Create the status line manager.
	 * 
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
	 * 
	 * @param args
	 */

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("Old but Gold");
		//Image imgOldButGold = new Image(null, "C:/oldbutgold.png");
		//newShell.setImage(imgOldButGold);
		// newShell.setBackgroundImage(imgOldButGold);
		newShell.setBackgroundMode(SWT.INHERIT_DEFAULT);
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize()
	{
		return new Point(425, 269);
	}
}
