package memberDb;

//Connector 오브젝트를 만듬 제반을 셋팅.
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnector {
	public SqlSession sqlSession() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		}catch(IOException e) {
	throw new IllegalArgumentException(e);
}return new SqlSessionFactoryBuilder().build(inputStream).openSession();}
}
