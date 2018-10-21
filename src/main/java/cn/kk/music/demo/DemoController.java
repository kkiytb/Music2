package cn.kk.music.demo;

import cn.kk.music.web.RequestMapping;

@RequestMapping("/user")
public class DemoController {
	@RequestMapping("/ok.do")
	public String hello() {
		System.out.println("hello World");
		return "OK";
	}
	@RequestMapping("/test.do")
	public void test() {
		System.out.println("It's a test");
	}

}
