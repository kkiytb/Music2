package cn.kk.music.web;

import java.util.HashMap;
/**
 * 用于从控制器带回需要到JSP中显示的数据
 * 增加这个类名的目的就是方便使用反射API的时候检查方法参数的类型
 * @author soft01
 *
 * @param <K>
 * @param <V>
 */
public class SessionMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 3901562833467648945L;

}
