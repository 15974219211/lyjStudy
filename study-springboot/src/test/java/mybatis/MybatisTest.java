package mybatis;


import com.my.study.StudySpringboot;
import com.my.study.dao.TUserMapper;
import com.my.study.entity.TJobHistory;
import com.my.study.entity.TUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudySpringboot.class})
public class MybatisTest {



	@Autowired
	private  TUserMapper tUserMapper;

	
	
	
	@Test
	// 1对多两种关联方式
	public void testOneToMany() {
		List<TUser> tUsers = tUserMapper.selectUserJobs();
		for (TUser tUser:tUsers){
			System.out.println(tUser.getUserName());
			List<TJobHistory> jobs = tUser.getJobs();
			jobs.forEach((e)-> System.out.println(e.toString()));
		}
	}
	//批量in查询
	@Test
	public  void  testIn(){
		String[] names = new String[]{"lison","james"};
		List<TUser> users = tUserMapper.selectForeachIn(names);
		System.out.println(users.size());
	}

	//批量插入1
	@Test
	public  void  testInsert1(){

		TUser user1 = new TUser();
		user1.setUserName("dfdgfd");
		user1.setRealName("李小京");
		user1.setEmail("dfgfdg@qq.com");
		user1.setMobile("1857575");
		user1.setNote("ggfgf");
		user1.setSex((byte)1);
		TUser user2 = new TUser();
		user2.setUserName("dgfgfgf");
		user2.setRealName("陈辅林");
		user2.setEmail("chen@qq.com");
		user2.setMobile("187203138787");
		user2.setNote("fdfdgfg");
		user2.setSex((byte)1);
		List<TUser> tUsers = Arrays.asList(user1, user2);
		int i = tUserMapper.insertForeachBatch(tUsers);
		System.out.println(i);
	}

	//单条插入，获取主键
	@Test
	public  void  inserOne(){
		TUser user2 = new TUser();
		user2.setUserName("test2");
		user2.setRealName("realname2");
		user2.setEmail("myemai2l");
		tUserMapper.insert2(user2);
//		user2.setId(131);
		System.out.println(user2.getId());
	}

	//动态sql
	@Test
	public  void  dynamicSql(){
		String email = "";
		Byte sex = 2;
		List<TUser> list = tUserMapper.selectChooseOper(email , sex);
		System.out.println(list.size());
	}
}
