package com.zpf.test.TestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

import com.zpf.test.Entity.User;
import com.zpf.test.Service.UserService;
import com.zpf.util.RespUtil;

/**
 * 测试专用controller
 * @author zpf
 *
 */
@Controller
public class TestController {
	@Autowired
	private UserService userService;

	// 访问形式1 用?带参 /index?id=1
	@RequestMapping("/index")
	public String index1Ac(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		System.out.println("id=" + id);
		model.addAttribute("hello", "hello");
		return "/testHtml/index.html";
		// return "/testHtml/index.jsp";
	}

	// 访问形式2 uri传参 /index/212
	@RequestMapping("/index/{id}")
	public String index2Ac(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@PathVariable("id") String id) {
		System.out.println("id=" + id);
		model.addAttribute("hello", "hello");
		return "/testHtml/index.html";
	}

	/*
	 * add HTML
	 */
	@RequestMapping("/add")
	public String addHtml(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "/testHtml/add.html";
	}

	/*
	 * add AC
	 */
	@RequestMapping("/addok")
	public String addAc(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		System.out.println("do");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");

		User user = new User();
		user.setAge(Integer.parseInt(age));
		user.setCreateDate(new Date());
		user.setModifyDate(new Date());
		user.setName(name);
		user.setSex(sex);
		this.userService.save(user);
		return "/testHtml/ok.html";
	}

	/*
	 * get entity by id 
	 */
	@RequestMapping("/get/{id}")
	public String getById(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@PathVariable("id") long id) {
		User u = this.userService.getById(id);
		model.addAttribute("user", u);
		return "/testHtml/getuser.html";
	}
	
	/*
	 * list entity
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/list")
	public String getList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<User> list = this.userService.listAll();
		List<Map> liMap =  new ArrayList<Map>();
		for (User user : list) {
			Map<Object, Object> map =new HashMap<Object, Object>();
			map.put("id", user.getId());
			map.put("age", user.getAge());
			map.put("name", user.getName());
			map.put("sex", user.getSex());
			liMap.add(map);
		}
		model.addAttribute("list_", liMap);
		return "/testHtml/getlist.html";
	}
	
	/*
	 * json way
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/jsonlist")
	public void getJsonList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JSONObject json = new JSONObject();
		List<User> list = this.userService.listAll();
		List<Map> liMap =  new ArrayList<Map>();
		for (User user : list) {
			Map<Object, Object> map =new HashMap<Object, Object>();
			map.put("id", user.getId());
			map.put("age", user.getAge());
			map.put("name", user.getName());
			map.put("sex", user.getSex());
			liMap.add(map);
		}
		json.put("list", liMap);
		RespUtil.renderJson(response, json.toString());
	}
	
	/*
	 * entity count
	 */
	@RequestMapping("/count")
	public String getCount(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		int count= this.userService.count();
		model.addAttribute("count", count);
		return "/testHtml/getcount.html";
	}
}
