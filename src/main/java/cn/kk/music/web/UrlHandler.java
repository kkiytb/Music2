package cn.kk.music.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class UrlHandler {
	private Object object;
	private Method method;
	public UrlHandler() {
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	@Override
	public String toString() {
		return "UrlHandler [object=" + object + ", method=" + method + "]";
	}
	public UrlHandler(Object object, Method method) {
		super();
		this.object = object;
		this.method = method;
	}
	/**
	 * UrlHandler 中添加执行控制器方法的方法
	 * 在当前控制器对象Obj的当前方法method
	 * 参数map用于封装返回到request中的数据
	 * @param paramMap 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public String execute(Map<String, String[]> paramMap, ModelMap<String,Object> map, SessionMap<String, Object> sessionMap) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/*
		 * 检查当前方法使用包含ModelMap 类型的参数,
		 * 如果包含ModelMap就传递参数调用方法
		 * 如果不包含参数就不传递参数调用方法
		 * 
		 *					String 	String[]  String String[]
		 *	paramMap={"user"->{"Tom"}, "age"->{"10"}} 
		 */
		//检查当前方法method的参数 (Parameter)类型
		Class[] paramTypes = method.getParameterTypes();
		System.out.println(paramTypes.length);
		//如果参数长度为0,则说明method方法没有参数
		if(paramTypes.length == 0) {
			Object obj = method.invoke(object);
			System.out.println(obj);
			return obj.toString();
		}
		//有参数的情况,检查参数的类型,根据参数的类型
		//顺序创建目标参数数组
		
		Object[] param = new Object[paramTypes.length];
		//param如果是{1,1.5,map}
		//则paramTypes = {int.class,double.class,ModelMap.class}
		//检测每个参数上的注解
		Annotation[][] anns = method.getParameterAnnotations();
		//根据参数类型来拼凑参数
		for (int i = 0; i < paramTypes.length; i++) {
			//获取当前参数的注解ann(没有参数长度为0)
			Annotation[] ann = anns[i];
			//获取Param注解有两种情况
			Param p = null;//没有注解
			System.out.println(p);
			if(ann.length == 1) {
				p = (Param) ann[0];//有Param注解
			}
			if(p != null) {
				String key = p.value();
				//key有两种情况,user或age
				//根据key获取表单中的参数值
				String[] paramValues = paramMap.get(key);
				//如果key为user,paramValue={"Tom"}
				//如果key为age,paramValue={"10"}
				//需要根据目标参数的类型,进行类型转换
				Class type = paramTypes[i];
				System.out.println("type:" +type);
				if (type == int.class || type == Integer.class) {
					//将paramValues中的数据转换为整数
					//添加到目标参数的数组中
					param[i] = Integer.parseInt(paramValues[0]);
				}else if (type == Float.class || type == float.class) {
					param[i] = paramValues[0];
				}else if (type == double.class || type == Double.class) {
					param[i] = paramValues[0];
				}else if (type == long.class || type == Long.class) {
					param[i] = paramValues[0];
				}else if (type == String[].class) {
					param[i] = paramValues;
				}else if (type == String.class) {
					//如果是字符串类型,就不需要转换类型了
					param[i] = paramValues[0];
				}
			}else {//p == null 的情况下要处理ModelMap
				//type是按照"顺序"出现的,目标中参数的类型
				Class type = paramTypes[i];
				if(type == ModelMap.class) {
					//如果参数类型是ModelMap则传递参数map
					param[i] = map;
				}else if(type == SessionMap.class){
					param[i] = sessionMap;
				}
			}
		}
		Object obj = method.invoke(object,param);
		return obj.toString();
	}
}
