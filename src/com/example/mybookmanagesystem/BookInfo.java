package com.example.mybookmanagesystem;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BookInfo extends Activity {
	Button AddBookInfoBtn,BookViewBtn,returnBeforeBtn,returnLoginBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_info);
		AddBookInfoBtn=(Button)findViewById(R.id.button1);
		BookViewBtn=(Button)findViewById(R.id.button2);
		returnBeforeBtn=(Button)findViewById(R.id.button3);
		returnLoginBtn=(Button)findViewById(R.id.button4);
	//增加图书信息
		AddBookInfoBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(BookInfo.this,AddBookInfo.class);
				startActivity(intent);
				Toast.makeText(BookInfo.this, "前往添加图书信息", Toast.LENGTH_LONG).show();
			}
		});
	//查看图书信息
		BookViewBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(BookInfo.this,ViewBookInfo.class);
				startActivity(intent);
				Toast.makeText(BookInfo.this, "前往查看图书信息", Toast.LENGTH_LONG).show();
			}
		});
	//返回上一级
		returnBeforeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
			Intent intent=new Intent(BookInfo.this,AdminActivity.class);
			startActivity(intent);
			Toast.makeText(BookInfo.this,"正在前往管理员主界面", Toast.LENGTH_LONG).show();
			}
		});
	//返回到登录界面
		returnLoginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
			Intent intent=new Intent(BookInfo.this,Login.class);
			startActivity(intent);
			Toast.makeText(BookInfo.this,"正在前往登录界面", Toast.LENGTH_LONG).show();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_info, menu);
		return true;
	}

}
