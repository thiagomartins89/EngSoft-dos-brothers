package GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
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
	private Combo comboVehicleCategory;

	/**
	 * Create the application window.
	 */
	public WindowSubscribeVehicle(CurrentState mainCurrentState,
			Database mainDatabase)
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		subscribeVehicleDatabase = mainDatabase;
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

		Button btnSubscribe = new Button(container, SWT.NONE);
		btnSubscribe.setBounds(251, 181, 76, 25);
		btnSubscribe.setText("Adicionar");

		Button btnReturn = new Button(container, SWT.NONE);
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
		btnReturn.setBounds(334, 181, 76, 25);
		btnReturn.setText("Voltar");

		Group grpSubscribeVehicle = new Group(container, SWT.NONE);
		grpSubscribeVehicle.setBounds(10, 10, 400, 165);
		grpSubscribeVehicle.setText("Cadastro de ve\u00EDculo");
		grpSubscribeVehicle.setLayout(null);

		Label lblVehicleBrand = new Label(grpSubscribeVehicle, SWT.NONE);
		lblVehicleBrand.setBounds(64, 25, 36, 15);
		lblVehicleBrand.setText("Marca:");

		txtVehicleBrand = new Text(grpSubscribeVehicle, SWT.BORDER);
		txtVehicleBrand.setBounds(106, 22, 110, 21);

		Label lblVehicleModel = new Label(grpSubscribeVehicle, SWT.NONE);
		lblVehicleModel.setBounds(56, 52, 44, 15);
		lblVehicleModel.setText("Modelo:");

		txtVehicleModel = new Text(grpSubscribeVehicle, SWT.BORDER);
		txtVehicleModel.setBounds(106, 49, 110, 21);
		
				Label lblVehicleManufacturingDate = new Label(grpSubscribeVehicle,
						SWT.NONE);
				lblVehicleManufacturingDate.setBounds(75, 79, 25, 15);
				lblVehicleManufacturingDate.setText("Ano:");
		
				txtVehicleManufacturingDate = new Text(grpSubscribeVehicle, SWT.BORDER);
				txtVehicleManufacturingDate.setBounds(106, 76, 76, 21);
		
				Label lblVehicleMileage = new Label(grpSubscribeVehicle, SWT.NONE);
				lblVehicleMileage.setBounds(13, 106, 87, 15);
				lblVehicleMileage.setText("Quilometragem:");
		
				txtVehicleMileage = new Text(grpSubscribeVehicle, SWT.BORDER);
				txtVehicleMileage.setBounds(106, 103, 76, 21);
		
				Label lblKm = new Label(grpSubscribeVehicle, SWT.NONE);
				lblKm.setBounds(188, 106, 17, 15);
				lblKm.setText("km");
		
				Label lblVehicleLength = new Label(grpSubscribeVehicle, SWT.NONE);
				lblVehicleLength.setBounds(22, 133, 78, 15);
				lblVehicleLength.setText("Comprimento:");
		
				txtVehicleLength = new Text(grpSubscribeVehicle, SWT.BORDER);
				txtVehicleLength.setBounds(106, 130, 76, 21);
		
				Label lblM = new Label(grpSubscribeVehicle, SWT.NONE);
				lblM.setBounds(188, 133, 11, 15);
				lblM.setText("m");

		Label lblVehicleCategory = new Label(grpSubscribeVehicle, SWT.NONE);
		lblVehicleCategory.setBounds(234, 25, 54, 15);
		lblVehicleCategory.setText("Categoria:");

		comboVehicleCategory = new Combo(grpSubscribeVehicle, SWT.READ_ONLY);
		comboVehicleCategory.setBounds(294, 21, 76, 23);
		comboVehicleCategory.setItems(new String[] { "A", "B", "C", "D" });

		Label lblVehiclePlate = new Label(grpSubscribeVehicle, SWT.NONE);
		lblVehiclePlate.setBounds(257, 52, 31, 15);
		lblVehiclePlate.setText("Placa:");

		txtVehiclePlate = new Text(grpSubscribeVehicle, SWT.BORDER);
		txtVehiclePlate.setBounds(294, 49, 76, 21);

		Label lblVehicleEnginePower = new Label(grpSubscribeVehicle, SWT.NONE);
		lblVehicleEnginePower.setBounds(239, 79, 49, 15);
		lblVehicleEnginePower.setText("Pot\u00EAncia:");

		txtVehicleEnginePower = new Text(grpSubscribeVehicle, SWT.BORDER);
		txtVehicleEnginePower.setBounds(294, 76, 76, 21);

		Label lblCv = new Label(grpSubscribeVehicle, SWT.NONE);
		lblCv.setBounds(376, 79, 12, 15);
		lblCv.setText("cv");

		Label lblVehicleWidth = new Label(grpSubscribeVehicle, SWT.NONE);
		lblVehicleWidth.setBounds(245, 106, 43, 15);
		lblVehicleWidth.setText("Largura:");

		txtVehicleWidth = new Text(grpSubscribeVehicle, SWT.BORDER);
		txtVehicleWidth.setBounds(294, 103, 76, 21);

		Label lblM_1 = new Label(grpSubscribeVehicle, SWT.NONE);
		lblM_1.setBounds(376, 106, 11, 15);
		lblM_1.setText("m");

		btnSubscribe.addSelectionListener(new SelectionAdapter()
		{
			@Override
			// Botão Adicionar
			public void widgetSelected(SelectionEvent e)
			{
				if(checkFields() == false)
					return;
				
				Vehicle vehicle = new Vehicle();
				
				try
				{
					vehicle.setBrand(txtVehicleBrand.getText());
					vehicle.setModel(txtVehicleModel.getText());
					vehicle.setManufacturingDate(Integer.parseInt(txtVehicleManufacturingDate.getText()));
					vehicle.setManufacturingDate(Integer.parseInt(txtVehicleMileage.getText()));
					vehicle.setLength(Double.parseDouble(txtVehicleLength.getText().replace(",", ".")));
					vehicle.setCategory(comboVehicleCategory.getText());
					vehicle.setPlate(txtVehiclePlate.getText());
					vehicle.setEnginePower(Integer.parseInt(txtVehicleEnginePower.getText()));
					vehicle.setWidth(Double.parseDouble(txtVehicleWidth.getText().replace(",", ".")));
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Não foi possível adicionar o veículo. Verifique os dados de entrada.");
					return;
				}

				subscribeVehicleDatabase.addVehicle(vehicle);
				
				JOptionPane.showMessageDialog(null, "Veículo adicionado com sucesso!");
				
				close();
			}
		});

		return container;
	}

	// Verifica se todas as entradas estão corretas.
	private boolean checkFields()
	{
		// Verifica se todas as entradas estão preenchidas:
		if (txtVehicleBrand.getText().isEmpty()
				|| txtVehicleModel.getText().isEmpty()
				|| txtVehicleManufacturingDate.getText().isEmpty()
				|| txtVehicleMileage.getText().isEmpty()
				|| txtVehicleLength.getText().isEmpty()
				|| comboVehicleCategory.getText().isEmpty()
				|| txtVehiclePlate.getText().isEmpty()
				|| txtVehicleEnginePower.getText().isEmpty()
				|| txtVehicleWidth.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos.");
			return false;
		}
		
		// Verifica se o ano é válido:
		try
		{
			if(txtVehicleManufacturingDate.getText().length() != 4)
			{
				JOptionPane.showMessageDialog(null, "O ano deve ser preenchido no formato 'AAAA'");
				return false;
			}
			
			int manufacturingDate = Integer.parseInt(txtVehicleManufacturingDate.getText());
			if(manufacturingDate < 0)
			{
				JOptionPane.showMessageDialog(null, "Ano inválido.");
				return false;
			}
		}
		catch (NumberFormatException e1)
		{
			JOptionPane.showMessageDialog(null, "Ano inválido.");
			return false;
		}

		// Verifica se a quilometragem é válida:
		try
		{
			int mileage = Integer.parseInt(txtVehicleMileage.getText());
			if(mileage < 0)
			{
				JOptionPane.showMessageDialog(null, "Quilometragem inválida.");
				return false;
			}
		}
		catch (NumberFormatException e1)
		{
			JOptionPane.showMessageDialog(null, "Quilometragem inválida.");
			return false;
		}
		
		// Verifica se o comprimento é válido:
		try
		{
			double length = Double.parseDouble(txtVehicleLength.getText().replace(",", "."));
			if(length < 0)
			{
				JOptionPane.showMessageDialog(null, "Comprimento inválido.");
				return false;
			}
		}
		catch (NumberFormatException e1)
		{
			JOptionPane.showMessageDialog(null, "Comprimento inválido.");
			return false;
		}
		
		// Verifica se a potência é válida:
		try
		{
			int enginePower = Integer.parseInt(txtVehicleEnginePower.getText());
			if(enginePower < 0)
			{
				JOptionPane.showMessageDialog(null, "Potência inválida.");
				return false;
			}
		}
		catch (NumberFormatException e1)
		{
			JOptionPane.showMessageDialog(null, "Potência inválida.");
			return false;
		}
		
		// Verifica se a largura é válida:
		try
		{
			double width = Double.parseDouble(txtVehicleWidth.getText().replace(",", "."));
			if(width < 0)
			{
				JOptionPane.showMessageDialog(null, "Largura inválida.");
				return false;
			}
		}
		catch (NumberFormatException e1)
		{
			JOptionPane.showMessageDialog(null, "Largura inválida.");
			return false;
		}

		return true;
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
		Image imgOldButGold = new Image(null, "images/oldbutgold.png");
		newShell.setImage(imgOldButGold);
		newShell.setBackgroundImage(imgOldButGold);
		newShell.setBackgroundMode(SWT.INHERIT_DEFAULT);
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize()
	{
		return new Point(437, 287);
	}
}
