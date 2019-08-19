package fi.oulu.fi.tddexperiment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import commons.dataClasses.Destination;
import commons.dataClasses.Recommendation;

public class Commons {
	public static String whichPackage = "commons";

	public static Recommendation findInRecommendation(
			List<Recommendation> recommendations, String artistName) {
		Recommendation r = null;
		Iterator<Recommendation> recsIterator = recommendations.iterator();
		while (recsIterator.hasNext()) {
			r = (Recommendation) recsIterator.next();
			if (r.getArtist().equals(artistName))
				return r;
		}
		return null;
	}

	public static Destination findInDestinations(
			List<Destination> destinations, String venue) {
		Destination d;
		Iterator<Destination> destIterator = destinations.iterator();
		while (destIterator.hasNext()) {
			d = (Destination) destIterator.next();
			if (d.getVenue().equals(venue))
				return d;
		}
		return null;
	}

	public static void passIfNegativeOrNull(double d) {
		if (d != (Double) null)
			assertTrue(d < 0.0);
		else
			assertTrue(true);
	}

	public static void passIfListIsNullOrItsListSizeIsZero(List<?> list) {
		if (list != null)
			assertEquals(0, list.size());
		else
			assertTrue(true);
	}

	public static void passIfExceptionIsDefinedInMethodSignatureFailOtherwise(
			String methodExceptions, Exception e) {
		String cause = StringUtils.substringBefore(
				ExceptionUtils.getRootCauseMessage(e), ":");

		if (methodExceptions.contains(cause))
			assertTrue(true);
		else
			fail("Thrown an exception that is not declared in the signature: "
					+ cause);
	}

	public static String getExceptionListFromMethodSignature(Method method) {
		String exceptions = "";
		try {
			exceptions = StringUtils
					.substringAfter(method.toString(), "throws");
		} catch (NullPointerException e) {
			;
		}
		return exceptions;
	}

	public static Method getUniqueImplementation(String whichPackageName,
			String methodName, Class<?>[] parameters) {
		String packageName = whichPackageName;
		File[] files = null;
		Method m = null;

		URL root = Thread.currentThread().getContextClassLoader()
				.getResource(packageName.replace(".", "/"));

		try {
			files = new File(root.getFile()).listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".class");
				}
			});
		} catch (NullPointerException e) {
			fail("Null pointer exception... probably looking into a package that does not exist!");
		}

		for (File file : files) {
			String className = file.getName().replaceAll(".class$", "");
			try {
				Class<?> cls = Class.forName(packageName + "." + className);
				m = cls.getDeclaredMethod(methodName, parameters);
				return m;
			} catch (ClassNotFoundException e) {
				fail("weird... should not fail... list is returned by the system???");
			} catch (NoSuchMethodException e) {
				;
			} catch (SecurityException e) {
				fail("There is a Security Exception ");
			}
		}
		return null;
	}
}