package com.young.network;

import com.young.Responce.YoungJSonBeanRegisterResponse;
import com.young.json.YoungJsonBeanBase;
import com.young.json.request.YoungJSonBeanRegisterRequest;

public class YoungHttpRegisterManager extends YoungHttpBaseManager {

	@Override
	protected YoungJsonParserAPI createParser() {
		// TODO Auto-generated method stub
		return new YoungRegisterResultParser();
	}
	public void register(YoungJSonBeanRegisterRequest bean)
	{

		doHttpPost("regist", bean.toJson());
	}
	
	private class YoungRegisterResultParser implements YoungJsonParserAPI
	{
		@Override
		public YoungJsonBeanBase parseJson(String strResult)
		{
			YoungJsonBeanBase resultBean = YoungJsonBeanBase.fromJson(strResult, YoungJSonBeanRegisterResponse.class);

			return resultBean;
		}

	}
}
