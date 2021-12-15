package test.my.myclass;

public class Obese {
    String bimanResult;
    String bimanImg;

    public void calcObesity( int height, int weight)
    {
        double standardWeight = (height - 100) * 0.85;
        double obesityRate = (weight / standardWeight ) * 100;

        if (obesityRate <= 90) {
            this.bimanResult ="저체중";
            this.bimanImg =  "/image/under.png";
        }else if (obesityRate > 90 && obesityRate <= 110 ) {
            this.bimanResult ="정상";
            this.bimanImg =  "/image/normal.png";
        }else if (obesityRate >110 && obesityRate <= 120 ) {
            this.bimanResult ="과체중";
            this.bimanImg =  "/image/over.png";
        }else{
            this.bimanResult ="비만";
            this.bimanImg =  "/image/obese.png";
        }
    }
    public String getBimanResult(){
        return this.bimanResult;
    }
    public String getBimanImg(){
        return this.bimanImg;
    }
}



