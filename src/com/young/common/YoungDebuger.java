
package com.young.common;

/**
 * 
 * @author Gaojun
 * 
 *         2013-4-16
 */
public class YoungDebuger
{

	public static final boolean IS_DEBUG = true;
	public static boolean IS_CANDOWNLOAD_TEST = IS_DEBUG ? true : false; // true-测试
																			// 服务器端的文件是否可以下载

	public static class Log
	{
		public static final void d(String tag, String message)
		{
			if(IS_DEBUG)
			{
				android.util.Log.d(tag, message);
			}
		}
	}
}
