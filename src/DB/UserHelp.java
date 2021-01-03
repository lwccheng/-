package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserHelp extends SQLiteOpenHelper{
	//这里仅须知上下文环境，所以构造方法只传Context
	public UserHelp(Context context) {
		super(context, "user.db", null, 1);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		//这里创建数据库
		db.execSQL("CREATE TABLE IF NOT EXISTS userInfo(id varchar(30)primary key,name varchar(30),classname varchar(30),password varchar(30),phonenumber varchar(30))");
		Log.v("UserHelp","数据库及userInfo表创建成功！");
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO 自动生成的方法存根
		
	}

}
