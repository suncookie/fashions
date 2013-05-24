package com.young.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.young.activity.R;
import com.young.lib.SlidingMenu;

public abstract class ContentBaseClass extends View implements ContentBaseInterface{
	protected SlidingMenu mMenu;
	protected View mContent;

	public ContentBaseClass(Context context){
		super(context);
	}
    public ContentBaseClass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    
    public ContentBaseClass(Context context, AttributeSet attrs, int defStyle){
    	super(context, attrs, defStyle);
    }
	public ContentBaseClass(Context context, SlidingMenu menu){
		super(context);
		mMenu = menu;
	}
	public void SetContentView(SlidingMenu menu, View content){
		mMenu = menu;
		mContent = content;
		Button btn = (Button) mContent.findViewById(R.id.button_activity_back);
		mMenu.setContent(mContent);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mMenu.showMenu();
			}
		});
	}
}

interface ContentBaseInterface{
	public View getContent();
}
