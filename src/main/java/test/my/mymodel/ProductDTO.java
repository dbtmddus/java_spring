package test.my.mymodel;

public class ProductDTO {
    String name;
    int qty;
    String yyyymmdd;
    public ProductDTO( String name, int qty, String yyyymmdd){
        this.name = name;
        this.qty = qty;
        this.yyyymmdd = yyyymmdd;
    }
	public String getName() {
		return name;
	}
	public int getQty() {
		return qty;
	}
	public String getYyyymmdd() {
		return yyyymmdd;
	}  
}	