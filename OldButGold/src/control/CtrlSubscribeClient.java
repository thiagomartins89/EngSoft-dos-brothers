package control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import db.Database;

import person.Client;

public class CtrlSubscribeClient 
{
	
	private Database mainDatabase;

	public CtrlSubscribeClient(Database mainDatabase) 
	{
		this.mainDatabase = mainDatabase;
	}

	public String subscribeClient(String name, String cpf, String cnh,
			String birthdate, String id, String password1, String password2) 
	{
		if (checkEntries(name, cpf, cnh, birthdate, id, password1, password2) == false)
			return "Todos os campos devem estar preenchidos.";

		if(checkBirthdate(birthdate) == false) {
		
			return "A data de nascimento deve estar no formato 'DD/MM/AAAA'.";
		}

		
		if (checkName(name) == false)
			return "Nome inv�lido.";

		if (checkCPF(cpf) == false)
			return "O CPF deve ter 11 d�gitos.";

		if (checkCNH(cnh) == false)
			return "A CNH deve ter 11 d�gitos.";

		if (checkID(id) == false)
			return "O Nome de Usu�rio deve ter no m�nimo 6 d�gitos.";
		
		if (checkPassword(password1, password2) == "tamanho")
			return "A Senha deve ter no m�nimo 6 d�gitos.";
		else if (checkPassword(password1, password2) == "diferentes")
			return "As Senhas devem coincidir.";

		Client client = new Client();

		try {
			client.setName(name);
			client.setCpf(Long.parseLong(cpf));
			client.setCnh(Long.parseLong(cnh));
			client.setId(id);
			client.setPassword(password1);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Calendar birth = new GregorianCalendar();  
			birth.setTime(formatter.parse(birthdate));
			
			client.setBirthDate(birth);
			
			mainDatabase.addUser(client);

			return "Cliente cadastrado com sucesso!";
		} 
		catch (Exception e) 
		{
			return "N�o foi poss�vel cadastrar o cliente. Verifique as entradas e tente novamente.";
		}
	}
	
	private String checkPassword(String password1, String password2) 
	{
		if (!password1.equals(password2))
			return "diferentes";	
		if(password1.length() < 6)
			return "tamanho";
		
		return "ok";
	}

	private boolean checkID(String id) 
	{
		if (id.length()<6)
			return false;	
		
		return true;
	}

	private boolean checkCNH(String cnh) 
	{
		if(cnh.length()!=11) 
			return false;
		
		return true;
	}

	private boolean checkCPF(String cpf) 
	{
		String auxiliaryString = cpf.replace(".", "");
		auxiliaryString = auxiliaryString.replace("-", "");
		
		if(auxiliaryString.length() != 11)
			return false;

		return true;
	}

	private boolean checkName(String name) 
	{
		if(name.length() < 10)
			return false;

		return true;
	}

	/**
	 * Verifica se o ano recebido como par�metro � v�lido. Retorna 0, caso o ano
	 * seja correto. Retorna 1, caso o ano n�o esteja no formato 'AAAA'. Retorna
	 * 2, caso o cliente seja menor de idade (incapaz de dirigir por lei). Retorna 3, para outros
	 * problemas.
	 */
	boolean checkBirthdate(String birthdate)
	{
		try
		{
			if (birthdate.length() != 10)		
				return false;
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			GregorianCalendar birth = new GregorianCalendar();  
			
			birth.setTime(formatter.parse(birthdate)); 
		} 
		catch (Exception e1)
		{
			return false;
		}

		return true;
	}
	
	/**
	 * Verifica se todos os campos recebidos como par�metro est�o preenchidos.
	 */
	private boolean checkEntries(String name, String cpf, String cnh, String birthdate, String id, String password1, String password2)
	{
		if (name.isEmpty() || cpf.isEmpty() || cnh.isEmpty() || birthdate.isEmpty() || id.isEmpty() || password1.isEmpty() || password2.isEmpty())
			return false;

		return true;
	}

}
