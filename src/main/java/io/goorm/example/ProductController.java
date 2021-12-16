package io.goorm.example;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.SpringVersion;

import java.text.DateFormat;
import java.util.Date;
import java.io.PrintWriter;



import java.util.Locale;
import java.util.ArrayList;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.apache.commons.dbcp.BasicDataSource;
import java.sql.*;
import test.my.mymodel.*;
import test.my.mybean.*;
import test.my.myclass.*;

@Controller
public class ProductController {
    @Autowired
	BasicDataSource dataSource;
    
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