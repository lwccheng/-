package com.example.mybookmanagesystem;

import java.util.List;

import Adapter.BookAdaper;
import Bean.Book;
import DB.BookBean;
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

public class ViewBookInfo extends Activity {
ListView list;//与布局中的listView相配合
Button quit;
BookBean bookBean=new BookBean(ViewBookInfo.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_book_info);
		list=(ListView)findViewById(R.id.listView1);
		quit=(Button)findViewById(R.id.button1);
	//退出查看按键的响应
		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(ViewBookInfo.this,BookInfo.class);
				startActivity(intent);
				Toast.makeText(ViewBookInfo.this, "前往用户信息管理界面", Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_book_info, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		// TODO 自动生成的方法存根
		super.onStart();
		List<Book>books=bookBean.showBookInfo();
		BookAdaper myAdapter=new BookAdaper(ViewBookInfo.this,R.layout.booklist,books);
		list.setAdapter(myAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO 自动生成的方法存根
				Book book=(Book)arg0.getItemAtPosition(arg2);
				final String bookid=book.getBookid();
				final String bookname=book.getBookname();
				final int booknumber=book.getBooknumber();
				AlertDialog.Builder builder=new AlertDialog.Builder(ViewBookInfo.this);
				builder.setTitle("确定对该图书进行操作？");
				builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Intent intent=new Intent();
						Bundle bundel=new Bundle();
						bundel.putString("bookid", bookid);
						bundel.putString("bookname",bookname);
						bundel.putInt("booknumber", booknumber);
						intent.setClass(getApplicationContext(),UpdateBookInfo.class);
						intent.putExtras(bundel);
						startActivity(intent);
					}});
				builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Book book=new Book(bookid,bookname,booknumber);
							bookBean.deleteBookInfo(book);
						Toast.makeText(getApplicationContext(), "图书删除成功", Toast.LENGTH_LONG).show();
						onStart();
					}});
				builder.show();
				
			}
		});
	}

}
