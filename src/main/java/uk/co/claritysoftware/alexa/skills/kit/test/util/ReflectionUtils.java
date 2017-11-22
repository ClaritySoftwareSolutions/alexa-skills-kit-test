package uk.co.claritysoftware.alexa.skills.kit.test.util;

import java.lang.reflect.Field;

/**
 * Class providing static reflection utilities
 */
public class ReflectionUtils {

	/**
	 * Gets the field value from the specified object with the specified field name, of type T
	 *
	 * @param obj       The object whose field should be returned
	 * @param fieldName the name of the field
	 * @param <T>       the type of the field
	 * @return the field value
	 * @throws RuntimeException is the field could not be found or accessed
	 */
	public static <T> T getFieldValue(Object obj, String fieldName) {
		try {
			Field field = getFieldFromHierachy(obj, fieldName);
			field.setAccessible(true);
			return (T) field.get(obj);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Could not access field: '" + fieldName + "' on class: '" + obj.getClass().getSimpleName() + "'");
		}
	}

	private static Field getFieldFromHierachy(Object target, String fieldName) {
		Class clazz = target.getClass();
		Field f;
		for (f = getField(clazz, fieldName); f == null && clazz != Object.class; f = getField(clazz, fieldName)) {
			clazz = clazz.getSuperclass();
		}

		if (f == null) {
			throw new RuntimeException("Could not find field: '" + fieldName + "' on class: '" + clazz.getSimpleName() + "' or any superclass");
		} else {
			return f;
		}
	}

	private static Field getField(Class clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			return null;
		}
	}

}
