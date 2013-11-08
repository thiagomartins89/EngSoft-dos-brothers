//Esta classe funciona da mesma forma que a WindowSearchVehicle e, inclusive,
//Usa a mesma classe de controle (CtrlSearchVehicle).

package GUI;

import java.util.ArrayList;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import control.CtrlSearchVehicle;
import control.CurrentState;
import db.Database;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class WindowClientCarRent extends ApplicationWindow
{

	private CurrentState currentState;
	private Database clientCarRentDatabase;
	private CtrlSearchVehicle searchVehicleCtrl;
	
	public WindowClientCarRent(CurrentState mainCurrentState, Database mainDatabase) 
	{
		super(null);
		setShellStyle(SWT.MAX);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		currentState = mainCurrentState;
		clientCarRentDatabase = mainDatabase;
		searchVehicleCtrl = new CtrlSearchVehicle(mainDatabase);
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
		
		Button btnRent = new Button(container, SWT.NONE);
		btnRent.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			//função de ação quando botão Sair é pressionado
			public void widgetSelected(SelectionEvent e)
			{
				
			}
		});
		btnRent.setBounds(52, 119, 96, 30);
		btnRent.setText("Locar");
		
		final Combo comboSearchOptions = new Combo(container, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
		comboSearchOptions.setBounds(106, 32, 192, 23);
		
		final Combo comboSearchOptionsResults = new Combo(container, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
		comboSearchOptionsResults.setBounds(106, 61, 192, 23);
		final List listSearchResults = new List(container, SWT.BORDER);
		listSearchResults.setBounds(337, 60, 148, 105);
		
		final Label lblUnity = new Label(container, SWT.NONE);
		lblUnity.setBounds(304, 64, 30, 25);
		
		
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
				
				ArrayList<String> secondComboItems = searchVehicleCtrl.getSecondComboItems(optionName);
				
				if(optionName.equals("Potência do motor"))
					lblUnity.setText("Cv");
				
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
				if(comboSearchOptionsResults.getItemCount() == 0)
					return;
				
				//limpa a lista para inserir os novos resultados				
				listSearchResults.removeAll();
				
				int optionIndex = comboSearchOptions.getSelectionIndex();				
				String chosenOption = comboSearchOptions.getItem(optionIndex);
				
				int optionResultIndex = comboSearchOptionsResults.getSelectionIndex();
				String chosenOptionResult = comboSearchOptionsResults.getItem(optionResultIndex);

				ArrayList<String> resultsListItems = searchVehicleCtrl.getResultsListItems(chosenOption, chosenOptionResult);
				
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
		
		Button btnDetalhes = new Button(container, SWT.NONE);
		btnDetalhes.setBounds(175, 119, 96, 30);
		btnDetalhes.setText("Detalhes");

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
