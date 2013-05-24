package com.young.view;

import android.content.Context;
import android.view.View;

import com.young.lib.SlidingMenu;
 
public class AboutMeView extends ContentBaseClass {

	public AboutMeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void SetContentView(SlidingMenu menu, View content) {
		// TODO Auto-generated method stub
		super.SetContentView(menu, content);
		
	}

	@Override
	public View getContent() {
		// TODO Auto-generated method stub
		//return getLayoutInflater().inflate(R.layout.content_about_me, null);
		return null;
	}

}
