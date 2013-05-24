
package com.young.common;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.young.activity.R;

public class YoungHttpProgressDialog extends AlertDialog
{
	private YoungProgressDialogListener mListener;

	public interface YoungProgressDialogListener
	{
		public void onDismiss();
	}

	public YoungHttpProgressDialog(Context context)
	{
		super(context);
	}

	public void setYoungProgressDialogListener(YoungProgressDialogListener listener)
	{
		mListener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.widget_progressdialog);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
			if(null != mListener)
			{
				YoungDebuger.Log.d("widget", "httpProgressDialog dismiss。。。。dispatchKeyEvent");
				mListener.onDismiss();
			}
		}

		return super.dispatchKeyEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		if(null != mListener)
		{
			YoungDebuger.Log.d("widget", "httpProgressDialog dismiss。。。。dispatchTouchEvent");
			mListener.onDismiss();
		}

		return super.dispatchTouchEvent(ev);
	}
}
