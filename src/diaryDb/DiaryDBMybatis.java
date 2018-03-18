package diaryDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

import memberDb.MemberDataBean;
import memberDb.MybatisConnector;

public class DiaryDBMybatis extends MybatisConnector{
   private final String namespace = "ldg.mybatis2";
   
   private static DiaryDBMybatis instance = new DiaryDBMybatis();
   private DiaryDBMybatis() {
   }
   public static DiaryDBMybatis getInstance() {
      return instance;
   }
   SqlSession sqlSession;
   
	public int getDataCount(String useremail) {
		int count = 0;
		sqlSession = sqlSession();
	      Map<String, String> map = new HashMap<String, String>();
	      map.put("useremail", useremail);	
	      count = sqlSession.selectOne(namespace+".getDataCount", map) ;
		return count;
		}
	
	public List articleList2(int startRow,int endRow ,String useremail) {
		// Article을 저장할 ArrayList 변수를 선언합니다.
		List articleList = null;	
		sqlSession = sqlSession();
		 Map map = new HashMap();
	      map.put("useremail", useremail);
	      map.put("startRow", startRow);
	      map.put("endRow", endRow);
	      articleList = sqlSession.selectList(namespace+".articleList2", map) ;      //오브젝트인가? 컬렉션인가?
	      sqlSession.close();
		return articleList;
		
		
		}
	
	public DiaryDataBean getContent(int num,String chk) {
		DiaryDataBean diary = null;
		sqlSession = sqlSession();
		 Map map = new HashMap();
		 map.put("num", num);	
		 diary =  sqlSession.selectOne(namespace+".getContent", map) ; ;
		 sqlSession.commit();
	     sqlSession.close();
		return diary;
	}
	
	public int update(DiaryDataBean diary) {
		sqlSession = sqlSession();	     
		int result = -1;
		result = sqlSession.update(namespace+".update", diary) ; 
		sqlSession.commit();
	    sqlSession.close();
		return result;
	}
	
	public int delete(int num) {
		int result = -1;
		  sqlSession = sqlSession();
	      Map map = new HashMap();
	      map.put("num", num);
		result = sqlSession.delete(namespace+".delete", map) ;
		  sqlSession.commit();
	      sqlSession.close();
		return result;
	}
	
	public List graphList(int startRow,int endRow,String useremail) {	

		ArrayList<DiaryDataBean> graphList = null;	
		 sqlSession = sqlSession();
	      Map map = new HashMap();
	      map.put("useremail", useremail);
	      map.put("startRow", startRow);
	      map.put("endRow", endRow);
	      graphList = sqlSession.selectOne(namespace+".graphList", map);
	      sqlSession.commit();
	      sqlSession.close();
		return graphList;
	} 
	
	public List graphAllList() {	
		ArrayList<DiaryDataBean> graphList = null;	
		sqlSession = sqlSession();
		graphList = sqlSession.selectOne(namespace+".graphAllList");
		return graphList;
	}
	 
	public void insertArticle(DiaryDataBean diary, String useremail) {
		sqlSession = sqlSession();
	   int number = sqlSession.selectOne(namespace+".getNextNumber");
	   diary.setNum(number);
	   diary.setUseremail(useremail);
	   System.out.println("diary는" + diary);
	   sqlSession.insert(namespace+".insertArticle",diary);
	      sqlSession.commit();
	      sqlSession.close();
	}	

	
	//comment 가져오는메소드
	public List commentList(String emotion) {
		List commentList = null;	
		sqlSession = sqlSession();
		commentList = sqlSession.selectList(namespace+".commentList",emotion);	
		sqlSession.close();
		return commentList;
	}
	
	
	
	 //검색 개수 받는 메소드
	public int getSearchCount(String useremail,String opt,String condition) {
		sqlSession = sqlSession();
		int count = 0;
		
		Map map = new HashMap();
		map.put("useremail", useremail);
		map.put("opt", opt);
		map.put("condition", condition);
		
		count = sqlSession.selectOne(namespace+".getSearchCount",map);
		sqlSession.close();
		/*
		
		
		try {
			StringBuffer sql = new StringBuffer();
		if(opt == null) {
			//전체 글 개수
		sql.append("select nvl(count(*),0)  from diarys "
				+ "where useremail = ? "); 				
		pstmt = con.prepareStatement(sql.toString()); //pstmt = sql 쿼리를 담음 
		pstmt.setString(1, useremail);
		sql.delete(0, sql.toString().length());
		}
		else if(opt.equals("0")) { //제목으로 검색한 글의 개수
			sql.append("select nvl(count(*),0)  from diarys "
					+ "where useremail = ? and title like ?"); 
			 pstmt = con.prepareStatement(sql.toString());
			 pstmt.setString(1, useremail);
	             pstmt.setString(2, '%'+condition+'%');
	                
	                sql.delete(0, sql.toString().length());
		}
		else if(opt.equals("1")) { //내용으로 검색한 글의 개수
			sql.append("select nvl(count(*),0)  from diarys "
					+ "where useremail = ? and content like ?"); 
			 pstmt = con.prepareStatement(sql.toString());
			 pstmt.setString(1, useremail);
	             pstmt.setString(2, '%'+condition+'%');
	                
	                sql.delete(0, sql.toString().length());
		}
		else if(opt.equals("2")) { //내용+제목으로 검색한 글의 개수
			sql.append("select nvl(count(*),0)  from diarys "
					+ "where useremail = ? and content like ? or title like ?"); 
			 pstmt = con.prepareStatement(sql.toString());
			 pstmt.setString(1, useremail);
	             pstmt.setString(2, '%'+condition+'%');
	             pstmt.setString(3, '%'+condition+'%');
	                
	                sql.delete(0, sql.toString().length());
		}
		else if(opt.equals("3")) { //감정으로 검색한 글의 개수
			sql.append("select nvl(count(*),0)  from diarys "
					+ "where useremail = ? and emotion like ?"); 
			 pstmt = con.prepareStatement(sql.toString());
			 pstmt.setString(1, useremail);
	             pstmt.setString(2, '%'+condition+'%');
	                
	                sql.delete(0, sql.toString().length());
		}
	
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			count =rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(con,rs,pstmt);
		}
		 */
		return count;
	} 
	
