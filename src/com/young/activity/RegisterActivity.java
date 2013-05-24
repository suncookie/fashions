package com.young.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.young.Responce.YoungJSonBeanRegisterResponse;
import com.young.common.YoungBaseActivity;
import com.young.common.YoungLibApp;
import com.young.json.YoungJsonBeanBase;
import com.young.json.request.YoungJSonBeanRegisterRequest;
import com.young.network.YoungHttpBaseManager.YoungHttpCallbackAPI;
import com.young.network.YoungHttpRegisterManager;

public class RegisterActivity extends YoungBaseActivity {
	YoungHttpRegisterManager mRegister;
	EditText mEtUserName;
	EditText mEtPassWord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Button btnRegister = (Button) findViewById(R.id.Register);
		mEtUserName = (EditText) findViewById(R.id.editPhone);
		mEtPassWord = (EditText) findViewById(R.id.editPassword);
		btnRegister.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doRegister();
			}
		});
	}

	private void doRegister() {
		final YoungJSonBeanRegisterRequest bean = new YoungJSonBeanRegisterRequest();
		bean.userNum = mEtUserName.getText().toString();
		bean.passWord = mEtPassWord.getText().toString();
		if (null == mRegister) {
			mRegister = new YoungHttpRegisterManager();
			mRegister.setCallBack(new YoungHttpCallbackAPI() {
				@Override
				public void onCallFinished(YoungJsonBeanBase bean) {
					dismissProgressDialog();

					if (null != bean) {
						YoungJSonBeanRegisterResponse registerResponse = (YoungJSonBeanRegisterResponse) bean;
						if (registerResponse.result == 0) // 注册成功
						{
							// 2. 保存userName
							((YoungLibApp) getApplication())
									.setUserNum(registerResponse.userName);
							((YoungLibApp) getApplication())
							.setmPassWord(registerResponse.passWord);
							runOnUiThread(new Runnable() {
								public void run() {
									Toast toast = Toast.makeText(
											RegisterActivity.this, "注册成功",
											Toast.LENGTH_SHORT);
									toast.show();
								}
							});
							finish();
						} else if (registerResponse.result == 1) // 注册失败
						{
							showAlertDialog(registerResponse.errorMsg,
									new YoungDialogButton("确定", null), null);
						}
					}
				}

				@Override
				public void onError(Exception e) {
					dismissProgressDialog();

					// showAlertDialog("网络连接失败，请重新尝试", new CUDialogButton("确定",
					// new DialogInterface.OnClickListener()
					// {
					// @Override
					// public void onClick(DialogInterface dialog, int which)
					// {
					// doLogin();
					// }
					// }), new CUDialogButton("取消", null));
				}
			});
		}

		mRegister.register(bean);
		showProgressDialog();
	}

	@Override
	protected void onProgressDialogDismissed() {
		// TODO Auto-generated method stub

	}
}
