package io.goorm.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.SpringVersion;

import java.text.DateFormat;
import java.util.Date;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.Locale;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.*;
import test.my.mymodel.*;

@Controller
public class ProductController {
    @RequestMapping(value = "/productInsert", method = RequestMethod.GET)
	public String insert(String name, int qty, String yyymmdd, Model model) 
    {
        ProductDAO productDao = new ProductDAO();
        String result = productDao.insertData(name, qty, yyymmdd);
        model.addAttribute("result", result);
        return "insertView";
	}
    
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
	public String select(Model model) 
    {
        ProductDAO productDao = new ProductDAO();
        ArrayList<ProductDTO> product = productDao.selectProduct();
        model.addAttribute("product", product);
        return "productSelectView";
	}
}