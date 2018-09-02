import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.login.domain.LoginInfoVO;

	//Runner 클래스(테스트 메소드를 실행하는 클래스) 를 SpringJUnit4ClassRunner로 함
@RunWith(SpringJUnit4ClassRunner.class)
	//location 속성 경로에 있는 xml 파일을 이용해서 스프링이 로딩됨
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
	
public class LoginTest {
		
	@Inject
	private SqlSession sqlsession;
	
	private static String NS = "LoginMapper";	//namespace
	private static String listAll = NS + ".listAll";
	private static String login = NS + ".login";
	
	@Test
	public void listAll() {
		List<LoginInfoVO> list = sqlsession.selectList(listAll);
		for(int i = 0; i < list.size(); ++i) {
			System.out.println(list.get(i).toString());	//리스트 i 번째를 문자열로 가져와서 출력
		}
	}	
	
	@Test
	public void loginTest() {
		LoginInfoVO logininfo = new LoginInfoVO();
		logininfo.setId("aaaa");
		logininfo.setPw("1234");
		Integer login = sqlsession.selectOne(this.login, logininfo);
		if (login == 1)
		{
			System.out.println("로그인 완료");
		}
		else {
			System.out.println("아이디 또는 비밀번호가 다릅니다");
		}
		
	}
		
}

