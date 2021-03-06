package GUI;

import java.util.ArrayList;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

import control.CtrlClientVehicleRent;
import control.CurrentState;
import db.Database;

public class WindowSearchVehicle extends ApplicationWindow {

	private CurrentState currentState;
	private Database searchVehicleDatabase;
	private CtrlClientVehicleRent searchVehicleCtrl;

	/**
	 * Create the application window.
	 */
	public WindowSearchVehicle(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		searchVehicleDatabase = mainDatabase;
		searchVehicleCtrl = new CtrlClientVehicleRent(searchVehicleDatabase);
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
		listSearchResults.setBounds(337, 60, 148, 105);
		
		final Label lblUnity = new Label(container, SWT.NONE);
		lblUnity.setBounds(304, 64, 30, 25);
		
		Button btnRent = new Button(container, SWT.NONE);
		btnRent.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			//fun��o de a��o quando bot�o Locar � pressionado
			public void widgetSelected(SelectionEvent e)
			{
				listSearchResults.getSelection();
			}
		});
		
		btnRent.setBounds(52, 119, 96, 30);
		btnRent.setText("Locar");
		
		
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
				
				ArrayList<String> secondComboItems = searchVehicleCtrl.getSecondComboItems(optionName, false, null);
				
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
		comboSearchOptionsResults.addModifyListener(new ModifyListener() 
		{
			//fun��o que preenche a lista com as op��es de ve�culos
			//dispon�veis do par�metro solicitado pelo usu�rio.
			public void modifyText(ModifyEvent arg0)
			{
				if(comboSearchOptionsResults.getItemCount() == 0)
					return;
				
				//limpa a lista para inserir os novos resultados				
				listSearchResults.removeAll();
				
				int optionIndex = comboSearchOptions.getSelectionIndex();				
				String chosenOption = comboSearchOptions.getItem(optionIndex);
				
				int optionResultIndex = comboSearchOptionsResults.getSelectionIndex();
				String chosenOptionResult = comboSearchOptionsResults.getItem(optionResultIndex);

				ArrayList<String> resultsListItems = searchVehicleCtrl.getResultsListItems(chosenOption, chosenOptionResult, false, null);
				
				for(int i = 0; i < resultsListItems.size(); i++)
				{
					listSearchResults.add(resultsListItems.get(i));
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
		lblSearchOptions.setBounds(10, 35, 90, 25);
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
		lblAvailableModels.setBounds(344, 35, 128, 20);
		lblAvailableModels.setText("Modelos dispon�veis:");
		
		Button btnDetails = new Button(container, SWT.NONE);
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
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize()
	{
		return new Point(500, 273);
	}
}
