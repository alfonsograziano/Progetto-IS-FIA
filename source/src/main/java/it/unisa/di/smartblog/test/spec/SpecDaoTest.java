package it.unisa.di.smartblog.test.spec;

import it.unisa.di.smartblog.spec.*;
import it.unisa.di.smartblog.test.TestWriter;
import it.unisa.di.smartblog.user.Reviewer;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecDaoTest extends TestCase {

    protected void setUp() throws Exception{
        sd = new SpecDao();
    }

    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(SpecDaoTest.class);
    }

    public void testGetAll() throws SQLException{

        Spec oracle1 = new Spec();
        oracle1.setId(2042);
        oracle1.setName("OnePlus Nord N100");
        oracle1.setSo("Android 10 OxygenOS 10.5");
        oracle1.setCpu("4x 1.8 GHz Kryo 240 + 4x 1.6 GHz Kryo 240");
        oracle1.setChipset("Snapdragon 460 Qualcomm SM4250");
        oracle1.setGpu("Adreno 610");
        oracle1.setRam("4 GB");
        oracle1.setMemory("64 GB");
        oracle1.setScreenSize("6.52");
        oracle1.setImage("https://hd2.tudocdn.net/941166?w=139&h=304");
        oracle1.setDisplay(2.8);
        oracle1.setCamera(2.5);
        oracle1.setPerformance(3.25);
        oracle1.setBattery(5000);
        oracle1.setDate("2020/4");
        oracle1.setPrice(167);

        Spec oracle2 = new Spec();
        oracle2.setId(2045);
        oracle2.setName("vivo Y11s");
        oracle2.setSo("Android 10 Funtouch OS 11");
        oracle2.setCpu("4x 1.8 GHz Kryo 240 + 4x 1.6 GHz Kryo 240");
        oracle2.setChipset("Snapdragon 460 Qualcomm SM4250");
        oracle2.setGpu("Adreno 610");
        oracle2.setRam("3 GB");
        oracle2.setMemory("32 GB");
        oracle2.setScreenSize("6.51");
        oracle2.setImage("https://hd2.tudocdn.net/947173?w=141&h=304");
        oracle2.setDisplay(2.8);
        oracle2.setCamera(2.5);
        oracle2.setPerformance(3.25);
        oracle2.setBattery(5000);
        oracle2.setDate("2020/4");
        oracle2.setPrice(149);

        Spec oracle3 = new Spec();
        oracle3.setId(2041);
        oracle3.setName("Redmi Note 9 4G");
        oracle3.setSo("Android 10 MIUI 12");
        oracle3.setCpu("4x 2.0 GHz Kryo 260 Gold + 4x 1.8 GHz Kryo 260 Silver");
        oracle3.setChipset("Snapdragon 662 Qualcomm SM6115");
        oracle3.setGpu("Adreno 610");
        oracle3.setRam("4 GB");
        oracle3.setMemory("128 GB");
        oracle3.setScreenSize("6.53");
        oracle3.setImage("https://hd2.tudocdn.net/947320?w=145&h=304");
        oracle3.setDisplay(5);
        oracle3.setCamera(5);
        oracle3.setPerformance(5);
        oracle3.setBattery(6000);
        oracle3.setDate("2020/4");
        oracle3.setPrice(206);

        Spec oracle4 = new Spec();
        oracle4.setId(2043);
        oracle4.setName("OnePlus Nord N10");
        oracle4.setSo("Android 10 OxygenOS 10.5");
        oracle4.setCpu("2x 2.0 GHz Kryo 560 Gold + 6x 1.7 GHz Kryo 560 Silver");
        oracle4.setChipset("Snapdragon 690 Qualcomm SM6350");
        oracle4.setGpu("Adreno 619L");
        oracle4.setRam("6 GB");
        oracle4.setMemory("128 GB");
        oracle4.setScreenSize("6.49");
        oracle4.setImage("https://hd2.tudocdn.net/941165?w=139&h=304");
        oracle4.setDisplay(4.35);
        oracle4.setCamera(3.15);
        oracle4.setPerformance(3.35);
        oracle4.setBattery(4300);
        oracle4.setDate("2020/4");
        oracle4.setPrice(325);

        Spec oracle5 = new Spec();
        oracle5.setId(2044);
        oracle5.setName("Huawei Mate 40 Pro");
        oracle5.setSo("Android 10 EMUI 11");
        oracle5.setCpu("1x 3.13 GHz Cortex-A77 + 3x 2.54 GHz Cortex-A77 + 4x 2.04 GHz Cortex-A55");
        oracle5.setChipset("Huawei HiSilicon Kirin 9000");
        oracle5.setGpu("Mali-G78 MP24");
        oracle5.setRam("8 GB");
        oracle5.setMemory("256 GB");
        oracle5.setScreenSize("6.7");
        oracle5.setImage("https://hd2.tudocdn.net/940650?w=141&h=304");
        oracle5.setDisplay(4.65);
        oracle5.setCamera(3.5);
        oracle5.setPerformance(3.5);
        oracle5.setBattery(4400);
        oracle5.setDate("2020/4");
        oracle5.setPrice(1238);


        List<Spec> specs = sd.getAll();
        assertTrue(specs.contains(oracle1));
        assertTrue(specs.contains(oracle2));
        assertTrue(specs.contains(oracle3));
        assertTrue(specs.contains(oracle4));
        assertTrue(specs.contains(oracle5));

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testGetByName(){
        List<Spec> specs=null, oracle=null;
        boolean flag=false;

        try {
            specs = sd.getByName("Redmi Note 9 4G");
            oracle = new ArrayList<>();

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
            s.setDisplay(10/2);
            s.setCamera(10/2);
            s.setPerformance(10/2);
            s.setBattery(6000);
            s.setDate("2020/4");
            s.setPrice(206);

            oracle.add(s);

            assertTrue(specs.contains(oracle.get(0)));
            flag=true;

        } catch (EmptyFieldException e){
            fail("testGetByName() not passed!");

        } catch (SQLException e){

            fail("testGetByName() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, specs.get(specs.indexOf(oracle.get(0))));
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }

    public void testGetByNameException() throws SQLException{
        try{
            sd.getByName(null);
            fail("testGetByNameException() [null name] not passed!");

        } catch (EmptyFieldException e){

        }

        try{
            sd.getByName("");
            fail("testGetByNameException() [empty name] not passed!");

        } catch (EmptyFieldException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testGetById(){
        boolean flag=false;
        Spec spec=null, s=null;

        s = new Spec();
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
        s.setDisplay(10/2);
        s.setCamera(10/2);
        s.setPerformance(10/2);
        s.setBattery(6000);
        s.setDate("2020/4");
        s.setPrice(206);

        Reviewer r = new Reviewer();
        r.setId(5);
        r.setUsername("reviewer");

        s.setReviewer(r);

        try{
            spec = sd.getById(2041);

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

            flag=true;

        } catch (SQLException e){
            fail("testGetById() not passed!");

        } finally {
            TestWriter.printTest(pw, s, spec);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }

    public void testSaveSpec() throws EmptyFieldException{
            boolean flag=false;
            Spec s = new Spec(), max=null;
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
            max = specs.get(0);

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

            flag=true;
            sd.deleteSpec(max.getId());

        } catch (SpecMismatchException e){
            fail("testSaveSpec() not passed!");

        } catch (SQLException e){
            fail("testSaveSpec() not passed!");

        } finally {
            TestWriter.printTest(pw, s, max);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testSaveSpecException() throws SQLException{
        try{
            Spec s = null;
            sd.saveSpec(s);
            fail("testSaveSpecException() not passed!");

        } catch(SpecMismatchException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testDeleteSpec() throws SpecMismatchException, EmptyFieldException{

        Spec s = new Spec(), max=null;
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
            max = specs.get(0);

            for(Spec spec : specs){
                if(spec.getId() > max.getId()) max = spec;
            }

            sd.deleteSpec(max.getId());
            sd.getById(max.getId());
            fail("testDeleteSpec() not passed!");

        } catch(SQLException e){

        } finally {
            TestWriter.printTest(pw, s, max);
        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testUpdateSpecScores() throws EmptyFieldException, SpecMismatchException{
        boolean flag=false;
        Spec max = null;
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
            max = specs.get(0);

            for(Spec spec : specs){
                if(spec.getId() > max.getId()) max = spec;
            }

            sd.updateSpecScores(r.getId(), max.getId(), 3, 3, 3);

            Spec spec = sd.getById(max.getId());

            assertEquals(s.getPerformance(), spec.getPerformance());
            assertEquals(s.getDisplay(), spec.getDisplay());
            assertEquals(s.getCamera(), spec.getCamera());
            assertEquals(s.getReviewer().getId(), spec.getReviewer().getId());

            flag=true;

            sd.deleteSpec(spec.getId());

        } catch(SQLException e){
            fail("testUpdateSpecScores() not passed!");
        } finally {
            TestWriter.printTest(pw, s, max);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testGetByPrice() throws SQLException{
        List<Spec> filteredResult=null, oracle=null;
        boolean flag=false;

        Spec oracle1 = new Spec();
        oracle1.setId(2042);
        oracle1.setName("OnePlus Nord N100");
        oracle1.setSo("Android 10 OxygenOS 10.5");
        oracle1.setCpu("4x 1.8 GHz Kryo 240 + 4x 1.6 GHz Kryo 240");
        oracle1.setChipset("Snapdragon 460 Qualcomm SM4250");
        oracle1.setGpu("Adreno 610");
        oracle1.setRam("4 GB");
        oracle1.setMemory("64 GB");
        oracle1.setScreenSize("6.52");
        oracle1.setImage("https://hd2.tudocdn.net/941166?w=139&h=304");
        oracle1.setDisplay(5.6);
        oracle1.setCamera(5);
        oracle1.setPerformance(6.5);
        oracle1.setBattery(5000);
        oracle1.setDate("2020/4");
        oracle1.setPrice(167);

        Spec oracle2 = new Spec();
        oracle2.setId(2045);
        oracle2.setName("vivo Y11s");
        oracle2.setSo("Android 10 Funtouch OS 11");
        oracle2.setCpu("4x 1.8 GHz Kryo 240 + 4x 1.6 GHz Kryo 240");
        oracle2.setChipset("Snapdragon 460 Qualcomm SM4250");
        oracle2.setGpu("Adreno 610");
        oracle2.setRam("3 GB");
        oracle2.setMemory("32 GB");
        oracle2.setScreenSize("6.51");
        oracle2.setImage("https://hd2.tudocdn.net/947173?w=141&h=304");
        oracle2.setDisplay(5.6);
        oracle2.setCamera(5);
        oracle2.setPerformance(6.5);
        oracle2.setBattery(5000);
        oracle2.setDate("2020/4");
        oracle2.setPrice(149);

        oracle= new ArrayList<>();
        oracle.add(oracle1);
        oracle.add(oracle2);

        try {

            filteredResult = sd.getByPrice(200);
            assertTrue(filteredResult.contains(oracle1));
            assertTrue(filteredResult.contains(oracle2));
            flag=true;

        }catch (PriceException e){

            fail("testGetByPrice() not passed!");

        } finally {
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testGetMaxBattery() throws SQLException{

        int maxBattery = sd.getMaxBattery();
        assertEquals(maxBattery, 6000);
        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testGetMinBattery() throws SQLException{

        int minBattery = sd.getMinBattery();
        assertEquals(minBattery, 4300);
        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    private SpecDao sd;
    private static PrintWriter pw;
}
