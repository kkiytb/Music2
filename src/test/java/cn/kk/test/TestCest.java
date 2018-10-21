package cn.kk.test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import cn.kk.music.demo.DemoController;
import cn.kk.music.web.ApplicationContext;
import cn.kk.music.web.HandlerMapping;
import cn.kk.music.web.UrlHandler;

public class TestCest {
	@Test
	public void testApplicationContext() {
		String file = "conf/context.xml";
		ApplicationContext applicationContext = new ApplicationContext(file);
		System.out.println(applicationContext);
	}
	@Test
	public void testHandlerMapping() {
		Collection controllers = new ArrayList();
		controllers.add(new DemoController());
		HandlerMapping mapping = new HandlerMapping();
		mapping.init(controllers);
		System.out.println(mapping);
		//测试查找功能
		String url = "/user/ok.do";
		UrlHandler urlHandler = mapping.getUrlHandler(url);
		System.out.println(urlHandler);
		
	}
}
