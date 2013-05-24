package com.young.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.Responce.YoungJSonBeanCloseFashionResponse;
import com.young.activity.R;
import com.young.json.YoungJsonBeanBase;
import com.young.json.request.YoungJSonBeanCloseFashionRequest;
import com.young.json.request.YoungJsonBeanLoginRequest;
import com.young.lib.ImageAdapter;
import com.young.lib.SlidingMenu;
import com.young.network.YoungHttpBaseManager;
import com.young.network.YoungHttpBaseManager.YoungHttpCallbackAPI;
import com.young.network.YoungHttpCloseFashionManager;


public class FashionView extends ContentBaseClass {
	public static final int FLAG_CLOSE_FASHION = 0;
	public static final int FlAG_ALL_FASHIONS = 1;
	private int mFlag = FLAG_CLOSE_FASHION;
	private YoungHttpBaseManager mHttpManager = null;
	public FashionView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    public FashionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    
    public FashionView(Context context, AttributeSet attrs, int defStyle){
    	super(context, attrs, defStyle);
    }
	public FashionView(Context context, SlidingMenu menu, int flag){
		super(context, menu);
		mFlag = flag;
	}
	@Override
	public View getContent() {
		// TODO Auto-generated method stub
		//return getLayoutInflater().inflate(R.layout.content_close_fashion, null);
		return null;
	}

	@Override
	public void SetContentView(SlidingMenu menu, View content) {
		// TODO Auto-generated method stub
		super.SetContentView(menu, content);
		if(mFlag == FLAG_CLOSE_FASHION){
			final YoungJSonBeanCloseFashionRequest bean = new YoungJSonBeanCloseFashionRequest();
			bean.pageCount = 0;
			bean.pageIndex = 1;
			if(mHttpManager == null){
				mHttpManager = new YoungHttpCloseFashionManager();
				mHttpManager.setCallBack(new YoungHttpCallbackAPI() {
					
					@Override
					public void onError(Exception e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onCallFinished(YoungJsonBeanBase bean) {
						// TODO Auto-generated method stub
						if(null != bean)
						{
							YoungJSonBeanCloseFashionResponse httpResponse = (YoungJSonBeanCloseFashionResponse) bean;
							if(httpResponse.result == 0) // 成功
							{
								Log.d("res", "get close");
								Log.d("res", bean.toJson());
							}
							else if(httpResponse.result == 1) // 失败
							{
								//showAlertDialog(loginResponse.errorMsg, new YoungDialogButton("确定", null), null);
							}
						}
					}
				});
;			}
			((YoungHttpCloseFashionManager)mHttpManager).getImages(bean);
		}else{
			
		}
		TextView titleView = (TextView)content.findViewById(R.id.contentTitle);
		titleView.setText("附近潮图");
		HorizontalScrollView scrollView =  (HorizontalScrollView)content.findViewById(R.id.topScrollView);
		ImageView imageView = new ImageView(this.getContext());
		imageView.setImageResource(R.drawable.img1);
		scrollView.addView(imageView);
		GridView gridImage = (GridView)content.findViewById(R.id.gridViewImage);
		gridImage.setAdapter(new ImageAdapter(this.getContext()));
		//gridImage.setBackgroundResource(R.drawable.activity_content);
		gridImage.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
				{
					if(mMenu.menuIsShow()){
						mMenu.showMenu(); //收
						Log.d("test", "isshow");
					}else{
//						Intent aboutMe = new Intent(CloseFashionView.this, AboutMeActivity.class);
//						startActivity(aboutMe);
					}
				}
			});
	}

}
