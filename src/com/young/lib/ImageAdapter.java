package com.young.lib;

import com.young.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter
{
	// 定义Context
	private Context		mContext;
	// 定义整型数组 即图片源
	private Integer[]	mImageIds	= 
	{ 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher,
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 

	};

	public ImageAdapter(Context c)
	{
		mContext = c;
	}

	// 获取图片的个数
	public int getCount()
	{
		return mImageIds.length;
	}

	// 获取图片在库中的位置
	public Object getItem(int position)
	{
		return position;
	}


	// 获取图片ID
	public long getItemId(int position)
	{
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageView;
		TextView textView;
		if (convertView == null)
		{
			// 给ImageView设置资源
			imageView = new ImageView(mContext);
			// 设置布局 图片120×120显示
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			// 设置显示比例类型
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		}
		else
		{
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mImageIds[position]);
		return imageView;
	}

}
