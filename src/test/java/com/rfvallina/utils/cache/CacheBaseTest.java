package com.rfvallina.utils.cache;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;

import com.rfvallina.utils.cache.ApplicationCache;

public abstract class CacheBaseTest {
	
	protected static ApplicationCache cache;

	@Before
	public void init() throws IOException {
		cache = ApplicationCache.getInstance();
	}

	@After
	public void end() {	}
}
