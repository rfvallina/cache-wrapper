package com.rfvallina.utils.cache;

import com.rfvallina.utils.exception.ApplicationException;

/**
 * Common interface for all cache implementations
 * @author rfvallina
 *
 */
public interface Cache {

	/**
	 * Get item from cache
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) throws ApplicationException ;
	
	/**
	 * Put item on cache
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) throws ApplicationException;
	
	/**
	 * Delete item from cache
	 * @param key
	 */
	public void deleteItem(String key) throws ApplicationException ;
	
	/**
	 * Clear cache
	 */
	public void clearCache() throws ApplicationException ;
}
