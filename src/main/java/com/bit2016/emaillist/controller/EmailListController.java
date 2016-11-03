package com.bit2016.emaillist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bit2016.emaillist.DAO.EmailListDAO;
import com.bit2016.emaillist.VO.EmailList;

@Controller
public class EmailListController {
	private EmailListDAO emailListDAO;

	public String list(Model model) {
		List<EmailList> list = emailListDAO.selectAll();
		model.addAttribute("list", list);

		return "/WEB-INF/views/list.jsp";
	}
}
