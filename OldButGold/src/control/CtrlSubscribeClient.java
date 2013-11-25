package control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

	public String subscribeVehicle(String name, String cpf, String cnh,
			String birthdate, String id, String password) 
	{
		if (checkEntries(name, cpf, cnh, birthdate, id, password) == false)
			return "Todos os campos devem estar preenchidos.";

		switch (checkBirthdate(birthdate)) {
		case 1:
			return "A data de nascimento deve estar no formato 'DD/MM/AAAA'.";
		case 2:
			return "O cliente deve ser maior de 18 anos.";
		case 3:
			return "Ano inválido.";
		}

		if (checkName(name) == false)
			return "Nome inválido.";

		if (checkCPF(cpf) == false)
			return "CPF inválido.";

		if (checkCNH(cnh) == false)
			return "CNH inválida.";

		if (checkID(id) == false)
			return "Nome de usuário inválido.";
		
		if (checkPassword(password) == false)
			return "Password inválido.";

		Client client = new Client();

		try {
			client.setName(name);
			client.setCpf(Long.parseLong(cpf));
			client.setCnh(Long.parseLong(cnh));
			client.setId(id);
			client.setPassword(password);
			
			Calendar date = new GregorianCalendar();
			
			String day = birthdate.substring(2);
			birthdate.substring(1);
			String month = birthdate.substring(2);
			birthdate.substring(1);
			String year = birthdate.substring(4);
			
			date.set(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
			
			client.setBirthDate(date);
			
			mainDatabase.addUser(client);

			return "Cliente cadastrado com sucesso!";
		} 
		catch (Exception e) 
		{
			return "Não foi possível cadastrar o cliente. Verifique as entradas e tente novamente.";
		}
	}
	
	private boolean checkPassword(String password) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	private boolean checkID(String id) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	private boolean checkCNH(String cnh) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	private boolean checkCPF(String cpf) 
	{
		String auxiliaryString = cpf.replace(".", "");
		auxiliaryString = cpf.replace("-", "");			//?
		
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
	 * Verifica se o ano recebido como parâmetro é válido. Retorna 0, caso o ano
	 * seja correto. Retorna 1, caso o ano não esteja no formato 'AAAA'. Retorna
	 * 2, caso o cliente seja menor de idade (incapaz de dirigir por lei). Retorna 3, para outros
	 * problemas.
	 */
	private int checkBirthdate(String birthdate)
	{
		try
		{
			if (birthdate.length() != 10)		
				return 1;
			
			SimpleDateFormat dataCompleta = new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dataCompleta.parse(birthdate));

			if(Integer.parseInt(dataCompleta.format(calendar.getTime())) == 10)
			{
				
			}
			
			//transformar/usar datas
			//JOptionPane.showMessageDialog(null, simpleDateFormat.format(calendar.getTime()));

			
			/*
			if (birthdate.equals("00/00/0000"))
				return 3;
			 */
		} 
		catch (Exception e1)
		{
			return 1;
		}

		return 0;
	}
	
	/**
	 * Verifica se todos os campos recebidos como parâmetro estão preenchidos.
	 */
	private boolean checkEntries(String name, String cpf, String cnh, String birthdate, String id, String password)
	{
		if (name.isEmpty() || cpf.isEmpty() || cnh.isEmpty() || birthdate.isEmpty() || id.isEmpty() || password.isEmpty())
			return false;

		return true;
	}

}
