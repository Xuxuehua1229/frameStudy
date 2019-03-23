package com.test.struts2.app;

import java.util.ArrayList;
import java.util.List;

public class Dao {
   public List<Department> getDepartments(){
	   List<Department> depts = new ArrayList<>();
	   depts.add(new Department(1001,"AA"));
	   depts.add(new Department(1002,"BB"));
	   depts.add(new Department(1003,"CC"));
	   depts.add(new Department(1004,"DD"));
	   depts.add(new Department(1005,"EE"));
	   return depts;
   }
   
   public List<Role> getRoles(){
	   List<Role> roles = new ArrayList<>();
	   roles.add(new Role(2001,"xxx"));
	   roles.add(new Role(2002,"yyy"));
	   roles.add(new Role(2003,"zzz"));
	   return roles;
   }
}
