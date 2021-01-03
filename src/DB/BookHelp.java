package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookHelp extends SQLiteOpenHelper{

	public BookHelp(Context context) {
		super(context, "book.db", null, 1);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		//bookid指图书编号，bookname指图书名称，booknumber指数量
		db.execSQL("CREATE TABLE IF NOT EXISTS bookInfo(bookid varchar(30)primary key,bookname varchar(30),booknumber int)");
		Log.v("BookHelp","数据库及bookInfo表创建成功！");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO 自动生成的方法存根
		
	}

}
