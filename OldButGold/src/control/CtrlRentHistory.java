/**
 * Classe que serve de controle � janela que exibe o hist�rico de loca��es.
 */

package control;

import java.text.SimpleDateFormat;

import person.Client;
import vehicle.Vehicle;
import db.Database;
import db.Rent;

public class CtrlRentHistory
{
	CurrentState rentCurrentState;
	Client currentClient;
	Database mainDatabase;
	
	public CtrlRentHistory(CurrentState rentCurrentState) {
		//super();
		this.rentCurrentState = rentCurrentState;
		this.currentClient = (Client) rentCurrentState.getCurrentUser();
	}
	
	public CtrlRentHistory(CurrentState rentCurrentState, Database mainDatabase) {
		//super();
		this.rentCurrentState = rentCurrentState;
		this.mainDatabase = mainDatabase;
	}

	public int getRentListSize()
	{
		return currentClient.getRentList().size();
	}
	
	public int getRentListSize(String clientUsername)
	{
		Client client = (Client) mainDatabase.getUser(clientUsername);
		if(client != null)
			return client.getRentList().size();
		return 0;
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return data de loca��o do ve�culo
	 */
	public String getRentWithdrawalDate(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return simpleDateFormat.format(rent.getWithdrawalDate().getTime());
	}
	
	public String getRentWithdrawalDate(String clientUsername, int index)
	{
		Client client = (Client) mainDatabase.getUser(clientUsername);
		if(client == null)
			return "";
		
		Rent rent = client.getRentList().get(index);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return simpleDateFormat.format(rent.getWithdrawalDate().getTime());
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return data prevista para devolu��o do ve�culo
	 */
	public String getRentReturnDate(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return simpleDateFormat.format(rent.getReturnDate().getTime());
	}
	
	public String getRentReturnDate(String clientUsername, int index)
	{
		Client client = (Client) mainDatabase.getUser(clientUsername);
		if(client == null)
			return "";
		
		Rent rent = client.getRentList().get(index);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return simpleDateFormat.format(rent.getReturnDate().getTime());
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return modelo do ve�culo
	 */
	public String getRentVehicleModel(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return rent.getRentVehicle().getModel();
	}
	
	public String getRentVehicleModel(String clientUsername, int index)
	{
		Client client = (Client) mainDatabase.getUser(clientUsername);
		if(client == null)
			return "";
		
		Rent rent = client.getRentList().get(index);
		
		return rent.getRentVehicle().getModel();
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return ve�culo locado
	 */
	public Vehicle getRentVehicle(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return rent.getRentVehicle();
	}
	
	public Vehicle getRentVehicle(String clientUsername, int index)
	{
		Client client = (Client) mainDatabase.getUser(clientUsername);
		if(client == null)
			return null;
		
		Rent rent = client.getRentList().get(index);
		
		return rent.getRentVehicle();
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return marca do ve�culo
	 */
	public String getRentVehicleBrand(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return rent.getRentVehicle().getBrand();
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return categoria do ve�culo
	 */
	public String getRentVehicleCategory(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return rent.getRentVehicle().getCategory();
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return valor da di�ria do ve�culo
	 */
	public String getRentVehicleDailyPrice(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getDailyPrice());
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return pot�ncia do ve�culo
	 */
	public String getRentVehicleEnginePower(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getEnginePower());
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return comprimento do ve�culo
	 */
	public String getRentVehicleLength(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getLength());
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return ano de fabrica��o do ve�culo
	 */
	public String getRentVehicleManufacturingDate(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getManufacturingDate());
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return quilometragem do ve�culo
	 */
	public String getRentVehicleMileage(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getMileage());
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return placa do ve�culo
	 */
	public String getRentVehiclePlate(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return rent.getRentVehicle().getPlate();
	}
	
	/**
	 * @param index (�ndice da loca��o na lista de loca��es do cliente)
	 * @return largura do ve�culo
	 */
	public String getRentVehicleWidth(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getWidth());
	}
}