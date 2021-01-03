package com.example.mybookmanagesystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AdminActivity extends Activity {
Button userInfoBtn,bookInfoBtn,quit,borrowBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin);
		userInfoBtn=(Button)findViewById(R.id.button1);
		bookInfoBtn=(Button)findViewById(R.id.button2);
		quit=(Button)findViewById(R.id.button3);
		borrowBtn=(Button)findViewById(R.id.button4);
	//�û���Ϣ
		userInfoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent(AdminActivity.this,UserInfo.class);
				startActivity(intent);
				Toast.makeText(AdminActivity.this,"����ǰ������Ա������", Toast.LENGTH_LONG).show();
			}
		});
	//ͼ����Ϣ
		bookInfoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent(AdminActivity.this,BookInfo.class);
				startActivity(intent);
				Toast.makeText(AdminActivity.this,"����ǰ��ͼ��������", Toast.LENGTH_LONG).show();
			}
		});
	//ͼ�������Ϣ
		borrowBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent(AdminActivity.this,ViewBorrowInfo.class);
				startActivity(intent);
				Toast.makeText(AdminActivity.this,"����ǰ��ͼ�������Ϣ", Toast.LENGTH_LONG).show();
			}
		});
		
		
		
	//�˳�����Ա���档
		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent(AdminActivity.this,Login.class);
				startActivity(intent);
				Toast.makeText(AdminActivity.this,"�˳�����¼����", Toast.LENGTH_LONG).show();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
		return true;
	}

}
