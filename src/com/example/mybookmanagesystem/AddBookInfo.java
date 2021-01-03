package com.example.mybookmanagesystem;

import Bean.Book;
import DB.BookBean;
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

public class AddBookInfo extends Activity {
EditText bookid,bookname,booknumber;
Button submit,quit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_book_info);
		bookid=(EditText)findViewById(R.id.editText1);
		bookname=(EditText)findViewById(R.id.editText2);
		booknumber=(EditText)findViewById(R.id.editText3);
		submit=(Button)findViewById(R.id.button1);
		quit=(Button)findViewById(R.id.button2);
submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				 String tempBookId=bookid.getText().toString();
				 String tempBookName=bookname.getText().toString();
				 String Str_tempBookNumber=booknumber.getText().toString();
				 int tempBookNumber=Integer.parseInt(Str_tempBookNumber);
				 Book book=new Book(tempBookId,tempBookName,tempBookNumber);
				 BookBean bookBean=new BookBean(AddBookInfo.this);
				 if(tempBookId.length()==0||tempBookName.length()==0||Str_tempBookNumber.length()==0){
					 Toast.makeText(AddBookInfo.this, "未填写正确或完整！", Toast.LENGTH_LONG).show();
				 }
				 else{
					 if(bookBean.CheckBookExist(book)){
						 Toast.makeText(AddBookInfo.this, "图书编号已存在！", Toast.LENGTH_LONG).show();	 
					 }else{
						 bookBean.addBookInfo(book);
						 Log.v("增加图书信息", "增加图书信息成功");						 
						 Toast.makeText(AddBookInfo.this, "恭喜！增加成功！", Toast.LENGTH_LONG).show();
						 finish();
						 Intent intent=new Intent(AddBookInfo.this,BookInfo.class);
						 startActivity(intent);
					 }
				 }
			}
		});
quit.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
	Intent intent=new Intent(AddBookInfo.this,BookInfo.class);
	startActivity(intent);
	Toast.makeText(getApplicationContext(), "返回管理图书界面", Toast.LENGTH_LONG).show();
}
});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_book_info, menu);
		return true;
	}

}
