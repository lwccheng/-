package Bean;

//idΪѧ�ţ�name��������className�ǰ༶��password�ǵ�¼���룬phonenumber���ֻ���
public class User {
	private String id,name,classname,password,phonenumber;

	//������췽�����ڵ�¼ʱ�Ĵ���
	public User(String id, String password) {
		// TODO �Զ����ɵĹ��캯�����
		this.id=id;
		this.password=password;
	}
	public User(){
		
	}
	public User(String id, String name, String classname,
			String password, String phonenumber) {
		// TODO �Զ����ɵĹ��캯�����
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
