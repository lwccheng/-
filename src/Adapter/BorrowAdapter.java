package Adapter;

import java.util.List;

import com.example.mybookmanagesystem.R;
import Bean.Borrow;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BorrowAdapter extends ArrayAdapter{
Borrow borrow;
	public BorrowAdapter(Context context, int resource, List<Borrow> borrows) {
		super(context, resource, borrows);
		// TODO 自动生成的构造函数存根
	}
	public void setList(Borrow borrow){
		this.borrow=borrow;
	}
	@Override
	public long getItemId(int position){
		return position;
		
	}
	public View getView(int position,View convertView,ViewGroup parent){
		borrow=(Borrow)getItem(position);//锁定对象的项目
		View view=LayoutInflater.from(getContext()).inflate(R.layout.borrowlist, parent,false);
		TextView BorrowId =(TextView)view.findViewById(R.id.textView1);
		TextView BorrowBookId =(TextView)view.findViewById(R.id.textView2);
		TextView BorrowBookName =(TextView)view.findViewById(R.id.textView3);
		BorrowId.setText(borrow.getId());
		BorrowBookId.setText(borrow.getBookid());
		BorrowBookName.setText(borrow.getBookname());
		return view;
	}
	
}
