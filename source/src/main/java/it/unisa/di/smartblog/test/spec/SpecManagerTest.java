package it.unisa.di.smartblog.test.spec;

import it.unisa.di.smartblog.review.ReviewDao;
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

public class SpecManagerTest extends TestCase {

    protected void setUp() throws Exception{

        sm = new SpecsManager();

    }

    public void testSearchAll() throws SQLException{

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

        List<Spec> specs = sm.searchAll();
        assertTrue(specs.contains(oracle1));
        assertTrue(specs.contains(oracle2));
        assertTrue(specs.contains(oracle3));
        assertTrue(specs.contains(oracle4));
        assertTrue(specs.contains(oracle5));
        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testSearchByName(){
        boolean flag=false;
        List<Spec> specs = null, oracle =null;

        try {
            specs = sm.searchByName("Redmi Note 9 4G");
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
            fail("testSearchByName() not passed!");

        } catch (SQLException e){

            fail("testSearchByName() not passed!");
        } finally {
            TestWriter.printTest(pw, oracle.get(0), specs.get(specs.indexOf(oracle.get(0))));
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testSearchByNameException() throws SQLException{

        try{
            sm.searchByName(null);
            fail("testGetByNameException() [null name] not passed!");

        } catch (EmptyFieldException e){

        }

        try{
            sm.searchByName("");
            fail("testGetByNameException() [empty name] not passed!");

        } catch (EmptyFieldException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testCreateSpec() throws SQLException, EmptyFieldException{

        Spec s = new Spec();
        s.setName("Spec di prova");
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
        boolean flag=false;
        Spec max = null;

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            List<Spec> specs = sm.searchByName("Spec di prova");
            max = specs.get(0);

            for(Spec spec : specs){
                if(spec.getId() > max.getId()) max = spec;
            }

            assertEquals(s.getName(), max.getName());
            assertEquals(s.getSo(), max.getSo());
            assertEquals(s.getCpu(), max.getCpu());
            assertEquals(s.getRam(), max.getRam());
            assertEquals(s.getMemory(), max.getMemory());
            assertEquals(s.getScreenSize(), max.getScreenSize());
            assertEquals(s.getImage(), max.getImage());
            assertEquals(s.getBattery(), max.getBattery());
            assertEquals(s.getDate(), max.getDate());
            assertEquals(s.getPrice(), max.getPrice());
            flag=true;
            sm.deleteSpec(max.getId());


        }catch (SpecMismatchException e){

            fail("testCreateSpec() not passed!");

        } finally {
            TestWriter.printTest(pw, s, max);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testCreateSpecException() throws SQLException{

        Spec s = new Spec();
        s.setName("Spec di prova");
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

            sm.createSpec(null, s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null deviceName) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec("", s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty deviceName) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), null, s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null releaseDate) not passed!");

        }catch (SpecMismatchException e){


        }

        try {

            sm.createSpec(s.getName(), "", s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty releaseDate) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), null, s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null image) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), "", s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty image) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), null, s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null OS) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), "", s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty OS) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), null, s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null CPU) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), "", s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty CPU) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), null, s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null chipset) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), "", s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty chipset) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), null, s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null GPU) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), "", s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty GPU) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), null, s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null RAM) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), "", s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty RAM) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), null, s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null memory) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), "", s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty memory) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), null, s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (null screenSize) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), "", s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (empty screenSize) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), -5, s.getPrice());
            fail("testCreateSpecException() (negative battery) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), -1);
            fail("testCreateSpecException() (negative price) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), "2020", s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (invalid releaseDate format) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), "1972/13", s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (invalid date) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), "url", s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (invalid image format) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), "-30 GB", s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (invalid image format) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), "-30 GB", s.getScreenSize(), s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (invalid image format) not passed!");

        }catch (SpecMismatchException e){

        }

        try {

            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), "-50", s.getBattery(), s.getPrice());
            fail("testCreateSpecException() (invalid image format) not passed!");

        }catch (SpecMismatchException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testDeleteSpec() throws EmptyFieldException, SpecMismatchException {

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
        Spec max = null;

        try{
            sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());

            List<Spec> specs = sm.searchByName("Test");
            max = specs.get(0);

            for(Spec spec : specs){
                if(spec.getId() > max.getId()) max = spec;
            }

            sm.deleteSpec(max.getId());
            sm.searchById(max.getId());
            fail("testDeleteSpec() not passed!");

        } catch(SQLException e){

        } finally {
            TestWriter.printTest(pw, s, max);
        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testSearchById(){
        boolean flag=false;
        Spec spec = null, s=null;

        try{
            spec = sm.searchById(2041);

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
            fail("testSearchById() not passed!");
        } finally {
            TestWriter.printTest(pw, s, spec);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testSetScores() throws SpecMismatchException, EmptyFieldException, SQLException{

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
        s.setPerformance(3);
        s.setDisplay(3);
        s.setCamera(3);

        Reviewer r = new Reviewer();
        r.setId(5);

        s.setReviewer(r);

        sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());

        List<Spec> specs = sm.searchByName("Test");
        Spec max = specs.get(0);

        for(Spec spec : specs){
            if(spec.getId() > max.getId()) max = spec;
        }

        if(sm.setScores(r.getId(), max.getId(), 3, 3, 3)) {

            Spec spec = sm.searchById(max.getId());

            assertEquals(s.getPerformance(), spec.getPerformance());
            assertEquals(s.getDisplay(), spec.getDisplay());
            assertEquals(s.getCamera(), spec.getCamera());
            assertEquals(s.getReviewer().getId(), spec.getReviewer().getId());
            TestWriter.printTest(pw, s, spec);
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

            sm.deleteSpec(spec.getId());

        } else {

            fail("setScores() not passed!");

        }


    }

    public void testSetScoresException() throws SpecMismatchException, EmptyFieldException, SQLException{

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

        sm.createSpec(s.getName(), s.getDate(), s.getImage(), s.getSo(), s.getCpu(), s.getChipset(), s.getGpu(), s.getRam(), s.getMemory(), s.getScreenSize(), s.getBattery(), s.getPrice());

        List<Spec> specs = sm.searchByName("Test");
        Spec max = specs.get(0);

        for(Spec spec : specs){
            if(spec.getId() > max.getId()) max = spec;
        }

        if(sm.setScores(r.getId(), max.getId(), -1, 3, 3)){
            sm.deleteSpec(max.getId());
            fail("testSetScoresException() not passed!");

        }

        if(sm.setScores(r.getId(), max.getId(), 3, 6, 3)){

            sm.deleteSpec(max.getId());
            fail("testSetScoresException() not passed!");

        }

        if(sm.setScores(r.getId(), max.getId(), 3, 3, 6)){

            sm.deleteSpec(max.getId());
            fail("testSetScoresException() not passed!");

        }


        sm.deleteSpec(max.getId());
        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");


    }

    public void testSearchMinBattery() throws SQLException{

        int minBattery = sm.searchMinBattery();
        assertEquals(minBattery, 4300);
        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testSearchMaxBattery() throws SQLException{

        int maxBattery = sm.searchMaxBattery();
        assertEquals(maxBattery, 6000);
        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testSearchByPrice() throws SQLException{

        try {

            List<Spec> filteredResult = sm.searchByPrice(200);
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
            assertTrue(filteredResult.contains(oracle1));
            assertTrue(filteredResult.contains(oracle2));

        }catch (PriceException e){

            fail("testSearchByPrice() not passed!");

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testSearchByPriceException() throws SQLException{

        try {

            List<Spec> filteredResult = sm.searchByPrice(-1);
            fail("testSearchByPriceException() not passed!");

        }catch (PriceException e){

            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

        }
    }

    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(SpecManagerTest.class);

    }

    private SpecsManager sm;
    private static PrintWriter pw;

}
