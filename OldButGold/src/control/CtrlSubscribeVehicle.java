package control;

import java.util.Calendar;
import java.util.GregorianCalendar;

import vehicle.Vehicle;
import db.Database;

public class CtrlSubscribeVehicle
{
	private Database mainDatabase;

	public CtrlSubscribeVehicle(Database mainDatabase)
	{
		this.mainDatabase = mainDatabase;
	}

	/**
	 * Recebe todos os dados do veículo como parâmetro, faz as devidas
	 * verificações, e, caso não encontre qualquer problema com as entradas,
	 * adiciona o veículo ao banco de dados.
	 */
	public String subscribeVehicle(String brand, String model,
			String manufacturingDate, String mileage, String length,
			String category, String plate, String enginePower, String width)
	{
		if (checkEntries(brand, model, manufacturingDate, mileage, length,
				category, plate, enginePower, width) == false)
			return "Todos os campos devem estar preenchidos.";

		switch (checkManufacturingDate(manufacturingDate))
		{
		case 1:
			return "O ano deve estar no formato 'AAAA'.";
		case 2:
			return "O ano não pode ser maior que o ano atual.";
		case 3:
			return "Ano inválido.";
		}

		if (checkMileage(mileage) == false)
			return "Quilometragem inválida.";

		length = length.replace(",", ".");

		if (checkLength(length) == false)
			return "Comprimento inválido.";

		if (checkEnginePower(enginePower) == false)
			return "Potência inválida.";

		width = width.replace(",", ".");

		if (checkWidth(width) == false)
			return "Largura inválida.";

		Vehicle vehicle = new Vehicle();

		try
		{
			vehicle.setBrand(brand);
			vehicle.setModel(model);
			vehicle.setManufacturingDate(Integer.parseInt(manufacturingDate));
			vehicle.setMileage(Integer.parseInt(mileage));
			vehicle.setLength(Double.parseDouble(length));
			vehicle.setCategory(category);
			vehicle.setPlate(plate);
			vehicle.setEnginePower(Integer.parseInt(enginePower));
			vehicle.setWidth(Double.parseDouble(width));
			vehicle.setAvailable(true);

			mainDatabase.addVehicle(vehicle);

			return "Veículo adicionado com sucesso!";
		} catch (Exception e)
		{
			return "Não foi possível adicionar o veículo. Verifique as entradas e tente novamente.";
		}
	}

	/**
	 * Verifica se todos os campos recebidos como parâmetro estão preenchidos.
	 */
	private boolean checkEntries(String brand, String model,
			String manufacturingDate, String mileage, String length,
			String category, String plate, String enginePower, String width)
	{
		if (brand.isEmpty() || model.isEmpty() || manufacturingDate.isEmpty()
				|| mileage.isEmpty() || length.isEmpty() || category.isEmpty()
				|| plate.isEmpty() || enginePower.isEmpty() || width.isEmpty())
			return false;

		return true;
	}

	/**
	 * Verifica se o ano recebido como parâmetro é válido. Retorna 0, caso o ano
	 * seja correto. Retorna 1, caso o ano não esteja no formato 'AAAA'. Retorna
	 * 2, caso o ano seja maior que o ano atual. Retorna 3, para outros
	 * problemas.
	 */
	private int checkManufacturingDate(String manufacturingDate)
	{
		try
		{
			if (manufacturingDate.length() != 4)
				return 1;

			if (manufacturingDate.equals("0000"))
				return 3;

			Calendar calendar = new GregorianCalendar();

			if (Integer.parseInt(manufacturingDate) <= 0
					|| Integer.parseInt(manufacturingDate) > calendar
							.get(Calendar.YEAR))
				return 2;
		} catch (NumberFormatException e1)
		{
			return 1;
		}

		return 0;
	}

	/**
	 * Verifica se a quilometragem recebida como parâmetro é válida.
	 */
	private boolean checkMileage(String mileage)
	{
		try
		{
			if (Integer.parseInt(mileage) < 0)
				return false;
		} catch (NumberFormatException e1)
		{
			return false;
		}

		return true;
	}

	/**
	 * Verifica se o comprimento recebido como parâmetro é válido.
	 */
	private boolean checkLength(String length)
	{
		try
		{
			if (Double.parseDouble(length) <= 0)
				return false;
		} catch (NumberFormatException e1)
		{
			return false;
		}

		return true;
	}

	/**
	 * Verifica se a potência recebida como parâmetro é válida.
	 */
	private boolean checkEnginePower(String enginePower)
	{
		try
		{
			if (Integer.parseInt(enginePower) <= 0)
				return false;
		} catch (NumberFormatException e1)
		{
			return false;
		}

		return true;
	}

	/**
	 * Verifica se a largura recebida como parâmetro é válida.
	 */
	private boolean checkWidth(String width)
	{
		try
		{
			if (Double.parseDouble(width) <= 0)
				return false;
		} catch (NumberFormatException e1)
		{
			return false;
		}

		return true;
	}
}