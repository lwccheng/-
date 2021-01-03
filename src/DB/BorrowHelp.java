package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BorrowHelp extends SQLiteOpenHelper{

	public BorrowHelp(Context context) {
		super(context, "borrow.db", null,1);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
	//id是借书人的学号,bookid是借的书编号，bookname是书的名称
		db.execSQL("CREATE TABLE IF NOT EXISTS borrowInfo(id varchar(30),bookid varchar(30),bookname varchar(30))");
		Log.v("BorrowHelp","数据库及borrowInfo表创建成功！");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO 自动生成的方法存根
		
	}

}
