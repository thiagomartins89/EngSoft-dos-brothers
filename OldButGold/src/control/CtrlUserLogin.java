package control;

import javax.swing.JOptionPane;

import person.Person;
import db.Database;

public class CtrlUserLogin
{

	private Database ctrlUserLoginDatabase;

	public CtrlUserLogin(Database mainDatabase)
	{
		this.ctrlUserLoginDatabase = mainDatabase;
	}
	
	public CurrentState setCurrentState(String username, String password, CurrentState currentState)
	{		
		Person user = ctrlUserLoginDatabase.getUser(username);
		
		if(user != null)
		{
			if(user.getPassword().equals(password))
			{
				JOptionPane.showMessageDialog(null, "Bem vindo, " + user.getName() + "!");
				
				currentState.setChosenAction("Logar");
				currentState.setCurrentUser(user);						
			}					
			else
			{
				JOptionPane.showMessageDialog(null, "Senha incorreta!");
				return null;
			}
		}				
		else
		{
			JOptionPane.showMessageDialog(null, "Usuário não existe!");
			return null;
		}
		
		return currentState;
	}
}
