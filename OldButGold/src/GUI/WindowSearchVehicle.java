package GUI;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

public class WindowSearchVehicle extends ApplicationWindow {

	/**
	 * Create the application window.
	 */
	public WindowSearchVehicle() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Button btnCategory = new Button(container, SWT.RADIO);
		btnCategory.setBounds(10, 10, 111, 20);
		btnCategory.setText("Categoria");
		
		Button btnRadioButton_1 = new Button(container, SWT.RADIO);
		btnRadioButton_1.setBounds(10, 36, 111, 20);
		btnRadioButton_1.setText("Radio Button");
		
		Button btnRadioButton_2 = new Button(container, SWT.RADIO);
		btnRadioButton_2.setBounds(10, 61, 111, 20);
		btnRadioButton_2.setText("Radio Button");
		
		Button btnRadioButton_3 = new Button(container, SWT.RADIO);
		btnRadioButton_3.setBounds(10, 87, 111, 20);
		btnRadioButton_3.setText("Radio Button");
		
		Button btnRadioButton_4 = new Button(container, SWT.RADIO);
		btnRadioButton_4.setBounds(10, 113, 111, 20);
		btnRadioButton_4.setText("Radio Button");
		
		Button btnRadioButton_5 = new Button(container, SWT.RADIO);
		btnRadioButton_5.setBounds(10, 138, 111, 20);
		btnRadioButton_5.setText("Radio Button");
		
		Combo combo = new Combo(container, SWT.NONE);
		combo.setBounds(254, 36, 97, 28);

		return container;
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
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			WindowSearchVehicle window = new WindowSearchVehicle();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
