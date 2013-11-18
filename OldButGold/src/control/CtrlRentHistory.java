package control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import person.Client;

public class CtrlRentHistory
{
	public ArrayList<String> getRentHistoryList(CurrentState rentCurrentState)
	{
		ArrayList<String> rentHistoryList = new ArrayList<String>();
		Client client = (Client) rentCurrentState.getCurrentUser();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		for(int i = 0; i < client.getRentList().size(); i++)
		{
			rentHistoryList.add("05/12/2013");
			rentHistoryList.add("08/12/2013");
			rentHistoryList.add(client.getRentList().get(i).getRentVehicle().getModel());
		}
		
		return rentHistoryList;
	}
}