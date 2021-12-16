package test.my.mymodel;

public class ImgDTO {
    String imgname;
    String imgdata;
    
    public ImgDTO(String imgname, String imgdata)
    {
        this.imgname = imgname;
        this.imgdata = imgdata;
    }
    public String getImgname(){
        return this.imgname;
    }
    public String getImgdata(){
        return this.imgdata;
    }   
    public void setImgname(String imgname)
    {
        this.imgname = imgname;
    }
    public void setImgdata(String imgdata)
    {
        this.imgdata = imgdata;
    }    
}
