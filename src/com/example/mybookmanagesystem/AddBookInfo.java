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
				// TODO �Զ����ɵķ������
				 String tempBookId=bookid.getText().toString();
				 String tempBookName=bookname.getText().toString();
				 String Str_tempBookNumber=booknumber.getText().toString();
				 int tempBookNumber=Integer.parseInt(Str_tempBookNumber);
				 Book book=new Book(tempBookId,tempBookName,tempBookNumber);
				 BookBean bookBean=new BookBean(AddBookInfo.this);
				 if(tempBookId.length()==0||tempBookName.length()==0||Str_tempBookNumber.length()==0){
					 Toast.makeText(AddBookInfo.this, "δ��д��ȷ��������", Toast.LENGTH_LONG).show();
				 }
				 else{
					 if(bookBean.CheckBookExist(book)){
						 Toast.makeText(AddBookInfo.this, "ͼ�����Ѵ��ڣ�", Toast.LENGTH_LONG).show();	 
					 }else{
						 bookBean.addBookInfo(book);
						 Log.v("����ͼ����Ϣ", "����ͼ����Ϣ�ɹ�");						 
						 Toast.makeText(AddBookInfo.this, "��ϲ�����ӳɹ���", Toast.LENGTH_LONG).show();
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
		// TODO �Զ����ɵķ������
	Intent intent=new Intent(AddBookInfo.this,BookInfo.class);
	startActivity(intent);
	Toast.makeText(getApplicationContext(), "���ع���ͼ�����", Toast.LENGTH_LONG).show();
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
