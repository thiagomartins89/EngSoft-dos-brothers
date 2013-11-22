package GUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import control.CtrlClientScheduling;
import control.CtrlClientVehicleRent;
import control.CurrentState;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

import vehicle.Vehicle;

import db.Database;
import db.Rent;

public class WindowClientScheduling extends ApplicationWindow 
{

	private CurrentState currentState;
	private Database searchVehicleDatabase;
	private CtrlClientScheduling searchVehicleCtrl;
	private Text txtRentDuration;
	private Text txtWithdrawalDate;
	
	/**
	 * Create the application window.
	 */
	public WindowClientScheduling(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		searchVehicleDatabase = mainDatabase;
		searchVehicleCtrl = new CtrlClientScheduling(mainDatabase);
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
		
		final Combo comboSearchOptions = new Combo(container, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
		comboSearchOptions.setBounds(117, 49, 192, 23);
		
		final Combo comboSearchOptionsResults = new Combo(container, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
		comboSearchOptionsResults.setBounds(117, 78, 192, 23);
		final List listSearchResults = new List(container, SWT.BORDER);
		
		listSearchResults.addMouseListener(new MouseAdapter() 
		{
			@Override
			//fun��o de a��o quando se clica duas vezes em um ve�culo da lista
			public void mouseDoubleClick(MouseEvent e)
			{
				ArrayList<Vehicle> vehicleList = searchVehicleCtrl.getVehicleList();			
				
				int selectionIndex = listSearchResults.getSelectionIndex();	
				
				if(selectionIndex != -1)
				{
					Vehicle selectedVehicle = vehicleList.get(selectionIndex);
					WindowVehicleDetails vehicleDeitalsWindow = new WindowVehicleDetails(selectedVehicle, false);
					vehicleDeitalsWindow.open();
				}	
				else
					JOptionPane.showMessageDialog(null, "Voc� precisa selecionar um ve�culo!");
			}
		});
		listSearchResults.setBounds(340, 38, 216, 105);
		
		final Label lblUnity = new Label(container, SWT.NONE);
		lblUnity.setBounds(315, 78, 23, 25);
		
		txtRentDuration = new Text(container, SWT.BORDER);
		txtRentDuration.setBounds(130, 115, 76, 21);
		
		Button btnScheduling = new Button(container, SWT.NONE);
		btnScheduling.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			//fun��o de a��o quando bot�o Agendar � pressionado
			public void widgetSelected(SelectionEvent e)
			{
				Rent newRent = null;
				
				String strChosenDate = txtWithdrawalDate.getText();
				
				//Verifica se o usu�rio digitou uma data v�lida.
				GregorianCalendar chosenDate = checkDate(strChosenDate);
				
				if(chosenDate == null)
					return;
				
				if(txtRentDuration.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Por favor, indique por quantos dias voc� deseja locar.");
					return;
				}
				
				int rentDuration = Integer.parseInt(txtRentDuration.getText());
				
				int selectionIndex = listSearchResults.getSelectionIndex();
				if(selectionIndex != -1) 
					newRent = searchVehicleCtrl.makeClientScheduling(selectionIndex, currentState, rentDuration, chosenDate);				
				else
					JOptionPane.showMessageDialog(null, "Voc� precisa selecionar um ve�culo!");
				
				if(newRent != null)
				{             
                    listSearchResults.remove(selectionIndex);
                    ArrayList<Vehicle> vehicleList = searchVehicleCtrl.getVehicleList();
                    vehicleList.remove(selectionIndex);
                    
                    if(listSearchResults.getItemCount() == 0)
                    {
                        listSearchResults.add("N�o h� ve�culos dispon�veis");
                        listSearchResults.add("para essa pesquisa");
                        listSearchResults.setEnabled(false);
                    }                                        
					
                    //Gera o comprovante de retirada do ve�culo para o cliente
					WindowWithdrawalReceipt generateReceiptWindow = new WindowWithdrawalReceipt(newRent);
					generateReceiptWindow.open();
				}
			}

		});		
		btnScheduling.setBounds(62, 160, 96, 30);
		btnScheduling.setText("Agendar");	
		
		txtWithdrawalDate = new Text(container, SWT.BORDER);
		txtWithdrawalDate.setBounds(130, 10, 76, 21);
		
		//fun��o que executa o que acontece quando o usu�rio
		//seleciona as op��es da primeira combo box.
		comboSearchOptions.addModifyListener(new ModifyListener() 
		{
			public void modifyText(ModifyEvent arg0)
			{
				lblUnity.setText("");
				comboSearchOptionsResults.removeAll(); //limpa os resultados anteriores
				listSearchResults.removeAll();
				
				int optionIndex = comboSearchOptions.getSelectionIndex();
				String optionName = comboSearchOptions.getItem(optionIndex);
				
				String strChosenDate = txtWithdrawalDate.getText();
				if(strChosenDate.equals(""))
					return;
				
				//Verifica se o usu�rio digitou uma data v�lida.
				GregorianCalendar chosenDate = checkDate(strChosenDate);
				
				if(chosenDate == null)
					return;
				
				ArrayList<String> secondComboItems = searchVehicleCtrl.getSecondComboItems(optionName, false, chosenDate);
				
                if(secondComboItems.isEmpty())
                {
                		comboSearchOptionsResults.add("N�o h� ve�culos dispon�veis");
                        comboSearchOptionsResults.setEnabled(false);
                        comboSearchOptionsResults.select(0);
                }
                
				
				if(optionName.equals("Pot�ncia do motor"))
					lblUnity.setText("cv");
				
				else if(optionName.equals("Comprimento m�ximo"))
					lblUnity.setText("m");
				
				
				for(int i = 0; i < secondComboItems.size(); i++)
				{
					comboSearchOptionsResults.add(secondComboItems.get(i));
				}
			}
		});
		
		//fun��o que executa o que acontece quando o usu�rio
		//seleciona as op��es da segunda combo box.
		//Preenche a lista com os ve�culos dispon�veis na data
		//escolhida pelo usu�rio.
		comboSearchOptionsResults.addModifyListener(new ModifyListener() 
		{
			//fun��o que preenche a lista com as op��es de ve�culos
			//dispon�veis do par�metro solicitado pelo usu�rio.
			public void modifyText(ModifyEvent arg0)
			{
				if(comboSearchOptionsResults.getItemCount() == 0)
					return;
				
                if(comboSearchOptionsResults.getItem(0).equals("N�o h� ve�culos dispon�veis"))
                	return;
				
				//limpa a lista para inserir os novos resultados				
				listSearchResults.removeAll();
				listSearchResults.setEnabled(true);
				
				int optionIndex = comboSearchOptions.getSelectionIndex();				
				String chosenOption = comboSearchOptions.getItem(optionIndex);
				
				int optionResultIndex = comboSearchOptionsResults.getSelectionIndex();
				String chosenOptionResult = comboSearchOptionsResults.getItem(optionResultIndex);

				String strChosenDate = txtWithdrawalDate.getText();
				
				//Verifica se o usu�rio digitou uma data v�lida.
				GregorianCalendar chosenDate = checkDate(strChosenDate);
				
				if(chosenDate == null)
					return;

				ArrayList<String> resultsListItems = searchVehicleCtrl.getResultsListItems(chosenOption, chosenOptionResult, false, chosenDate);
				
				for(int i = 0; i < resultsListItems.size(); i++)
				{
					listSearchResults.add(resultsListItems.get(i));
				}
				
				ArrayList<Vehicle> vehicleList = searchVehicleCtrl.getVehicleList();
				if(vehicleList.isEmpty())
				{
					listSearchResults.setEnabled(false);
					return;
				}	
					 
			}
		});
		
		//Adicionando as op��es � ComboBox
		comboSearchOptions.add("Categoria");	
		comboSearchOptions.add("Pot�ncia do motor");	
		comboSearchOptions.add("Ano");	
		comboSearchOptions.add("Comprimento m�ximo");
		comboSearchOptions.add("Marca");
		comboSearchOptions.add("Modelo");		
		comboSearchOptions.select(0); //Coloca a primeira op��o como default
		
		Label lblSearchOptions = new Label(container, SWT.NONE);
		lblSearchOptions.setBounds(23, 52, 83, 20);
		lblSearchOptions.setText("Pesquisar por :");
		
		Button btnReturn = new Button(container, SWT.NONE);
		btnReturn.setBounds(359, 184, 96, 30);
		btnReturn.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			//fun��o de a��o quando bot�o "Voltar" � pressionado
			public void widgetSelected(SelectionEvent e) 
			{
				currentState.setChosenAction("Voltar");
				close();
			}
		});
		btnReturn.setText("Voltar");
		
		Label lblAvailableModels = new Label(container, SWT.NONE);
		lblAvailableModels.setBounds(355, 13, 192, 20);
		lblAvailableModels.setText("Modelos dispon�veis nesta data:");
		
		Button btnDetails = new Button(container, SWT.NONE);
		btnDetails.addSelectionListener(new SelectionAdapter()
		{
			@Override
			//fun��o de a��o quando bot�o Detalhes � pressionado
			public void widgetSelected(SelectionEvent e) 
			{
				int selectionIndex = listSearchResults.getSelectionIndex();
				
				if(selectionIndex != -1)
				{
					ArrayList<Vehicle> vehicleList = searchVehicleCtrl.getVehicleList();
					Vehicle selectedVehicle = vehicleList.get(selectionIndex);
					WindowVehicleDetails vehicleDeitalsWindow = new WindowVehicleDetails(selectedVehicle, false);
					vehicleDeitalsWindow.open();
				}	
				else
					JOptionPane.showMessageDialog(null, "Voc� precisa selecionar um ve�culo!");
			}
		});
		btnDetails.setBounds(184, 160, 96, 30);
		btnDetails.setText("Detalhes");
		
		Label lblRentDuration = new Label(container, SWT.NONE);
		lblRentDuration.setBounds(23, 118, 101, 35);
		lblRentDuration.setText("Tempo de loca��o:\n            (dias)");
		
		Label lblWithdrawalDate = new Label(container, SWT.NONE);
		lblWithdrawalDate.setBounds(23, 13, 90, 15);
		lblWithdrawalDate.setText("Data da retirada:");

		return container;
	}

	private void createActions()
	{
		// Create the actions
	}
	
	//Fun��o que pega como par�metro uma data inserida pelo usu�rio
	//e faz as verifica��es necess�rias.
	private GregorianCalendar checkDate(String strChosenDate) 
	{
		Date date = null;
		GregorianCalendar chosenDate = new GregorianCalendar();
		chosenDate.setLenient(false);
		
		if(strChosenDate.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Por favor, indique a data de retirada.");
			return null;
		}				
		
		else
		{
			DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
			try
			{
				date = (Date)dateFormatter.parse(strChosenDate);
			} 
			catch (ParseException e1) 
			{
				JOptionPane.showMessageDialog(null, "Data deve ser no formato dd/mm/aaaa.");
				return null;
			}
			
			try
			{
				chosenDate.setTime(date);
			} 
			catch (Exception e1) 
			{
				JOptionPane.showMessageDialog(null, "Data inv�lida.");
				chosenDate =  null;
			}
			
		}
		
		if(chosenDate != null)
		{
			GregorianCalendar auxDate = new GregorianCalendar();
			if (chosenDate.compareTo(auxDate) < 0)
			{
				JOptionPane.showMessageDialog(null, "Data inv�lida! Indique uma data a partir de amanh�.");
				chosenDate = null;
			}
		}
		
		return chosenDate;
	}

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
		return new Point(563, 273);
	}
}
