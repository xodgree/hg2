package diaryDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DiaryDBBean {
	//�̱���
	private static DiaryDBBean instance = new DiaryDBBean();	//��ü ����, ���۷������� static ���� -> 1���� ����.
	private DiaryDBBean() {	//������ private���� �����Ͽ� �ۿ��� ��ü ���� �Ұ���.
		
	}
	//��ü�� static���� �����Ͽ� ������ �ʰ� �����ְ� instance ���۷��� ������ ����. �̰� �������ؼ� get���� �������� �ؾ���. 
	public static DiaryDBBean getInstance() {
		return instance;				
	}
	
	
	//DB���� �޼ҵ�
	public Connection getConnection(){
		//   ����Ÿ��      ����             
		   Connection con = null;			
		   try {
			   //DB�� URL,����ID,PW
		      String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";	
		      String dbUser = "scott";
		      String dbPass = "tiger";
		      
		      // ���÷���(reflection) ���� �ε��� ���� �ڵ�
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      
		      // DB URL,����, ��й�ȣ�� ������ DB�� �����մϴ�. ���� ����� con�� �����մϴ�.
		      con = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
		      
		   // ����(Exception)�� �߻��ϸ� � �������� �ľ��ϱ� ���� �ڵ尡 ���⿡ ���ϴ�.
		   }catch(Exception e) {
		      e.printStackTrace();
		      System.out.println(e.getMessage());
		   }
		   // Exception�� �߻����� �ʾҴٸ� ������ �����Ͽ����ϴ�.
		   // ���� ������ return�մϴ�.
		   return con;
		   }
	//���� �޼ҵ�
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
		
		//���� �޼ҵ�
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
/*	//Count ���� �޼ҵ� 1
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

		//Count ���� �޼ҵ�2
		public int getDataCount(String useremail) {
			Connection con = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			try {
			String sql = "select nvl(count(*),0)  from diarys where useremail = ? ";
           		
			
			
			pstmt = con.prepareStatement(sql); //pstmt = sql ������ ���� 
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
		
		//Count ���� �޼ҵ�
		public int getSearchCount(String useremail,String opt,String condition) {
			Connection con = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			try {
				StringBuffer sql = new StringBuffer();
			if(opt == null) {
				//��ü �� ����
			sql.append("select nvl(count(*),0)  from diarys "
					+ "where useremail = ? "); 				
			pstmt = con.prepareStatement(sql.toString()); //pstmt = sql ������ ���� 
			pstmt.setString(1, useremail);
			sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("0")) { //�������� �˻��� ���� ����
				sql.append("select nvl(count(*),0)  from diarys "
						+ "where useremail = ? and title like ?"); 
				 pstmt = con.prepareStatement(sql.toString());
				 pstmt.setString(1, useremail);
	             pstmt.setString(2, '%'+condition+'%');
	                
	                sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("1")) { //�������� �˻��� ���� ����
				sql.append("select nvl(count(*),0)  from diarys "
						+ "where useremail = ? and content like ?"); 
				 pstmt = con.prepareStatement(sql.toString());
				 pstmt.setString(1, useremail);
	             pstmt.setString(2, '%'+condition+'%');
	                
	                sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("2")) { //����+�������� �˻��� ���� ����
				sql.append("select nvl(count(*),0)  from diarys "
						+ "where useremail = ? and content like ? or title like ?"); 
				 pstmt = con.prepareStatement(sql.toString());
				 pstmt.setString(1, useremail);
	             pstmt.setString(2, '%'+condition+'%');
	             pstmt.setString(3, '%'+condition+'%');
	                
	                sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("3")) { //�������� �˻��� ���� ����
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
		
		// �˻�����Ʈ �������� �޼ҵ� (getArticles)
		public List articleList(int startRow,int endRow,String useremail,String opt,String condition) {
			// Connection, PreparedStatement, ResultSet �� 
			// DB�� �����Ͽ� �۾��ϱ� ���� �ʿ��� ���۷��� ������ �����մϴ�.
			// ���� 3������ DB �۾��� �ʿ��� �⺻ ��ҵ��Դϴ�.
			Connection conn = null;	//Ŀ�ؼ� ����.
			PreparedStatement pstmt = null; //������ ����.
			ResultSet rs = null;	//select ���� ������ DB�� ��û�� ����� ��.
			
			// Article�� ������ ArrayList ������ �����մϴ�.
			List articleList = null;	

			
			try {
				conn = getConnection();		//conn�� getConnection�޼ҵ带 ����. ��, con�� ����.
				StringBuffer sql = new StringBuffer();
				if(opt == null) {
				sql.append("select * from (select rownum AS aa , a.* from( " + 
						"select  num,regdate,content,imagename,emotion,useremail,title from diarys where useremail = ?  order by regdate desc) a) " + 
						   "where aa between ? AND ?");
				System.out.println();
	           // Connection�� ������ ����ϰ� PreparedStatement�� �ֽ��ϴ�.
				pstmt = conn.prepareStatement(sql.toString()); //pstmt = sql ������ ���� 
				
				pstmt.setString(1, useremail);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				// PreparedStatement�� ��ϵ� ������ �����մϴ�.
				// Select �����̹Ƿ� ResultSet���� �� ����� ����ϴ�.
				sql.delete(0, sql.toString().length());
				}
				else if(opt.equals("0")) { //�������� �˻�
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
				else if(opt.equals("1")) { //�������� �˻�
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
				else if(opt.equals("2")) { //����+�������� �˻�
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
				
				
			}else if(opt.equals("3")) {	//�������� �˻�
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
					articleList = new ArrayList();	//null�̾��� articleList�� �迭�� ������. article���� ������� list.
					
					do {
						DiaryDataBean article = new DiaryDataBean(); //���̺� ������ �� �������� ��ü ����.
						
						// ResultSet���� �ʿ��� �����͸� column �̸����� ���� ����ϴ�.
						// ���� �����ʹ� Model�� BoardDataBean ��ü�� setter�� �̿��ؼ� ���� �������ݴϴ�.
						article.setNum(rs.getInt("num"));
						article.setRegdate(rs.getDate("regdate"));
						article.setContent(rs.getString("content"));
						article.setImagename(rs.getString("imagename"));
						article.setEmotion(rs.getString("emotion"));
						article.setUseremail(rs.getString("useremail"));
						article.setTitle(rs.getString("title"));
						
						
						// ResultSet�� ������, ��, Article �����Ͱ� BoardDataBean ��ü�� ���޵Ǿ����ϴ�.
						// �տ��� ����� �� BoardDataBean ��ü�� �����ϱ� ���ؼ� �����Ͽ��� ArrayList�� �����մϴ�.
						articleList.add(article);
						//System.out.println(article);
					}while(rs.next());
					// �� ������ ResultSet�� ���̻� �����Ͱ� ���������� ����˴ϴ�.
				}	
				
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				close(conn,rs,pstmt);
				
				//System.out.println(articleList);
			}
			return articleList;
			
			
			
		}
		
		
	/*	// ������ �������� �޼ҵ� (getArticles)
		public List articleList(int startRow,int endRow,String useremail) {
			// Connection, PreparedStatement, ResultSet �� 
			// DB�� �����Ͽ� �۾��ϱ� ���� �ʿ��� ���۷��� ������ �����մϴ�.
			// ���� 3������ DB �۾��� �ʿ��� �⺻ ��ҵ��Դϴ�.
			Connection conn = null;	//Ŀ�ؼ� ����.
			PreparedStatement pstmt = null; //������ ����.
			ResultSet rs = null;	//select ���� ������ DB�� ��û�� ����� ��.
			
			// Article�� ������ ArrayList ������ �����մϴ�.
			List articleList = null;	
			String sql = "";		//���� �ۼ� ����
			
			try {
				conn = getConnection();		//conn�� getConnection�޼ҵ带 ����. ��, con�� ����.
			
				sql = "select * from (select rownum AS aa , a.* from( " + 
					"select  num,regdate,content,imagename,emotion,useremail,title from diarys where useremail = ?  order by regdate desc) a) " + 
					   "where aa between ? AND ?";	
	         
				System.out.println();
	           // Connection�� ������ ����ϰ� PreparedStatement�� �ֽ��ϴ�.
				pstmt = conn.prepareStatement(sql); //pstmt = sql ������ ���� 
				
				pstmt.setString(1, useremail);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				// PreparedStatement�� ��ϵ� ������ �����մϴ�.
				// Select �����̹Ƿ� ResultSet���� �� ����� ����ϴ�.
				rs = pstmt.executeQuery();
				
				// ResultSet�� �����͸� Ȯ���մϴ�
				
				// ResultSet.next()�� ó�� ����Ǹ� ResultSet�� ������ �ִ� ù��° �����͸� ����ŵ�ϴ�.
				// ���� ResultSet�� ������ �ִ� �����Ͱ� ���ٸ� null�� return�մϴ�.
				if(rs.next()) {
					articleList = new ArrayList();	//null�̾��� articleList�� �迭�� ������. article���� ������� list.
					
					do {
						DiaryDataBean article = new DiaryDataBean(); //���̺� ������ �� �������� ��ü ����.
						
						// ResultSet���� �ʿ��� �����͸� column �̸����� ���� ����ϴ�.
						// ���� �����ʹ� Model�� BoardDataBean ��ü�� setter�� �̿��ؼ� ���� �������ݴϴ�.
						article.setNum(rs.getInt("num"));
						article.setRegdate(rs.getDate("regdate"));
						article.setContent(rs.getString("content"));
						article.setImagename(rs.getString("imagename"));
						article.setEmotion(rs.getString("emotion"));
						article.setUseremail(rs.getString("useremail"));
						article.setTitle(rs.getString("title"));
						
						
						// ResultSet�� ������, ��, Article �����Ͱ� BoardDataBean ��ü�� ���޵Ǿ����ϴ�.
						// �տ��� ����� �� BoardDataBean ��ü�� �����ϱ� ���ؼ� �����Ͽ��� ArrayList�� �����մϴ�.
						articleList.add(article);
						//System.out.println(article);
					}while(rs.next());
					// �� ������ ResultSet�� ���̻� �����Ͱ� ���������� ����˴ϴ�.
				}	
				
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				close(conn,rs,pstmt);
				
				//System.out.println(articleList);
			}
			return articleList;
			
			
		} */
		
		//ȸ�� ���, ������ ���� �޼ҵ�
		//	���� Ÿ�� void, BoardDataBean type�� article�� �Ű������� ����
		public void insertArticle(DiaryDataBean diary, String useremail) {
			//������ ������ sql ���� ����
			String sql ="";
			//db�� Ŀ�ؼ� ����.
			Connection con = getConnection();
			//������ ����
			PreparedStatement pstmt = null;
			//����� ������
			ResultSet rs = null;
			int number =0;
			try {
				//Ŀ�ؼ��� �̿��Ͽ� ������ ����. ���� ���� => ������. ������ ����ϸ� num�� 1�� �ڵ� �����ǵ��� ��.
				pstmt = con.prepareStatement("select DIARY_SEQ.nextval from dual");
				rs = pstmt.executeQuery(); //���� �������� rs�� ����.
				
				//ResultSet.next()�� ó�� ����Ǹ� ResultSet�� ������ �ִ� ù��° �����͸� ����ŵ�ϴ�.
				if(rs.next())
					//�����Ͱ� ������ number�� +1�ȴ�.
					number = rs.getInt(1)+1;
				// ���� ResultSet�� ������ �ִ� �����Ͱ� ���ٸ�  number�� 1�̴�.
				else
					number = 1;
			
			
			
			//������ ���� sql���� �ۼ�
			sql = "insert into diarys(num,regdate,content,imagename,emotion,useremail,title) "
					+ "values (?,sysdate,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			//������ �� ������ �̿�
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
		
		
		
		//�� ���� �޼ҵ� (getArticle)
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
		
				// ������ 7���� �������� �޼ҵ� (getArticles)
				public List articleList2(int startRow,int endRow ,String useremail) {
					// Connection, PreparedStatement, ResultSet �� 
					// DB�� �����Ͽ� �۾��ϱ� ���� �ʿ��� ���۷��� ������ �����մϴ�.
					// ���� 3������ DB �۾��� �ʿ��� �⺻ ��ҵ��Դϴ�.
					Connection conn = null;	//Ŀ�ؼ� ����.
					PreparedStatement pstmt = null; //������ ����.
					ResultSet rs = null;	//select ���� ������ DB�� ��û�� ����� ��.
					
					// Article�� ������ ArrayList ������ �����մϴ�.
					List articleList = null;	
					String sql = "";		//���� �ۼ� ����
					
					try {
						conn = getConnection();		//conn�� getConnection�޼ҵ带 ����. ��, con�� ����.
			           
			           /*sql = "select * from (select rownum rnum, b.* " + 
			           		" from (select num,regdate,content,imagename,emotion,useremail,title from diarys order by regdate desc)b)" + 
			           		" where rnum between ? and ? and useremail = ?" ;*/
			           	sql = "select * from (select * from diarys where useremail = ? "
			           			+ " order by regdate desc)where ROWNUM between ? AND ?";	
			           // Connection�� ������ ����ϰ� PreparedStatement�� �ֽ��ϴ�.
						pstmt = conn.prepareStatement(sql); //pstmt = sql ������ ���� 
						pstmt.setString(1, useremail);
						pstmt.setInt(2, startRow);
						pstmt.setInt(3, endRow);
						
						// PreparedStatement�� ��ϵ� ������ �����մϴ�.
						// Select �����̹Ƿ� ResultSet���� �� ����� ����ϴ�.
						rs = pstmt.executeQuery();
						
						// ResultSet�� �����͸� Ȯ���մϴ�
						
						// ResultSet.next()�� ó�� ����Ǹ� ResultSet�� ������ �ִ� ù��° �����͸� ����ŵ�ϴ�.
						// ���� ResultSet�� ������ �ִ� �����Ͱ� ���ٸ� null�� return�մϴ�.
						if(rs.next()) {
							articleList = new ArrayList();	//null�̾��� articleList�� �迭�� ������. article���� ������� list.
							
							do {
								DiaryDataBean article = new DiaryDataBean(); //���̺� ������ �� �������� ��ü ����.
								
								// ResultSet���� �ʿ��� �����͸� column �̸����� ���� ����ϴ�.
								// ���� �����ʹ� Model�� BoardDataBean ��ü�� setter�� �̿��ؼ� ���� �������ݴϴ�.
								article.setNum(rs.getInt("num"));
								article.setRegdate(rs.getDate("regdate"));
								article.setContent(rs.getString("content"));
								article.setImagename(rs.getString("imagename"));
								article.setEmotion(rs.getString("emotion"));
								article.setUseremail(rs.getString("useremail"));
								article.setTitle(rs.getString("title"));
								
								// ResultSet�� ������, ��, Article �����Ͱ� BoardDataBean ��ü�� ���޵Ǿ����ϴ�.
								// �տ��� ����� �� BoardDataBean ��ü�� �����ϱ� ���ؼ� �����Ͽ��� ArrayList�� �����մϴ�.
								articleList.add(article);
								//System.out.println(article);
							}while(rs.next());
							// �� ������ ResultSet�� ���̻� �����Ͱ� ���������� ����˴ϴ�.
						}	
						
					}catch (Exception e){
						e.printStackTrace();
					}finally {
						close(conn,rs,pstmt);
						
						//System.out.println(articleList);
					}
					return articleList;
					
					
				}
		
				//������ �������� �޼ҵ�
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
		
		//comment �������¸޼ҵ�
				public List commentList(String emotion) {

					Connection conn = null;	//Ŀ�ؼ� ����.
					PreparedStatement pstmt = null; //������ ����.
					ResultSet rs = null;	//select ���� ������ DB�� ��û�� ����� ��.
					
					// Article�� ������ ArrayList ������ �����մϴ�.
					List commentList = null;	
					String sql = "";		//���� �ۼ� ����
					
					try {
						conn = getConnection();		
					if(emotion.equals("���")) {
			           sql = "select * from COMFORTCOMMENT where emotion = '���'";
					}else if(emotion.equals("����")){
						sql = "select * from COMFORTCOMMENT where emotion = '����'";	
					}else {
						sql = "select * from COMFORTCOMMENT where emotion = '����'";
					}
				
						pstmt = conn.prepareStatement(sql); //pstmt = sql ������ ���� 
						rs = pstmt.executeQuery();
						if(rs.next()) {
							 commentList = new ArrayList();
							do {
								commentList.add(rs.getString("content"));
								//System.out.println(article);
							}while(rs.next());
							// �� ������ ResultSet�� ���̻� �����Ͱ� ���������� ����˴ϴ�.
						}	
						
					}catch (Exception e){
						e.printStackTrace();
					}finally {
						close(conn,rs,pstmt);
						
						//System.out.println(articleList);
					}
					return commentList;
				}
				
	// ���� �׷��� 
				public List graphList(int startRow,int endRow,String useremail) {	

					Connection conn = null;	//Ŀ�ؼ� ����.
					PreparedStatement pstmt = null; //������ ����.
					ResultSet rs = null;	//select ���� ������ DB�� ��û�� ����� ��.
					
					// Article�� ������ ArrayList ������ �����մϴ�.
					ArrayList<DiaryDataBean> graphList = null;	
					String sql = "";		//���� �ۼ� ����
					
					try {
						conn = getConnection();		
						/*sql = "select * from (select rownum rnum, b.* "
								+" from (select regdate,emotion,useremail from diarys order by regdate desc)b)"
								+ " where rnum between ? and ? and useremail = ?";*/
						
						sql = "select regdate,emotion from (select * from diarys where useremail = ? "
								+ "order by regdate desc)where ROWNUM between ? AND ?";
				
						pstmt = conn.prepareStatement(sql); //pstmt = sql ������ ���� 
						
						pstmt.setString(1, useremail);
						pstmt.setInt(2, startRow);
						pstmt.setInt(3, endRow);
						
						rs = pstmt.executeQuery();
						if(rs.next()) {
							graphList = new ArrayList();
							do {
								DiaryDataBean article = new DiaryDataBean(); //���̺� ������ �� �������� ��ü ����.
								
								article.setRegdate(rs.getDate("regdate"));
								article.setEmotion(rs.getString("emotion"));
							
								graphList.add(article);
								//System.out.println(article);
							}while(rs.next());
							// �� ������ ResultSet�� ���̻� �����Ͱ� ���������� ����˴ϴ�.
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

					Connection conn = null;	//Ŀ�ؼ� ����.
					PreparedStatement pstmt = null; //������ ����.
					ResultSet rs = null;	//select ���� ������ DB�� ��û�� ����� ��.
					
					// Article�� ������ ArrayList ������ �����մϴ�.
					ArrayList<DiaryDataBean> graphList = null;	
					String sql = "";		//���� �ۼ� ����
					
					try {
						conn = getConnection();		
						/*sql = "select * from (select rownum rnum, b.* "
								+" from (select regdate,emotion,useremail from diarys order by regdate desc)b)"
								+ " where rnum between ? and ? and useremail = ?";*/
						
						sql = "select emotion from diarys";
				
						pstmt = conn.prepareStatement(sql); //pstmt = sql ������ ���� 
						
						rs = pstmt.executeQuery();
						if(rs.next()) {
							graphList = new ArrayList();
							do {
								DiaryDataBean article = new DiaryDataBean(); //���̺� ������ �� �������� ��ü ����.
								
								article.setEmotion(rs.getString("emotion"));
							
								graphList.add(article);
								//System.out.println(article);
							}while(rs.next());
							// �� ������ ResultSet�� ���̻� �����Ͱ� ���������� ����˴ϴ�.
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