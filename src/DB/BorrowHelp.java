package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BorrowHelp extends SQLiteOpenHelper{

	public BorrowHelp(Context context) {
		super(context, "borrow.db", null,1);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO �Զ����ɵķ������
	//id�ǽ����˵�ѧ��,bookid�ǽ�����ţ�bookname���������
		db.execSQL("CREATE TABLE IF NOT EXISTS borrowInfo(id varchar(30),bookid varchar(30),bookname varchar(30))");
		Log.v("BorrowHelp","���ݿ⼰borrowInfo�����ɹ���");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO �Զ����ɵķ������
		
	}

}
