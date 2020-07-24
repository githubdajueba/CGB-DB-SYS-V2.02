package com.db.common.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan(value= {"com.db.sys.controller","com.db.common.web"})
@EnableWebMvc //启用spring mvc 默认配置
public class SpringWeb extends 
     WebMvcConfigurerAdapter{
	@Override
	public void configureViewResolvers(
			ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/pages/",".html");
	}
}
