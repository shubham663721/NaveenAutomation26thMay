package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

	public void onTestStart(ITestResult result) {
	
		System.out.println("Test Case Started and details are " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		
		System.out.println("Test Case Passed and details are " + result.getName());
		
	}

	public void onTestFailure(ITestResult result) {
		
		System.out.println("Test Case Failed and details are " + result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		
		System.out.println("Test Case Skipped and details are " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	public void onStart(ITestContext context) {
		
		System.out.println("Test Case Started and details are " + context.getName());
	}

	public void onFinish(ITestContext context) {
		
		System.out.println("Test Case finished and details are " + context.getName());
	}

}
