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
EditText idEdit,passwordEdit;//�˺ź����������
Button loginBtn,resetBtn;//��¼���������ð���
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
	//����¼����
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				String id=idEdit.getText().toString();
		        String password=passwordEdit.getText().toString();
				if(admin.isChecked()==true){
						if(id.equals("admin") && password.equals("123")){
							Intent intent=new Intent(Login.this,AdminActivity.class);
							startActivity(intent);
							Toast.makeText(getApplicationContext(), "����Ա��¼�ɹ���", Toast.LENGTH_LONG).show();
																}
						else Toast.makeText(getApplicationContext(), "�û������������", Toast.LENGTH_LONG).show();
				}else{
					//����ǹ���Ա����ȥ���ݿ������Ƿ���ڸ��û�
					User user=new User(id,password);//����user������
					UserBean userBean=new UserBean(Login.this);//�����������ݿ�Ĳ���
					if(userBean.userIsExist(user)){
						Intent intent=new Intent();
						Bundle bundle=new Bundle();
						bundle.putString("id", id);//���ҽ����鼮�͸����붼�õ��ϡ�
						bundle.putString("password", password);//�������ȥ���޸������ʱ��ʹ��
						intent.setClass(getApplicationContext(),UserActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
						Toast.makeText(getApplicationContext(), "�û���¼�ɹ���", Toast.LENGTH_LONG).show();
					}else Toast.makeText(getApplicationContext(), "�û������������", Toast.LENGTH_LONG).show();
				}
			}
		});
		resetBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
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
