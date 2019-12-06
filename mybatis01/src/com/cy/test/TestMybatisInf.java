package com.cy.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cy.dao.EmpMapper;
import com.cy.pojo.Emp;

public class TestMybatisInf {
	@Test
	public void testFindById() throws Exception{
	    //0.加载sqlMapConfig.xml文件，获取所有的配置信息
	    InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
	    //1.创建工厂对象
	    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
	    //2.通过工厂创建一个SqlSession对象
	    SqlSession session = factory.openSession();
	    //3.获取Mapper接口对象
	    EmpMapper mapper = session.getMapper(EmpMapper.class);
	    //4.调用接口对象的方法进行查询
	    Emp e = mapper.findById(2);
	    //5.输出结果
	    System.out.println(e);
	}
	
	@Test
	public void testFindAll() throws Exception {
		//0.加载sqlMapConfig.xml文件，获取所有的配置信息
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//1.创建工厂对象
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(in);
		//2.通过工厂创建一个SqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//3.获取Mapper接口对象
		EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
		//4.调用接口对象的方法进行查询
		List<Emp> e = mapper.findAll();
		//5.输出结果
		for (Emp emp : e) {
			System.out.println(emp);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
