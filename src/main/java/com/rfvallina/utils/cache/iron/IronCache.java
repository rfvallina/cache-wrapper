package com.rfvallina.utils.cache.iron;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.github.mrcritical.ironcache.DefaultIronCacheProvider;
import com.github.mrcritical.ironcache.IronCacheProvider;
import com.github.mrcritical.ironcache.model.CacheItemRequest;
import com.github.mrcritical.ironcache.model.CachedItem;
import com.google.common.base.Optional;
import com.rfvallina.utils.cache.Cache;
import com.rfvallina.utils.exception.ApplicationException;
import com.rfvallina.utils.exception.GenericExceptionMessage;

public class IronCache implements Cache {

	private static String IRON_PROPERTIES = "iron.properties";
	private IronCacheProvider cacheProvider;
	private String cacheName;

	public IronCache(String cacheName) {
		this.cacheProvider = getCacheProvider();
		this.cacheName = cacheName;
	}
	
	private IronCacheProvider getCacheProvider() {
		IronCacheProvider cacheProvider = null;
		try {
			Properties props = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(IRON_PROPERTIES);
			props.load(in);
			in.close();
			if (props != null) {
				String projectId = props.getProperty("iron.projectId");
				String token = props.getProperty("iron.token");
				cacheProvider = new DefaultIronCacheProvider(token, projectId);
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return cacheProvider;
	}

	public Object get(String key) {
		Optional<CachedItem> item = cacheProvider.getItem(cacheName, key);
		if (item.isPresent()) {
			return item.get().getValue();
		}

		return null;
	}

	public void put(String key, Object value) throws ApplicationException {
		if(cacheProvider == null){
			throw new ApplicationException(GenericExceptionMessage.CACHE_NOT_INITIALIZED);
		}
		//Iron cache only allows string and integer values
		if(!(value instanceof String) && !(value instanceof Integer)){
			throw new ApplicationException(GenericExceptionMessage.CACHE_VALUE_TYPE_NOT_PERMITTED);
		}
		final CacheItemRequest request = CacheItemRequest.create().key(key).value(value);
		cacheProvider.putItem(cacheName, request);
	}

	public void deleteItem(String key) throws ApplicationException {
		if(cacheProvider == null){
			throw new ApplicationException(GenericExceptionMessage.CACHE_NOT_INITIALIZED);
		}
		cacheProvider.deleteItem(cacheName, key);
	}

	public void clearCache() throws ApplicationException {
		if(cacheProvider == null){
			throw new ApplicationException(GenericExceptionMessage.CACHE_NOT_INITIALIZED);
		}
		cacheProvider.clearCache(cacheName);
	}

}
