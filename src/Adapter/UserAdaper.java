package Adapter;

import java.util.List;

import com.example.mybookmanagesystem.R;

import Bean.User;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
public class UserAdaper extends ArrayAdapter{
	User user;//用户对象
	public UserAdaper(Context context, int resource,List<User>users) {
		super(context, resource,users);
		// TODO 自动生成的构造函数存根
	}
	public void setList(User user){
		this.user=user;
	}
	@Override
	public long getItemId(int position){
		return position;
	}
	public View getView(int position,View convertView,ViewGroup parent){
		user=(User)getItem(position);//锁定对象的项目
		View view=LayoutInflater.from(getContext()).inflate(R.layout.userlist, parent,false);
		TextView id =(TextView)view.findViewById(R.id.textView1);
		TextView name =(TextView)view.findViewById(R.id.textView3);
		TextView classname=(TextView)view.findViewById(R.id.textView5);
		TextView password =(TextView)view.findViewById(R.id.textView7);
		TextView phonenumber =(TextView)view.findViewById(R.id.textView9);
		id.setText(user.getId());
		name.setText(user.getName());
		classname.setText(user.getClassName());
		password.setText(user.getPassword());
		phonenumber.setText(user.getPhonenumber());
		return view;
	}
	

}
