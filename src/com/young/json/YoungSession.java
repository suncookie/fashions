
package com.young.json;

public class YoungSession
{
	public long maxInactiveInterval = -1; // -1 永久有效
	public int expiring = 1; // 是否过期：0过期；1未过期
	public long lastAccessedTime = 0; // 最后一次访问时间(毫秒)
}
