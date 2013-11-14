//Classe que serve de controle às janelas que precisam pesquisar carros

package control;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import vehicle.Vehicle;

import db.Database;

public class CtrlSearchVehicle
{
	private Database ctrlSearchVehicleDatabase;
	ArrayList<Vehicle> resultVehiclesList = new ArrayList<Vehicle>();
	
	public CtrlSearchVehicle(Database mainDatabase)
	{
		this.ctrlSearchVehicleDatabase = mainDatabase;
	}
	
	//função que verifica a opção escolhida pelo usuário e retorna a lista
	//de opções disponíveis do tipo escolhido, de forma ordenada.
	//Ex: se tiver apenas uma moto e um carro e o usuário selecionar categoria,
	//a função retornará uma lista contendo A e B.
	public ArrayList<String> getSecondComboItems(String optionName, boolean isEmployee) 
	{
		ArrayList<Vehicle> vehicleList = ctrlSearchVehicleDatabase.getVehicleList();
		ArrayList<String> secondComboItems = new ArrayList<String>();
		
		switch(optionName)
		{
			case "Categoria":
				//lista auxiliar para ordenar os itens
				ArrayList<String> sortCategoryList = new ArrayList<String>();
				
				for(int i=0; i < vehicleList.size(); i++)
				{
					if(vehicleList.get(i).IsAvailable() || isEmployee)
						if(!sortCategoryList.contains(vehicleList.get(i).getCategory()))
							sortCategoryList.add(vehicleList.get(i).getCategory());
				}
				
				Collections.sort(sortCategoryList);
				secondComboItems = sortCategoryList;
				break;
				
			case "Potência do motor":
				
				ArrayList<Integer> sortEnginePowerList = new ArrayList<Integer>();
				
				for(int i=0; i < vehicleList.size(); i++)
				{
					if(vehicleList.get(i).IsAvailable() || isEmployee)
						if(!sortEnginePowerList.contains(vehicleList.get(i).getEnginePower()))
							sortEnginePowerList.add(vehicleList.get(i).getEnginePower());
					
				}				
				Collections.sort(sortEnginePowerList);
				
				for(int i=0; i < sortEnginePowerList.size(); i++)
				{
					secondComboItems.add(sortEnginePowerList.get(i).toString());
				}
				
				break;
				
			case "Ano":
				ArrayList<Integer> sortYearList = new ArrayList<Integer>();
				
				for(int i=0; i < vehicleList.size(); i++)
				{
					if(vehicleList.get(i).IsAvailable() || isEmployee)
						if(!sortYearList.contains(vehicleList.get(i).getManufacturingDate()))
							sortYearList.add(vehicleList.get(i).getManufacturingDate());
				}						
				Collections.sort(sortYearList);
				
				for(int i=0; i < sortYearList.size(); i++)
				{
					secondComboItems.add(sortYearList.get(i).toString());
				}
		
				break;
				
			case "Comprimento máximo":
				ArrayList<Double> sortMaxLengthList = new ArrayList<Double>();
				
				for(int i=0; i < vehicleList.size(); i++)
				{
					if(vehicleList.get(i).IsAvailable() || isEmployee)
						if(!sortMaxLengthList.contains(vehicleList.get(i).getLength()))
							sortMaxLengthList.add(vehicleList.get(i).getLength());
				}						
				Collections.sort(sortMaxLengthList);
				
				for(int i=0; i < sortMaxLengthList.size(); i++)
				{
					secondComboItems.add(sortMaxLengthList.get(i).toString());
				}
				
				break;
				
			case "Marca":
				ArrayList<String> sortBrandList = new ArrayList<String>();
				
				for(int i=0; i < vehicleList.size(); i++)
				{
					if(vehicleList.get(i).IsAvailable() || isEmployee)
						if(!sortBrandList.contains(vehicleList.get(i).getBrand()))
							sortBrandList.add(vehicleList.get(i).getBrand());
				}						
				Collections.sort(sortBrandList);
				
				secondComboItems = sortBrandList;
					
				break;
				
			case "Modelo":
				ArrayList<String> sortModelList = new ArrayList<String>();
				
				for(int i=0; i < vehicleList.size(); i++)
				{
					if(vehicleList.get(i).IsAvailable() || isEmployee)
						if(!sortModelList.contains(vehicleList.get(i).getModel()))
							sortModelList.add(vehicleList.get(i).getModel());
				}						
				Collections.sort(sortModelList);
				
				secondComboItems = sortModelList;

				break;
		}
		return secondComboItems;
	}
	
