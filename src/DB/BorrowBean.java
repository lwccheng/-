package DB;
import java.util.ArrayList;
import java.util.List;

import Bean.Book;
import Bean.Borrow;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class BorrowBean {
	private Context context;
	private BorrowHelp borrowHelp;
	
public BorrowBean(Context context){
		this.context=context;
		borrowHelp=new BorrowHelp(context);
	}

//借阅的情况分析
//管理员而言，可以删除用户的借阅情况，可以查看借阅情况。
//用户而言，可以借书和还书，这就需要增加，删除，查看
//所以，这里只设增加，删除，查看，不实现修改。

//增加借书信息
	public void addBorrowBookInfo(Borrow borrow){
		SQLiteDatabase db=borrowHelp.getWritableDatabase();//写的方式打开数据库
		if(db.isOpen()){
			String BorrowId=borrow.getId();
			String BorrowBookId=borrow.getBookid();
			String BorrowBookName=borrow.getBookname();
			db.execSQL("insert into borrowInfo (id,bookid,bookname) values (?,?,?)",new Object[]{BorrowId,BorrowBookId,BorrowBookName});
			Log.v("添加借书信息操作", "借书 信息添加成功！");
			db.close();
		}else{
			Toast.makeText(this.context,"数据库打开失败", Toast.LENGTH_LONG).show();
		}
	}
//删除借书信息
	public void deleteBorrowBookInfo(Borrow borrow){
		SQLiteDatabase db=borrowHelp.getWritableDatabase();//同样的先以写的方式打开数据库
		if(db.isOpen()){
			String Id=borrow.getId();//获取借的书的编号
			String Bookid=borrow.getBookid();
			db.delete("borrowInfo","id=? and bookid=?",new String[]{Id,Bookid});
			Log.v("删除借书信息操作","删除借书信息成功！");
		}else{
			Toast.makeText(this.context,"数据库打开失败",Toast.LENGTH_LONG).show();
		}
	}

//查看借阅的书籍，这是管理员查看，可以查看所有人借书情况
	public List<Borrow>showBorrowBookInfo(){
		List<Borrow>borrows=null;
		SQLiteDatabase db=borrowHelp.getReadableDatabase();
		if(db.isOpen()){
				Cursor cursor = db.query("borrowInfo", null, null, null, null, null, null, null);//查询
				borrows=new ArrayList<Borrow>();
				while(cursor.moveToNext()){//一行一行遍历,并将一个一个book添加到books
					Borrow borrow=new Borrow();
					String BorrowId=cursor.getString(cursor.getColumnIndex("id"));//获取游标指定的id列的值
					borrow.setId(BorrowId);
					String BorrowBookId=cursor.getString(cursor.getColumnIndex("bookid"));
					borrow.setBookid(BorrowBookId);
					String BorrowBookName=cursor.getString(cursor.getColumnIndex("bookname"));
					borrow.setBookname(BorrowBookName);
				//用list的add方法将borrow添加到borrows
					borrows.add(borrow);
				}
				cursor.close();//关闭cursor
				db.close();
		}
		return borrows;
	}

//查看某学号用户全部的借阅信息
	public List<Borrow> UserAllBorrowBooks(String id){
		List<Borrow>borrows=null;
		SQLiteDatabase db=borrowHelp.getReadableDatabase();
		if(db.isOpen()){
				Cursor cursor = db.query("borrowInfo", null, "id=?", new String[]{id}, null, null, null);//查询
				borrows=new ArrayList<Borrow>();
				while(cursor.moveToNext()){//一行一行遍历,并将一个一个book添加到books
					Borrow borrow=new Borrow();
					String BorrowId=cursor.getString(cursor.getColumnIndex("id"));//获取游标指定的id列的值
					borrow.setId(BorrowId);
					String BorrowBookId=cursor.getString(cursor.getColumnIndex("bookid"));
					borrow.setBookid(BorrowBookId);
					String BorrowBookName=cursor.getString(cursor.getColumnIndex("bookname"));
					borrow.setBookname(BorrowBookName);
				//用list的add方法将borrow添加到borrows
					borrows.add(borrow);
				}
				cursor.close();//关闭cursor
				db.close();
		}
		return borrows;
	}
	

}
