package com.example.mybookmanagesystem;

import java.util.List;

import Adapter.BookAdaper;
import Bean.Book;
import Bean.Borrow;
import DB.BookBean;
import DB.BorrowBean;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FindBook extends Activity {
ListView list;//�벼���е�listView�����
Button quit;
String id;
String bookid,bookname;
int booknumber;
BookBean bookBean=new BookBean(FindBook.this);
BorrowBean borrowBean=new BorrowBean(FindBook.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_book);
		list=(ListView)findViewById(R.id.listView1);
		quit=(Button)findViewById(R.id.button1);
		
		//�Ƚ��ܴ�����id
		Intent intent=getIntent();
		id=intent.getStringExtra("id");
		
//�˳��鿴��������Ӧ
	quit.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO �Զ����ɵķ������
			finish();
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_book, menu);
		return true;
	}
	@Override
	protected void onStart() {
		// TODO �Զ����ɵķ������
		super.onStart();
		List<Book>books=bookBean.showBookInfo();
		BookAdaper myAdapter=new BookAdaper(FindBook.this,R.layout.booklist,books);
		list.setAdapter(myAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO �Զ����ɵķ������
				Book book=(Book)arg0.getItemAtPosition(arg2);
				bookid=book.getBookid();
				bookname=book.getBookname();
				booknumber=book.getBooknumber();
				
				if(booknumber<=0){
					Toast.makeText(getApplicationContext(), "��ͼ������:0�����ɽ��ģ�", Toast.LENGTH_LONG).show();
				}else{
					boolean flage=false;
					List<Borrow> Borrowed= borrowBean.UserAllBorrowBooks(id);
					for(int i=0;i<Borrowed.size();i++){
						if((Borrowed.get(i).getBookid()).equals(bookid)){
							flage=true;
						}
					}
					if(flage){
						Toast.makeText(getApplicationContext(), "���ѽ��ģ������ظ����ģ�", Toast.LENGTH_LONG).show();
					}else{
		//����ָ��ͬһ�����飬��ͼ����һ���ģ�ÿ���û�ֻ�ܽ���һ����
		//�Ȳ鿴���û��Ƿ��Ѿ�����
				AlertDialog.Builder builder=new AlertDialog.Builder(FindBook.this);
				builder.setTitle("�Ƿ���ģ�");
				builder.setPositiveButton("�ǵ�", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Book tempbook=bookBean.BorrowBookNumberChange(bookid);
						bookBean.UpdateBorrowOrReturnBookInfo(tempbook);
						Borrow borrow=new Borrow(id, bookid, bookname);
						borrowBean.addBorrowBookInfo(borrow);
						bookBean=new BookBean(FindBook.this);
						Toast.makeText(getApplicationContext(), "��ϲ�����ĳɹ�", Toast.LENGTH_LONG).show();
						finish();
					}});
				builder.setNegativeButton("����,���뿴��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finish();
					}});
				builder.show();
					}
				}
			}
		});
	}
}
