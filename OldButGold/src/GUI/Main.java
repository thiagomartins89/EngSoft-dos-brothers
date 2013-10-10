package GUI;

import org.eclipse.swt.widgets.Display;

public class Main
{

	public static void main(String[] args){
	
		try 
		{	
			JanelaLogin window = new JanelaLogin();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	

}
