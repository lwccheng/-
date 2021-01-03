package Bean;

public class Borrow {
	private String id,bookid,bookname;

public Borrow(){
	
}
public Borrow(String id,String bookid,String bookname){
	this.id=id;
	this.bookid=bookid;
	this.bookname=bookname;
}

//用于删除借书记录
public Borrow(String id,String bookid){
	this.id=id;
	this.bookid=bookid;
}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
}
