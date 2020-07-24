package com.db.common.config;
import java.io.IOException;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

@PropertySource("classpath:db.properties")
@MapperScan("com.db.**.dao")
public class SpringRespository {
	//读取properties文件中的内容
	@Value("${jdbcDriver}")
	private String driverClass;
	
	@Value("${jdbcUrl}")
	private String url;
	
	@Value("${jdbcUser}")
	private String username;
	
	@Value("${jdbcPassword}")
	private String password;

	
	//1.整合连接池
	//整合第三方bean对象时通常使用@Bean注解
	@Scope("singleton")
	@Lazy(false)
	@Bean(value="dataSource",initMethod="init",destroyMethod="close")
	//"dataSource"为bean对象对象的key
	public DataSource newDataSource() {
		DruidDataSource ds=new DruidDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}
	//2.整合mybatis
	@Bean("sqlSessionFactory")
	public SqlSessionFactoryBean newSqlSessionFactoryBean(
		@Autowired DataSource dataSource) throws IOException {
		SqlSessionFactoryBean fBean=new SqlSessionFactoryBean();
		fBean.setDataSource(newDataSource());
		Resource[] mapperLocations=
		new PathMatchingResourcePatternResolver()
		.getResources("classpath*:mapper/sys/*Mapper.xml");
		fBean.setMapperLocations(mapperLocations);
		return fBean;
	}
}
