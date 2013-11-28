/**
 * Classe que serve de controle à janela que exibe o histórico de locações.
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
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return data de locação do veículo
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
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return data prevista para devolução do veículo
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
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return modelo do veículo
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
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return veículo locado
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
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return marca do veículo
	 */
	public String getRentVehicleBrand(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return rent.getRentVehicle().getBrand();
	}
	
	/**
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return categoria do veículo
	 */
	public String getRentVehicleCategory(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return rent.getRentVehicle().getCategory();
	}
	
	/**
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return valor da diária do veículo
	 */
	public String getRentVehicleDailyPrice(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getDailyPrice());
	}
	
	/**
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return potência do veículo
	 */
	public String getRentVehicleEnginePower(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getEnginePower());
	}
	
	/**
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return comprimento do veículo
	 */
	public String getRentVehicleLength(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getLength());
	}
	
	/**
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return ano de fabricação do veículo
	 */
	public String getRentVehicleManufacturingDate(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getManufacturingDate());
	}
	
	/**
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return quilometragem do veículo
	 */
	public String getRentVehicleMileage(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getMileage());
	}
	
	/**
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return placa do veículo
	 */
	public String getRentVehiclePlate(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return rent.getRentVehicle().getPlate();
	}
	
	/**
	 * @param index (índice da locação na lista de locações do cliente)
	 * @return largura do veículo
	 */
	public String getRentVehicleWidth(int index)
	{
		Rent rent = currentClient.getRentList().get(index);
		
		return String.valueOf(rent.getRentVehicle().getWidth());
	}
}