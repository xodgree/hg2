package controller;
//��Ʈ�ѷ�
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.msk.Action;

import diaryDb.DiaryDBBean;
import diaryDb.DiaryDataBean;
import memberDb.MemberDBBean;
import memberDb.MemberDataBean;

/*for fileupload*/
import javax.servlet.ServletContext;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProjectController extends Action{
	//����
	public String Main(HttpServletRequest request,
			HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String useremail = (String) session.getAttribute("userEmail");
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     List articleList = null;
	     DiaryDBBean dbPro = DiaryDBBean.getInstance();
	     int count = 0;
			 count = dbPro.getDataCount(useremail);
			 
			  if(count > 0){
				  articleList = dbPro.articleList2(1, 7, useremail);}
			  
	     request.setAttribute("articleList", articleList);
	     request.setAttribute("count", count);
	     
	    System.out.println(articleList);
		return  "/view/main_modal.jsp"; 
				} 
	
	//ȸ�� Ż��
	public String memberDelete(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable {
		HttpSession session = request.getSession();
		String useremail = (String) session.getAttribute("userEmail");
		MemberDBBean member = MemberDBBean.getInstance();
		member.deleteMypage(useremail);
			response.sendRedirect("/HugHug2/board/login");
			 return null; 
			} 
	//���������� ����pro
	public String myPageUpdatePro(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String useremail = (String) session.getAttribute("userEmail");
		String userName = MemberDBBean.getInstance().MainName(useremail);
		session.setAttribute("userName", userName);
		MemberDataBean member = new MemberDataBean();
		member.setName(request.getParameter("name"));
	
		MemberDBBean dbPro = MemberDBBean.getInstance();
		dbPro.updateMypage(member,useremail);
		
	
			 return  "/board/myPage"; 
			} 
	
	//���������� ����
		public String myPageUpdate(HttpServletRequest request,
				 HttpServletResponse response)  throws Throwable { 
			HttpSession session = request.getSession();
			String useremail = (String) session.getAttribute("userEmail");
			try{
				MemberDBBean dbPro = MemberDBBean.getInstance();
				MemberDataBean member = dbPro.getMember(useremail);
				request.setAttribute("member", member);
		
			}catch(Exception e){
				
			}
				 return  "/view/myPageUpdate.jsp"; 
				} 
		
	//����������
	public String myPage(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String useremail = (String) session.getAttribute("userEmail");
		try{
			MemberDBBean dbPro = MemberDBBean.getInstance();
			MemberDataBean member = dbPro.getMember(useremail);
			request.setAttribute("member", member);
	
		}catch(Exception e){
			
		}
			 return  "/view/myPage.jsp"; 
			} 
	
	//�ϱ� ����
	public String diaryUpdateForm(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
	
		//int num = Integer.parseInt(request.getParameter("num"));
		int num = 104;
		try{
			DiaryDBBean dbPro = DiaryDBBean.getInstance();
			DiaryDataBean diary = dbPro.getContent(num,"update");
			request.setAttribute("diary", diary);
			request.setAttribute("num", num);
	
		}catch(Exception e){
			
		}
	
			 return  "/view/diaryUpdateForm.jsp"; 
			} 
	

	public String diaryUpdatePro(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		
		/*
		 <jsp:useBean id="member" class="memberDb.MemberDataBean">
		<jsp:setProperty name = "member" property="*"/>
		</jsp:useBean>
			�Ʒ� ó�� �ٲ���.
		 * */
	HttpSession session = request.getSession();
		
		/*----- image upload -----*/
		String realFolder = "";
		String imagename = "";
		int maxSize = 1024 * 1024 * 10;
		String encType = "euc-kr";
		String uploadPath = "images";
		ServletContext scontext = getServletContext();
		realFolder = scontext.getRealPath(uploadPath);
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		try {
			Enumeration<?> files = multi.getFileNames();
			String nextfile = (String)files.nextElement();
			imagename = multi.getFilesystemName(nextfile);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//���ǿ� ����� �̸��� �ּҸ� ������.
		String useremail = (String) session.getAttribute("userEmail");
				
		/*
		 * �ϱ� �ۼ� ������ ����� ��.
		 * ���⼭�� db�� �����ؼ� �ۼ��� ������ db�� insert�ϰ� �� ����. diaryWriteDb���� �ڸ�Ʈ â�� �����.
		 * maindb�� ������ ����Ʈ �޾Ƽ� �Ѹ��� �Ѵ����� main���� ������.
		 * */ 
		//db�� �����ؼ� insert�ϱ�.
		
		
		DiaryDataBean diary = new DiaryDataBean();
		//diary.setNum(Integer.parseInt(request.getParameter("num")));
		diary.setImagename(imagename);
		diary.setContent(multi.getParameter("content"));
		diary.setTitle(multi.getParameter("title"));
		diary.setNum(104);
		
	
	DiaryDBBean dbPro = DiaryDBBean.getInstance();
	dbPro.update(diary);


		/*DiaryDataBean diary = new DiaryDataBean();
		diary.setTitle(request.getParameter("title"));
		diary.setContent(request.getParameter("content"));
		diary.setImagename(request.getParameter("imagename"));
		//diary.setNum(Integer.parseInt(request.getParameter("num")));
		diary.setNum(104);
		DiaryDBBean dbPro = DiaryDBBean.getInstance();
		dbPro.update(diary);*/
		return  "/view/diaryUpdatePro.jsp"; 
		}
	
		//�ϱ� ����
		public String DeletediaryPro(HttpServletRequest request,
				 HttpServletResponse response)  throws Throwable {

			int num = Integer.parseInt(request.getParameter("num")); //deleteForm ���� �Ѿ�� ������
			DiaryDBBean diary = DiaryDBBean.getInstance();
			diary.delete(num);
				 return  "/board/Main"; 
				}
		//�˻� �Խ���
		public String searchList(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
			HttpSession session = request.getSession();
			String useremail = (String) session.getAttribute("userEmail");
			String boardid = request.getParameter("boardid");
			String opt = request.getParameter("opt");//�˻��ɼ� ����,����,����
			String condition = request.getParameter("condition");//�˻�����

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
			  DiaryDBBean dbPro = DiaryDBBean.getInstance();
			   count = dbPro.getSearchCount(useremail, opt, condition);
			   if(count > 0){
				   
				   System.out.println("startRow==="+startRow);
				   System.out.println("startRow==="+endRow);
			      articleList = dbPro.articleList(startRow, endRow, useremail,opt,condition);}

			   number=count - (currentPage-1)*pageSize;
			   System.out.println("number=="+number);
			
			
			//������ ó��
			   int bottomLine =3;
			   int pageCount=count/pageSize+(count%pageSize==0?0:1);
			   int startPage = 1+(currentPage-1)/bottomLine*bottomLine;
			   int endPage = startPage+bottomLine-1;
			   if(endPage>pageCount) endPage = pageCount;
			System.out.println(count);
			System.out.println(pageCount);
			System.out.println(startPage);
			System.out.println(endPage);
			request.setAttribute("boardid", boardid);
			request.setAttribute("count", count);
			request.setAttribute("articleList", articleList);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("bottomLine", bottomLine);	
			request.setAttribute("currentPage", currentPage);
			//request.setAttribute("number", number);
			request.setAttribute("pageCount", pageCount);
			

			   //response.sendRedirect(request.getContextPath() + "/mb_view/list.jsp");
			   return "/view/searchMain.jsp";
		}
		
		/*�˻� �Խ���
		public String searchList(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
			HttpSession session = request.getSession();
			String useremail = (String) session.getAttribute("userEmail");
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
			  DiaryDBBean dbPro = DiaryDBBean.getInstance();
			   count = dbPro.getDataCount(useremail);
			   if(count > 0){
				   
				   System.out.println("startRow==="+startRow);
				   System.out.println("startRow==="+endRow);
			      articleList = dbPro.articleList(startRow, endRow, useremail);}

			   number=count - (currentPage-1)*pageSize;
			   System.out.println("number=="+number); */
	
/*	//�˻� ��
	public String searchMain(HttpServletRequest request,
			HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String useremail = (String) session.getAttribute("userEmail");
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     List articleList = null;
	     DiaryDBBean dbPro = DiaryDBBean.getInstance();
	     int count = 0;
			 count = dbPro.getDataCount(useremail, 1, 7);
			 
			  if(count > 0){
				  articleList = dbPro.articleList2(1, 7, useremail);}
			  
	     request.setAttribute("articleList", articleList);
	     request.setAttribute("count", count);
	    System.out.println(articleList);
		 return "/view/searchMain.jsp";
			}*/
//��Ʈ
	public String chart(HttpServletRequest request,
			HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String useremail = (String) session.getAttribute("userEmail");
		
		DiaryDBBean dbPro = DiaryDBBean.getInstance();
		List graphList = dbPro.graphList(1, 7, useremail);
		request.setAttribute("graphList", graphList);
		System.out.println(graphList);
		System.out.println("1");
		 return "/view/graphTest.jsp";
	}
	
//�ϱ� 7�� �Ѹ���.	
	public String sevenList(HttpServletRequest request,
			HttpServletResponse response)  throws Throwable { 
		HttpSession session = request.getSession();
		String useremail = (String) session.getAttribute("userEmail");
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        List articleList = null;
	        DiaryDBBean dbPro = DiaryDBBean.getInstance();
	        int count = 0;
			 count = dbPro.getDataCount(useremail);
			 
			  if(count > 0){
				  articleList = dbPro.articleList2(1, 7, useremail);}
			  
	        request.setAttribute("articleList", articleList);
	        request.setAttribute("count", count);
           System.out.println(articleList);
	        return "/view/list.jsp";
	     }	
//�ϱ� ���� ������
public String read(HttpServletRequest request,
		HttpServletResponse response)  throws Throwable {
	//�ϱ� �޾ƿͼ� �Ѹ���
	//int num =Integer.parseInt(request.getParameter("num"));
	int num = 1;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	try{
		DiaryDBBean dbPro = DiaryDBBean.getInstance();
		DiaryDataBean diary = dbPro.getContent(num,"content");
		
		request.setAttribute("diary", diary);
		
	    
	}catch(Exception e){
		
	}
	return  "/view/read.jsp"; 
			}  
	


	//�ϱ� ����
public String diaryWrite(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
	HttpSession session = request.getSession();
	/*int num = 0;
	if(request.getParameter("num")!=null){
		num = Integer.parseInt(request.getParameter("num"));
	};*/
	//useremail�� ��������.
	String useremail = (String) session.getAttribute("userEmail");
	
	request.setAttribute("useremail", useremail);
	System.out.println(useremail);
		return  "/view/diaryWrite.jsp"; 
		} 
	
//�ϱ� ���� pro
	public String diaryWritePro(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		
		/*----- image upload -----*/
		String realFolder = "";
		String imagename = "";
		int maxSize = 1024 * 1024 * 10;
		String encType = "euc-kr";
		String uploadPath = "images";
		ServletContext scontext = getServletContext();
		realFolder = scontext.getRealPath(uploadPath);
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		try {
			Enumeration<?> files = multi.getFileNames();
			String nextfile = (String)files.nextElement();
			imagename = multi.getFilesystemName(nextfile);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//���ǿ� ����� �̸��� �ּҸ� ������.
		String useremail = (String) session.getAttribute("userEmail");
				
		/*
		 * �ϱ� �ۼ� ������ ����� ��.
		 * ���⼭�� db�� �����ؼ� �ۼ��� ������ db�� insert�ϰ� �� ����. diaryWriteDb���� �ڸ�Ʈ â�� �����.
		 * maindb�� ������ ����Ʈ �޾Ƽ� �Ѹ��� �Ѵ����� main���� ������.
		 * */ 
		//db�� �����ؼ� insert�ϱ�.
		
		
		DiaryDataBean diary = new DiaryDataBean();
		//diary.setNum(Integer.parseInt(request.getParameter("num")));
		diary.setImagename(imagename);
		diary.setEmotion(multi.getParameter("emotion"));
		diary.setContent(multi.getParameter("content"));
		diary.setTitle(multi.getParameter("title"));

		System.out.println("�̹���11==="+imagename);
		System.out.println("�̹���22==="+multi.getParameter("emotion"));
		System.out.println("�̹���33==="+useremail);
		System.out.println("�̹���44==="+multi.getParameter("content"));
		System.out.println("�̹���44==="+multi.getParameter("title"));
	
	DiaryDBBean dbPro = DiaryDBBean.getInstance();
		dbPro.insertArticle(diary,useremail);
		String emotion = diary.getEmotion();
		//request.setAttribute("emotion", emotion);	//���� ����.
		//���� �ڸ�Ʈ
		List commentList = dbPro.commentList(emotion);
		Random r = new Random();
		int commentnum = r.nextInt(commentList.size());
		String comment = (String) commentList.get(commentnum);
		request.setAttribute("comment", comment);
		return  "/view/diaryWritePro.jsp"; 	
		} 
	
	
	
	//����(�α���ȭ��)
	public String login(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
			 return  "/view/login.jsp"; 
			} 
	
	//�α��� Ȯ��
	public String LoginDb(HttpServletRequest request, HttpServletResponse response)  throws Throwable {
		String inputEmail = null;
		String inputPasswd = null;
		int result = -1;
		
		HttpSession session = request.getSession();
		
		try {
			Object tempEmail = session.getAttribute("userEmail");
			Object tempPass = session.getAttribute("userPasswd");
			
			// ���� �α����� �����Ǿ inputEmail�� �ִٸ�
			if( tempEmail != null && tempPass != null) {
				inputEmail = tempEmail.toString();
				inputPasswd = tempPass.toString();
			} else {
				// �α��� ȭ�鿡�� �Ѿ�� ��쿡�� �Ķ���ͷ� �����ϱ� ������
				inputEmail = request.getParameter("inputEmail");
				inputPasswd = request.getParameter("inputPasswd");
			}

			result = MemberDBBean.getInstance().login(inputEmail, inputPasswd);
			
			System.out.println(inputEmail + "," + inputPasswd);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == 1){
			/* result�� 1�� �°�, email�� admin@hughug.com�̸� ȸ������ �������� �����ϴ�. 
			result�� 1�� �°�, email�� adim�� �ƴϸ� ����.jsp�� �̵��մϴ�. */
			
			// �α����� �����ϸ� attribute�� �������ݴϴ�.
			// admin, user ���
			String userName = MemberDBBean.getInstance().MainName(inputEmail);
			
			// �α��� �����ϸ� ���⼭ ��Ʈ����Ʈ �����մϴ�.
			// userEmail, userName, userPasswd �׸����� getAttribute�� �޽��ϴ�.
			session.setAttribute("userEmail", inputEmail);
			session.setAttribute("userName", userName);
			session.setAttribute("userPasswd", inputPasswd);
			
			if(inputEmail.equals("admin@hughug.com")){		
				return "/board/list";
			}else{
				//response.sendRedirect(request.getContextPath() + "/view/Main.jsp");
				return "/board/Main";
			}
		}
		else {
			// �α��� ������ ��
			request.setAttribute("result", result);
			return  "/view/loginDb.jsp"; 
		} 
		
	}
	
	//ȸ������
	public String signUp(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		int num = 0;
		if(request.getParameter("num")!=null){
			num = Integer.parseInt(request.getParameter("num"));
		};
		request.setAttribute("num", num);
			 return  "/view/signUp.jsp"; 
			} 
	
	//ȸ������ action
	public String signUpDb(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable {
		MemberDataBean member = new MemberDataBean();
		member.setEmail(request.getParameter("email"));
		member.setName(request.getParameter("name"));
		member.setNum(Integer.parseInt(request.getParameter("num")));
		member.setPasswd(request.getParameter("passwd"));
		 System.out.println(member); 
		 MemberDBBean dbPro = MemberDBBean.getInstance();
			dbPro.insertArticle(member);

			 return  "/view/login.jsp"; 
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
			
		   //response.sendRedirect(request.getContextPath() + "/mb_view/list.jsp");
		   return "/mb_view/list.jsp";
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
		System.out.println("asdfasfd");
		System.out.println("asdfasfd");
		System.out.println("asdfasfd");
		System.out.println("asdfasfd");
		return  "/view/Logout.jsp"; 
			} 
}
