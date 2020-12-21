package  it.unisa.di.smartblog.spec;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecsManager {
	
	static {
		dao = new SpecDao();
	}
	
	public List<Spec> searchAll() throws SQLException{
		return dao.getAll();
	}
	
	public List<Spec> searchByName(String name) throws EmptyFieldException, SQLException{
		return dao.getByName(name);
	}
	
	public Spec searchById(int id) throws SQLException {
		return dao.getById(id);
	}
	
	public boolean createSpec(String deviceName, String releaseDate, String image, String OS, String CPU, String chipset, String GPU, String RAM, String internalMemory, String displayInches, int battery, double price) throws SQLException, SpecMismatchException {
		if(checkSpecValidation(deviceName, releaseDate, image, OS, CPU, chipset, GPU, RAM, internalMemory, displayInches, battery, price)) {
			Spec s = new Spec(deviceName, releaseDate, image, OS, CPU, chipset, GPU, RAM, internalMemory, displayInches, battery, price);
			return dao.saveSpec(s);
		} else return false;
	}
	
	public boolean deleteSpec(int id) throws SQLException {
		return dao.deleteSpec(id);
	}
	
	public boolean setScores(int reviewerId, int specId, double performance, double display, double camera) throws SQLException {
		if(performance<0 || performance>5 || display<0 || display>5 || camera<0 || camera>5) return false;
		return dao.updateSpecScores(reviewerId, specId, performance*2, display*2, camera*2);
	}
	
	private boolean checkSpecValidation(String deviceName, String releaseDate, String image, String OS, String CPU, String chipset, String GPU, String RAM, String internalMemory, String displayInches, int battery, double price) throws SpecMismatchException{
		if(deviceName== null || releaseDate == null || image == null || OS == null || CPU == null || chipset == null || GPU == null || RAM == null || internalMemory == null || displayInches == null) throw new SpecMismatchException("Field/s cannot be null");
		if(deviceName.equals("") || releaseDate.equals("") || image.equals("") || OS.equals("") || CPU.equals("") || chipset.equals("") || GPU.equals("") || RAM.equals("") || internalMemory.equals("") || displayInches.equals("")) throw new SpecMismatchException("Field/s cannot be empty");
		
		if(price<0) throw new SpecMismatchException("Price cannot be negative");
		
		Pattern pattern = Pattern.compile("[0-9]{4}/[0-1][0-9]");
		Matcher matcher = pattern.matcher(releaseDate);
		if(!matcher.find()) throw new SpecMismatchException("Invalid releaseDate format");

		String[] date = releaseDate.split("/");
		int year = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		if(year<1973 || month<1 || month>12) throw new SpecMismatchException("Invalid releaseDate value");
		
		pattern = Pattern.compile("(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})");
		matcher = pattern.matcher(image);
		if(!matcher.find()) throw new SpecMismatchException("Invalid image url format");
		
		
		String[] checkRAM = RAM.split(" ");			
		try {
			if(Integer.parseInt(checkRAM[0])<=0) throw new SpecMismatchException("RAM cannot be negative");
			if(!checkRAM[1].equals("MB") && !checkRAM[1].equals("GB"))throw new SpecMismatchException("Invalid RAM field, has to be MB or GB");
		} catch(NumberFormatException e) {
			e.printStackTrace();
			throw new SpecMismatchException("RAM has to be a number");
		}
		
		
		String[] checkMemory = internalMemory.split(" ");		
		try {
			if(Integer.parseInt(checkMemory[0])<=0) throw new SpecMismatchException("internalMemory cannot be negative");
			if(!checkMemory[1].equals("MB") && !checkMemory[1].equals("GB")) throw new SpecMismatchException("Invalid internalMemory field, has to be MB or GB");
		} catch(NumberFormatException e) {
			e.printStackTrace();
			throw new SpecMismatchException("internalMemory has to be a number");
		}		
		
		try {
			if(Double.parseDouble(displayInches)<=0) throw new SpecMismatchException("displayInches cannot be negative");
		} catch(NumberFormatException e) {
			e.printStackTrace();
			throw new SpecMismatchException("displayInches has to be a number");
		}
		
		return true;		
	}
	
	public int searchMinBattery() throws SQLException{
		return dao.getMinBattery();
	}
	
	public int searchMaxBattery() throws SQLException{
		return dao.getMaxBattery();
	}
	
	public List<Spec> searchByPrice(double price) throws SQLException, PriceException{
		return dao.getByPrice(price);
	}
	
	private static SpecDao dao;
}