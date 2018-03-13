package memberDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class MemDBMybatis extends MybatisConnector{
   private final String namespace = "ldg.mybatis";
   
   private static MemDBMybatis instance = new MemDBMybatis();
   private MemDBMybatis() {
   }
   public static MemDBMybatis getInstance() {
      return instance;
   }
   SqlSession sqlSession;
   
   //탈퇴 메소드
   public int deleteMypage(String useremail){
	   sqlSession = sqlSession();
	      Map map = new HashMap();
	      map.put("useremail", useremail);
	      
	      int x 
	      = sqlSession.delete(namespace+".deleteMypage", map) ;
	       
	      sqlSession.commit();
	      sqlSession.close();
	      
	      return x;
   }
   
   //member가져오기
   public MemberDataBean getMember(String useremail) {
	   sqlSession = sqlSession();
	      Map map = new HashMap();
	      map.put("useremail", useremail);
	  
	      MemberDataBean article 
	      = sqlSession.selectOne(namespace+".getMember", map) ;      //오브젝트인가? 컬렉션인가?
	      sqlSession.commit();
	      sqlSession.close();
	      return article;
   }
   
   //마이페이지 수정
   public int updateMypage(MemberDataBean member,String useremail) {
		  sqlSession = sqlSession();	  
		  Map map = new HashMap();
		  map.put("name", member.getName());
		  map.put("useremail", useremail);
	      int pwdck
	      = sqlSession.update(namespace+".updateMypage", map) ;      //오브젝트인가? 컬렉션인가?      
	      sqlSession.commit();
	      sqlSession.close();
	      return pwdck;
   }
   
   //아이디, 비밀번호 조회
   public int login(String inputEmail,String inputPasswd)throws Exception{
	   sqlSession = sqlSession();	  
		  Map map = new HashMap();
		  map.put("inputEmail", inputEmail);
	   int loginResult = -1;
	 String passwd =  sqlSession.selectOne(namespace+".login", map);
			if(passwd.equals(inputPasswd)) {
				loginResult = 1;
			}else {
				loginResult = 0;
			}

	   return loginResult;
   }
   
   public String MainName(String inputEmail){
	   sqlSession = sqlSession();	  
		  Map map = new HashMap();
		  map.put("inputEmail", inputEmail);
		  String userName =  sqlSession.selectOne(namespace+".MainName", map);
		return userName;
   }
   
   public void insertArticle(MemberDataBean member) {
	   sqlSession = sqlSession();
	      int number = sqlSession.selectOne(namespace+".getNextNumber",member);
	      member.setNum(number);
			 sqlSession.insert(namespace+".insertArticle",member);
		      sqlSession.commit();
		      sqlSession.close();
   }
   
   public int getDataCount() {
	   sqlSession = sqlSession();
		int count = 0;
		count = sqlSession.selectOne(namespace+".getDataCount");	//하나가져오니까 selectOne
		return count;
   }
   
   public List articleList(int startRow,int endRow) {
	   sqlSession = sqlSession();
	      Map map = new HashMap();
	      map.put("startRow", startRow);
	      map.put("endRow", endRow);
	      
	      List articleList = sqlSession.selectList(namespace+".articleList", map) ;      //오브젝트인가? 컬렉션인가?
	      sqlSession.close();
	      return articleList;
   }

   
   
   
   
   
   
   
   
   
   
   //---------------------------------------------------
   
   
 /*  public int getArticleCount(String boardid) {
      int x=0;
      sqlSession = sqlSession();
      Map<String, String> map = new HashMap<String, String>();
      map.put("boardid", boardid);
      
      x = sqlSession.selectOne(namespace+".getArticleCount", map) ;      //selectOne (오브젝트) /오브젝트인가? 컬렉션인가?
      sqlSession.close();
      return x;
   }
   
   public List getArticles(int startRow, int endRow, String boardid) {
      sqlSession = sqlSession();
      Map map = new HashMap();
      map.put("startRow", startRow);
      map.put("endRow", endRow);
      map.put("boardid", boardid);
      
      List li = sqlSession.selectList(namespace+".getArticles", map) ;      //오브젝트인가? 컬렉션인가?
      sqlSession.close();
      return li;
      }
   
   
   public void insertArticle(BoardDataBean article) {
      sqlSession = sqlSession();
      int number = sqlSession.selectOne(namespace+".getNextNumber",article);
      
      //답글쓰기
      if(article.getNum()!=0) {
         sqlSession.update(namespace+".updateRe_step",article);
         article.setRe_level(article.getRe_level()+1);
         article.setRe_step(article.getRe_step()+1);
      sqlSession.commit();
      //새글쓰기
      }else {
         article.setRef(number);
         article.setRe_level(0);
         article.setRe_step(0);
         
      }
      article.setNum(number);

      sqlSession.insert(namespace+".insertBoard",article);
      sqlSession.commit();
      sqlSession.close();
//
      
      
      //
         
         //새글쓰기
         

   }
   public BoardDataBean getArticle(int num, String boardid, String chk) {
	  
	  
	   sqlSession = sqlSession();
	      Map map = new HashMap();
	      map.put("num", num);
	      map.put("boardid", boardid);
	      if(chk.equals("content")) {
	    	  sqlSession.update(namespace+".addReadCount",map);
	      }
	      BoardDataBean article 
	      = sqlSession.selectOne(namespace+".getArticle", map) ;      //오브젝트인가? 컬렉션인가?
	      sqlSession.commit();
	      sqlSession.close();
	      return article;
   }
   
   public int updateArticle(BoardDataBean article) {
	   sqlSession = sqlSession();	     
	      int chk 
	      = sqlSession.update(namespace+".updateArticle", article) ;      //오브젝트인가? 컬렉션인가?      
	      sqlSession.commit();
	      sqlSession.close();
	      return chk;
   }
   
   public int deleteArticle(int num, String passwd, String boardid)throws Exception{
	   sqlSession = sqlSession();
	      Map map = new HashMap();
	      map.put("num", num);
	      map.put("passwd", passwd);
	      map.put("boardid", boardid);
	   
	      int chk 
	      = sqlSession.delete(namespace+".deleteArticle", map) ;      //오브젝트인가? 컬렉션인가?
	      sqlSession.commit();
	      sqlSession.close();
	      return chk;
	   
   }*/
}