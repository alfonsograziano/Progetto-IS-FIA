package it.unisa.di.smartblog.test.spec;

import it.unisa.di.smartblog.spec.EmptyFieldException;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecDao;
import it.unisa.di.smartblog.spec.SpecMismatchException;
import it.unisa.di.smartblog.user.Reviewer;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecDaoTest extends TestCase {

    protected void setUp() throws Exception{
        sd = new SpecDao();
    }

    public static Test suite(){
        return new TestSuite(SpecDaoTest.class);
    }

    public void testGetByName(){
        try {
            List<Spec> specs = sd.getByName("Redmi Note 9 4G");
            List<Spec> oracle = new ArrayList<>();

            Spec s = new Spec();
            s.setId(2041);
            s.setName("Redmi Note 9 4G");
            s.setSo("Android 10 MIUI 12");
            s.setCpu("4x 2.0 GHz Kryo 260 Gold + 4x 1.8 GHz Kryo 260 Silver");
            s.setChipset("Snapdragon 662 Qualcomm SM6115");
            s.setGpu("Adreno 610");
            s.setRam("4 GB");
            s.setMemory("128 GB");
            s.setScreenSize("6.53");
            s.setImage("https://hd2.tudocdn.net/947320?w=145&h=304");
            s.setDisplay(8.6/2);
            s.setCamera(6.2/2);
            s.setPerformance(6.8/2);
            s.setBattery(6000);
            s.setDate("2020/4");
            s.setPrice(206);

            oracle.add(s);

            assertTrue(specs.contains(oracle.get(0)));
            System.out.println("testGetByName() passed!");

        } catch (EmptyFieldException e){
            fail("testGetByName() not passed!");

        } catch (SQLException e){

            fail("testGetByName() not passed!");
        }
    }

    public void testGetByNameException() throws SQLException{
        try{
            sd.getByName(null);
            fail("testGetByNameException() [null name] not passed!");

        } catch (EmptyFieldException e){
            System.out.println("testGetByNameException() [null name] passed!");

        }

        try{
            sd.getByName("");
            fail("testGetByNameException() [empty name] not passed!");

        } catch (EmptyFieldException e){
            System.out.println("testGetByNameException() [empty name] passed!");

        }
    }

    public void testGetById(){
        try{
            Spec spec = sd.getById(2041);

            Spec s = new Spec();
            s.setId(2041);
            s.setName("Redmi Note 9 4G");
            s.setSo("Android 10 MIUI 12");
            s.setCpu("4x 2.0 GHz Kryo 260 Gold + 4x 1.8 GHz Kryo 260 Silver");
            s.setChipset("Snapdragon 662 Qualcomm SM6115");
            s.setGpu("Adreno 610");
            s.setRam("4 GB");
            s.setMemory("128 GB");
            s.setScreenSize("6.53");
            s.setImage("https://hd2.tudocdn.net/947320?w=145&h=304");
            s.setDisplay(8.6/2);
            s.setCamera(6.2/2);
            s.setPerformance(6.8/2);
            s.setBattery(6000);
            s.setDate("2020/4");
            s.setPrice(206);

            Reviewer r = new Reviewer();
            r.setId(5);
            r.setUsername("reviewer");

            s.setReviewer(r);

            assertEquals(s.getId(), spec.getId());
            assertEquals(s.getName(), spec.getName());
            assertEquals(s.getSo(), spec.getSo());
            assertEquals(s.getCpu(), spec.getCpu());
            assertEquals(s.getRam(), spec.getRam());
            assertEquals(s.getMemory(), spec.getMemory());
            assertEquals(s.getScreenSize(), spec.getScreenSize());
            assertEquals(s.getImage(), spec.getImage());
            assertEquals(s.getDisplay(), spec.getDisplay());
            assertEquals(s.getCamera(), spec.getCamera());
            assertEquals(s.getPerformance(), spec.getPerformance());
            assertEquals(s.getBattery(), spec.getBattery());
            assertEquals(s.getDate(), spec.getDate());
            assertEquals(s.getPrice(), spec.getPrice());
            assertEquals(s.getReviewer().getId(), spec.getReviewer().getId());
            assertEquals(s.getReviewer().getUsername(), spec.getReviewer().getUsername());

            System.out.println("testGetById() passed!");

        } catch (SQLException e){
            fail("testGetById() not passed!");
        }
    }

    public void testSaveSpec() throws EmptyFieldException{
            Spec s = new Spec();
            s.setName("Test");
            s.setSo("Android 10 MIUI 12");
            s.setCpu("4x 2.0 GHz Kryo 260 Gold + 4x 1.8 GHz Kryo 260 Silver");
            s.setChipset("Snapdragon 662 Qualcomm SM6115");
            s.setGpu("Adreno 610");
            s.setRam("4 GB");
            s.setMemory("128 GB");
            s.setScreenSize("6.53");
            s.setImage("https://hd2.tudocdn.net/947320?w=145&h=304");
            s.setBattery(6000);
            s.setDate("2020/4");
            s.setPrice(206);

        try {
            sd.saveSpec(s);

            List<Spec> specs = sd.getByName("Test");
            Spec max = specs.get(0);

            for(Spec spec : specs){
                if(spec.getId() > max.getId()) max = spec;
            }

            assertEquals(s.getName(), max.getName());
            assertEquals(s.getSo(), max.getSo());
            assertEquals(s.getCpu(), max.getCpu());
            assertEquals(s.getChipset(), max.getChipset());
            assertEquals(s.getGpu(), max.getGpu());
            assertEquals(s.getRam(), max.getRam());
            assertEquals(s.getMemory(), max.getMemory());
            assertEquals(s.getScreenSize(), max.getScreenSize());
            assertEquals(s.getImage(), max.getImage());
            assertEquals(s.getBattery(), max.getBattery());
            assertEquals(s.getDate(), max.getDate());
            assertEquals(s.getPrice(), max.getPrice());

            System.out.println("testSaveSpec() passed!");
            sd.deleteSpec(max.getId());

        } catch (SpecMismatchException e){
            fail("testSaveSpec() not passed!");

        } catch (SQLException e){
            fail("testSaveSpec() not passed!");

        }

    }

    public void testSaveSpecException() throws SQLException{
        try{
            Spec s = null;
            sd.saveSpec(s);
            fail("testSaveSpecException() not passed!");

        } catch(SpecMismatchException e){
            System.out.println("testSaveSpecException() passed!");
        }
    }

    public void testDeleteSpec() throws SpecMismatchException, EmptyFieldException{

        Spec s = new Spec();
        s.setName("Test");
        s.setSo("Android 10 MIUI 12");
        s.setCpu("4x 2.0 GHz Kryo 260 Gold + 4x 1.8 GHz Kryo 260 Silver");
        s.setChipset("Snapdragon 662 Qualcomm SM6115");
        s.setGpu("Adreno 610");
        s.setRam("4 GB");
        s.setMemory("128 GB");
        s.setScreenSize("6.53");
        s.setImage("https://hd2.tudocdn.net/947320?w=145&h=304");
        s.setBattery(6000);
        s.setDate("2020/4");
        s.setPrice(206);

        try{
            sd.saveSpec(s);

            List<Spec> specs = sd.getByName("Test");
            Spec max = specs.get(0);

            for(Spec spec : specs){
                if(spec.getId() > max.getId()) max = spec;
            }

            sd.deleteSpec(max.getId());
            sd.getById(max.getId());
            fail("testDeleteSpec() not passed!");

        } catch(SQLException e){
            System.out.println("testDeleteSpec() passed!");
        }
    }

    public void testUpdateSpecScores() throws EmptyFieldException, SpecMismatchException{
        Spec s = new Spec();
        s.setName("Test");
        s.setSo("Android 10 MIUI 12");
        s.setCpu("4x 2.0 GHz Kryo 260 Gold + 4x 1.8 GHz Kryo 260 Silver");
        s.setChipset("Snapdragon 662 Qualcomm SM6115");
        s.setGpu("Adreno 610");
        s.setRam("4 GB");
        s.setMemory("128 GB");
        s.setScreenSize("6.53");
        s.setImage("https://hd2.tudocdn.net/947320?w=145&h=304");
        s.setBattery(6000);
        s.setDate("2020/4");
        s.setPrice(206);
        s.setPerformance(1.5);
        s.setDisplay(1.5);
        s.setCamera(1.5);

        Reviewer r = new Reviewer();
        r.setId(5);

        s.setReviewer(r);

        try{
            sd.saveSpec(s);


            List<Spec> specs = sd.getByName("Test");
            Spec max = specs.get(0);

            for(Spec spec : specs){
                if(spec.getId() > max.getId()) max = spec;
            }

            sd.updateSpecScores(r.getId(), max.getId(), 3, 3, 3);

            Spec spec = sd.getById(max.getId());

            assertEquals(s.getPerformance(), spec.getPerformance());
            assertEquals(s.getDisplay(), spec.getDisplay());
            assertEquals(s.getCamera(), spec.getCamera());
            assertEquals(s.getReviewer().getId(), spec.getReviewer().getId());
            System.out.println("testUpdateSpecScores() passed!");

        } catch(SQLException e){
            fail("testUpdateSpecScores() not passed!");
        }

    }

    private SpecDao sd;
}
