package GUI;

import org.eclipse.swt.widgets.Display;

public class Main
{

	public static void main(String[] args){
	
		try 
		{	
			WindowLogin loginWindow = new WindowLogin();
			loginWindow.setBlockOnOpen(true);
			loginWindow.open();
			Display.getCurrent().dispose();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	

}
