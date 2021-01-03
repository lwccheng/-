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
import android.widget.RadioButton;
import android.widget.Toast;

public class Login extends Activity {
EditText idEdit,passwordEdit;//账号和密码输入框
Button loginBtn,resetBtn;//登录按键和重置按键
RadioButton admin,user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		idEdit=(EditText)findViewById(R.id.editText1);
		passwordEdit=(EditText)findViewById(R.id.editText2);
		admin=(RadioButton)findViewById(R.id.radioButton1);
		user=(RadioButton)findViewById(R.id.radioButton2);
		loginBtn=(Button)findViewById(R.id.button1);
		resetBtn=(Button)findViewById(R.id.button2);
	//按登录键后
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				String id=idEdit.getText().toString();
		        String password=passwordEdit.getText().toString();
				if(admin.isChecked()==true){
						if(id.equals("admin") && password.equals("123")){
							Intent intent=new Intent(Login.this,AdminActivity.class);
							startActivity(intent);
							Toast.makeText(getApplicationContext(), "管理员登录成功！", Toast.LENGTH_LONG).show();
																}
						else Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_LONG).show();
				}else{
					//如果是管理员，就去数据库中找是否存在该用户
					User user=new User(id,password);//创建user并传参
					UserBean userBean=new UserBean(Login.this);//创建用于数据库的操作
					if(userBean.userIsExist(user)){
						Intent intent=new Intent();
						Bundle bundle=new Bundle();
						bundle.putString("id", id);//查找借阅书籍和改密码都用得上。
						bundle.putString("password", password);//传密码过去，修改密码的时候使用
						intent.setClass(getApplicationContext(),UserActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
						Toast.makeText(getApplicationContext(), "用户登录成功！", Toast.LENGTH_LONG).show();
					}else Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_LONG).show();
				}
			}
		});
		resetBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				idEdit.setText("");
				passwordEdit.setText("");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
