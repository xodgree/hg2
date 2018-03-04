package controller;
//�� �ȿ� �޼ҵ� �� ����!!
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.msk.Action;

import memberDb.MemberDBBean;
import memberDb.MemberDataBean;


public class ProjectController extends Action{
	//����(�α���ȭ��)
	public String login(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
			 return  "/view/login.jsp"; 
			} 
	//�α��� Ȯ��
	public String loginDb(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		String inputEmail = request.getParameter("inputEmail");
		String inputPasswd = request.getParameter("inputPasswd");

		MemberDBBean instance =MemberDBBean.getInstance();
		int result = instance.login(inputEmail, inputPasswd);
		if(result == 1){
			/* result�� 1�� �°�, email�� admin@hughug.com�̸� ȸ������ �������� �����ϴ�. 
			result�� 1�� �°�, email�� adim�� �ƴϸ� ����.jsp�� �̵��մϴ�. */
			
			if(inputEmail.equals("admin@hughug.com")){
				return "/board/list";
			}else{
				String userName = instance.MainName(inputEmail);
				request.setAttribute("userEmail", inputEmail);
				request.setAttribute("userName", userName);        
				return "/board/main";  
			}
		}
		else {
			request.setAttribute("inputEmail", inputEmail);
			request.setAttribute("inputPasswd", inputPasswd);
			request.setAttribute("result", result);
			return  "/view/1.jsp"; 
		} 
	}
	
	//����
	public String main(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		 //String userName = (String)session.getAttribute("userName"); 
		//String userEmail = (String)session.getAttribute("userEmail"); 
		 String userName = (String)request.getAttribute("userName"); 
		 String userEmail = (String)request.getAttribute("userEmail"); 
		 
		 if(userName == null || userName.equals("")){
			return "/board/login";
			//response.sendRedirect("login.jsp");
		}
		 	request.setAttribute("userName", userName);
			 return  "/view/Main.jsp"; 
			} 
	
	
//ȸ������ �Խ���
	//���
	public String list(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 

		String boardid = request.getParameter("boardid");
		   if(boardid == null)
			   boardid ="1";

		   int pageSize=5;
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		   String pageNum = request.getParameter("pageNum");
		   if(pageNum==null || pageNum==""){
		      pageNum = "1";}
		   int currentPage = Integer.parseInt(pageNum);
		   int startRow = (currentPage-1)*pageSize+1;
		   int endRow = currentPage* pageSize;
		   int count = 0;
		   int number = 0;
		   List articleList = null;
		   MemberDBBean dbPro = MemberDBBean.getInstance();
		   count = dbPro.getDataCount();
		   if(count > 0){
		      articleList = dbPro.articleList(startRow, endRow);}
		         number=count - (currentPage-1)*pageSize;
		
		//������ ó��
		   int bottomLine =3;
		   int pageCount=count/pageSize+(count%pageSize==0?0:1);
		   int startPage = 1+(currentPage-1)/bottomLine*bottomLine;
		   int endPage = startPage+bottomLine-1;
		   if(endPage>pageCount) endPage = pageCount;
		
		request.setAttribute("boardid", boardid);
		request.setAttribute("count", count);
		request.setAttribute("articleList", articleList);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("bottomLine", bottomLine);	
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("number", number);
			
		   return  "/mb_view/list.jsp"; 
	}
	
	//ȸ������
	public String viewContent(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		
		int num =Integer.parseInt(request.getParameter("num"));
		//������ ��ȣ �ѱ��
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum == ""){
			pageNum = "1";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try{
			MemberDBBean dbPro = MemberDBBean.getInstance();
			MemberDataBean member = dbPro.getContent(num,"content");
			
			request.setAttribute("member", member);
			request.setAttribute("pageNum", pageNum);
		    
		}catch(Exception e){
			
		}
		return  "/mb_view/viewContent.jsp"; 
	}
	
	//����
	public String updateForm(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
	
		int num = Integer.parseInt(request.getParameter("num"));
		try{
			MemberDBBean dbPro = MemberDBBean.getInstance();
			MemberDataBean member = dbPro.getContent(num,"update");
			request.setAttribute("member", member);
			request.setAttribute("num", num);
	
		}catch(Exception e){
			
		}
	
			 return  "/mb_view/updateForm.jsp"; 
			} 
	
	
	public String updatePro(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		/*
		 <jsp:useBean id="member" class="memberDb.MemberDataBean">
		<jsp:setProperty name = "member" property="*"/>
		</jsp:useBean>
			�Ʒ� ó�� �ٲ���.
		 * */
		
		MemberDataBean member = new MemberDataBean();
		member.setEmail(request.getParameter("email"));
		member.setName(request.getParameter("name"));
		member.setNum(Integer.parseInt(request.getParameter("num")));
		member.setPasswd(request.getParameter("passwd"));
	
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum =="1"){
			pageNum ="1";
		}
		MemberDBBean dbPro = MemberDBBean.getInstance();
		int pwdck = dbPro.updatemember(member);
			
		request.setAttribute("pwdck", pwdck);
		request.setAttribute("pageNum", pageNum);
		return  "/mb_view/updatePro.jsp"; 
		}
	
	//����
	public String deleteForm(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum"); 
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		
			 return  "/mb_view/deleteForm.jsp"; 
			}
	
	public String deletePro(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable {

		   String boardid = request.getParameter("boardid");
		if(boardid==null) boardid = "1";
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null || pageNum==""){
		   pageNum = "1";   }

		int num = Integer.parseInt(request.getParameter("num")); //deleteForm ���� �Ѿ�� ������
		String passwd = request.getParameter("passwd");
		MemberDBBean dbPro = MemberDBBean.getInstance();
		int check = dbPro.deletemember(num, passwd);
		
		request.setAttribute("check", check);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
			 return  "/mb_view/deletePro.jsp"; 
			}
	
	//�˻�
	public String Search(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
			 return  "/mb_view/Search.jsp"; 
			} 
	
	//�α׾ƿ�
	public String Logout(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		request.getSession().invalidate();

			 return  "/view/Logout.jsp"; 
			} 
}
