
package com.young.network;

import com.young.Responce.YoungJsonBeanLoginResponse;
import com.young.json.YoungJsonBeanBase;
import com.young.json.request.YoungJsonBeanLoginRequest;


/**
 * 登录接口管理类
 * 
 * @author Gaojun
 * 
 *         2013-4-2
 */
public class YoungHttpLoginManager extends YoungHttpBaseManager
{

	@Override
	protected YoungJsonParserAPI createParser()
	{
		return new YoungLoginResultParser();
	}

	/**
	 * 登录
	 */
	public void login(YoungJsonBeanLoginRequest bean)
	{

		doHttpPost("login", bean.toJson());
	}

	private class YoungLoginResultParser implements YoungJsonParserAPI
	{
		@Override
		public YoungJsonBeanBase parseJson(String strResult)
		{
			YoungJsonBeanBase resultBean = YoungJsonBeanBase.fromJson(strResult, YoungJsonBeanLoginResponse.class);

			return resultBean;
		}

	}
}
