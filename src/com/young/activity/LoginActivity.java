package com.young.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.young.Responce.YoungJsonBeanLoginResponse;
import com.young.common.YoungBaseActivity;
import com.young.common.YoungDebuger;
import com.young.common.YoungLibApp;
import com.young.json.YoungJsonBeanBase;
import com.young.json.request.YoungJsonBeanLoginRequest;
import com.young.lib.SlidingActivity;
import com.young.lib.SlidingActivity.CUDialogButton;
import com.young.network.YoungHttpBaseManager.YoungHttpCallbackAPI;
import com.young.network.YoungHttpLoginManager;

public class LoginActivity extends YoungBaseActivity {
	private EditText mEtUserName;
	private EditText mEtPassWord;
	private YoungHttpLoginManager mLoginManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button btnLogin = (Button)findViewById(R.id.button_activityLogin_login);
		Button btnRegister = (Button)findViewById(R.id.button_activityLogin_register);
		mEtUserName = (EditText)findViewById(R.id.editText_activityLogin_userName);
		mEtPassWord = (EditText)findViewById(R.id.editText_activityLogin_passWord);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(YoungDebuger.IS_DEBUG)
				{
					mEtUserName.setText("123");
					mEtPassWord.setText("456");
				}
				if(mEtUserName.getText().toString() == null || mEtUserName.getText().toString().equals(""))
				{
					showAlertDialog("请输入账号!", new YoungDialogButton("确定", null), null);
					return;
				}
				if(mEtPassWord.getText().toString() == null || mEtPassWord.getText().toString().equals(""))
				{
					showAlertDialog("请输入密码!", new YoungDialogButton("确定", null), null);
					return;
				}
				doLogin();
			}
		});
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doRigister();
			}
		});
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login, menu);
//		return true;
//	}

	private void doLogin()
	{
		final YoungJsonBeanLoginRequest bean = new YoungJsonBeanLoginRequest();
		bean.userNum = mEtUserName.getText().toString();
		bean.passWord = mEtPassWord.getText().toString();
		if(null == mLoginManager)
		{
			mLoginManager = new YoungHttpLoginManager();
			mLoginManager.setCallBack(new YoungHttpCallbackAPI()
			{
				@Override
				public void onCallFinished(YoungJsonBeanBase bean)
				{
					dismissProgressDialog();
					if(null != bean)
					{
						YoungJsonBeanLoginResponse loginResponse = (YoungJsonBeanLoginResponse) bean;
						if(loginResponse.result == 0) // 登录成功
						{
							// 2. 保存userName
							((YoungLibApp) getApplication()).setUserNum(loginResponse.userName);
							((YoungLibApp) getApplication()).setmPassWord(loginResponse.passWord);
							Intent login = new Intent(LoginActivity.this, SlidingActivity.class);
							LoginActivity.this.startActivity(login);
							finish();
						}
						else if(loginResponse.result == 1) // 登录失败
						{
							showAlertDialog(loginResponse.errorMsg, new YoungDialogButton("确定", null), null);
						}
					}
				}

				@Override
				public void onError(Exception e)
				{
					dismissProgressDialog();

					showAlertDialog("网络连接失败，请重新尝试", new YoungDialogButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							doLogin();
						}
					}),new YoungDialogButton("取消", null));
				}
			});
		}

		mLoginManager.login(bean);

		showProgressDialog();
	}
	
	private void doRigister(){
		Intent register = new Intent(this, RegisterActivity.class);
		this.startActivity(register);
	}

	@Override
	protected void onProgressDialogDismissed() {
		// TODO Auto-generated method stub
		
	}
}
