package com.example.mybookmanagesystem;

import java.util.List;

import Adapter.BookAdaper;
import Adapter.BorrowAdapter;
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

public class BorrowBook extends Activity {
ListView list;
Button quit;
String id,bookid;
int booknumber;//某类型书籍数量
BorrowBean borrowBean=new BorrowBean(BorrowBook.this);
//借书和还书，书的数量应该实现加或者减
BookBean bookBean=new BookBean(BorrowBook.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.borrow_book);
		list=(ListView)findViewById(R.id.listView1);
		quit=(Button)findViewById(R.id.button1);
		
//先接受传来的id和password
		Intent intent=getIntent();
		id=intent.getStringExtra("id");
		
//退出查看按键的响应
		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
//				Intent intent=new Intent(BorrowBook.this,UserActivity.class);
//				startActivity(intent);
				finish();
				Toast.makeText(BorrowBook.this, "前往用户主界面", Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.borrow_book, menu);
		return true;
	}
	@Override
	protected void onStart() {
		// TODO 自动生成的方法存根
		super.onStart();
		List<Borrow>borrows=borrowBean.UserAllBorrowBooks(id);
		BorrowAdapter myAdapter=new BorrowAdapter(BorrowBook.this,R.layout.borrowlist,borrows);
		list.setAdapter(myAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO 自动生成的方法存根
				Borrow borrow=(Borrow)arg0.getItemAtPosition(arg2);
				bookid=borrow.getBookid();
				AlertDialog.Builder builder=new AlertDialog.Builder(BorrowBook.this);
				builder.setTitle("是否还此书？");
				builder.setPositiveButton("是的,还书", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						//这里要打开book表，修改书的数量
						bookBean=new BookBean(BorrowBook.this);
						Book tempbook=bookBean.ReturnBookNumberChange(bookid);
						bookBean.UpdateBorrowOrReturnBookInfo(tempbook);
						Borrow borrow=new Borrow(id,bookid);
						borrowBean.deleteBorrowBookInfo(borrow);
						Toast.makeText(getApplicationContext(), "恭喜！还书成功", Toast.LENGTH_LONG).show();
						finish();
					}});
				builder.setNegativeButton("算了,先不还", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finish();
					}});
				builder.show();
				
			}
		});
	}

}
