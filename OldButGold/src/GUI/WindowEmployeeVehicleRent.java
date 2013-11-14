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

import control.CtrlClientVehicleRent;
import control.CtrlEmployeeVehicleRent;
import control.CurrentState;
import db.Database;
import db.Rent;

import org.eclipse.swt.widgets.Text;

import person.Person;
import vehicle.Vehicle;

public class WindowEmployeeVehicleRent extends ApplicationWindow
{

        private CurrentState rentCurrentState;
        private Database employeeVehicleRentDatabase;
        private CtrlEmployeeVehicleRent employeeVehicleRentCtrl;
        private Text txtRentDuration;
        
        public WindowEmployeeVehicleRent(CurrentState mainCurrentState, Database mainDatabase) 
        {
                super(null);
                setShellStyle(SWT.MAX);
                createActions();
                addToolBar(SWT.FLAT | SWT.WRAP);
                addMenuBar();
                addStatusLine();
                rentCurrentState = mainCurrentState;
                employeeVehicleRentDatabase = mainDatabase;
                employeeVehicleRentCtrl = new CtrlEmployeeVehicleRent(mainDatabase);
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
                comboSearchOptions.setBounds(106, 32, 192, 28);
                
                final Combo comboSearchOptionsResults = new Combo(container, SWT.NONE | SWT.DROP_DOWN | SWT.READ_ONLY);
                comboSearchOptionsResults.setBounds(106, 61, 192, 28);
                final List listSearchResults = new List(container, SWT.BORDER);
                
                listSearchResults.addMouseListener(new MouseAdapter() 
                {
                        @Override
                        //função de ação quando se clica duas vezes em um veículo da lista
                        public void mouseDoubleClick(MouseEvent e)
                        {
                                int selectionIndex = listSearchResults.getSelectionIndex();
                                
                                if(selectionIndex != -1)
                                {
                                        ArrayList<Vehicle> vehicleList = employeeVehicleRentCtrl.getVehicleList();
                                        Vehicle selectedVehicle = vehicleList.get(selectionIndex);
                                        WindowVehicleDetails vehicleDeitalsWindow = new WindowVehicleDetails(selectedVehicle);
                                        vehicleDeitalsWindow.open();
                                }        
                                else
                                        JOptionPane.showMessageDialog(null, "Você precisa selecionar um veículo!");
                        }
                });
                
                listSearchResults.setBounds(337, 60, 210, 105);
                
                final Label lblUnity = new Label(container, SWT.NONE);
                lblUnity.setBounds(304, 64, 30, 25);
                
                final Text textClientUsername = new Text(container, SWT.BORDER);
                textClientUsername.setBounds(125, 100, 121, 26);
                
        		txtRentDuration = new Text(container, SWT.BORDER);
        		txtRentDuration.setBounds(125, 148, 121, 26);
                
                Button btnRent = new Button(container, SWT.NONE);
                btnRent.setBounds(64, 197, 96, 30);
                btnRent.addSelectionListener(new SelectionAdapter() 
                {
                    @Override
                    //função de ação quando botão Locar é pressionado
                    public void widgetSelected(SelectionEvent e)
                    {
                        String clientUsername = textClientUsername.getText();
                        
                        Rent newRent = null;
                        
        				if(txtRentDuration.getText().equals(""))
        				{
        					JOptionPane.showMessageDialog(null, "Por favor, indique por quantos dias você deseja locar.");
        					return;
        				}
        				
        				int rentDuration = Integer.parseInt(txtRentDuration.getText());
                        
                        int selectionIndex = listSearchResults.getSelectionIndex();
                        if(selectionIndex != -1) 
                        	newRent = employeeVehicleRentCtrl.MakeCarRent(selectionIndex, clientUsername, rentDuration);                                
                        else
                                JOptionPane.showMessageDialog(null, "Você precisa selecionar um veículo!");
                        
                        if(newRent != null)
                        {
                            listSearchResults.remove(selectionIndex);
                            ArrayList<Vehicle> vehicleList = employeeVehicleRentCtrl.getVehicleList();
                            vehicleList.remove(selectionIndex);
                            
                            if(listSearchResults.getItemCount() == 0)
                            {
                                listSearchResults.add("Não há veículos disponíveis");
                                listSearchResults.add("para essa pesquisa");
                                listSearchResults.setEnabled(false);
                            }
                            
                            //Gera o comprovante de retirada do veículo para o cliente
        					WindowWithdrawalReceipt generateReceiptWindow = new WindowWithdrawalReceipt(newRent);
        					generateReceiptWindow.open();
                        }        
                    }
                });
                btnRent.setText("Locar");
                
                //função que executa o que acontece quando o usuário
                //seleciona as opções da primeira combo box.
                comboSearchOptions.addModifyListener(new ModifyListener() 
                {
                    public void modifyText(ModifyEvent arg0)
                    {
                        lblUnity.setText("");
                        comboSearchOptionsResults.removeAll(); //limpa os resultados anteriores
                        listSearchResults.removeAll();
                        
                        comboSearchOptionsResults.setEnabled(true);
                        
                        int optionIndex = comboSearchOptions.getSelectionIndex();
                        String optionName = comboSearchOptions.getItem(optionIndex);
                        
                        ArrayList<String> secondComboItems = employeeVehicleRentCtrl.getSecondComboItems(optionName);
                        
                        if(secondComboItems.isEmpty())
                        {
                    		comboSearchOptionsResults.add("Não há veículos disponíveis");
                            comboSearchOptionsResults.setEnabled(false);
                            comboSearchOptionsResults.select(0);
                        }
                        
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
                                if(comboSearchOptionsResults.getItemCount() == 0)
                                        return;
                                
                                if(comboSearchOptionsResults.getItem(0).equals("Não há veículos disponíveis"))
                                	return;
                                
                                //limpa a lista para inserir os novos resultados                                
                                listSearchResults.removeAll();
                                listSearchResults.setEnabled(true);
                                
                                int optionIndex = comboSearchOptions.getSelectionIndex();                                
                                String chosenOption = comboSearchOptions.getItem(optionIndex);
                                
                                int optionResultIndex = comboSearchOptionsResults.getSelectionIndex();
                                String chosenOptionResult = comboSearchOptionsResults.getItem(optionResultIndex);

                                ArrayList<String> resultsListItems = employeeVehicleRentCtrl.getResultsListItems(chosenOption, chosenOptionResult);
                                
                                for(int i = 0; i < resultsListItems.size(); i++)
                                {
                                        listSearchResults.add(resultsListItems.get(i));
                                }
                                
                                ArrayList<Vehicle> vehicleList = employeeVehicleRentCtrl.getVehicleList();
                                if(vehicleList.isEmpty())
                                {
                                        listSearchResults.setEnabled(false);
                                        return;
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
                                rentCurrentState.setChosenAction("Voltar");
                                close();
                        }
                });
                btnReturn.setText("Voltar");
                
                Label lblAvailableModels = new Label(container, SWT.NONE);
                lblAvailableModels.setBounds(356, 35, 148, 21);
                lblAvailableModels.setText("Modelos disponíveis:");
                
                Button btnDetails= new Button(container, SWT.NONE);
                
                btnDetails.addSelectionListener(new SelectionAdapter()
                {
                        @Override
                        //função de ação quando botão Detalhes é pressionado
                        public void widgetSelected(SelectionEvent e) 
                        {
                                int selectionIndex = listSearchResults.getSelectionIndex();
                                if(selectionIndex != -1)
                                {
                                        ArrayList<Vehicle> vehicleList = employeeVehicleRentCtrl.getVehicleList();
                                        Vehicle selectedVehicle = vehicleList.get(selectionIndex);
                                        WindowVehicleDetails vehicleDeitalsWindow = new WindowVehicleDetails(selectedVehicle);
                                        vehicleDeitalsWindow.open();
                                }
                                else
                                        JOptionPane.showMessageDialog(null, "Você precisa selecionar um veículo!");
                        }
                });
                
                btnDetails.setBounds(179, 197, 96, 30);
                btnDetails.setText("Detalhes");
                
                Label lblRentToClient = new Label(container, SWT.NONE);
                lblRentToClient.setBounds(10, 103, 114, 30);
                lblRentToClient.setText("Locar para o cliente:\r\n       (Username)");
                
        		Label lblRentDuration = new Label(container, SWT.NONE);
        		lblRentDuration.setBounds(10, 151, 114, 38);
        		lblRentDuration.setText("Tempo de locação:\n            (dias)");

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
                newShell.setText("Old but Gold");
        }

        /**
         * Return the initial size of the window.
         */
        @Override
        protected Point getInitialSize()
        {
                return new Point(563, 283);
        }
}