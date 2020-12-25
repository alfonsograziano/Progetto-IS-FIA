package it.unisa.di.smartblog.test.spec;

import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.spec.SpecsManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SpecManagerTest extends TestCase {

    protected void setUp() throws Exception{

        sm = new SpecsManager();

    }

    public void testSearchAll(){



    }


    public static Test suite(){

        return new TestSuite(SpecManagerTest.class);

    }

    private SpecsManager sm;

}
