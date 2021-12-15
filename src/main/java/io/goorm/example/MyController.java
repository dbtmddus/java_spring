package io.goorm.example;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
public class MyController 
{
    @RequestMapping(value = "/my", method = RequestMethod.GET)
	public String my(Locale locale, Model model) 
    {
		return "my"; //forward: test.jsp
	}    
}
