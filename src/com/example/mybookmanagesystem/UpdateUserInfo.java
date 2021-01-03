package com.example.mybookmanagesystem;

import Bean.User;
import DB.UserBean;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUserInfo extends Activity {
EditText id,name,classname,password,phonenumber;
Button submitUpdate,quit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_user_info);
		id=(EditText)findViewById(R.id.editText1);
		name=(EditText)findViewById(R.id.editText2);
		classname=(EditText)findViewById(R.id.editText3);
		password=(EditText)findViewById(R.id.editText4);
		phonenumber=(EditText)findViewById(R.id.editText5);
		submitUpdate=(Button)findViewById(R.id.button1);
		quit=(Button)findViewById(R.id.button2);
		Intent intent=getIntent();
	//以下是获取查询用户信息界面传过来的数据
		String tempid=intent.getStringExtra("id");
		String tempname=intent.getStringExtra("name");
		String tempclassname=intent.getStringExtra("classname");
		String temppassword=intent.getStringExtra("password");
		String tempphonenumber=intent.getStringExtra("phonenumber");
	//将这些获取的数据进行显示
		id.setText(tempid);
		id.setEnabled(false);
		name.setText(tempname);
		classname.setText(tempclassname);
		password.setText(temppassword);
		phonenumber.setText(tempphonenumber);
	//如果递交了更新
		submitUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				//还是判断一下是否填写完整，
				if(id.length()==0||name.length()==0||classname.length()==0||password.length()==0||phonenumber.length()==0){
					 Toast.makeText(UpdateUserInfo.this, "未填写完整！", Toast.LENGTH_LONG).show();
				 }else{
					 String wantUpdateId=id.getText().toString();
					 String wantUpdateName=name.getText().toString();
					 String wantUpdateClassName=classname.getText().toString();
					 String wantUpdatePassword=password.getText().toString();
					 String wantUpdatePhonenumber=phonenumber.getText().toString();
					 User user=new User(wantUpdateId,wantUpdateName,wantUpdateClassName,wantUpdatePassword,wantUpdatePhonenumber);
					 UserBean userBean=new UserBean(UpdateUserInfo.this);
					 userBean.updateUserInfo(user);
					 Toast.makeText(UpdateUserInfo.this,"用户信息修改成功！",Toast.LENGTH_LONG).show();
					 Intent intent=new Intent(UpdateUserInfo.this,ViewUserInfo.class);
					 startActivity(intent);
				 }
			}
		});
	//取消修改返回查看界面
		quit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(UpdateUserInfo.this,ViewUserInfo.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_user, menu);
		return true;
	}

}
