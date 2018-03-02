package controller;
//이 안에 메소드 다 넣음!!
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.msk.Action;

import memberDb.MemberDBBean;


public class ProjectController extends Action{
	public String index(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
			 return  "/view/index.jsp"; 
			} 

	public String LoginDb(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		String inputEmail = request.getParameter("inputEmail");
		String inputPasswd = request.getParameter("inputPasswd");

		MemberDBBean instance =MemberDBBean.getInstance();
		int result = instance.login(inputEmail, inputPasswd);
		
		request.setAttribute("inputEmail", inputEmail);
		request.setAttribute("inputPasswd", inputPasswd);
		request.setAttribute("result", result);
		return  "/view/LoginDb.jsp"; 
		} 
	
}
