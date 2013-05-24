
package com.young.network;

import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.young.common.YoungDebuger;
import com.young.common.YoungLibApp;
import com.young.json.YoungJsonBeanBase;

/**
 * 网络请求基类,实例化后需先调用setcallback设置回调
 * 
 * @author Gaojun
 * 
 *         2013-4-2
 */
public abstract class YoungHttpBaseManager
{
	private YoungJsonParserAPI mParser = null; // 返回结果解析器
	private YoungHttpCallbackAPI mCallBack = null;
	private boolean mIsCancled = false; // 标示该请求是否被取消
	private int TIME_OUT_DELAY = 10 * 1000;
	private HttpClient mClient = null;

	public void cancel()
	{
		mIsCancled = true;
	}

	public interface YoungHttpCallbackAPI
	{
		public void onCallFinished(YoungJsonBeanBase bean);

		public void onError(Exception e);
	}

	public void setCallBack(YoungHttpCallbackAPI callback)
	{
		mCallBack = callback;
	}

	protected abstract YoungJsonParserAPI createParser();

	/**
	 * 发送请求
	 * 
	 * @param serviceName
	 *            请求的接口名
	 * @param strJson
	 *            入参json串
	 */
	protected void doHttpPost(final String serviceName, final String strJson)
	{
		mIsCancled = false;
		mParser = createParser();

		Thread thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				String httpRoot = YoungLibApp.getInstance().getServiceRoot();
				HttpPost post = new HttpPost(httpRoot + serviceName);
				mClient = new DefaultHttpClient();
				HttpResponse response = null;
				try
				{
					StringEntity entity = new StringEntity(strJson);
//					mClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, TIME_OUT_DELAY); // 超时设置
//					mClient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, TIME_OUT_DELAY);// 连接超时

					entity.setContentType("application/json");
					post.setEntity(entity);
					YoungDebuger.Log.d("http", serviceName + " ==== " + strJson);
					response = mClient.execute(post);

					HttpEntity responseEntity = response.getEntity();
					String strJson = EntityUtils.toString(responseEntity);

					YoungDebuger.Log.d("http", "http response statusCode ========"
							+ response.getStatusLine().getStatusCode() + "entity==" + strJson);;

					YoungJsonBeanBase resultBean = null;

					if(!mIsCancled)
					{
						if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
						{
							strJson = URLDecoder.decode(strJson, "UTF-8");

			

							if(null != mParser)
							{
								resultBean = mParser.parseJson(strJson);
								// 1. 给session赋值
								YoungLibApp app = (YoungLibApp) YoungLibApp.getInstance();
								app.setSession(resultBean.session);
							}
						}

						if(mCallBack != null)
						{
							mCallBack.onCallFinished(resultBean);
						}
					}
					else
					{
						YoungDebuger.Log.d("http", "callBack canceled....");
					}
				}
				catch(Exception e)
				{
					if(mCallBack != null && !mIsCancled)
					{
						mCallBack.onError(e);
					}
					e.printStackTrace();
				}
				finally
				{
					try
					{
						mClient.getConnectionManager().shutdown();
					}
					catch(Exception e2)
					{
					}

				}
			}
		});

		thread.start();
	}
}