	// 검색리스트 가져오는 메소드 (getArticles)
	public List articleList(int startRow,int endRow,String useremail,String opt,String condition) {
		// Article을 저장할 ArrayList 변수를 선언합니다.
		
		List articleList = null;	
		
		sqlSession = sqlSession();
		
		Map map = new HashMap();
		map.put("useremail", useremail);
		map.put("opt", opt);
		map.put("condition", condition);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		System.out.println("리스트map:::"+map);
		
		
		articleList = sqlSession.selectList(namespace+".articleList",map);	
		
		sqlSession.close();
		
		return articleList;
		

		/*
		try {
			conn = getConnection();		//conn에 getConnection메소드를 넣음. 즉, con을 넣음.
			StringBuffer sql = new StringBuffer();
			if(opt == null) {
			sql.append("select * from (select rownum AS aa , a.* from( " + 
					"select  num,regdate,content,imagename,emotion,useremail,title from diarys where useremail = ?  order by regdate desc) a) " + 
					   "where aa between ? AND ?");
			System.out.println();
           // Connection에 쿼리를 등록하고 PreparedStatement에 넣습니다.
			pstmt = conn.prepareStatement(sql.toString()); //pstmt = sql 쿼리를 담음 
			
			pstmt.setString(1, useremail);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			// PreparedStatement로 등록된 쿼리를 실행합니다.
			// Select 쿼리이므로 ResultSet으로 그 결과를 얻습니다.
			sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("0")) { //제목으로 검색
				sql.append("select * from (select rownum AS aa , a.* from( " + 
						"select  num,regdate,content,imagename,emotion,useremail,title from diarys where useremail = ?  order by regdate desc) a) " + 
						   "where aa between ? AND ? and title like ?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, useremail);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				pstmt.setString(4, "%"+condition+"%");
				
				sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("1")) { //내용으로 검색
				sql.append("select * from (select rownum AS aa , a.* from( " + 
						"select  num,regdate,content,imagename,emotion,useremail,title from diarys where useremail = ?  order by regdate desc) a) " + 
						   "where aa between ? AND ? and content like ?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, useremail);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				pstmt.setString(4, "%"+condition+"%");
				
				sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("2")) { //내용+제목으로 검색
				sql.append("select * from (select rownum AS aa , a.* from( " + 
						"select  num,regdate,content,imagename,emotion,useremail,title from diarys where useremail = ?  order by regdate desc) a) " + 
						   "where aa between ? AND ? and content like ? or title like ?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, useremail);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				pstmt.setString(4, "%"+condition+"%");
				pstmt.setString(5, "%"+condition+"%");
				
				sql.delete(0, sql.toString().length());
			
			
		}else if(opt.equals("3")) {	//감정으로 검색
			sql.append("select * from (select rownum AS aa , a.* from( " + 
					"select  num,regdate,content,imagename,emotion,useremail,title from diarys where useremail = ?  order by regdate desc) a) " + 
					   "where aa between ? AND ? and emotion like ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, useremail);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			pstmt.setString(4, "%"+condition+"%");
			
			sql.delete(0, sql.toString().length());
		}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleList = new ArrayList();	//null이었던 articleList에 배열을 생성함. article들을 담기위한 list.
				
				do {
					DiaryDataBean article = new DiaryDataBean(); //테이블 변수에 값 설정위해 객체 생성.
					
					// ResultSet에서 필요한 데이터를 column 이름으로 각각 얻습니다.
					// 얻은 데이터는 Model인 BoardDataBean 객체의 setter를 이용해서 값을 설정해줍니다.
					article.setNum(rs.getInt("num"));
					article.setRegdate(rs.getDate("regdate"));
					article.setContent(rs.getString("content"));
					article.setImagename(rs.getString("imagename"));
					article.setEmotion(rs.getString("emotion"));
					article.setUseremail(rs.getString("useremail"));
					article.setTitle(rs.getString("title"));
					
					
					// ResultSet의 데이터, 즉, Article 데이터가 BoardDataBean 객체로 전달되었습니다.
					// 앞에서 만들어 둔 BoardDataBean 객체를 보관하기 위해서 생성하였던 ArrayList에 저장합니다.
					articleList.add(article);
					//System.out.println(article);
				}while(rs.next());
				// 이 과정은 ResultSet에 더이상 데이터가 없을때까지 진행됩니다.
			}	
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			close(conn,rs,pstmt);
			
			//System.out.println(articleList);
		}
		return articleList;
		*/
		
		
	}
	
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