package com.example.mybookmanagesystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class UserInfo extends Activity {
Button AddUserInfoBtn,UserViewBtn,returnBeforeBtn,returnLoginBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		AddUserInfoBtn=(Button)findViewById(R.id.button1);
		UserViewBtn=(Button)findViewById(R.id.button2);
		returnBeforeBtn=(Button)findViewById(R.id.button3);
		returnLoginBtn=(Button)findViewById(R.id.button4);
	//增加用户信息
		AddUserInfoBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(UserInfo.this,AddUserInfo.class);
				startActivity(intent);
				Toast.makeText(UserInfo.this, "前往添加用户信息", Toast.LENGTH_LONG).show();
			}
		});
	//查看用户信息
		UserViewBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(UserInfo.this,ViewUserInfo.class);
				startActivity(intent);
				Toast.makeText(UserInfo.this, "前往查看用户信息", Toast.LENGTH_LONG).show();
			}
		});
	//返回上一级
		returnBeforeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
			Intent intent=new Intent(UserInfo.this,AdminActivity.class);
			startActivity(intent);
			Toast.makeText(UserInfo.this,"正在前往管理员主界面", Toast.LENGTH_LONG).show();
			}
		});
	//返回到登录界面
		returnLoginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
			Intent intent=new Intent(UserInfo.this,Login.class);
			startActivity(intent);
			Toast.makeText(UserInfo.this,"正在前往登录界面", Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_info, menu);
		return true;
	}

}
