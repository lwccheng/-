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
	//�ĸ�Activity��Ҫ�������࣬�ͽ��������������ݴ���
	public UserBean(Context context){
		this.context=context;
		userHelp=new UserHelp(context);//�������ݿ�
	}
	
	//�������ͽ���ɾ�Ĳ�ķ���ȫ�����������ȥʵ��
	
	//�����û���Ϣ
	public void addUserInfo(User user){
		SQLiteDatabase db=userHelp.getWritableDatabase();//д�ķ�ʽ�����ݿ�
		if(db.isOpen()){
			String id=user.getId();
			String name=user.getName();
			String classname=user.getClassName();
			String password=user.getPassword();
			String phonenumber=user.getPhonenumber();
			db.execSQL("insert into userInfo (id,name,classname,password,phonenumber) values (?,?,?,?,?)",new Object[]{id,name,classname,password,phonenumber});
			Log.v("����û���Ϣ����", "�û���Ϣ��ӳɹ���");
			db.close();
		}else{
			Toast.makeText(this.context,"���ݿ��ʧ��", Toast.LENGTH_LONG).show();
		}
	}
	//ɾ���û���Ϣ
	public void deleteUserInfo(User user){
		SQLiteDatabase db=userHelp.getWritableDatabase();//ͬ��������д�ķ�ʽ�����ݿ�
		if(db.isOpen()){
			String id=user.getId();//ֱ�Ӹ���id�ж�����û��Ƿ����
			db.delete("userInfo","id=?",new String[]{id});//�ĸ����ݿ⣬�ĸ��У��е���һ����
			Log.v("ɾ���û���Ϣ����","ɾ���û���Ϣ�ɹ���");
		}else{
			Toast.makeText(this.context,"���ݿ��ʧ��",Toast.LENGTH_LONG).show();
		}
	}
	//�����û���Ϣ
	public void updateUserInfo(User user){
		SQLiteDatabase db=userHelp.getWritableDatabase();//
		if(db.isOpen()){
			String id=user.getId();
			String name=user.getName();
			String classname=user.getClassName();
			String password=user.getPassword();
			String phonenumber=user.getPhonenumber();
		//
			ContentValues c=new ContentValues();//������HashMap�������ֻ�ܴ洢��������
			c.put("id", id);
			c.put("name", name);
			c.put("classname", classname);
			c.put("password", password);
			c.put("phonenumber", phonenumber);
			db.update("userInfo", c, "id=?",new String[]{id});
			Log.v("�����û���Ϣ����", "�����û���Ϣ�ɹ���");
		}else{
			Toast.makeText(this.context,"���ݿ��ʧ��",Toast.LENGTH_LONG).show();
		}
	}
	//�鿴�����û���Ϣ
	public List<User>showUserInfo(){
		List<User>users=null;
		SQLiteDatabase db=userHelp.getReadableDatabase();//չʾ����Ҫ�����������ö��ķ�ʽ�������ݿ�
		if(db.isOpen()){
				Cursor cursor = db.query("userInfo", null, null, null, null, null, null, null);//��ѯuserInfo���α�
				users=new ArrayList<User>();
				while(cursor.moveToNext()){//һ��һ�б���,����һ��һ��user��ӵ�users
					User user=new User();//�����û��Ķ����������ܼӵ�users���user���͵��������ˡ�
					String id=cursor.getString(cursor.getColumnIndex("id"));//��ȡ�α�ָ����id�е�ֵ
					user.setId(id);
					String name=cursor.getString(cursor.getColumnIndex("name"));
					user.setName(name);
					String classname=cursor.getString(cursor.getColumnIndex("classname"));
					user.setClassName(classname);
					String password=cursor.getString(cursor.getColumnIndex("password"));
					user.setPassword(password);
					String phonenumber=cursor.getString(cursor.getColumnIndex("phonenumber"));
					user.setPhoneNumber(phonenumber);
				//��list��add������user��ӵ�users
					users.add(user);
				}
				cursor.close();//�ر�cursor
				db.close();
		}
		return users;
	}
	
//��ɾ�Ĳ�֮�󣬿��ǵ���ɾ�Ĳ������Ҫ�ķ���������1.�����û�Ҫ�����Ƿ��Ѵ��ڣ�2.ɾ���û����޸��û�����Ҫ���ڸ��û���
	
	//�����û�Ҫ�����Ƿ��Ѵ���,����true��Ϊ����
	public boolean CheckExist(User user){
		boolean bool=false;
		if(FindId(user).getId()!=null){
			bool=true;
		}
		return bool;
	}
	//������������ǰ�CheckExistȷ���Ƿ���ں���Ҫ��ӵ�id��ͬ��id��
	public User FindId(User user){
		SQLiteDatabase db=userHelp.getReadableDatabase();
		User tempUser=null;
		if(db.isOpen()){
			tempUser=new User();//
			String id=user.getId();//�Ȼ�ȡ������û���id
			Cursor cursor=db.query("userInfo",null,"id=?",new String[]{id},null,null,null);
			while(cursor.moveToNext()){
				 	String tempId=cursor.getString(cursor.getColumnIndex("id"));//��ȡ�α�ָ����id�е�ֵ
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
	//��¼ʱ��֤�˺������Ƿ���ȷ
	  public boolean userIsExist(User user){ 
		   String id=user.getId();
		   String password=user.getPassword();
		   SQLiteDatabase db=userHelp.getReadableDatabase();
			if(db.isOpen()){
				//�������ݿ����Ƿ���ڸ��û�
				Cursor c = db.rawQuery("select * from userInfo where id= ? and password=?", new String[] { id,password});//�����ݿ��в���Ҫ��¼���û�����Ϣ
				while (c.moveToNext()) {//������ھͻ�ִ�����ѭ�����
					return true;
				}
				return false;
		}
		   return false;
	   }
	  
//ͨ���û�id,��ȡ���û�������Ϣ�������û��޸�����ʹ��
		public User By_Id_Find_User(User user){
			SQLiteDatabase db=userHelp.getReadableDatabase();
			User tempUser=null;
			if(db.isOpen()){
				tempUser=new User();//
				String id=user.getId();//�Ȼ�ȡ������û���id
				Cursor cursor=db.query("userInfo",null,"id=?",new String[]{id},null,null,null);
				while(cursor.moveToNext()){
					 	String tempId=cursor.getString(cursor.getColumnIndex("id"));//��ȡ�α�ָ����id�е�ֵ
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
