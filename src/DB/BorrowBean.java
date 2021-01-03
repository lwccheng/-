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

//���ĵ��������
//����Ա���ԣ�����ɾ���û��Ľ�����������Բ鿴���������
//�û����ԣ����Խ���ͻ��飬�����Ҫ���ӣ�ɾ�����鿴
//���ԣ�����ֻ�����ӣ�ɾ�����鿴����ʵ���޸ġ�

//���ӽ�����Ϣ
	public void addBorrowBookInfo(Borrow borrow){
		SQLiteDatabase db=borrowHelp.getWritableDatabase();//д�ķ�ʽ�����ݿ�
		if(db.isOpen()){
			String BorrowId=borrow.getId();
			String BorrowBookId=borrow.getBookid();
			String BorrowBookName=borrow.getBookname();
			db.execSQL("insert into borrowInfo (id,bookid,bookname) values (?,?,?)",new Object[]{BorrowId,BorrowBookId,BorrowBookName});
			Log.v("��ӽ�����Ϣ����", "���� ��Ϣ��ӳɹ���");
			db.close();
		}else{
			Toast.makeText(this.context,"���ݿ��ʧ��", Toast.LENGTH_LONG).show();
		}
	}
//ɾ��������Ϣ
	public void deleteBorrowBookInfo(Borrow borrow){
		SQLiteDatabase db=borrowHelp.getWritableDatabase();//ͬ��������д�ķ�ʽ�����ݿ�
		if(db.isOpen()){
			String Id=borrow.getId();//��ȡ�����ı��
			String Bookid=borrow.getBookid();
			db.delete("borrowInfo","id=? and bookid=?",new String[]{Id,Bookid});
			Log.v("ɾ��������Ϣ����","ɾ��������Ϣ�ɹ���");
		}else{
			Toast.makeText(this.context,"���ݿ��ʧ��",Toast.LENGTH_LONG).show();
		}
	}

//�鿴���ĵ��鼮�����ǹ���Ա�鿴�����Բ鿴�����˽������
	public List<Borrow>showBorrowBookInfo(){
		List<Borrow>borrows=null;
		SQLiteDatabase db=borrowHelp.getReadableDatabase();
		if(db.isOpen()){
				Cursor cursor = db.query("borrowInfo", null, null, null, null, null, null, null);//��ѯ
				borrows=new ArrayList<Borrow>();
				while(cursor.moveToNext()){//һ��һ�б���,����һ��һ��book��ӵ�books
					Borrow borrow=new Borrow();
					String BorrowId=cursor.getString(cursor.getColumnIndex("id"));//��ȡ�α�ָ����id�е�ֵ
					borrow.setId(BorrowId);
					String BorrowBookId=cursor.getString(cursor.getColumnIndex("bookid"));
					borrow.setBookid(BorrowBookId);
					String BorrowBookName=cursor.getString(cursor.getColumnIndex("bookname"));
					borrow.setBookname(BorrowBookName);
				//��list��add������borrow��ӵ�borrows
					borrows.add(borrow);
				}
				cursor.close();//�ر�cursor
				db.close();
		}
		return borrows;
	}

//�鿴ĳѧ���û�ȫ���Ľ�����Ϣ
	public List<Borrow> UserAllBorrowBooks(String id){
		List<Borrow>borrows=null;
		SQLiteDatabase db=borrowHelp.getReadableDatabase();
		if(db.isOpen()){
				Cursor cursor = db.query("borrowInfo", null, "id=?", new String[]{id}, null, null, null);//��ѯ
				borrows=new ArrayList<Borrow>();
				while(cursor.moveToNext()){//һ��һ�б���,����һ��һ��book��ӵ�books
					Borrow borrow=new Borrow();
					String BorrowId=cursor.getString(cursor.getColumnIndex("id"));//��ȡ�α�ָ����id�е�ֵ
					borrow.setId(BorrowId);
					String BorrowBookId=cursor.getString(cursor.getColumnIndex("bookid"));
					borrow.setBookid(BorrowBookId);
					String BorrowBookName=cursor.getString(cursor.getColumnIndex("bookname"));
					borrow.setBookname(BorrowBookName);
				//��list��add������borrow��ӵ�borrows
					borrows.add(borrow);
				}
				cursor.close();//�ر�cursor
				db.close();
		}
		return borrows;
	}
	

}
