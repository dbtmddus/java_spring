package io.goorm.example;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import java.util.HashMap;
import java.util.Random;

@Controller
public class MyController 
{
    HashMap<Integer, String> mapImg = new HashMap<Integer,String>();
    HashMap<Integer, String> mapResult = new HashMap<Integer,String>();
    
    public MyController(){
        mapImg.put(0,"/image/s.png");
        mapImg.put(1,"/image/r.png");
        mapImg.put(2,"/image/b.png");
        mapResult.put(0,"무승부");
        mapResult.put(1,"내가 승리");
        mapResult.put(2,"컴퓨터승리");
    }
    
    @RequestMapping(value = "/my", method = RequestMethod.GET)
	public String my(Locale locale, Model model) 
    {
		return "my"; //forward: test.jsp
	}    
    
    @RequestMapping(value = "/gameControl", method = RequestMethod.GET)
	public String gameControl(int user, Model model) 
    {
        Random rnd = new Random();
        int nCom = rnd.nextInt(3); //0,1,2
        int result = (3+user-nCom)%3;
        model.addAttribute("userImg", mapImg.get(user));
        model.addAttribute("comImg", mapImg.get(nCom));
        model.addAttribute("result", mapResult.get(result));
        
		return "gameControlView"; //forward: test.jsp
	}        
    
}



