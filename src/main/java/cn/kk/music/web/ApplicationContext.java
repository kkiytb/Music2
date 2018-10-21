package cn.kk.music.web;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ApplicationContext {
	private Map<String, Object> beans = new HashMap<String, Object>();
	public ApplicationContext() {
	}
	/**
	 * 根据配置文件初始化全部对象
	 * @param cfg xml配置文件
	 */
	public ApplicationContext(String cfg) {
		init(cfg);
	}
	/**
	 * 初始化:根据配置文件,将配置文件中的Bean创建
	 * 把对象存储到Map集合中以备以后使用
	 * @param cfg
	 */
	public void init(String cfg) {
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(cfg);
			Element root = new SAXReader().read(in).getRootElement();
			List<Element> elements = root.elements("bean");
			for (Element element : elements) {
				String className = element.attributeValue("class");
				Class cls = Class.forName(className);
				Object object = cls.newInstance();
				String name = cls.getName();
				name = name.substring(name.lastIndexOf('.') + 1);
				String key = name.substring(0,1).toLowerCase() + name.substring(1);
				beans.put(key, object);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public String toString() {
		return "ApplicationContext [beans=" + beans + "]";
	}
	public Collection<Object> getBeans() {
		//Map 类型上有一个方法values()用于返回
		//Map中全部value对象
		return beans.values();
	}
	

}
