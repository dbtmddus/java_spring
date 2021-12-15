package test.my.mybean;

public class Student {
    String name;
    int age;
    MyData myData;         
    public Student(){
        this.name = "";
        this.age = 0;
    }    
    public Student( String name, int age, MyData myData){
        this.name = name;
        this.age = age;
        this.myData = myData;
    }
    
	public MyData getMyData() {
		return this.myData;
	}
	public void setMyData(MyData myData) {
		this.myData = myData;
	}
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
