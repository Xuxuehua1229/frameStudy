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
    	// ��ȡ��ǰ web Ӧ�õĳ�ʼ������ pattern
    	/*ServletContext sc = ServletActionContext.getServletContext();
    	String pattern = sc.getInitParameter("pattern");
		dateFormat = new SimpleDateFormat(pattern);*/
		
		/*
		   �ڻ�ȡ web.xml ��
		   <context-param>
		      <param-name>pattern</param-name>
		      <param-value>yyyy:MM:dd hh:mm:ss</param-value>
  		   </context-param>
  		 ����ʱ��ʹ��  xwork-conversion.properties ���ûᱨ���´���
		  ERROR DefaultConversionPropertiesProcessor Conversion registration error
          java.lang.NullPointerException
		  at com.test.struts2.app.DateConverter.<init>(DateConverter.java:22)
	      at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
		*/
	}
    
    //����������ķ������� ʹ���ֶ����� Customer-conversion.properties �� �������´���
    public DateFormat getDateFormat() {
    	if(dateFormat == null) {
    		//��ȡ��ǰ web Ӧ�õĳ�ʼ������ pattern
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
		// ��û��ת���ɹ����򷵻� values
		return values;
	}

	@Override
	public String convertToString(Map context, Object o) {
		System.out.println("convertToString...");
		if(o instanceof Date) {
			Date date = (Date)o;
			return getDateFormat().format(date);
		}
		//��ת��ʧ�ܷ��� null
		return null;
	}
	

}
