package cn.kk.music.web;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)//注解将保留到程序运行期间
@Target(PARAMETER)//注解只能在方法参数上使用
public @interface Param {
	String value();
}
