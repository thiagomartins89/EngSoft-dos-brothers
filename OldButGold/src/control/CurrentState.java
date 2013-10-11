package control;

import person.Person;

public class CurrentState
{

	//Classe que serve de controle para todas as ações executadas nas janelas.
	private
	
	Person currentUser; //usuário que está logado no sistema
	String chosenAction; //texto do botão pressionado pelo usuário
	
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
