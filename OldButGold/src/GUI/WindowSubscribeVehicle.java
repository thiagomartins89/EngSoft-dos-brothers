package GUI;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import control.CurrentState;

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

	/**
	 * Create the application window.
	 */
	public WindowSubscribeVehicle(CurrentState mainCurrentState)
	{
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
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
		
		Label lblVehicleBrand = new Label(container, SWT.NONE);
		lblVehicleBrand.setBounds(136, 73, 36, 15);
		lblVehicleBrand.setText("Marca:");
		
		txtVehicleBrand = new Text(container, SWT.BORDER);
		txtVehicleBrand.setBounds(177, 70, 76, 21);
		
		Label lblVehicleModel = new Label(container, SWT.NONE);
		lblVehicleModel.setBounds(286, 73, 44, 15);
		lblVehicleModel.setText("Modelo:");
		
		txtVehicleModel = new Text(container, SWT.BORDER);
		txtVehicleModel.setBounds(335, 70, 76, 21);
		
		Label lblVehiclePlate = new Label(container, SWT.NONE);
		lblVehiclePlate.setBounds(141, 99, 31, 15);
		lblVehiclePlate.setText("Placa:");
		
		txtVehiclePlate = new Text(container, SWT.BORDER);
		txtVehiclePlate.setBounds(177, 96, 76, 21);
		
		Label lblVehicleManufacturingDate = new Label(container, SWT.NONE);
		lblVehicleManufacturingDate.setBounds(305, 99, 25, 15);
		lblVehicleManufacturingDate.setText("Ano:");
		
		txtVehicleManufacturingDate = new Text(container, SWT.BORDER);
		txtVehicleManufacturingDate.setBounds(335, 96, 76, 21);
		
		Label lblVehicleCategory = new Label(container, SWT.NONE);
		lblVehicleCategory.setBounds(118, 126, 54, 15);
		lblVehicleCategory.setText("Categoria:");
		
		Combo cmbVehicleCategory = new Combo(container, SWT.NONE);
		cmbVehicleCategory.setBounds(177, 122, 76, 23);
		cmbVehicleCategory.setItems(new String[] {"A", "B", "C", "D"});
		
		Label lblVehicleEnginePower = new Label(container, SWT.NONE);
		lblVehicleEnginePower.setBounds(281, 126, 49, 15);
		lblVehicleEnginePower.setText("Pot\u00EAncia:");
		
		txtVehicleEnginePower = new Text(container, SWT.BORDER);
		txtVehicleEnginePower.setBounds(335, 123, 76, 21);
		
		Label lblVehicleMileage = new Label(container, SWT.NONE);
		lblVehicleMileage.setBounds(85, 153, 87, 15);
		lblVehicleMileage.setText("Quilometragem:");
		
		txtVehicleMileage = new Text(container, SWT.BORDER);
		txtVehicleMileage.setBounds(177, 150, 76, 21);
		
		Label lblVehicleWidth = new Label(container, SWT.NONE);
		lblVehicleWidth.setBounds(287, 153, 43, 15);
		lblVehicleWidth.setText("Largura:");
		
		txtVehicleWidth = new Text(container, SWT.BORDER);
		txtVehicleWidth.setBounds(335, 150, 76, 21);
		
		Label lblVehicleLength = new Label(container, SWT.NONE);
		lblVehicleLength.setBounds(94, 179, 78, 15);
		lblVehicleLength.setText("Comprimento:");
		
		txtVehicleLength = new Text(container, SWT.BORDER);
		txtVehicleLength.setBounds(177, 176, 76, 21);

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
		return new Point(600, 400);
	}
}
