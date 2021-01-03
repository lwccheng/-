package Bean;

public class Book {
//图书编号，图书名称，图书数量
	private String bookid,bookname;
	private int booknumber;
	
public Book(){
	
}
public Book(String bookid,String bookname,int booknumber){
	this.bookid=bookid;
	this.bookname=bookname;
	this.booknumber=booknumber;
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
	public int getBooknumber() {
		return booknumber;
	}
	public void setBooknumber(int booknumber) {
		this.booknumber = booknumber;
	}
	
}
