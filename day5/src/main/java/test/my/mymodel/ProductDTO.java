package test.my.mymodel;

import java.sql.*;

public class ProductDTO {
    String myname;
    int myqty;
    String mydate;
    String myimg;
    public ProductDTO( String myname, int myqty, String mydate, String myimg){
        this.myname = myname;
        this.myqty = myqty;
        this.mydate = mydate;
        this.myimg = myimg;
    }
	public String getMyname() {
		return myname;
	}
	public int getMyqty() {
		return myqty;
	}
	public String getMydate() {
		return mydate;
	}
    public String getMyimg() {
        return myimg;
    }
}	
