package GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import vehicle.Vehicle;
import control.CurrentState;
import db.Database;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Group;

public class WindowSubscribeVehicle extends ApplicationWindow
{
	private Text txtVehicleModel;
	private Text txtVehicleBrand;
	private Text txtVehiclePlate;
	private Text txtVehicleManufacturingDate;
	private Text txtVehicleEnginePower;
	private Text txtVehicleLength;
	private Text txtVehicleWidth;
	private Text txtVehicleMileage;
	private CurrentState currentState;
	private Database subscribeVehicleDatabase;

	/**
	 * Create the application window.
	 */
	public WindowSubscribeVehicle(CurrentState mainCurrentState, Database mainDatabase)
	{
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		subscribeVehicleDatabase = mainDatabase;
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(null);
		
		Button btnSubscribe = new Button(container, SWT.NONE);
		btnSubscribe.setBounds(251, 181, 76, 25);
		btnSubscribe.setText("Adicionar");
		
		Button btnReturn = new Button(container, SWT.NONE);
		btnReturn.setBounds(334, 181, 76, 25);
		btnReturn.setText("Voltar");
		
		Group grpCadastroDeVeculo = new Group(container, SWT.NONE);
		grpCadastroDeVeculo.setBounds(10, 10, 400, 165);
		grpCadastroDeVeculo.setText("Cadastro de ve\u00EDculo");
		grpCadastroDeVeculo.setLayout(null);
		
		Label lblVehicleBrand = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblVehicleBrand.setBounds(64, 25, 36, 15);
		lblVehicleBrand.setText("Marca:");
		
		txtVehicleBrand = new Text(grpCadastroDeVeculo, SWT.BORDER);
		txtVehicleBrand.setBounds(106, 22, 110, 21);
		
		Label lblVehicleModel = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblVehicleModel.setBounds(56, 52, 44, 15);
		lblVehicleModel.setText("Modelo:");
		
		txtVehicleModel = new Text(grpCadastroDeVeculo, SWT.BORDER);
		txtVehicleModel.setBounds(106, 49, 110, 21);
		
		Label lblVehicleCategory = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblVehicleCategory.setBounds(234, 25, 54, 15);
		lblVehicleCategory.setText("Categoria:");
		
		final Combo cmbVehicleCategory = new Combo(grpCadastroDeVeculo, SWT.READ_ONLY);
		cmbVehicleCategory.setBounds(294, 21, 76, 23);
		cmbVehicleCategory.setItems(new String[] {"A", "B", "C", "D"});
		
		Label lblVehiclePlate = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblVehiclePlate.setBounds(257, 52, 31, 15);
		lblVehiclePlate.setText("Placa:");
		
		txtVehiclePlate = new Text(grpCadastroDeVeculo, SWT.BORDER);
		txtVehiclePlate.setBounds(294, 49, 76, 21);
		
		Label lblVehicleManufacturingDate = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblVehicleManufacturingDate.setBounds(75, 79, 25, 15);
		lblVehicleManufacturingDate.setText("Ano:");
		
		txtVehicleManufacturingDate = new Text(grpCadastroDeVeculo, SWT.BORDER);
		txtVehicleManufacturingDate.setBounds(106, 76, 76, 21);
		
		Label lblVehicleEnginePower = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblVehicleEnginePower.setBounds(239, 79, 49, 15);
		lblVehicleEnginePower.setText("Pot\u00EAncia:");
		
		txtVehicleEnginePower = new Text(grpCadastroDeVeculo, SWT.BORDER);
		txtVehicleEnginePower.setBounds(294, 76, 76, 21);
		
		Label lblCv = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblCv.setBounds(376, 79, 12, 15);
		lblCv.setText("cv");
		
		Label lblVehicleMileage = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblVehicleMileage.setBounds(13, 106, 87, 15);
		lblVehicleMileage.setText("Quilometragem:");
		
		txtVehicleMileage = new Text(grpCadastroDeVeculo, SWT.BORDER);
		txtVehicleMileage.setBounds(106, 103, 76, 21);
		
		Label lblKm = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblKm.setBounds(188, 106, 17, 15);
		lblKm.setText("km");
		
		Label lblVehicleWidth = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblVehicleWidth.setBounds(245, 106, 43, 15);
		lblVehicleWidth.setText("Largura:");
		
		txtVehicleWidth = new Text(grpCadastroDeVeculo, SWT.BORDER);
		txtVehicleWidth.setBounds(294, 103, 76, 21);
		
		Label lblM_1 = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblM_1.setBounds(376, 106, 11, 15);
		lblM_1.setText("m");
		
		Label lblVehicleLength = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblVehicleLength.setBounds(22, 133, 78, 15);
		lblVehicleLength.setText("Comprimento:");
		
		txtVehicleLength = new Text(grpCadastroDeVeculo, SWT.BORDER);
		txtVehicleLength.setBounds(106, 130, 76, 21);
		
		Label lblM = new Label(grpCadastroDeVeculo, SWT.NONE);
		lblM.setBounds(188, 133, 11, 15);
		lblM.setText("m");
		
		btnSubscribe.addSelectionListener(new SelectionAdapter() {
			@Override
			//Botão Adicionar
			public void widgetSelected(SelectionEvent e) {
				Vehicle vehicle = new Vehicle();
				
				vehicle.setBrand(txtVehicleBrand.getText());
				vehicle.setCategory(cmbVehicleCategory.getText());
				vehicle.setEnginePower(txtVehicleEnginePower.getText());
				vehicle.setIsAvailable(true);
				vehicle.setLength(Double.parseDouble(txtVehicleLength.getText()));
				
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
				try
				{
					date = dateFormat.parse(txtVehicleManufacturingDate.getText());
				} catch (ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				vehicle.setManufacturingDate(date);
				vehicle.setModel(txtVehicleModel.getText());
				vehicle.setPlate(txtVehiclePlate.getText());
				vehicle.setWidth(Double.parseDouble(txtVehicleWidth.getText()));
				
				subscribeVehicleDatabase.addVehicle(vehicle);
			}
		});

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
/*	public static void main(String args[])
	{
		try
		{
			WindowSubscribeVehicle window = new WindowSubscribeVehicle();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/

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
		return new Point(437, 275);
	}
}
