package com.member.anno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.member.anno.model.MemberDAOImpl;
import com.member.anno.model.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemberDAOImpl dao;//AutoWired�� �̿��� �ڵ����� ������
	
	@RequestMapping("/test.go")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("data","anno controller���� ������ ��");
		 mv.setViewName("result"); 
		//mv.setViewName("WEB-INF/view/result.jsp");
		return mv;
	}
	//insert
	@RequestMapping(value="/insert.go", method=RequestMethod.POST)
	public ModelAndView insert(MemberVO user) {
		dao.insert(user);
		return new ModelAndView("index");
	}
	//insert-form
	@RequestMapping("/insert.go")//�ڿ� �ƹ��͵� �Ⱥ����� �ٹ��
	public ModelAndView insert() {
		return new ModelAndView("memberInsert");
	}
	//list
	@RequestMapping("/list.go")
	public ModelAndView getMemberList() {
		List<MemberVO> arr = dao.getMemberList();
		ModelAndView mv = new ModelAndView();
		mv.addObject("arr",arr);
		mv.setViewName("list");
		return mv;
	}
	//View
	@RequestMapping("/ViewInfo.go")
	public ModelAndView findById(String id) {
		MemberVO vo = dao.findById(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo",vo);
		mv.setViewName("view");
		return mv;
	}
	//delete
	@RequestMapping("/delete.go")
	public ModelAndView delete(String id) {
		dao.delete(id);
		
		return new ModelAndView("redirect:list.go");
	}
	@RequestMapping("/update.go")
	public ModelAndView update(MemberVO user) {
		dao.update(user);
		return new ModelAndView("redirect:list.go");
	}
}
