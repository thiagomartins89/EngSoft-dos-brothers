package GUI;

import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import vehicle.Vehicle;

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
	private Text txtPrice;
	private boolean isEmployee;
	private Text txtIsAvailable;
	private Text txtRentedBy;

	/**
	 * Create the application window.
	 */
	public WindowVehicleDetails(Vehicle vehicle, boolean isEmployee)
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		selectedVehicle = vehicle;
		this.isEmployee = isEmployee;
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

		Group grpVehicleDetails = new Group(container, SWT.NONE);
		grpVehicleDetails.setBounds(10, 23, 400, 220);
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
		lblVehicleLength.setBounds(22, 133, 78, 21);
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
		lblVehicleCategory.setBounds(32, 160, 54, 15);
		lblVehicleCategory.setText("Categoria:");

		Label lblVehiclePlate = new Label(grpVehicleDetails, SWT.NONE);
		lblVehiclePlate.setBounds(245, 25, 31, 15);
		lblVehiclePlate.setText("Placa:");

		txtVehiclePlate = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehiclePlate.setEnabled(false);
		txtVehiclePlate.setEditable(false);
		txtVehiclePlate.setBounds(294, 22, 76, 21);
		txtVehiclePlate.setText(selectedVehicle.getPlate());

		Label lblVehicleEnginePower = new Label(grpVehicleDetails, SWT.NONE);
		lblVehicleEnginePower.setBounds(227, 52, 49, 15);
		lblVehicleEnginePower.setText("Potência:");

		txtVehicleEnginePower = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehicleEnginePower.setEnabled(false);
		txtVehicleEnginePower.setEditable(false);
		txtVehicleEnginePower.setBounds(294, 49, 76, 21);
		txtVehicleEnginePower.setText("" + selectedVehicle.getEnginePower());

		Label lblCv = new Label(grpVehicleDetails, SWT.NONE);
		lblCv.setBounds(376, 52, 12, 15);
		lblCv.setText("cv");

		Label lblVehicleWidth = new Label(grpVehicleDetails, SWT.NONE);
		lblVehicleWidth.setBounds(245, 79, 43, 15);
		lblVehicleWidth.setText("Largura:");

		txtVehicleWidth = new Text(grpVehicleDetails, SWT.BORDER);
		txtVehicleWidth.setEnabled(false);
		txtVehicleWidth.setEditable(false);
		txtVehicleWidth.setBounds(294, 76, 76, 21);
		txtVehicleWidth.setText("" + selectedVehicle.getWidth());

		Label lblM_1 = new Label(grpVehicleDetails, SWT.NONE);
		lblM_1.setBounds(376, 79, 11, 15);
		lblM_1.setText("m");
		
		txtCategory = new Text(grpVehicleDetails, SWT.BORDER);
		txtCategory.setEditable(false);
		txtCategory.setEnabled(false);
		txtCategory.setBounds(106, 157, 76, 21);
		txtCategory.setText(selectedVehicle.getCategory());
		
		Label lblPrice = new Label(grpVehicleDetails, SWT.NONE);
		lblPrice.setBounds(222, 106, 54, 15);
		lblPrice.setText("Preço/dia:");
		
		txtPrice = new Text(grpVehicleDetails, SWT.BORDER);
		txtPrice.setEditable(false);
		txtPrice.setEnabled(false);
		txtPrice.setBounds(294, 103, 76, 21);
		txtPrice.setText("" + selectedVehicle.getDailyPrice());
		
		Label lblRS = new Label(grpVehicleDetails, SWT.NONE);
		lblRS.setBounds(278, 106, 25, 15);
		lblRS.setText("R$");
		
		if(isEmployee) //só o funcionário pode ver os carros locados, e com quem eles estão.
		{
			Label lblAvailable = new Label(grpVehicleDetails, SWT.NONE);
			lblAvailable.setBounds(218, 133, 70, 20);
			lblAvailable.setText("Disponível:");
			

			
			txtIsAvailable = new Text(grpVehicleDetails, SWT.BORDER);
			txtIsAvailable.setEditable(false);
			txtIsAvailable.setEnabled(false);
			txtIsAvailable.setBounds(294, 130, 76, 21);
			if(selectedVehicle.IsAvailable())
				txtIsAvailable.setText("Sim");
			
			else
			{
				Label lblRentedBy = new Label(grpVehicleDetails, SWT.NONE);
				lblRentedBy.setBounds(218, 158, 70, 20);
				lblRentedBy.setText("Locado por:");
				
				txtIsAvailable.setText("Não");
				txtRentedBy = new Text(grpVehicleDetails, SWT.BORDER);
				txtRentedBy.setEditable(false);
				txtRentedBy.setEnabled(false);
				txtRentedBy.setBounds(294, 157, 76, 21);
				txtRentedBy.setText(selectedVehicle.getCurrentClient().getId());
			}
			
	
		}
		
		Button btnReturn = new Button(grpVehicleDetails, SWT.NONE);
		btnReturn.setBounds(304, 185, 76, 25);
		btnReturn.addSelectionListener(new SelectionAdapter()
		{
			@Override
			// botão "Voltar"
			public void widgetSelected(SelectionEvent e)
			{
				close();
			}
		});
		btnReturn.setText("Voltar");
		
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
		return new Point(425, 320);
	}
}
