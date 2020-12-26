package it.unisa.di.smartblog.test.user;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.PrintWriter;

public class UserDaoTestListener extends RunListener {
    public UserDaoTestListener(PrintWriter pw){
        this.pw = pw;
    }

    public void testRunStarted(Description description) throws java.lang.Exception
    {
        pw.println("UserDaoTest");
        pw.println("Number of tests to execute : " + description.testCount() + "\n");
    }

    /**
     *  Called when all tests have finished
     * */
    public void testRunFinished(Result result) throws java.lang.Exception
    {
        pw.println("Number of tests executed: " + result.getRunCount());
        pw.println("Number of failures: " + result.getFailureCount());
    }

    /**
     *  Called when an atomic test is about to be started.
     * */
    public void testStarted(Description description) throws java.lang.Exception
    {
        pw.println("Starting execution of test case : "+ description.getMethodName());
    }

    /**
     *  Called when an atomic test has finished, whether the test succeeds or fails.
     * */
    public void testFinished(Description description) throws java.lang.Exception
    {
       //pw.println("Finished execution of test case : "+ description.toString());
        pw.println();
    }

    /**
     *  Called when an atomic test fails.
     * */
    public void testFailure(Failure failure) throws java.lang.Exception
    {
        pw.println("\nExecution of test case failed : "+ failure.getMessage());
    }

    /**
     *  Called when a test will not be run, generally because a test method is annotated with Ignore.
     * */
    public void testIgnored(Description description) throws java.lang.Exception
    {
        pw.println("Execution of test case ignored : "+ description.getMethodName());
    }

    private PrintWriter pw;
}
