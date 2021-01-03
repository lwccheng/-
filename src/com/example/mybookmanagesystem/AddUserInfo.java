package com.example.mybookmanagesystem;

import Bean.User;
import DB.UserBean;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserInfo extends Activity {
EditText id,name,classname,password,phonenumber;
Button submit,quit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_user_info);
		id=(EditText)findViewById(R.id.editText1);
		name=(EditText)findViewById(R.id.editText2);
		classname=(EditText)findViewById(R.id.editText3);
		password=(EditText)findViewById(R.id.editText4);
		phonenumber=(EditText)findViewById(R.id.editText5);
		submit=(Button)findViewById(R.id.button1);
		quit=(Button)findViewById(R.id.button2);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				 String tempId=id.getText().toString();
				 String tempName=name.getText().toString();
				 String tempClassName=classname.getText().toString();
				 String tempPassword=password.getText().toString();
				 String tempPhoneNumber=phonenumber.getText().toString();
				 User user=new User(tempId,tempName,tempClassName,tempPassword,tempPhoneNumber);
				 UserBean userBean=new UserBean(AddUserInfo.this);
				 if(tempId.length()==0||tempName.length()==0||tempClassName.length()==0||tempPassword.length()==0||tempPhoneNumber.length()==0){
					 Toast.makeText(AddUserInfo.this, "未填写完整！", Toast.LENGTH_LONG).show();
				 }else{
					 if(userBean.CheckExist(user)){
						 Toast.makeText(AddUserInfo.this, "该学号已存在！", Toast.LENGTH_LONG).show();	 
					 }else{
						 userBean.addUserInfo(user);
						 Log.v("增加用户信息", "增加用户信息成功");
						 Toast.makeText(AddUserInfo.this, "恭喜！增加成功！", Toast.LENGTH_LONG).show();
						 finish();
						 Intent intent=new Intent(AddUserInfo.this,UserInfo.class);
						 startActivity(intent);
					 }
				 }
			}
		});
		quit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
			Intent intent=new Intent(AddUserInfo.this,UserInfo.class);
			startActivity(intent);
			Toast.makeText(getApplicationContext(), "返回管理用户界面", Toast.LENGTH_LONG).show();
		}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_user_info, menu);
		return true;
	}

}
