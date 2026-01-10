package Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryAnnotationTransformer implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {

		// If test doesn't have retry already, assign Retry class
		if (annotation.getRetryAnalyzerClass() == null) {
			annotation.setRetryAnalyzer(Utils.RetryAnalyzer.class);

			/*
			 * if (testMethod != null) {
			 * annotation.setRetryAnalyzer(Utils.RetryScenario.class); }
			 */

		}
	}
}
/*
 * You implement this interface when you want to dynamically modify TestNG
 * annotations (@Test, @BeforeMethod, etc.) before the test methods are
 * executed.
 */
