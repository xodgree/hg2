package diaryDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DiaryDBBean {
	//싱글톤
	private static DiaryDBBean instance = new DiaryDBBean();	//객체 생성, 레퍼런스변수 static 설정 -> 1개로 공유.
	private DiaryDBBean() {	//생성자 private으로 설정하여 밖에서 객체 생성 불가능.
		
	}
	//객체를 static으로 정의하여 만들지 않고 쓸수있고 instance 레퍼런스 변수도 동일. 이걸 쓰기위해선 get으로 가져오게 해야함. 
	public static DiaryDBBean getInstance() {
		return instance;				
	}
	
	
	//DB연결 메소드
	public Connection getConnection(){
		//   리턴타입      변수             
		   Connection con = null;			
		   try {
			   //DB의 URL,계정ID,PW
		      String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";	
		      String dbUser = "scott";
		      String dbPass = "tiger";
		      
		      // 리플렌션(reflection) 동적 로딩에 대한 코드
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      
		      // DB URL,계정, 비밀번호를 가지고 DB에 접속합니다. 접속 결과를 con에 저장합니다.
		      con = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
		      
		   // 예외(Exception)가 발생하면 어떤 문제인지 파악하기 위한 코드가 여기에 들어갑니다.
		   }catch(Exception e) {
		      e.printStackTrace();
		      System.out.println(e.getMessage());
		   }
		   // Exception이 발생하지 않았다면 무사히 접속하였습니다.
		   // 접속 정보를 return합니다.
		   return con;
		   }
	//삭제 메소드
		public int delete(int num) {
			Connection conn = null;
			String sql = "delete from diarys where num = ?";
			PreparedStatement ps = null;
			int result = -1;
			conn = getConnection();
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, num);
				result = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		//수정 메소드
		public int update(DiaryDataBean diary) {
			Connection con = null;
			String sql = "update diarys set title = ?,content=?,imagename=? where num = ?";
			PreparedStatement ps = null;
			con = getConnection();
			int result = -1;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, diary.getTitle());
				ps.setString(2, diary.getContent());
				ps.setString(3, diary.getImagename());
				ps.setInt(4, diary.getNum());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
/*	//Count 세는 메소드 1
			public int getDataCount() {
//				String sql = "select nvl(count(*),0) from diarys";
				String sql = "select * from (select * from diarys where useremail = ? "
	           			+ " order by regdate desc)where ROWNUM between ? AND ?";
				Connection con = getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int count = 0;
				try {
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next()) {count =rs.getInt(1);}
				}catch(Exception e) {
					e.printStackTrace();
					
				}finally {
					close(con,rs,pstmt);
				}
				return count;
			}*/

		//Count 세는 메소드2
		public int getDataCount(String useremail) {
			Connection con = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			try {
			String sql = "select nvl(count(*),0)  from diarys where useremail = ? ";
           		
			
			
			pstmt = con.prepareStatement(sql); //pstmt = sql 쿼리를 담음 
			pstmt.setString(1, useremail);
		
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count =rs.getInt(1);
				}
			}catch(Exception e) {
				e.printStackTrace();
				
			}finally {
				close(con,rs,pstmt);
			}
			return count;
		}	
		
		//Count 세는 메소드
		public int getSearchCount(String useremail,String opt,String condition) {
			Connection con = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
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
			return count;
		}
		
		// 검색리스트 가져오는 메소드 (getArticles)
		public List articleList(int startRow,int endRow,String useremail,String opt,String condition) {
			// Connection, PreparedStatement, ResultSet 등 
			// DB에 접속하여 작업하기 위해 필요한 레퍼런스 변수를 선언합니다.
			// 위의 3가지는 DB 작업에 필요한 기본 요소들입니다.
			Connection conn = null;	//커넥션 정보.
			PreparedStatement pstmt = null; //쿼리를 담음.
			ResultSet rs = null;	//select 쿼리 날리면 DB에 요청한 결과를 줌.
			
			// Article을 저장할 ArrayList 변수를 선언합니다.
			List articleList = null;	

			
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
			
			
			
		}
		
		
	/*	// 데이터 가져오는 메소드 (getArticles)
		public List articleList(int startRow,int endRow,String useremail) {
			// Connection, PreparedStatement, ResultSet 등 
			// DB에 접속하여 작업하기 위해 필요한 레퍼런스 변수를 선언합니다.
			// 위의 3가지는 DB 작업에 필요한 기본 요소들입니다.
			Connection conn = null;	//커넥션 정보.
			PreparedStatement pstmt = null; //쿼리를 담음.
			ResultSet rs = null;	//select 쿼리 날리면 DB에 요청한 결과를 줌.
			
			// Article을 저장할 ArrayList 변수를 선언합니다.
			List articleList = null;	
			String sql = "";		//쿼리 작성 변수
			
			try {
				conn = getConnection();		//conn에 getConnection메소드를 넣음. 즉, con을 넣음.
			
				sql = "select * from (select rownum AS aa , a.* from( " + 
					"select  num,regdate,content,imagename,emotion,useremail,title from diarys where useremail = ?  order by regdate desc) a) " + 
					   "where aa between ? AND ?";	
	         
				System.out.println();
	           // Connection에 쿼리를 등록하고 PreparedStatement에 넣습니다.
				pstmt = conn.prepareStatement(sql); //pstmt = sql 쿼리를 담음 
				
				pstmt.setString(1, useremail);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				// PreparedStatement로 등록된 쿼리를 실행합니다.
				// Select 쿼리이므로 ResultSet으로 그 결과를 얻습니다.
				rs = pstmt.executeQuery();
				
				// ResultSet의 데이터를 확인합니다
				
				// ResultSet.next()는 처음 실행되면 ResultSet이 가지고 있는 첫번째 데이터를 가리킵니다.
				// 만약 ResultSet이 가지고 있는 데이터가 없다면 null을 return합니다.
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
			
			
		} */
		
		//회원 등록, 데이터 삽입 메소드
		//	리턴 타입 void, BoardDataBean type의 article을 매개변수로 받음
		public void insertArticle(DiaryDataBean diary, String useremail) {
			//쿼리를 저장할 sql 변수 선언
			String sql ="";
			//db와 커넥션 해줌.
			Connection con = getConnection();
			//쿼리를 담음
			PreparedStatement pstmt = null;
			//결과를 보여줌
			ResultSet rs = null;
			int number =0;
			try {
				//커넥션을 이용하여 쿼리를 담음. 쿼리 내용 => 시퀀스. 데이터 등록하면 num이 1씩 자동 증가되도록 함.
				pstmt = con.prepareStatement("select DIARY_SEQ.nextval from dual");
				rs = pstmt.executeQuery(); //쿼리 내보낸걸 rs에 담음.
				
				//ResultSet.next()는 처음 실행되면 ResultSet이 가지고 있는 첫번째 데이터를 가리킵니다.
				if(rs.next())
					//데이터가 있으면 number은 +1된다.
					number = rs.getInt(1)+1;
				// 만약 ResultSet이 가지고 있는 데이터가 없다면  number은 1이다.
				else
					number = 1;
			
			
			
			//데이터 삽입 sql쿼리 작성
			sql = "insert into diarys(num,regdate,content,imagename,emotion,useremail,title) "
					+ "values (?,sysdate,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			//위에서 쓴 시퀀스 이용
			pstmt.setInt(1, getDiarySeq());
			
			pstmt.setString(2, diary.getContent());
			
			pstmt.setString(3, diary.getImagename());
			
			pstmt.setString(4, diary.getEmotion());
			
			pstmt.setString(5, useremail);
			
			pstmt.setString(6, diary.getTitle());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e1) {
				e1.printStackTrace();
			}finally {
				close(con,rs,pstmt);
			}
		
	}

		private void close(Connection con, ResultSet rs, PreparedStatement pstmt) {
			// TODO Auto-generated method stub
			if(rs != null)
				try {
					rs.close();
				}catch(SQLException ex) {}
			if(pstmt !=null) try {pstmt.close();
				}catch(SQLException ex) {}
			if(con !=null)
				try {
					con.close();
				}catch(SQLException ex) {
			}
		}
		
		
		
		//글 보기 메소드 (getArticle)
				public DiaryDataBean getContent(int num,String chk) {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					DiaryDataBean diary = null;
					String sql ="";
					try {
						conn = getConnection();
						sql = "select * from diarys where num =?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, num);
						rs = pstmt.executeQuery();

						if(rs.next()) {
							diary = new DiaryDataBean();
							diary.setNum(rs.getInt("num"));
							diary.setRegdate(rs.getDate("regdate"));
							diary.setContent(rs.getString("content"));
							diary.setImagename(rs.getString("imagename"));
							diary.setEmotion(rs.getString("emotion"));
							diary.setUseremail(rs.getString("useremail"));
							diary.setTitle(rs.getString("title"));

						}
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						close(conn,rs,pstmt);
					}
					System.out.println(diary.getContent());
					return diary;
					
				}
		
				// 데이터 7개만 가져오는 메소드 (getArticles)
				public List articleList2(int startRow,int endRow ,String useremail) {
					// Connection, PreparedStatement, ResultSet 등 
					// DB에 접속하여 작업하기 위해 필요한 레퍼런스 변수를 선언합니다.
					// 위의 3가지는 DB 작업에 필요한 기본 요소들입니다.
					Connection conn = null;	//커넥션 정보.
					PreparedStatement pstmt = null; //쿼리를 담음.
					ResultSet rs = null;	//select 쿼리 날리면 DB에 요청한 결과를 줌.
					
					// Article을 저장할 ArrayList 변수를 선언합니다.
					List articleList = null;	
					String sql = "";		//쿼리 작성 변수
					
					try {
						conn = getConnection();		//conn에 getConnection메소드를 넣음. 즉, con을 넣음.
			           
			           /*sql = "select * from (select rownum rnum, b.* " + 
			           		" from (select num,regdate,content,imagename,emotion,useremail,title from diarys order by regdate desc)b)" + 
			           		" where rnum between ? and ? and useremail = ?" ;*/
			           	sql = "select * from (select * from diarys where useremail = ? "
			           			+ " order by regdate desc)where ROWNUM between ? AND ?";	
			           // Connection에 쿼리를 등록하고 PreparedStatement에 넣습니다.
						pstmt = conn.prepareStatement(sql); //pstmt = sql 쿼리를 담음 
						pstmt.setString(1, useremail);
						pstmt.setInt(2, startRow);
						pstmt.setInt(3, endRow);
						
						// PreparedStatement로 등록된 쿼리를 실행합니다.
						// Select 쿼리이므로 ResultSet으로 그 결과를 얻습니다.
						rs = pstmt.executeQuery();
						
						// ResultSet의 데이터를 확인합니다
						
						// ResultSet.next()는 처음 실행되면 ResultSet이 가지고 있는 첫번째 데이터를 가리킵니다.
						// 만약 ResultSet이 가지고 있는 데이터가 없다면 null을 return합니다.
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
					
					
				}
		
				//시퀀스 가져오는 메소드
				public int getDiarySeq() {
					Connection con = null;
					String sql = "select diary_seq.nextval from dual";
					PreparedStatement ps = null;
					ResultSet rs = null;
					int number = 0;
					con = getConnection();
					try {
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						if(rs.next()) {
							number = rs.getInt(1)+1;
						}else {
							number = 1;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return number;
				}
		
		//comment 가져오는메소드
				public List commentList(String emotion) {

					Connection conn = null;	//커넥션 정보.
					PreparedStatement pstmt = null; //쿼리를 담음.
					ResultSet rs = null;	//select 쿼리 날리면 DB에 요청한 결과를 줌.
					
					// Article을 저장할 ArrayList 변수를 선언합니다.
					List commentList = null;	
					String sql = "";		//쿼리 작성 변수
					
					try {
						conn = getConnection();		
					if(emotion.equals("기쁨")) {
			           sql = "select * from COMFORTCOMMENT where emotion = '기쁨'";
					}else if(emotion.equals("보통")){
						sql = "select * from COMFORTCOMMENT where emotion = '보통'";	
					}else {
						sql = "select * from COMFORTCOMMENT where emotion = '나쁨'";
					}
				
						pstmt = conn.prepareStatement(sql); //pstmt = sql 쿼리를 담음 
						rs = pstmt.executeQuery();
						if(rs.next()) {
							 commentList = new ArrayList();
							do {
								commentList.add(rs.getString("content"));
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
					return commentList;
				}
				
	// 감정 그래프 
				public List graphList(int startRow,int endRow,String useremail) {	

					Connection conn = null;	//커넥션 정보.
					PreparedStatement pstmt = null; //쿼리를 담음.
					ResultSet rs = null;	//select 쿼리 날리면 DB에 요청한 결과를 줌.
					
					// Article을 저장할 ArrayList 변수를 선언합니다.
					ArrayList<DiaryDataBean> graphList = null;	
					String sql = "";		//쿼리 작성 변수
					
					try {
						conn = getConnection();		
						/*sql = "select * from (select rownum rnum, b.* "
								+" from (select regdate,emotion,useremail from diarys order by regdate desc)b)"
								+ " where rnum between ? and ? and useremail = ?";*/
						
						sql = "select regdate,emotion from (select * from diarys where useremail = ? "
								+ "order by regdate desc)where ROWNUM between ? AND ?";
				
						pstmt = conn.prepareStatement(sql); //pstmt = sql 쿼리를 담음 
						
						pstmt.setString(1, useremail);
						pstmt.setInt(2, startRow);
						pstmt.setInt(3, endRow);
						
						rs = pstmt.executeQuery();
						if(rs.next()) {
							graphList = new ArrayList();
							do {
								DiaryDataBean article = new DiaryDataBean(); //테이블 변수에 값 설정위해 객체 생성.
								
								article.setRegdate(rs.getDate("regdate"));
								article.setEmotion(rs.getString("emotion"));
							
								graphList.add(article);
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
					return graphList;
				}
				
				public List graphAllList() {	

					Connection conn = null;	//커넥션 정보.
					PreparedStatement pstmt = null; //쿼리를 담음.
					ResultSet rs = null;	//select 쿼리 날리면 DB에 요청한 결과를 줌.
					
					// Article을 저장할 ArrayList 변수를 선언합니다.
					ArrayList<DiaryDataBean> graphList = null;	
					String sql = "";		//쿼리 작성 변수
					
					try {
						conn = getConnection();		
						/*sql = "select * from (select rownum rnum, b.* "
								+" from (select regdate,emotion,useremail from diarys order by regdate desc)b)"
								+ " where rnum between ? and ? and useremail = ?";*/
						
						sql = "select emotion from diarys";
				
						pstmt = conn.prepareStatement(sql); //pstmt = sql 쿼리를 담음 
						
						rs = pstmt.executeQuery();
						if(rs.next()) {
							graphList = new ArrayList();
							do {
								DiaryDataBean article = new DiaryDataBean(); //테이블 변수에 값 설정위해 객체 생성.
								
								article.setEmotion(rs.getString("emotion"));
							
								graphList.add(article);
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
					return graphList;
				}
				
				
		
}