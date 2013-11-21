package GUI;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import control.CtrlReturnVehicle;
import control.CurrentState;
import db.Database;
import db.Rent;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;

import person.Client;

public class WindowReturnVehicle extends ApplicationWindow 
{

    private CurrentState returnVehicleCurrentState;
    private Database returnVehicleDatabase;
    private CtrlReturnVehicle returnVehicleCtrl;
	/**
	 * Create the application window.
	 * @param mainEmployeeDatabase 
	 * @param mainEmployeeCurrentState 
	 */
	public WindowReturnVehicle(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		returnVehicleCurrentState = mainCurrentState;
        returnVehicleDatabase = mainDatabase;
        returnVehicleCtrl = new CtrlReturnVehicle(mainDatabase);
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		Group grpReturnVehicle = new Group(container, SWT.NONE);
		grpReturnVehicle.setBounds(10, 10, 455, 254);
		grpReturnVehicle.setText("Devolução de veículo");
		grpReturnVehicle.setLayout(null);
		
        Button btnReturn = new Button(grpReturnVehicle, SWT.NONE);
        btnReturn.setBounds(349, 195, 96, 30);
        btnReturn.addSelectionListener(new SelectionAdapter() 
        {
                @Override
                //função de ação quando botão "Voltar" é pressionado
                public void widgetSelected(SelectionEvent e) 
                {
                    returnVehicleCurrentState.setChosenAction("Voltar");
                    close();
                }
        });
        btnReturn.setText("Voltar");
        
        Label lblSelectClient = new Label(grpReturnVehicle, SWT.NONE);
        lblSelectClient.setBounds(10, 49, 147, 20);
        lblSelectClient.setText("Selecione um cliente:");
        
        final Combo comboClientList = new Combo(grpReturnVehicle, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
        comboClientList.setBounds(163, 46, 140, 28);
        
        final ArrayList<Client> clientList = returnVehicleDatabase.getClientList();
        
        for(int i = 0; i < clientList.size(); i++)
        {
        	comboClientList.add(clientList.get(i).getId());
        }
                      
        Label lblSelectVehicle = new Label(grpReturnVehicle, SWT.NONE);
        lblSelectVehicle.setBounds(10, 103, 147, 20);
        lblSelectVehicle.setText("Selecione um veículo:");
        
        final Combo comboVehicleList = new Combo(grpReturnVehicle, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
        comboVehicleList.setBounds(163, 95, 140, 28);
        
        //função que executa o que acontece quando o usuário
        //seleciona as opções da primeira combo box.
        comboClientList.addModifyListener(new ModifyListener() 
        {
            public void modifyText(ModifyEvent arg0)
            {
            	comboVehicleList.removeAll();
            	
                int clientIndex = comboClientList.getSelectionIndex();
                Client chosenClient = clientList.get(clientIndex);
                ArrayList<Rent> clientRentList = chosenClient.getRentList();
                
                for(int i = 0; i < clientRentList.size(); i++)
                {                	
                	String vehicleModel = clientRentList.get(i).getRentVehicle().getModel();
                	comboVehicleList.add(vehicleModel);
                }
                
            }
        });
        
        comboClientList.select(0); //Coloca a primeira opção como default
        
        Button btnReturnVehicle = new Button(grpReturnVehicle, SWT.NONE);
        btnReturnVehicle.addSelectionListener(new SelectionAdapter()
        {
        	@Override
        	//função de ação quando botão "Devolver" é pressionado
        	public void widgetSelected(SelectionEvent e)        	
        	{
        		/*
                int selectionIndex = comboClientList.getSelectionIndex();
                if(selectionIndex != -1) 
                	newReturn = returnVehicleCtrl.makeVehicleReturn(selectionIndex, clientUsername);                                
                else
                        JOptionPane.showMessageDialog(null, "Você precisa selecionar um veículo!");
                        */
        	}
        	
        });
        btnReturnVehicle.setBounds(40, 153, 96, 30);
        btnReturnVehicle.setText("Devolver");
		
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
	protected Point getInitialSize() 
	{
		return new Point(493, 346);
	}
}
