package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookHelp extends SQLiteOpenHelper{

	public BookHelp(Context context) {
		super(context, "book.db", null, 1);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO �Զ����ɵķ������
		//bookidָͼ���ţ�booknameָͼ�����ƣ�booknumberָ����
		db.execSQL("CREATE TABLE IF NOT EXISTS bookInfo(bookid varchar(30)primary key,bookname varchar(30),booknumber int)");
		Log.v("BookHelp","���ݿ⼰bookInfo�����ɹ���");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO �Զ����ɵķ������
		
	}

}
