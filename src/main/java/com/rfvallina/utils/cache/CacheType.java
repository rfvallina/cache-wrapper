package com.rfvallina.utils.cache;


public enum CacheType {

	IRON;
	
	public static CacheType getType(final String type) {
		return CacheType.valueOf(type.toUpperCase().trim());
	}
	
}
