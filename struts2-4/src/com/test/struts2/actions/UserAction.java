package com.test.struts2.actions;

public class UserAction {
   public String save() {
	   System.out.println("save....");
	   return "save-success";
   }
   
   public String modify() {
	   System.out.println("modify....");
	   return "modify-success";
   }
   
   public String delete() {
	   System.out.println("delete....");
	   return "delete-success";
   }
   
   public String select() {
	   System.out.println("select....");
	   return "select-success";
   }
}
