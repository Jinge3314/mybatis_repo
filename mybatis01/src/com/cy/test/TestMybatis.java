package com.cy.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cy.pojo.Emp;

/**
 * Mybatis的快速入门
 */
public class TestMybatis {
	/**
	 * 1.查询mybatisdb库中的emp表中所有员工，将所有员工记录以一个List 
	 * 集合（List<Emp>）返回
	 * @throws IOException 
	 */
	@Test
	public void findAll() throws IOException{
		//1.读取sqlMapConfig.xml文件，获取其中的基本信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2. 根据配置信息生成SqlSessionFactory工厂对象， 
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		//3.通过工厂获取一个SqlSession对象(用于执行SQL及返回结果)
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//4.执行SQL语句，查询emp表中的所有记录，封装到List集合中
		List<Emp> empList = sqlSession.selectList("com.cy.pojo.EmpMapper.findAll");
		//5.打印list集合
		for (Emp emp : empList) {
			System.out.println(emp);
		}
	}

	/**
	 * 2.新增：往emp表中添加一个新员工
	 * @throws Exception
	 */
	@Test
	public void addEmp() throws Exception{
		//1.读取sqlMapConfig.xml文件, 获取所有配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3.通过工厂获取一个SqlSession对象
		SqlSession session = factory.openSession();
		//4.执行新增操作, 往emp表中添加一个新员工
		session.insert("com.cy.pojo.EmpMapper.insert");
		//>>提交事务
		session.commit();
		//5.打印结果
		System.out.println("执行完成!");
	}

	@Test
	public void updateEmp() throws Exception{
		//1.读取sqlMapConfig.xml文件, 获取所有配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3.通过工厂获取一个SqlSession对象
		SqlSession session = factory.openSession();
		//4.执行更新操作
		int rows = session.update("com.cy.pojo.EmpMapper.update");
		//>>提交事务
		session.commit();
		//5.打印结果
		System.out.println("影响的行数："+rows);
	}

	/**
	 * 4.：删除emp表中的一条员工信息
	 * @throws Exception
	 */
	@Test
	public void deleteEmp() throws Exception{
		//1.读取sqlMapConfig.xml文件, 获取所有配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.创建工厂对象, 用于创建SqlSession对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3.获取一个SqlSession对象
		SqlSession session  = factory.openSession();
		//4.执行删除操作
		session.delete("com.cy.pojo.EmpMapper.delete");
		//>>提交事务
		session.commit();

		//5.打印结果
		System.out.println("执行完成!");
	}

	@Test
	public void findById() throws IOException{
		//0.加载sqlMapConfig.xml文件，获取所有的配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//1.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//2.通过工厂创建一个SqlSession对象
		SqlSession session = factory.openSession();
		//3.执行SQL语句（namespace+Id）
		Emp emp = session.selectOne("com.cy.pojo.EmpMapper.findById");
		//4.输出结果
		System.out.println(emp);
	}

	@Test
	public void testInsert() throws IOException{
		//0.加载sqlMapConfig.xml文件，获取所有的配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//1.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//2.通过工厂创建一个SqlSession对象
		SqlSession session = factory.openSession();
		//3.执行SQL语句（namespace+SQLId）
		//>>准备参数
		Emp emp = new Emp();
		emp.setName("马化腾");
		emp.setJob("腾讯CEO");
		emp.setSalary(9000D);
		//>>执行新增
		session.insert("com.cy.pojo.EmpMapper.insert2", emp);
		//4.提交事务
		session.commit();
		System.out.println("执行完成！");
	}

	@Test
	public void testFindEmpById() throws IOException{
		//0.加载sqlMapConfig.xml文件，获取所有的配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//1.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//2.通过工厂创建一个SqlSession对象
		SqlSession session = factory.openSession();
		//3.执行SQL语句（namespace+Id）
		Emp emp = session.selectOne("com.cy.pojo.EmpMapper.findById2", 1);
		//4.输出结果
		System.out.println(emp);
	}

	@Test
	public void testUpdate() throws IOException{
		//0.加载sqlMapConfig.xml文件，获取所有的配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//1.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//2.通过工厂创建一个SqlSession对象
		SqlSession session = factory.openSession();
		//3.执行SQL语句（namespace+SQLId）
		//>>准备参数
		Emp emp = new Emp();
		emp.setId(1);
		emp.setName("王五");
		emp.setJob("架构师");
		emp.setSalary(8000.0);
		//>>执行修改
		session.update("com.cy.pojo.EmpMapper.update2",emp);
		//>>提交事务
		session.commit();
		System.out.println("执行完成！");
	}

