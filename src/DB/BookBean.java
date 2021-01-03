package DB;

import java.util.ArrayList;
import java.util.List;

import Bean.Book;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class BookBean {
	private Context context;
	private BookHelp bookHelp;
//哪个Activity想要操作该类，就将它的上下文内容传入
	public BookBean(Context context){
		this.context=context;
		bookHelp=new BookHelp(context);//创建数据库
	}
	//只需要传书的编号，查找相应的书籍
	 String bookid;
	public BookBean(String bookid){
		this.bookid=bookid;
	}
	
	
//接下来就将增删改查的方法全部在这个类中去实现
//增加图书信息
	public void addBookInfo(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();//写的方式打开数据库
		if(db.isOpen()){
			String bookid=book.getBookid();
			String bookname=book.getBookname();
			int booknumber=book.getBooknumber();
			db.execSQL("insert into bookInfo (bookid,bookname,booknumber) values (?,?,?)",new Object[]{bookid,bookname,booknumber});
			Log.v("添加图书信息操作", "图书信息添加成功！");
			db.close();
		}else{
			Toast.makeText(this.context,"数据库打开失败", Toast.LENGTH_LONG).show();
		}
	}
//删除图书信息
	public void deleteBookInfo(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();//同样的先以写的方式打开数据库
		if(db.isOpen()){
			String bookid=book.getBookid();//直接根据bookid判断这本书是否存在
			db.delete("bookInfo","bookid=?",new String[]{bookid});//哪个数据库，哪个列，列的哪一个。
			Log.v("删除图书信息操作","删除图书信息成功！");
		}else{
			Toast.makeText(this.context,"数据库打开失败",Toast.LENGTH_LONG).show();
		}
	}
//更新用户信息
	public void updateBookInfo(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();//
		if(db.isOpen()){
			String bookid=book.getBookid();
			String bookname=book.getBookname();
			int booknumber=book.getBooknumber();
		
			ContentValues c=new ContentValues();//类似于HashMap，但这个只能存储基本类型
			c.put("bookid", bookid);
			c.put("bookname", bookname);
			c.put("booknumber", booknumber);
			db.update("bookInfo", c, "bookid=?",new String[]{bookid});
			Log.v("更新图书信息操作", "更新图书信息成功！");
		}else{
			Toast.makeText(this.context,"数据库打开失败",Toast.LENGTH_LONG).show();
		}
	}
//查看所有图书信息
	public List<Book>showBookInfo(){
		List<Book>books=null;
		SQLiteDatabase db=bookHelp.getReadableDatabase();//展示不需要操作，所以用都的方式操作数据库
		if(db.isOpen()){
				Cursor cursor = db.query("bookInfo", null, null, null, null, null, null, null);//查询userInfo的游标
				books=new ArrayList<Book>();
				while(cursor.moveToNext()){//一行一行遍历,并将一个一个book添加到books
					Book book=new Book();//创建图书的对象，这样就能加到books这个Book这个实体类型的容器中了。
					String bookid=cursor.getString(cursor.getColumnIndex("bookid"));//获取游标指定的bookid列的值
					book.setBookid(bookid);
					String bookname=cursor.getString(cursor.getColumnIndex("bookname"));
					book.setBookname(bookname);
					int booknumber=cursor.getInt(cursor.getColumnIndex("booknumber"));
					book.setBooknumber(booknumber);
				//用list的add方法将book添加到books
					books.add(book);
				}
				cursor.close();//关闭cursor
				db.close();
		}
		return books;
	}
	
//其他用到的辅助方法
	
//增加图书要看看是否已存在,返回true则为存在
	public boolean CheckBookExist(Book book){
		boolean bool=false;
		if(FindBookId(book).getBookid()!=null){
			bool=true;
		}
		return bool;
	}
//这个方法函数是帮CheckExist确定是否存在和想要添加的bookid相同的bookid。
	public Book FindBookId(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();
		Book tempBook=null;
		if(db.isOpen()){
			tempBook=new Book();//
			String bookid=book.getBookid();//先获取传入的图书的bookid
			Cursor cursor=db.query("bookInfo",null,"bookid=?",new String[]{bookid},null,null,null);
			while(cursor.moveToNext()){
				 	String tempBookId=cursor.getString(cursor.getColumnIndex("bookid"));//获取游标指定的id列的值
				 	tempBook.setBookid(tempBookId);
			}
		}
		return tempBook;
	}
//这里对还的书籍数量进行操作
	public Book ReturnBookNumberChange(String bookid){
		SQLiteDatabase db=bookHelp.getWritableDatabase();
		Book tempBook=null;
		if(db.isOpen()){
			tempBook=new Book();//
			Cursor cursor=db.query("bookInfo",null,"bookid=?",new String[]{bookid},null,null,null);
			while(cursor.moveToNext()){
					String tempBookId=cursor.getString(cursor.getColumnIndex("bookid"));
					String tempBookName=cursor.getString(cursor.getColumnIndex("bookname"));
				 	int tempBookNumber=cursor.getInt(cursor.getColumnIndex("booknumber"));//获取游标指定的id列的值
				 	tempBook.setBookid(tempBookId);
				 	tempBook.setBookname(tempBookName);
				 	tempBook.setBooknumber(tempBookNumber+1);//还书成功，数量加1
				 	break;
			}
		}
		return tempBook;
	}
//这里对借的书籍数量进行操作
	public Book BorrowBookNumberChange(String bookid){
		SQLiteDatabase db=bookHelp.getReadableDatabase();
		Book tempBook=null;
		if(db.isOpen()){
			tempBook=new Book();//
			Cursor cursor=db.query("bookInfo",null,"bookid=?",new String[]{bookid},null,null,null);
			while(cursor.moveToNext()){
				String tempBookId=cursor.getString(cursor.getColumnIndex("bookid"));
				String tempBookName=cursor.getString(cursor.getColumnIndex("bookname"));
			 	int tempBookNumber=cursor.getInt(cursor.getColumnIndex("booknumber"));//获取游标指定的id列的值
			 	tempBook.setBookid(tempBookId);
			 	tempBook.setBookname(tempBookName);
			 	tempBook.setBooknumber(tempBookNumber-1);//借书，数量键1
			 	break;
			}
		}
		return tempBook;
	}	
//更新借阅后的图书信息
	public void UpdateBorrowOrReturnBookInfo(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();
		if(db.isOpen()){
			String bookid=book.getBookid();
			String bookname=book.getBookname();
			int booknumber=book.getBooknumber();
		//
			ContentValues c=new ContentValues();//类似于HashMap，但这个只能存储基本类型
			c.put("bookid", bookid);
			c.put("bookname", bookname);
			c.put("booknumber", booknumber);
			db.update("bookInfo", c, "bookid=?",new String[]{bookid});
		}else{
			Toast.makeText(this.context,"数据库打开失败",Toast.LENGTH_LONG).show();
		}
	}
}
