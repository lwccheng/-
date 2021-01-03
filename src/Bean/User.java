package Bean;

//id为学号，name是姓名，className是班级，password是登录密码，phonenumber是手机号
public class User {
	private String id,name,classname,password,phonenumber;

	//这个构造方法用于登录时的传参
	public User(String id, String password) {
		// TODO 自动生成的构造函数存根
		this.id=id;
		this.password=password;
	}
	public User(){
		
	}
	public User(String id, String name, String classname,
			String password, String phonenumber) {
		// TODO 自动生成的构造函数存根
		this.id=id;
		this.name=name;
		this.classname=classname;
		this.password=password;
		this.phonenumber=phonenumber;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return classname;
	}

	public void setClassName(String classname) {
		this.classname = classname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhoneNumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