	//Retornará apenas os disponíveis se o usuário for um cliente, e todos os carros 
	//do sistema se for um funcionário.
	public ArrayList<String> getResultsListItems(String chosenOption, String chosenOptionResult, boolean isEmployee)
	{
		ArrayList<Vehicle> vehicleList = ctrlSearchVehicleDatabase.getVehicleList();
		ArrayList<String> listItems = new ArrayList<String>();
		
		resultVehiclesList.clear();
		
		switch(chosenOption)
		{
			case "Categoria":						
				for(int i = 0; i < vehicleList.size(); i++)
				{
					if(vehicleList.get(i).getCategory().equals(chosenOptionResult))
					{
						if(isEmployee || vehicleList.get(i).IsAvailable())
						{
							resultVehiclesList.add(vehicleList.get(i));
							listItems.add(vehicleList.get(i).getModel());
						}
					}
				}
								
				break; //fim do Categoria
				
			case "Potência do motor":
				for(int i = 0; i < vehicleList.size(); i++)
				{
					int vehicleEnginePower = vehicleList.get(i).getEnginePower();
					String strEnginePower = ("" + vehicleEnginePower);
					if(strEnginePower.equals(chosenOptionResult))
					{
						if(isEmployee || vehicleList.get(i).IsAvailable())
						{
							resultVehiclesList.add(vehicleList.get(i));
							listItems.add(vehicleList.get(i).getModel());
						}
					}
					
				}
				break; //fim do Potência
				
			case "Ano":
				for(int i = 0; i < vehicleList.size(); i++)
				{
					int vehicleManufacturingYear = vehicleList.get(i).getManufacturingDate();
					String strManufacturingYear = ("" + vehicleManufacturingYear);
					if(strManufacturingYear.equals(chosenOptionResult))
					{
						if(isEmployee || vehicleList.get(i).IsAvailable())
						{
							resultVehiclesList.add(vehicleList.get(i));
							listItems.add(vehicleList.get(i).getModel());
						}
					}
				}
				break;
				
			case "Comprimento máximo":
				for(int i = 0; i < vehicleList.size(); i++)
				{
					double vehicleMaxLength = vehicleList.get(i).getLength();
					String strMaxLength = ("" + vehicleMaxLength);
					if(strMaxLength.equals(chosenOptionResult) || vehicleMaxLength < Double.parseDouble(chosenOptionResult))
					{
						if(isEmployee || vehicleList.get(i).IsAvailable())
						{
							resultVehiclesList.add(vehicleList.get(i));
							listItems.add(vehicleList.get(i).getModel());
						}
					}
				}
				break;
				
			case "Marca":
				for(int i = 0; i < vehicleList.size(); i++)
				{
					String vehicleBrand = vehicleList.get(i).getBrand();
					if(vehicleBrand.equals(chosenOptionResult))
					{
						if(isEmployee || vehicleList.get(i).IsAvailable())
						{
							resultVehiclesList.add(vehicleList.get(i));
							listItems.add(vehicleList.get(i).getModel());
						}
					}
				}			
				break;
				
			case "Modelo":
				for(int i = 0; i < vehicleList.size(); i++)
				{
					String vehicleModel = vehicleList.get(i).getModel();
					if(vehicleModel.equals(chosenOptionResult))
					{
						if(isEmployee || vehicleList.get(i).IsAvailable())
						{
							resultVehiclesList.add(vehicleList.get(i));
							listItems.add(vehicleModel);
						}
					}
				}	
				break;
			}
		
		if(listItems.size() == 0)
		{
			listItems.add("Não há veículos disponíveis");
			listItems.add("para essa pesquisa");
		}	
		return listItems;
		
	}
	
	public ArrayList<Vehicle> getVehicleList()
	{
		return resultVehiclesList;
	}

}
