package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserHelp extends SQLiteOpenHelper{
	//�������֪�����Ļ��������Թ��췽��ֻ��Context
	public UserHelp(Context context) {
		super(context, "user.db", null, 1);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO �Զ����ɵķ������
		//���ﴴ�����ݿ�
		db.execSQL("CREATE TABLE IF NOT EXISTS userInfo(id varchar(30)primary key,name varchar(30),classname varchar(30),password varchar(30),phonenumber varchar(30))");
		Log.v("UserHelp","���ݿ⼰userInfo�����ɹ���");
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO �Զ����ɵķ������
		
	}

}
