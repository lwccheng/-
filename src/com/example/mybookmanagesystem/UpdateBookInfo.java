package com.example.mybookmanagesystem;

import Bean.Book;
import DB.BookBean;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBookInfo extends Activity {
EditText bookid,bookname,booknumber;
Button submitUpdate,quit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_book_info);
		bookid=(EditText)findViewById(R.id.editText1);
		bookname=(EditText)findViewById(R.id.editText2);
		booknumber=(EditText)findViewById(R.id.editText3);
		submitUpdate=(Button)findViewById(R.id.button1);
		quit=(Button)findViewById(R.id.button2);
		Intent intent=getIntent();
		//以下是获取查询用户信息界面传过来的数据
			String tempBookid=intent.getStringExtra("bookid");
			String tempBookname=intent.getStringExtra("bookname");
			int tempBooknumber=intent.getIntExtra("booknumber", 0);
		//将这些获取的数据进行显示
			bookid.setText(tempBookid);
			bookid.setEnabled(false);
			bookname.setText(tempBookname);
			booknumber.setText(String.valueOf(tempBooknumber));
			//如果递交了更新
			submitUpdate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO 自动生成的方法存根
					//还是判断一下是否填写完整，
					if(bookid.length()==0||bookname.length()==0||booknumber.length()==0){
						 Toast.makeText(UpdateBookInfo.this, "未填写完整！", Toast.LENGTH_LONG).show();
					 }else{
						 String wantUpdateBookId=bookid.getText().toString();
						 String wantUpdateBookName=bookname.getText().toString();
						 String Str_wantUpdateBookNumber=booknumber.getText().toString();
						 int wantUpdateBookNumber=Integer.parseInt(Str_wantUpdateBookNumber);
						 Book book=new Book(wantUpdateBookId,wantUpdateBookName,wantUpdateBookNumber);
						 BookBean bookBean=new BookBean(UpdateBookInfo.this);
						 bookBean.updateBookInfo(book);
						 Toast.makeText(UpdateBookInfo.this,"用户信息修改成功！",Toast.LENGTH_LONG).show();
						 Intent intent=new Intent(UpdateBookInfo.this,ViewBookInfo.class);
						 startActivity(intent);
					 }
				}
			});
		//取消修改返回查看界面
			quit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO 自动生成的方法存根
					Intent intent=new Intent(UpdateBookInfo.this,ViewBookInfo.class);
					startActivity(intent);
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_book_info, menu);
		return true;
	}

}
