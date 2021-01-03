package com.example.mybookmanagesystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends Activity {
Button findBook,IsBorrorBook,UpdateMyPassword,quit;
TextView welcomeName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);
		findBook=(Button)findViewById(R.id.button1);
		IsBorrorBook=(Button)findViewById(R.id.button2);
		UpdateMyPassword=(Button)findViewById(R.id.button3);
		quit=(Button)findViewById(R.id.button4);
		welcomeName=(TextView)findViewById(R.id.textView2);
	//先把Login传过来的id和password接收
		Intent intent=getIntent();
		final String MyId=intent.getStringExtra("id");//当前用户的账号和密码
		final String MyPassword=intent.getStringExtra("password");
		welcomeName.setText(MyId);
		
		
	//查找书籍
		findBook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("id", MyId);
				intent.putExtras(bundle);
				intent.setClass(getApplicationContext(), FindBook.class);
				startActivity(intent);
				Toast.makeText(UserActivity.this, "查找书籍", Toast.LENGTH_LONG).show();
			}
		});
	//自己的借阅情况
		IsBorrorBook.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("id", MyId);
				intent.putExtras(bundle);
				intent.setClass(getApplicationContext(), BorrowBook.class);
				startActivity(intent);
				Toast.makeText(UserActivity.this, "借阅情况", Toast.LENGTH_LONG).show();
			}
		});
	//修改自己的登录密码
		UpdateMyPassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("id", MyId);
				bundle.putString("password",MyPassword);
				intent.putExtras(bundle);
				intent.setClass(getApplicationContext(), UpdatePassword.class);
				startActivity(intent);
				Toast.makeText(UserActivity.this, "修改密码", Toast.LENGTH_LONG).show();
			}
		});
	//退出登录
		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}

}
