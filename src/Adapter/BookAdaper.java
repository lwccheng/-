package Adapter;

import java.util.List;

import com.example.mybookmanagesystem.R;

import Bean.Book;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BookAdaper extends ArrayAdapter{
	Book book;
	public BookAdaper(Context context, int resource, List<Book>books) {
		super(context, resource, books);
		// TODO 自动生成的构造函数存根
	}
	public void setList(Book book){
		this.book=book;
	}
	@Override
	public long getItemId(int position){
		return position;
		
	}
	public View getView(int position,View convertView,ViewGroup parent){
		book=(Book)getItem(position);//锁定对象的项目
		View view=LayoutInflater.from(getContext()).inflate(R.layout.booklist, parent,false);
		TextView bookid =(TextView)view.findViewById(R.id.textView1);
		TextView bookname =(TextView)view.findViewById(R.id.textView2);
		TextView booknumber =(TextView)view.findViewById(R.id.textView3);
		bookid.setText(book.getBookid());
		bookname.setText(book.getBookname());
		booknumber.setText(String.valueOf(book.getBooknumber()));
		return view;
	}
	
}
