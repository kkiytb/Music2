package cn.kk.music.web;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class HandlerMapping {
	/**
	 * Map中的key是用户请求的url,Map中的value是对象和控制器方法组成的一个实例
	 */
	private Map<String, UrlHandler> urlMap = new HashMap<String, UrlHandler>();
	/**
	 * init方法用于根据控制器对象初始化map集合
	 * @param controllers
	 */
	public void init(Collection<Object> controllers) {
		for (Object object : controllers) {
			//object = demoController
			//利用反射API获取类上的注解
			//cls = DemoController
			Class cls = object.getClass();
			//获取类上标注的注解
			RequestMapping rm = (RequestMapping) cls.getAnnotation(RequestMapping.class);
			//获取注解上标注的值
			String path = "";
			if(rm == null) {
				path = "";
			}else {
				if(rm.value() != null) {
					path = rm.value();
				}
			}
			//解析方法上的全部注解
			//找到全部的方法信息
			Method[] methods = cls.getDeclaredMethods();
			for (Method method : methods) {
				//找到方法上声明的注解
				RequestMapping rMapping = method.getAnnotation(RequestMapping.class);
				//如果找到没有注解的方法,则跳过继续赵
				if(rMapping == null) {
					continue;
				}
				String sub = rMapping.value();
				String pathWhole = path + sub;
				//添加到Map
				urlMap.put(pathWhole, new UrlHandler(object, method));
			}
		}
	}
	/**
	 * 根据用户提交url路径获取(对象和方法) UrlHanlder
	 * @param path
	 * @return
	 */
	public UrlHandler getUrlHandler(String path) {
		return urlMap.get(path);
	}
	@Override
	public String toString() {
		return "HandlerMapping [urlMap=" + urlMap + "]";
	}
}