	@Test
	public void testDelete() throws IOException{
		//0.加载sqlMapConfig.xml文件，获取所有的配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//1.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//2.通过工厂创建一个SqlSession对象
		SqlSession session = factory.openSession();
		//3.执行SQL语句（namespace+SQLId）
		//>>执行删除
		session.delete("com.cy.pojo.EmpMapper.delete2", "马化腾");
		//>>提交事务
		session.commit();
		System.out.println("执行完成！");
	}

	@Test
	public void testFindAll2() throws IOException{
		//0.加载sqlMapConfig.xml文件，获取所有的配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//1.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//2.通过工厂创建一个SqlSession对象
		SqlSession session = factory.openSession();
		//3.执行SQL语句（namespace+SQLId）
		Map<String,String> map = new HashMap<String,String>();
		map.put("cols", "name, job");
		List<Emp> list = session.selectList(
				"com.cy.pojo.EmpMapper.findAll2",map);
		//4.输出结果
		for(Emp emp : list){
			System.out.println(emp);
		}
	}

	@Test
	public void testFindAll3() throws IOException{
		//0.加载sqlMapConfig.xml文件，获取所有的配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//1.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//2.通过工厂创建一个SqlSession对象
		SqlSession session = factory.openSession();
		//3.执行SQL语句（namespace+SQLId）
		Map map = new HashMap();
		map.put("name", "刘");//查询name中包含"刘"字的所有员工
		List<Emp> list = session.selectList(
				"com.cy.pojo.EmpMapper.findAll3",map);
		//4.输出结果
		for(Emp emp : list){
			System.out.println(emp);
		}
	}

	@Test
	public void testFindAll4() throws IOException{
		//0.加载sqlMapConfig.xml文件，获取所有的配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//1.创建工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//2.通过工厂创建一个SqlSession对象
		SqlSession session = factory.openSession();
		//3.执行SQL语句（namespace+SQLId）
		Map map = new HashMap();
		//查询薪资大于3000和小于4000的员工
		map.put("minSal", 3000);
		map.put("maxSal", 4000);
		List<Emp> list = session.selectList(
				"com.cy.pojo.EmpMapper.findAll4",map);
		//4.输出结果
		for(Emp emp : list){
			System.out.println(emp);
		}
	}

	@Test
	public void testUpdateById() throws IOException{
	    //0.加载sqlMapConfig.xml文件，获取所有的配置信息
	    InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
	    //1.创建工厂对象
	    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
	    //2.通过工厂创建一个SqlSession对象
	    SqlSession session = factory.openSession();
	    //3.执行SQL语句（namespace+SQLId）
	   Emp emp = new Emp();
	   emp.setId(1);
	   emp.setName("王海涛");
	   emp.setJob("王牌讲师");
	   emp.setSalary(4999.0);
	    //4.执行更新
	    session.update("com.cy.pojo.EmpMapper.updateEmpById",emp);
	    //>>提交事务
	    session.commit();
	    System.out.println("执行完成！");
	}
	
	@Test
	public void testfindByIds() throws IOException{
	    //0.加载sqlMapConfig.xml文件，获取所有的配置信息
	    InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
	    //1.创建工厂对象
	    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
	    //2.通过工厂创建一个SqlSession对象
	    SqlSession session = factory.openSession();
	    //3.执行SQL语句（namespace+SQLId）
	   //查询指定id的所有员工信息
	   int[] ids = {1,3,5};
	    //4.执行更新
	    List<Emp> list = session.selectList("com.cy.pojo.EmpMapper.findByIds",ids);
	    //5.输出结果
	    for (Emp e : list) {
	       System.out.println(e);
	    }
	}

	@Test
	public void testDeleteByIds() throws IOException{
	    //0.加载sqlMapConfig.xml文件，获取所有的配置信息
	    InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
	    //1.创建工厂对象
	    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
	    //2.通过工厂创建一个SqlSession对象
	    SqlSession session = factory.openSession();
	    //3.执行SQL语句（namespace+SQLId）
	   //删除指定id的员工信息
	   int[] ids = {1,3,5};
	    //4.执行更新
	    session.delete("com.cy.pojo.EmpMapper.deleteByIds",ids);
	    //>>提交事务
	    session.commit();
	    System.out.println("执行完成!");
	}

	




}