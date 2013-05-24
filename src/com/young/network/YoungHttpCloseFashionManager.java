package com.young.network;

import com.young.Responce.YoungJSonBeanCloseFashionResponse;
import com.young.json.YoungJsonBeanBase;
import com.young.json.request.YoungJSonBeanCloseFashionRequest;

public class YoungHttpCloseFashionManager extends YoungHttpBaseManager {

	@Override
	protected YoungJsonParserAPI createParser() {
		// TODO Auto-generated method stub
		return new YoungCloseFashionResultParser();
	}
	public void getImages(YoungJSonBeanCloseFashionRequest bean){
		doHttpPost("getNearlyImages", bean.toJson());
	}
	
	private class YoungCloseFashionResultParser implements YoungJsonParserAPI
	{
		@Override
		public YoungJsonBeanBase parseJson(String strResult)
		{
			YoungJsonBeanBase resultBean = YoungJsonBeanBase.fromJson(strResult, YoungJSonBeanCloseFashionResponse.class);

			return resultBean;
		}

	}
}
