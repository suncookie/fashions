
package com.young.json;

import com.google.gson.Gson;


/**
 * json 实体类的基类，继承自该类，调用tojson即可转为json串，调用fromJson即可由json串转为实体类的对象
 * 
 * @author Gaojun
 * 
 *         2013-4-2
 */
public class YoungJsonBeanBase
{
	public YoungSession session = new YoungSession();

	/**
	 * 转为json串
	 * 
	 * @return
	 */
	public String toJson()
	{
		String strJson = null;

		Gson gson = new Gson();
		strJson = gson.toJson(this);

		//CUDebuger.Log.d("Json", strJson);

		return strJson;
	}

	/**
	 * 转为实体类的对象
	 * 
	 * @param strJson
	 * @return
	 */
	public static YoungJsonBeanBase fromJson(String strJson, Class<? extends YoungJsonBeanBase> classType)
	{
		Gson gson = new Gson();
		YoungJsonBeanBase bean = gson.fromJson(strJson, classType);

		return bean;
	}
}
