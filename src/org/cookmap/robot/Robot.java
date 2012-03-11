package org.cookmap.robot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.rowset.JdbcRowSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


import com.mysql.jdbc.CallableStatement;

public class Robot {
	
	private DefaultCookService cookService;
	
	public void setCoockService(DefaultCookService cookService) {
		this.cookService = cookService;		
	}
	
	public static void main(String[] argv) {
		
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("bean.xml"));
 
		DefaultCookService cookService = (DefaultCookService) beanFactory.getBean("cookService");

		ArrayList<String> ingridients = new ArrayList<String>();

        Document doc = null;
        try {
            doc = Jsoup.connect("http://eda.ru/main-course/recipe/3346/").get();
        } catch (IOException e) {
        }
        Elements elements = doc.select("li.ingredient").select("span.name");
        Iterator<Element> it = elements.iterator();
        while(it.hasNext()) {
            final String ingridient = it.next().text();
            ingridients.add(ingridient);
        }        
        cookService.addIngridients(ingridients);		
	}
	
	
	

}
