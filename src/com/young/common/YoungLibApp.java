
package com.young.common;

import java.util.List;

import android.app.Application;

import com.young.activity.R;
import com.young.json.YoungSession;

public class YoungLibApp extends Application
{
	private static YoungLibApp mApp = null;
	private String mServiceRoot = null; // 接口地址
	private String mUserNum = null; // 用户账号
	private YoungSession mSession = null;
	private String mPassWord = null;

	@Override
	public void onCreate()
	{
		super.onCreate();

		mApp = this;
		mServiceRoot = getString(R.string.HTTP_ROOT);
		mSession = new YoungSession();
	}

	/**
	 * 保存用户账号
	 * 
	 * @param userNum
	 */
	public void setUserNum(String userNum)
	{
		mUserNum = userNum;
	}

	public String getmPassWord() {
		return mPassWord;
	}

	public void setmPassWord(String mPassWord) {
		this.mPassWord = mPassWord;
	}

	/**
	 * 获取用户账号
	 * 
	 * @return
	 */
	public String getUserNum()
	{
		return mUserNum;
	}

	public String getServiceRoot()
	{
		return mServiceRoot;
	}

	public static YoungLibApp getInstance()
	{
		return mApp;
	}

	public void setSession(YoungSession session)
	{
		mSession = session;
	}

	public YoungSession getSession()
	{
		return mSession;
	}
}
