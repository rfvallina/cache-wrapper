package com.rfvallina.utils.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.rfvallina.utils.cache.iron.IronCache;
import com.rfvallina.utils.exception.ApplicationException;

public class ApplicationCache implements com.rfvallina.utils.cache.Cache {
	private static final String CACHE_TYPE_ARGUMENT_NOT_DEFINED = "Cache type injected value is not defined. Check your configuration script";
	private static final String CACHE_TYPE_ARGUMENT_ERROR = "Cache type injected value is not recognized. Check your configuration script";
	private static final String CONFIG_PROPERTIES = "config.properties";

	private String type;
	private String name;

	private com.rfvallina.utils.cache.Cache _cache;
	private static ApplicationCache _appCache = null;

	private ApplicationCache() {
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
		try {
			try {
				prop.load(in);
			} catch (IOException e) {
				System.out.println("Error while loading " + CONFIG_PROPERTIES + ": " + e.getMessage());
				System.exit(1);
			}
			this.type = prop.getProperty("cache.type");
			this.name = prop.getProperty("cache.name");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("Error while closing " + CONFIG_PROPERTIES + ": " + e.getMessage());
					System.exit(1);
				}
			}
		}
	}

	public static ApplicationCache getInstance() {
		if (null == _appCache) {
			synchronized (ApplicationCache.class) {
				if (null == _appCache)
					_appCache = new ApplicationCache();
			}
		}

		return _appCache;
	}

	private com.rfvallina.utils.cache.Cache getCache() {
		if (null == _cache) {
			synchronized (com.rfvallina.utils.cache.Cache.class) {
				if (type == null)
					throw new RuntimeException(CACHE_TYPE_ARGUMENT_NOT_DEFINED);
				CacheType cacheType = CacheType.getType(type);

				switch (cacheType) {
				case IRON:
					_cache = new IronCache(name);
					break;
				default:
					throw new RuntimeException(CACHE_TYPE_ARGUMENT_ERROR);
				}
			}
		}

		return _cache;
	}

	public Object get(String key) throws ApplicationException {
		return getCache().get(key);
	}

	public void put(String key, Object value) throws ApplicationException {
		getCache().put(key, value);
	}

	public void deleteItem(String key) throws ApplicationException {
		getCache().deleteItem(key);
	}

	public void clearCache() throws ApplicationException {
		getCache().clearCache();
	}
}
