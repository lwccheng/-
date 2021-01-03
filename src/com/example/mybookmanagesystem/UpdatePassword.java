package com.example.mybookmanagesystem;

import Bean.User;
import DB.UserBean;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.SumPathEffect;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePassword extends Activity {
EditText MyId,MyPassword,NewPassword;
Button submit,quit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_password);
		MyId=(EditText)findViewById(R.id.editText1);
		MyPassword=(EditText)findViewById(R.id.editText2);
		NewPassword=(EditText)findViewById(R.id.editText3);
		submit=(Button)findViewById(R.id.button1);
		quit=(Button)findViewById(R.id.button2);
		
	//先接受传来的id和password
		Intent intent=getIntent();
		final String id=intent.getStringExtra("id");
		final String password=intent.getStringExtra("password");
		
	//展示
		MyId.setText(id);
		MyPassword.setText(password);
		MyId.setEnabled(false);
		MyPassword.setEnabled(false);
		
	//提交修改密码
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
			//保证新密码不为空
				if(NewPassword.length()==0||NewPassword.getText().toString().equals(" ")){
					Toast.makeText(UpdatePassword.this, "未填写完整！", Toast.LENGTH_LONG).show();
				}else{
					String password=NewPassword.getText().toString();
					User user=new User(id,password);
					UserBean userBean=new UserBean(UpdatePassword.this);
					User tempUser=userBean.By_Id_Find_User(user);//现在就获得了该用户所有的信息
					tempUser.setPassword(password);
					userBean.updateUserInfo(tempUser);
					Toast.makeText(UpdatePassword.this, "恭喜！修改成功", Toast.LENGTH_LONG).show();
					finish();
				}
			}
		});
		quit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
//				Intent intent=new Intent(UpdatePassword.this,UserActivity.class);
//				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_password, menu);
		return true;
	}

}
