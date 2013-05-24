package com.young.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import com.young.activity.R;

public class ActMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_main, menu);
		return true;
	}

	// @Override
	// public void setContentView(int id) {
	// setContentView(getLayoutInflater().inflate(id, null));
	// }
	//
	// @Override
	// public void setContentView(View v) {
	// setContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT,
	// LayoutParams.MATCH_PARENT));
	// }
	//
	// @Override
	// public void setContentView(View v, LayoutParams params) {
	// super.setContentView(v, params);
	// // mHelper.registerAboveContentView(v, params);
	// }
	//
	// public void setBehindContentView(int id) {
	// setBehindContentView(getLayoutInflater().inflate(id, null));
	// }
	//
	// public void setBehindContentView(View v) {
	// setBehindContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT,
	// LayoutParams.MATCH_PARENT));
	// }
	//
	// public void setBehindContentView(View v, LayoutParams params) {
	// // mHelper.setBehindContentView(v, params);
	// }

}
