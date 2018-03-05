package controller;
//이 안에 메소드 다 넣음!!
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.msk.Action;

import memberDb.MemberDBBean;
import memberDb.MemberDataBean;


public class ProjectController extends Action{
	//일기 쓰기
public String diaryWrite(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
	
	int num = 0;
	if(request.getParameter("num")!=null){
		num = Integer.parseInt(request.getParameter("num"));
	};
	request.setAttribute("num", num);
		return  "/view/diaryWrite.jsp"; 
		} 

	//일기 쓰기 pro
	public String diaryWriteDb(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
		 
		
		return  "/view/diaryWriteDb"; 	
		} 
	
	
	//메인(로그인화면)
	public String login(HttpServletRequest request, HttpServletResponse response)  throws Throwable { 
			 return  "/view/login.jsp"; 
			} 
	
	//로그인 확인
	public String LoginDb(HttpServletRequest request, HttpServletResponse response)  throws Throwable {
		String inputEmail = null;
		String inputPasswd = null;
		int result = -1;
		
		HttpSession session = request.getSession();
		
		try {
			Object tempEmail = session.getAttribute("userEmail");
			Object tempPass = session.getAttribute("userPasswd");
			
			// 만약 로그인이 성공되어서 inputEmail이 있다면
			if( tempEmail != null && tempPass != null) {
				inputEmail = tempEmail.toString();
				inputPasswd = tempPass.toString();
			} else {
				// 로그인 화면에서 넘어올 경우에는 파라미터로 전달하기 때문에
				inputEmail = request.getParameter("inputEmail");
				inputPasswd = request.getParameter("inputPasswd");
			}

			result = MemberDBBean.getInstance().login(inputEmail, inputPasswd);
			
			System.out.println(inputEmail + "," + inputPasswd);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == 1){
			/* result가 1이 맞고, email이 admin@hughug.com이면 회원관리 페이지로 보냅니다. 
			result가 1이 맞고, email이 adim이 아니면 메인.jsp로 이동합니다. */
			
			// 로그인이 성공하면 attribute를 설정해줍니다.
			// admin, user 모두
			String userName = MemberDBBean.getInstance().MainName(inputEmail);
			
			// 로그인 성공하면 여기서 어트리뷰트 지정합니다.
			// userEmail, userName, userPasswd 항목으로 getAttribute를 받습니다.
			session.setAttribute("userEmail", inputEmail);
			session.setAttribute("userName", userName);
			session.setAttribute("userPasswd", inputPasswd);
			
			if(inputEmail.equals("admin@hughug.com")){		
				return "/board/list";
			}else{
				//response.sendRedirect(request.getContextPath() + "/view/Main.jsp");
				return "/view/Main.jsp";
			}
		}
		else {
			// 로그인 실패할 때
			request.setAttribute("result", result);
			return  "/view/loginDb.jsp"; 
		} 
		
	}
	
	//회원가입
	public String signUp(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		int num = 0;
		if(request.getParameter("num")!=null){
			num = Integer.parseInt(request.getParameter("num"));
		};
		request.setAttribute("num", num);
			 return  "/view/signUp.jsp"; 
			} 
	
	//회원가입 action
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
	
//회원관리 게시판
	//목록
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
		
		//페이지 처리
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
	
	//회원보기
	public String viewContent(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		
		int num =Integer.parseInt(request.getParameter("num"));
		//페이지 번호 넘기기
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
	
	//수정
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
			아래 처럼 바꿔줌.
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
	
	//삭제
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

		int num = Integer.parseInt(request.getParameter("num")); //deleteForm 에서 넘어온 데이터
		String passwd = request.getParameter("passwd");
		MemberDBBean dbPro = MemberDBBean.getInstance();
		int check = dbPro.deletemember(num, passwd);
		
		request.setAttribute("check", check);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
			 return  "/mb_view/deletePro.jsp"; 
			}
	
	//검색
	public String Search(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
			 return  "/mb_view/Search.jsp"; 
			} 
	
	//로그아웃
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
