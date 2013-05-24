package com.young.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.young.common.YoungHttpProgressDialog.YoungProgressDialogListener;

public abstract class YoungBaseActivity extends Activity {
	private YoungHttpProgressDialog mProgressDialog = null; // 页面加载时显示的progress
	private AlertDialog mAlertDialog = null; // 显示提示信息的dialog

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		mProgressDialog = new YoungHttpProgressDialog(this);
		mProgressDialog.setYoungProgressDialogListener(new YoungProgressDialogListener()
		{
			@Override
			public void onDismiss()
			{
				onProgressDialogDismissed();
			}
		});
	}

	protected abstract void onProgressDialogDismissed();

	@Override
	protected void onDestroy()
	{
		dismissProgressDialog();
		dismissAlertDialog();

		super.onDestroy();
	}

	/**
	 * 显示“正在加载”
	 */
	protected void showProgressDialog()
	{
		runOnUiThread(new Runnable()
		{
			public void run()
			{
				mProgressDialog.show();
			}
		});
	}

	protected boolean isProgressDialogShowing()
	{
		boolean ret = false;
		if(null != mProgressDialog)
		{
			ret = mProgressDialog.isShowing();
		}
		return ret;
	}

	/**
	 * 隐藏“正在加载”
	 */
	protected void dismissProgressDialog()
	{
		if(null != mProgressDialog && mProgressDialog.isShowing())
		{
			runOnUiThread(new Runnable()
			{
				public void run()
				{
					mProgressDialog.dismiss();
				}
			});
		}
	}

	/**
	 * 显示提示信息的dialog
	 * 
	 * @param message
	 * @param negativeButton
	 * @param positiveButton
	 */
	protected void showAlertDialog(final String message, final YoungDialogButton negativeButton,
			final YoungDialogButton positiveButton)
	{
		runOnUiThread(new Runnable()
		{
			public void run()
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(YoungBaseActivity.this);
				builder.setMessage(message);
				if(null != negativeButton)
				{
					builder.setNegativeButton(negativeButton.mText, negativeButton.mListener);
				}
				if(null != positiveButton)
				{
					builder.setPositiveButton(positiveButton.mText, positiveButton.mListener);
				}
				mAlertDialog = builder.create();
				mAlertDialog.show();
			}
		});
	}

	/**
	 * 隐藏alertDialog
	 */
	protected void dismissAlertDialog()
	{
		if(mAlertDialog != null && mAlertDialog.isShowing())
		{
			runOnUiThread(new Runnable()
			{
				public void run()
				{
					mAlertDialog.dismiss();
					mAlertDialog = null;
				}
			});
		}
	}

	/**
	 * 提示信息的alertdialog上的按钮
	 * 
	 * @author Gaojun
	 * 
	 *         2013-4-7
	 */
	protected class	YoungDialogButton
	{
		public String mText = null; // 按钮上显示的文字
		public DialogInterface.OnClickListener mListener = null; // 按钮点击响应事件

		public YoungDialogButton(String text, DialogInterface.OnClickListener listener)
		{
			mText = text;
			mListener = listener;
		}
	}
}
