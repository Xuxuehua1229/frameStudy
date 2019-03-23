package com.test.struts2.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.StrutsTypeConverter;


public class DateConverter extends StrutsTypeConverter{
    private DateFormat dateFormat;
    
    public DateConverter() {
    	System.out.println("DateConverter's constructor....");
    	// 获取当前 web 应用的初始化参数 pattern
    	/*ServletContext sc = ServletActionContext.getServletContext();
    	String pattern = sc.getInitParameter("pattern");
		dateFormat = new SimpleDateFormat(pattern);*/
		
		/*
		   在获取 web.xml 中
		   <context-param>
		      <param-name>pattern</param-name>
		      <param-value>yyyy:MM:dd hh:mm:ss</param-value>
  		   </context-param>
  		 属性时，使用  xwork-conversion.properties 配置会报以下错误：
		  ERROR DefaultConversionPropertiesProcessor Conversion registration error
          java.lang.NullPointerException
		  at com.test.struts2.app.DateConverter.<init>(DateConverter.java:22)
	      at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
		*/
	}
    
    //解决上面错误的方案：① 使用字段配置 Customer-conversion.properties ② 加上以下代码
    public DateFormat getDateFormat() {
    	if(dateFormat == null) {
    		//获取当前 web 应用的初始化参数 pattern
    		ServletContext sc = ServletActionContext.getServletContext();
    		String pattern = sc.getInitParameter("pattern");
    		dateFormat = new SimpleDateFormat(pattern);
    	}
    	return dateFormat;
    }
    
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		System.out.println("convertFromString...");
		if(toClass == Date.class) {
			if(values != null && values.length > 0) {
				String value = values[0];
				try {
					return getDateFormat().parseObject(value);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// 若没有转换成功，则返回 values
		return values;
	}

	@Override
	public String convertToString(Map context, Object o) {
		System.out.println("convertToString...");
		if(o instanceof Date) {
			Date date = (Date)o;
			return getDateFormat().format(date);
		}
		//若转换失败返回 null
		return null;
	}
	

}
