package com.young.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.young.lib.SlidingActivity.CUDialogButton;

public class YoungTool{
	static AlertDialog mAlertDialog = null;
	public static void showAlertDialog(Context context, final String message, final YoungDialogButton youngDialogButton,
			final YoungDialogButton positiveButton)
	{
		//runOnUiThread(new Runnable()
		{
			//public void run()
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage(message);
				if(null != youngDialogButton)
				{
					builder.setNegativeButton(youngDialogButton.mText, youngDialogButton.mListener);
				}
				if(null != positiveButton)
				{
					builder.setPositiveButton(positiveButton.mText, positiveButton.mListener);
				}
				mAlertDialog = builder.create();
				mAlertDialog.show();
			}
		};
	}

	/**
	 * 共同alertDialog
	 */
	protected void dismissAlertDialog()
	{
		if(mAlertDialog != null && mAlertDialog.isShowing())
		{
			//runOnUiThread(new Runnable()
			{
				//public void run()
				{
					mAlertDialog.dismiss();
					mAlertDialog = null;
				}
			};
		}
	}
	public class YoungDialogButton
	{
		public String mText = null; 
		public DialogInterface.OnClickListener mListener = null;

		public YoungDialogButton(String text, DialogInterface.OnClickListener listener)
		{
			mText = text;
			mListener = listener;
		}
	}
}
