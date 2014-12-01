package com.rfvallina.utils.cache;

import org.junit.Assert;
import org.junit.Test;

import com.rfvallina.utils.exception.ApplicationException;

public class ApplicationCacheTest extends CacheBaseTest {

	private String stringKey = "stringKey";
	private String stringValue = "testValue";

	@Test(expected = ApplicationException.class)
	public void putArrayTest() throws ApplicationException {
		String arrayKey = "arrayKey";
		String[] arrayValues = { "test1", "test2", "test3" };

		putItem(arrayKey, arrayValues);
	}

	@Test
	public void putStringTest() throws ApplicationException {
		putItem(stringKey, stringValue);
		Object value = getItem(stringKey);
		Assert.assertNotNull(value);
		Assert.assertEquals(stringValue, value);
	}

	@Test
	public void deleteItemTest() throws ApplicationException {
		// put item
		putItem(stringKey, stringValue);
		// get item
		Object value = getItem(stringKey);
		Assert.assertEquals(value, stringValue);
		// delete item
		cache.deleteItem(stringKey);
		// get item
		value = getItem(stringKey);
		Assert.assertNull(value);
	}

	@Test
	public void clearCache() throws ApplicationException {
		// put item
		putItem(stringKey, stringValue);
		// get item
		Object value = getItem(stringKey);
		Assert.assertEquals(value, stringValue);
		// delete item
		cache.clearCache();
		// get item
		value = getItem(stringKey);
		Assert.assertNull(value);
	}

	private void putItem(String key, Object value) throws ApplicationException {
		// put array - exception expected
		cache.put(key, value);
	}

	private Object getItem(String key) throws ApplicationException {
		return cache.get(key);
	}

}
