package org.rui.mem;
import org.junit.Assert;
import org.junit.Test;

public class MemcachedUtilTest {
 
	@Test
	public void testMemcached() {
		MemcachedUtil.put("hello", "world", 60);
		String hello = (String) MemcachedUtil.get("hello");
	   // System.out.println(hello);
		Assert.assertEquals("world", hello);
		
		
		 
		for(int i = 0; i < 100; ++i) {
			UserBean userBean = new UserBean("code-" + i, "pass-" + i);
			MemcachedUtil.put("token-" + i, userBean, 60);
			Object obj = MemcachedUtil.get("token-" + i);
			Assert.assertEquals(userBean, obj);
		}
	}
}