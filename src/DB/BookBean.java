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
//�ĸ�Activity��Ҫ�������࣬�ͽ��������������ݴ���
	public BookBean(Context context){
		this.context=context;
		bookHelp=new BookHelp(context);//�������ݿ�
	}
	//ֻ��Ҫ����ı�ţ�������Ӧ���鼮
	 String bookid;
	public BookBean(String bookid){
		this.bookid=bookid;
	}
	
	
//�������ͽ���ɾ�Ĳ�ķ���ȫ�����������ȥʵ��
//����ͼ����Ϣ
	public void addBookInfo(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();//д�ķ�ʽ�����ݿ�
		if(db.isOpen()){
			String bookid=book.getBookid();
			String bookname=book.getBookname();
			int booknumber=book.getBooknumber();
			db.execSQL("insert into bookInfo (bookid,bookname,booknumber) values (?,?,?)",new Object[]{bookid,bookname,booknumber});
			Log.v("���ͼ����Ϣ����", "ͼ����Ϣ��ӳɹ���");
			db.close();
		}else{
			Toast.makeText(this.context,"���ݿ��ʧ��", Toast.LENGTH_LONG).show();
		}
	}
//ɾ��ͼ����Ϣ
	public void deleteBookInfo(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();//ͬ��������д�ķ�ʽ�����ݿ�
		if(db.isOpen()){
			String bookid=book.getBookid();//ֱ�Ӹ���bookid�ж��Ȿ���Ƿ����
			db.delete("bookInfo","bookid=?",new String[]{bookid});//�ĸ����ݿ⣬�ĸ��У��е���һ����
			Log.v("ɾ��ͼ����Ϣ����","ɾ��ͼ����Ϣ�ɹ���");
		}else{
			Toast.makeText(this.context,"���ݿ��ʧ��",Toast.LENGTH_LONG).show();
		}
	}
//�����û���Ϣ
	public void updateBookInfo(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();//
		if(db.isOpen()){
			String bookid=book.getBookid();
			String bookname=book.getBookname();
			int booknumber=book.getBooknumber();
		
			ContentValues c=new ContentValues();//������HashMap�������ֻ�ܴ洢��������
			c.put("bookid", bookid);
			c.put("bookname", bookname);
			c.put("booknumber", booknumber);
			db.update("bookInfo", c, "bookid=?",new String[]{bookid});
			Log.v("����ͼ����Ϣ����", "����ͼ����Ϣ�ɹ���");
		}else{
			Toast.makeText(this.context,"���ݿ��ʧ��",Toast.LENGTH_LONG).show();
		}
	}
//�鿴����ͼ����Ϣ
	public List<Book>showBookInfo(){
		List<Book>books=null;
		SQLiteDatabase db=bookHelp.getReadableDatabase();//չʾ����Ҫ�����������ö��ķ�ʽ�������ݿ�
		if(db.isOpen()){
				Cursor cursor = db.query("bookInfo", null, null, null, null, null, null, null);//��ѯuserInfo���α�
				books=new ArrayList<Book>();
				while(cursor.moveToNext()){//һ��һ�б���,����һ��һ��book��ӵ�books
					Book book=new Book();//����ͼ��Ķ����������ܼӵ�books���Book���ʵ�����͵��������ˡ�
					String bookid=cursor.getString(cursor.getColumnIndex("bookid"));//��ȡ�α�ָ����bookid�е�ֵ
					book.setBookid(bookid);
					String bookname=cursor.getString(cursor.getColumnIndex("bookname"));
					book.setBookname(bookname);
					int booknumber=cursor.getInt(cursor.getColumnIndex("booknumber"));
					book.setBooknumber(booknumber);
				//��list��add������book��ӵ�books
					books.add(book);
				}
				cursor.close();//�ر�cursor
				db.close();
		}
		return books;
	}
	
//�����õ��ĸ�������
	
//����ͼ��Ҫ�����Ƿ��Ѵ���,����true��Ϊ����
	public boolean CheckBookExist(Book book){
		boolean bool=false;
		if(FindBookId(book).getBookid()!=null){
			bool=true;
		}
		return bool;
	}
//������������ǰ�CheckExistȷ���Ƿ���ں���Ҫ��ӵ�bookid��ͬ��bookid��
	public Book FindBookId(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();
		Book tempBook=null;
		if(db.isOpen()){
			tempBook=new Book();//
			String bookid=book.getBookid();//�Ȼ�ȡ�����ͼ���bookid
			Cursor cursor=db.query("bookInfo",null,"bookid=?",new String[]{bookid},null,null,null);
			while(cursor.moveToNext()){
				 	String tempBookId=cursor.getString(cursor.getColumnIndex("bookid"));//��ȡ�α�ָ����id�е�ֵ
				 	tempBook.setBookid(tempBookId);
			}
		}
		return tempBook;
	}
//����Ի����鼮�������в���
	public Book ReturnBookNumberChange(String bookid){
		SQLiteDatabase db=bookHelp.getWritableDatabase();
		Book tempBook=null;
		if(db.isOpen()){
			tempBook=new Book();//
			Cursor cursor=db.query("bookInfo",null,"bookid=?",new String[]{bookid},null,null,null);
			while(cursor.moveToNext()){
					String tempBookId=cursor.getString(cursor.getColumnIndex("bookid"));
					String tempBookName=cursor.getString(cursor.getColumnIndex("bookname"));
				 	int tempBookNumber=cursor.getInt(cursor.getColumnIndex("booknumber"));//��ȡ�α�ָ����id�е�ֵ
				 	tempBook.setBookid(tempBookId);
				 	tempBook.setBookname(tempBookName);
				 	tempBook.setBooknumber(tempBookNumber+1);//����ɹ���������1
				 	break;
			}
		}
		return tempBook;
	}
//����Խ���鼮�������в���
	public Book BorrowBookNumberChange(String bookid){
		SQLiteDatabase db=bookHelp.getReadableDatabase();
		Book tempBook=null;
		if(db.isOpen()){
			tempBook=new Book();//
			Cursor cursor=db.query("bookInfo",null,"bookid=?",new String[]{bookid},null,null,null);
			while(cursor.moveToNext()){
				String tempBookId=cursor.getString(cursor.getColumnIndex("bookid"));
				String tempBookName=cursor.getString(cursor.getColumnIndex("bookname"));
			 	int tempBookNumber=cursor.getInt(cursor.getColumnIndex("booknumber"));//��ȡ�α�ָ����id�е�ֵ
			 	tempBook.setBookid(tempBookId);
			 	tempBook.setBookname(tempBookName);
			 	tempBook.setBooknumber(tempBookNumber-1);//���飬������1
			 	break;
			}
		}
		return tempBook;
	}	
//���½��ĺ��ͼ����Ϣ
	public void UpdateBorrowOrReturnBookInfo(Book book){
		SQLiteDatabase db=bookHelp.getWritableDatabase();
		if(db.isOpen()){
			String bookid=book.getBookid();
			String bookname=book.getBookname();
			int booknumber=book.getBooknumber();
		//
			ContentValues c=new ContentValues();//������HashMap�������ֻ�ܴ洢��������
			c.put("bookid", bookid);
			c.put("bookname", bookname);
			c.put("booknumber", booknumber);
			db.update("bookInfo", c, "bookid=?",new String[]{bookid});
		}else{
			Toast.makeText(this.context,"���ݿ��ʧ��",Toast.LENGTH_LONG).show();
		}
	}
}
