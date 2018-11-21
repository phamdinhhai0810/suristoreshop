package com.suristore.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suristore.shop.domain.Type;
import com.suristore.shop.service.TypeService;

@Controller
public class AdminTypeController {

	@Autowired
	private TypeService typeService;

	@GetMapping("/admin/type")
	public String index(Model model) {
		model.addAttribute("types", typeService.findAll());
		return "admin/type_list";
	}

	@GetMapping("/admin/type/add")
	public String add(Model model) {
		model.addAttribute("type", new Type());
		return "admin/type_form";
	}

	@GetMapping("/admin/type/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("type", typeService.findOne(id));
		return "admin/type_form";
	}

	@PostMapping("/admin/type/save")
	public String save(@Valid Type type, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "admin/type_form";
		}

		typeService.save(type);

		redirect.addFlashAttribute("success", "Lưu loại sản phẩm thành công!");
		return "redirect:/admin/type";
	}

	@GetMapping("/admin/type/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect) {
		typeService.delete(id);

		redirect.addFlashAttribute("success", "Xóa loại sản phẩm thành công!");
		return "redirect:/admin/type";
	}
}
