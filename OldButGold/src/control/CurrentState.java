package control;

import person.Person;

public class CurrentState
{

	//Classe que serve de controle para todas as a��es executadas nas janelas.
	private
	
	Person currentUser; //usu�rio que est� logado no sistema
	String chosenAction; //texto do bot�o pressionado pelo usu�rio
	
	public Person getCurrentUser() 
	{
		return currentUser;
	}
	
	public void setCurrentUser(Person currentUser) 
	{
		this.currentUser = currentUser;
	}
	
	public String getChosenAction() 
	{
		return chosenAction;
	}
	
	public void setChosenAction(String chosenAction) 
	{
		this.chosenAction = chosenAction;
	}
	
	
}
