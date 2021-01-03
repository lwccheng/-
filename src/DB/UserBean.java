package DB;

import java.util.ArrayList;
import java.util.List;

import Bean.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class UserBean {
	private Context context;
	private UserHelp userHelp;
	//哪个Activity想要操作该类，就将它的上下文内容传入
	public UserBean(Context context){
		this.context=context;
		userHelp=new UserHelp(context);//创建数据库
	}
	
	//接下来就将增删改查的方法全部在这个类中去实现
	
	//增加用户信息
	public void addUserInfo(User user){
		SQLiteDatabase db=userHelp.getWritableDatabase();//写的方式打开数据库
		if(db.isOpen()){
			String id=user.getId();
			String name=user.getName();
			String classname=user.getClassName();
			String password=user.getPassword();
			String phonenumber=user.getPhonenumber();
			db.execSQL("insert into userInfo (id,name,classname,password,phonenumber) values (?,?,?,?,?)",new Object[]{id,name,classname,password,phonenumber});
			Log.v("添加用户信息操作", "用户信息添加成功！");
			db.close();
		}else{
			Toast.makeText(this.context,"数据库打开失败", Toast.LENGTH_LONG).show();
		}
	}
	//删除用户信息
	public void deleteUserInfo(User user){
		SQLiteDatabase db=userHelp.getWritableDatabase();//同样的先以写的方式打开数据库
		if(db.isOpen()){
			String id=user.getId();//直接根据id判断这个用户是否存在
			db.delete("userInfo","id=?",new String[]{id});//哪个数据库，哪个列，列的哪一个。
			Log.v("删除用户信息操作","删除用户信息成功！");
		}else{
			Toast.makeText(this.context,"数据库打开失败",Toast.LENGTH_LONG).show();
		}
	}
	//更新用户信息
	public void updateUserInfo(User user){
		SQLiteDatabase db=userHelp.getWritableDatabase();//
		if(db.isOpen()){
			String id=user.getId();
			String name=user.getName();
			String classname=user.getClassName();
			String password=user.getPassword();
			String phonenumber=user.getPhonenumber();
		//
			ContentValues c=new ContentValues();//类似于HashMap，但这个只能存储基本类型
			c.put("id", id);
			c.put("name", name);
			c.put("classname", classname);
			c.put("password", password);
			c.put("phonenumber", phonenumber);
			db.update("userInfo", c, "id=?",new String[]{id});
			Log.v("更新用户信息操作", "更新用户信息成功！");
		}else{
			Toast.makeText(this.context,"数据库打开失败",Toast.LENGTH_LONG).show();
		}
	}
	//查看所有用户信息
	public List<User>showUserInfo(){
		List<User>users=null;
		SQLiteDatabase db=userHelp.getReadableDatabase();//展示不需要操作，所以用都的方式操作数据库
		if(db.isOpen()){
				Cursor cursor = db.query("userInfo", null, null, null, null, null, null, null);//查询userInfo的游标
				users=new ArrayList<User>();
				while(cursor.moveToNext()){//一行一行遍历,并将一个一个user添加到users
					User user=new User();//创建用户的对象，这样就能加到users这个user类型的容器中了。
					String id=cursor.getString(cursor.getColumnIndex("id"));//获取游标指定的id列的值
					user.setId(id);
					String name=cursor.getString(cursor.getColumnIndex("name"));
					user.setName(name);
					String classname=cursor.getString(cursor.getColumnIndex("classname"));
					user.setClassName(classname);
					String password=cursor.getString(cursor.getColumnIndex("password"));
					user.setPassword(password);
					String phonenumber=cursor.getString(cursor.getColumnIndex("phonenumber"));
					user.setPhoneNumber(phonenumber);
				//用list的add方法将user添加到users
					users.add(user);
				}
				cursor.close();//关闭cursor
				db.close();
		}
		return users;
	}
	
//增删改查之后，考虑到增删改查可能需要的方法，比如1.增加用户要看看是否已存在，2.删除用户和修改用户都需要存在该用户。
	
	//增加用户要看看是否已存在,返回true则为存在
	public boolean CheckExist(User user){
		boolean bool=false;
		if(FindId(user).getId()!=null){
			bool=true;
		}
		return bool;
	}
	//这个方法函数是帮CheckExist确定是否存在和想要添加的id相同的id。
	public User FindId(User user){
		SQLiteDatabase db=userHelp.getReadableDatabase();
		User tempUser=null;
		if(db.isOpen()){
			tempUser=new User();//
			String id=user.getId();//先获取传入的用户的id
			Cursor cursor=db.query("userInfo",null,"id=?",new String[]{id},null,null,null);
			while(cursor.moveToNext()){
				 	String tempId=cursor.getString(cursor.getColumnIndex("id"));//获取游标指定的id列的值
				 	tempUser.setId(tempId);
//					String name=cursor.getString(cursor.getColumnIndex("name"));
//					tempUser.setName(name);
//					String classname=cursor.getString(cursor.getColumnIndex("classname"));
//					tempUser.setClassName(classname);
//					String password=cursor.getString(cursor.getColumnIndex("password"));
//					tempUser.setPassword(password);
//					String phonenumber=cursor.getString(cursor.getColumnIndex("phonenumber"));
//					tempUser.setPhoneNumber(phonenumber);
			}
		}
		return tempUser;
	}
	//////
	//登录时验证账号密码是否正确
	  public boolean userIsExist(User user){ 
		   String id=user.getId();
		   String password=user.getPassword();
		   SQLiteDatabase db=userHelp.getReadableDatabase();
			if(db.isOpen()){
				//查找数据库中是否存在该用户
				Cursor c = db.rawQuery("select * from userInfo where id= ? and password=?", new String[] { id,password});//在数据库中查找要登录的用户的信息
				while (c.moveToNext()) {//如果存在就会执行这个循环语句
					return true;
				}
				return false;
		}
		   return false;
	   }
	  
//通过用户id,获取该用户所有信息，用于用户修改密码使用
		public User By_Id_Find_User(User user){
			SQLiteDatabase db=userHelp.getReadableDatabase();
			User tempUser=null;
			if(db.isOpen()){
				tempUser=new User();//
				String id=user.getId();//先获取传入的用户的id
				Cursor cursor=db.query("userInfo",null,"id=?",new String[]{id},null,null,null);
				while(cursor.moveToNext()){
					 	String tempId=cursor.getString(cursor.getColumnIndex("id"));//获取游标指定的id列的值
					 	tempUser.setId(tempId);
						String name=cursor.getString(cursor.getColumnIndex("name"));
						tempUser.setName(name);
						String classname=cursor.getString(cursor.getColumnIndex("classname"));
						tempUser.setClassName(classname);
						String password=cursor.getString(cursor.getColumnIndex("password"));
						tempUser.setPassword(password);
						String phonenumber=cursor.getString(cursor.getColumnIndex("phonenumber"));
						tempUser.setPhoneNumber(phonenumber);
				}
			}
			return tempUser;
		}
	  
	  
	  
	
}
