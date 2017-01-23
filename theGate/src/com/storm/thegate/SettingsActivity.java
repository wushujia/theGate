package com.storm.thegate;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.common.utils.ULog;
import com.storm.thegate.base.BaseActivity;

public class SettingsActivity extends BaseActivity implements OnClickListener{
	TextView title;
	TextView right_text;
	TextView back;
	
	List<AVObject> mList = new ArrayList<AVObject>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		ULog.i( "SettingsActivity onCreate");
		
		initView();
		initData();
		
		fetchData();
	}
	
	private void initView() {
		title = (TextView)findViewById(R.id.title);
		right_text = (TextView)findViewById(R.id.right_text);
		back = (TextView)findViewById(R.id.back);
		right_text.setVisibility(View.VISIBLE);
		right_text.setClickable(true);
		right_text.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	private void initData() {
		title.setText("设置");
		right_text.setText("意见反馈");
	}
	
	private void fetchData() {
		ULog.i( "fetchData");
	    mList.clear();
	    AVQuery<AVObject> avQuery = new AVQuery<AVObject>("TestObject");
	    avQuery.orderByDescending("updatedAt");
	    avQuery.whereMatches("shows2", "yes");
	    avQuery.setLimit(2);
	    avQuery.findInBackground(new FindCallback<AVObject>() {
	        @Override
	        public void done(List<AVObject> list, AVException e) {
	          if (e == null) {
	            mList.addAll(list);
	            for (int i = 0; i < mList.size(); i++){
	            	ULog.i( mList.get(i).get("words").toString() + ":" + mList.get(i).getObjectId());
	            }
	          } else {
	            e.printStackTrace();
	          }
	        }
	      });
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
//		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_text:
			
			break;
		case R.id.back:
			onBackPressed();
			break;

		default:
			break;
		}
	}
}
