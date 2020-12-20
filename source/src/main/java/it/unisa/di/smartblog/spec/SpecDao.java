package  it.unisa.di.smartblog.spec;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecDao {
	
    static{
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("smartblog");

        } catch(NamingException e){
            e.printStackTrace();
        }
    }
    
    public synchronized List<Spec> getAll() throws SQLException{
    	Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM spec";
        ArrayList<Spec> result = new ArrayList<>();

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
        	Spec s = new Spec();
        	s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSo(rs.getString("so"));
            s.setCpu(rs.getString("cpu"));
            s.setChipset(rs.getString("chipset"));
            s.setGpu(rs.getString("gpu"));
            s.setRam(rs.getString("ram"));
            s.setMemory(rs.getString("memory"));
            s.setScreenSize(rs.getString("screenSize"));
            s.setImage(rs.getString("image"));
            s.setDisplay(rs.getDouble("display"));
            s.setCamera(rs.getDouble("camera"));
            s.setPerformance(rs.getDouble("performance"));
            s.setBattery(rs.getInt("battery"));
            s.setDate(rs.getString("date"));
            s.setPrice(rs.getDouble("price"));
            result.add(s);
        }
        
        try {
            conn.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
            ps.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        return result;        
    }

    public synchronized List<Spec> getByName(String name) throws EmptyFieldException, SQLException{
        if(name==null || name.equals("")) {
        	throw new EmptyFieldException("This field cannot be empty");
        }

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM spec WHERE spec.name LIKE ?";
        ArrayList<Spec> result = new ArrayList<>();

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, "%"+name+"%");
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
        	Spec s = new Spec();
        	s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSo(rs.getString("so"));
            s.setCpu(rs.getString("cpu"));
            s.setChipset(rs.getString("chipset"));
            s.setGpu(rs.getString("gpu"));
            s.setRam(rs.getString("ram"));
            s.setMemory(rs.getString("memory"));
            s.setScreenSize(rs.getString("screenSize"));
            s.setImage(rs.getString("image"));
            s.setDisplay(rs.getDouble("display"));
            s.setCamera(rs.getDouble("camera"));
            s.setPerformance(rs.getDouble("performance"));
            s.setBattery(rs.getInt("battery"));
            s.setDate(rs.getString("date"));
            s.setPrice(rs.getDouble("price"));
            result.add(s);
        }

        try {
            conn.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
            ps.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }

        return result;
    }

    public synchronized Spec getById(int id) throws SQLException{
        
    	Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM spec WHERE spec.id = ?";
        Spec result = new Spec();

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        rs.next();
        result.setId(rs.getInt("id"));
        result.setName(rs.getString("name"));
        result.setSo(rs.getString("so"));
        result.setCpu(rs.getString("cpu"));
        result.setChipset(rs.getString("chipset"));
        result.setGpu(rs.getString("gpu"));
        result.setRam(rs.getString("ram"));
        result.setMemory(rs.getString("memory"));
        result.setScreenSize(rs.getString("screenSize"));
        result.setImage(rs.getString("image"));
        result.setDisplay(rs.getDouble("display")/2);
        result.setCamera(rs.getDouble("camera")/2);
        result.setPerformance(rs.getDouble("performance")/2);
        result.setBattery(rs.getInt("battery"));
        result.setDate(rs.getString("date"));
        result.setPrice(rs.getDouble("price"));

        try {
            conn.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
            ps.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }

        return result;
    }

    public synchronized boolean saveSpec(Spec spec) throws SQLException, SpecMismatchException{
        if(spec==null) throw new SpecMismatchException("This spec does not fit the requirements nedded to be saved");

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO spec (name, so, cpu, chipset, gpu, ram, memory, screenSize, image, battery, date, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, spec.getName());
        ps.setString(2, spec.getSo());
        ps.setString(3, spec.getCpu());
        ps.setString(4, spec.getChipset());
        ps.setString(5, spec.getGpu());
        ps.setString(6, spec.getRam());
        ps.setString(7, spec.getMemory());
        ps.setString(8, spec.getScreenSize());
        ps.setString(9, spec.getImage());
        ps.setInt(10, spec.getBattery());
        ps.setString(11, spec.getDate());
        ps.setDouble(12, spec.getPrice());

        ps.executeUpdate();

        try {
            conn.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
            ps.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }

        return true;
    }

    public synchronized boolean deleteSpec(int id) throws SQLException{

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "DELETE FROM spec WHERE spec.id = ?";

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();

        try {
            conn.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
            ps.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }

        return true;
    }

    public boolean updateSpecScores(int reviewerId, int specId, double performance, double display, double camera) throws SQLException{

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE spec SET performance = ?, display = ?, camera = ?, reviewerId = ? WHERE id = ?";

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setDouble(1, performance);
        ps.setDouble(2, display);
        ps.setDouble(3, camera);
        ps.setInt(4, reviewerId);
        ps.setInt(5, specId);

        ps.executeUpdate();

        try {
            conn.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
            ps.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }

        return true;
    }

    public synchronized List<Spec> getByPrice(double price) throws SQLException, PriceException{
        if(price<=0) throw new PriceException("This price range is not valid");

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM spec WHERE spec.price <= ?";
        ArrayList<Spec> result = new ArrayList<>();
        
        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setDouble(1, price);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Spec s = new Spec();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSo(rs.getString("so"));
            s.setCpu(rs.getString("cpu"));
            s.setChipset(rs.getString("chipset"));
            s.setGpu(rs.getString("gpu"));
            s.setRam(rs.getString("ram"));
            s.setMemory(rs.getString("memory"));
            s.setScreenSize(rs.getString("screenSize"));
            s.setImage(rs.getString("image"));
            s.setDisplay(rs.getDouble("display"));
            s.setCamera(rs.getDouble("camera"));
            s.setPerformance(rs.getDouble("performance"));
            s.setBattery(rs.getInt("battery"));
            s.setDate(rs.getString("date"));
            s.setPrice(rs.getDouble("price"));
            result.add(s);
        }

        try {
            conn.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
            ps.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }

        return result;
    }

    public synchronized int getMaxBattery() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT MAX(battery) AS battery FROM spec";
        int result = -1;
        
        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        result = rs.getInt("battery");        

        try {
            conn.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
            ps.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }

        return result;
    }

    public synchronized int getMinBattery() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT MIN(battery) AS battery FROM spec";
        int result = -1;

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        result = rs.getInt("battery");

        try {
            conn.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        try {
            ps.close();

        } catch(Exception e) {
        	e.printStackTrace();
        }

        return result;
    }

    private static DataSource ds;
}