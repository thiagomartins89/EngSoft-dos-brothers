package GUI;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import vehicle.Vehicle;
import control.CtrlRemoveVehicle;
import control.CurrentState;
import db.Database;

public class WindowRemoveVehicle extends ApplicationWindow {

	private CurrentState currentState;
	private Database searchVehicleDatabase;
	private CtrlRemoveVehicle removeVehicleCtrl;

	/**
	 * Create the application window.
	 */
	public WindowRemoveVehicle(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		searchVehicleDatabase = mainDatabase;
		removeVehicleCtrl = new CtrlRemoveVehicle(searchVehicleDatabase);
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
		comboSearchOptions.setBounds(106, 32, 192, 23);
		
		final Combo comboSearchOptionsResults = new Combo(container, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
		comboSearchOptionsResults.setBounds(106, 61, 192, 23);
		final List listSearchResults = new List(container, SWT.BORDER);
		listSearchResults.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
				int selectionIndex = listSearchResults.getSelectionIndex();
				
				if(selectionIndex != -1)
				{
					ArrayList<Vehicle> vehicleList = removeVehicleCtrl.getVehicleList();
					Vehicle selectedVehicle = vehicleList.get(selectionIndex);
					WindowVehicleDetails vehicleDeitalsWindow = new WindowVehicleDetails(selectedVehicle, true);
					vehicleDeitalsWindow.open();
				}	
				else
					JOptionPane.showMessageDialog(null, "Você precisa selecionar um veículo!");
			}
			
		});
		listSearchResults.setBounds(337, 60, 157, 105);
		
		final Label lblUnity = new Label(container, SWT.NONE);
		lblUnity.setBounds(304, 64, 30, 25);
		
		Button btnRemove = new Button(container, SWT.NONE);
		btnRemove.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			//função de ação quando botão Remover é pressionado
			public void widgetSelected(SelectionEvent e)
			{
				int selectionIndex = listSearchResults.getSelectionIndex();
				if(selectionIndex != -1) 
				{
					if (removeVehicleCtrl.RemoveVehicle(selectionIndex))
					{
						//atualiza a lista para removendo veículos que não estão mais na lista
						listSearchResults.removeAll();
						
						int optionIndex = comboSearchOptions.getSelectionIndex();				
						String chosenOption = comboSearchOptions.getItem(optionIndex);
						
						int optionResultIndex = comboSearchOptionsResults.getSelectionIndex();
						String chosenOptionResult = comboSearchOptionsResults.getItem(optionResultIndex);
						
						ArrayList<String> resultsListItems = removeVehicleCtrl.getResultsListItems(chosenOption, chosenOptionResult, true, null);
						
						for(int i = 0; i < resultsListItems.size(); i++)
						{
							listSearchResults.add(resultsListItems.get(i));
						}
						
						JOptionPane.showMessageDialog(null, "Veículo removido.");
					}
					else
						JOptionPane.showMessageDialog(null, "ERRO ao remover veículo .");
				}
				else
					JOptionPane.showMessageDialog(null, "Você precisa selecionar um veículo!");
				
				if(removeVehicleCtrl.getVehicleList().isEmpty())
				{
					listSearchResults.setEnabled(false);
					return;
				}
			}
		});
		
		btnRemove.setBounds(52, 119, 96, 30);
		btnRemove.setText("Remover");
		
		
		//função que executa o que acontece quando o usuário
		//seleciona as opções da primeira combo box.
		comboSearchOptions.addModifyListener(new ModifyListener() 
		{
			public void modifyText(ModifyEvent arg0)
			{
				lblUnity.setText("");
				comboSearchOptionsResults.removeAll(); //limpa os resultados anteriores
				listSearchResults.removeAll();
				
				int optionIndex = comboSearchOptions.getSelectionIndex();
				String optionName = comboSearchOptions.getItem(optionIndex);
				
				ArrayList<String> secondComboItems = removeVehicleCtrl.getSecondComboItems(optionName, true, null);
				
				if(optionName.equals("Potência do motor"))
					lblUnity.setText("cv");
				
				else if(optionName.equals("Comprimento máximo"))
					lblUnity.setText("m");
				
				
				for(int i = 0; i < secondComboItems.size(); i++)
				{
					comboSearchOptionsResults.add(secondComboItems.get(i));
				}
			}
		});
		
		//função que executa o que acontece quando o usuário
		//seleciona as opções da segunda combo box.
		comboSearchOptionsResults.addModifyListener(new ModifyListener() 
		{
			//função que preenche a lista com as opções de veículos
			//disponíveis do parâmetro solicitado pelo usuário.
			public void modifyText(ModifyEvent arg0)
			{
				listSearchResults.setEnabled(true);
				
				if(comboSearchOptionsResults.getItemCount() == 0)
					return;
				
				//limpa a lista para inserir os novos resultados				
				listSearchResults.removeAll();

				int optionIndex = comboSearchOptions.getSelectionIndex();				
				String chosenOption = comboSearchOptions.getItem(optionIndex);
				
				int optionResultIndex = comboSearchOptionsResults.getSelectionIndex();
				String chosenOptionResult = comboSearchOptionsResults.getItem(optionResultIndex);

				ArrayList<String> resultsListItems = removeVehicleCtrl.getResultsListItems(chosenOption, chosenOptionResult, true, null);
				
				for(int i = 0; i < resultsListItems.size(); i++)
				{
					listSearchResults.add(resultsListItems.get(i));
				}
					 
			}
		});
		
		//Adicionando as opções à ComboBox
		comboSearchOptions.add("Categoria");	
		comboSearchOptions.add("Potência do motor");	
		comboSearchOptions.add("Ano");	
		comboSearchOptions.add("Comprimento máximo");
		comboSearchOptions.add("Marca");
		comboSearchOptions.add("Modelo");		
		comboSearchOptions.select(0); //Coloca a primeira opção como default
		
		Label lblSearchOptions = new Label(container, SWT.NONE);
		lblSearchOptions.setBounds(10, 35, 90, 25);
		lblSearchOptions.setText("Pesquisar por :");
		
		Button btnReturn = new Button(container, SWT.NONE);
		btnReturn.setBounds(359, 184, 96, 30);
		btnReturn.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			//função de ação quando botão "Voltar" é pressionado
			public void widgetSelected(SelectionEvent e) 
			{
				currentState.setChosenAction("Voltar");
				close();
			}
		});
		btnReturn.setText("Voltar");
		
		Label lblAvailableModels = new Label(container, SWT.NONE);
		lblAvailableModels.setBounds(344, 35, 128, 20);
		lblAvailableModels.setText("Modelos disponíveis:");
		
		Button btnDetails = new Button(container, SWT.NONE);
		btnDetails.addSelectionListener(new SelectionAdapter()
		{
			@Override
			//função de ação quando botão Detalhes é pressionado
			public void widgetSelected(SelectionEvent e) 
			{
				int selectionIndex = listSearchResults.getSelectionIndex();
				
				if(selectionIndex != -1)
				{
					ArrayList<Vehicle> vehicleList = removeVehicleCtrl.getVehicleList();
					Vehicle selectedVehicle = vehicleList.get(selectionIndex);
					WindowVehicleDetails vehicleDeitalsWindow = new WindowVehicleDetails(selectedVehicle, true);
					vehicleDeitalsWindow.open();
				}	
				else
					JOptionPane.showMessageDialog(null, "Você precisa selecionar um veículo!");
			}
		});
		btnDetails.setBounds(175, 119, 96, 30);
		btnDetails.setText("Detalhes");

		return container;
	}

	private void createActions()
	{
		// Create the actions
	}


	@Override
	protected void configureShell(Shell newShell) 
	{
		super.configureShell(newShell);
		newShell.setText("Remover Veículo");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize()
	{
		return new Point(510, 273);
	}
}
