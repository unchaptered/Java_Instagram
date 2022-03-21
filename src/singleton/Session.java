package singleton;

import java.util.HashMap;

public class Session {
	
	private static HashMap<String, Object> sessions = new HashMap<String, Object>();
	
	/** @throws new AssertionError();
	 */
	private Session() {
		throw new AssertionError();
	}
	
	public static void getInstance() {
		sessions=new HashMap<String, Object>();
	}
	
	public static void set(String key, Object value) {
		sessions.put(key, value);
	}
	
	public static Object get(String key) {
		return sessions.get(key);
	}
}
