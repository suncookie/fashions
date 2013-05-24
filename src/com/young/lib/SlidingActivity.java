package com.young.lib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.young.activity.R;
import com.young.view.AboutMeView;
import com.young.view.ContentBaseClass;
import com.young.view.FashionShopView;
import com.young.view.FashionView;
import com.young.view.FocusView;
import com.young.view.MessageView;

public class SlidingActivity extends FragmentActivity {
	SlidingMenu mSlidingMenu;
	Button btn;
	Handler mHandler = new Handler();
	AlertDialog mAlertDialog;
	private int curIndex = 0;
	private ContentBaseClass mAboutMe;
	private ContentBaseClass mFocus;
	private ContentBaseClass mCloseFashion;
	private ContentBaseClass mAllFashions;
	private ContentBaseClass mMessage;
	private ContentBaseClass mFashionShop;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mSlidingMenu = new SlidingMenu(this);
		setContentView(mSlidingMenu);
		View menu = getLayoutInflater().inflate(R.layout.menu, null);
		mSlidingMenu.setMenu(menu);
		Button btnMe = (Button) menu.findViewById(R.id.me);
		Button btnFocus = (Button) menu.findViewById(R.id.focus);
		Button btnClosefashion = (Button) menu.findViewById(R.id.closefashion);
		Button btnAllfashions = (Button) menu.findViewById(R.id.allfashions);
		Button btnMessage = (Button) menu.findViewById(R.id.message);
		Button btnFashionshop = (Button) menu.findViewById(R.id.fashionshop);
		curIndex = R.id.closefashion;
		mAboutMe = new AboutMeView(this);
		mCloseFashion = new FashionView(this, mSlidingMenu, FashionView.FLAG_CLOSE_FASHION);
		mAllFashions = new FashionView(this, mSlidingMenu, FashionView.FlAG_ALL_FASHIONS);
		mFocus = new FocusView(this, mSlidingMenu);
		mMessage = new MessageView(this, mSlidingMenu);
		mFashionShop = new FashionShopView(this, mSlidingMenu);
		SetViewByIndex(curIndex);
		MenuBtnClickListener menuBtnClickListener = new MenuBtnClickListener();
		btnMe.setOnClickListener(menuBtnClickListener);
		btnFocus.setOnClickListener(menuBtnClickListener);
		btnClosefashion.setOnClickListener(menuBtnClickListener);
		btnAllfashions.setOnClickListener(menuBtnClickListener);
		btnMessage.setOnClickListener(menuBtnClickListener);
		btnFashionshop.setOnClickListener(menuBtnClickListener);
		 mHandler.post(new Runnable() {
			
			@Override
			public void run() {
	//			mSlidingMenu.showMenu();
			}
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			showAlertDialog("是否要退出程序", new CUDialogButton("确定", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
					System.exit(0);
				}
			}), new CUDialogButton("取消", null));

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
	/**
	 * 信息提示dialog
	 * 
	 * @param message
	 * @param negativeButton
	 * @param positiveButton
	 */
	protected void showAlertDialog(final String message, final CUDialogButton negativeButton,
			final CUDialogButton positiveButton)
	{
		runOnUiThread(new Runnable()
		{
			public void run()
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(SlidingActivity.this);
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
	 * 共同alertDialog
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

	public class CUDialogButton
	{
		public String mText = null; 
		public DialogInterface.OnClickListener mListener = null;

		public CUDialogButton(String text, DialogInterface.OnClickListener listener)
		{
			mText = text;
			mListener = listener;
		}
	}
	private class MenuBtnClickListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			SetViewByIndex(v.getId());
			TextView contentTitle = (TextView)findViewById(R.id.contentTitle);
			switch(v.getId()){
			case R.id.me:
				contentTitle.setText(getBaseContext().getString(R.string.menu_me));
				break;
			case R.id.focus:
				contentTitle.setText("我的关注");
				break;
			case R.id.closefashion:
				contentTitle.setText("附近潮图");
				break;
			case R.id.allfashions:
				contentTitle.setText("全部潮图");
				break;
			case R.id.message:
				contentTitle.setText("消息");
				break;
			case R.id.fashionshop:
				contentTitle.setText("潮品汇");
				break;
			}
			mSlidingMenu.showMenu();
			Log.e("ad", "on click");
		}
	}
	private void SetViewByIndex(int curIndex){
		View content = null;
		ContentBaseClass contentView = null;
		switch(curIndex){
		case R.id.me:
			contentView = mAboutMe;
			content = getLayoutInflater().inflate(R.layout.content_about_me, null);
			break;
		case R.id.focus:
			contentView = mFocus;
			content = (View)getLayoutInflater().inflate(R.layout.content_focus, null);
			break;
		case R.id.closefashion:
			contentView = mCloseFashion;
			content = (View)getLayoutInflater().inflate(R.layout.content_close_fashion, null);
			break;
		case R.id.allfashions:
			contentView = mAllFashions;
			content = (View)getLayoutInflater().inflate(R.layout.content_close_fashion, null);
			break;
		case R.id.message:
			contentView = mMessage;
			content = (View)getLayoutInflater().inflate(R.layout.content_message, null);
			break;
		case R.id.fashionshop:
			contentView = mFashionShop;
			content = (View)getLayoutInflater().inflate(R.layout.content_fashion_shop, null);
			break;
		}
		contentView.SetContentView(mSlidingMenu, content);
	}
}
