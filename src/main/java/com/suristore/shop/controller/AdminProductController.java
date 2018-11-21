package com.suristore.shop.controller;

import java.beans.PropertyEditorSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suristore.shop.domain.Category;
import com.suristore.shop.domain.Product;
import com.suristore.shop.service.CategoryService;
import com.suristore.shop.service.ProductService;
import com.suristore.shop.service.TypeService;

@Controller
@RequestMapping(value = "/admin/product")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TypeService typeService;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("products", productService.findAll());
		return "admin/product_list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "admin/product_form";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("product", productService.findOne(id));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "admin/product_form";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, "category", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Category category = categoryService.findOne(Integer.parseInt(text));
				setValue(category);
			}
		});
	}

	@PostMapping("/save")
	public String save(@Valid Product product, BindingResult result, Model model, RedirectAttributes redirect) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("types", typeService.findAll());

		if (result.hasErrors()) {
			return "admin/product_form";
		}

		productService.save(product);

		redirect.addFlashAttribute("success", "Lưu sản phẩm thành công!");
		return "redirect:/admin/product";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect) {
		productService.delete(id);

		redirect.addFlashAttribute("success", "Xóa sản phẩm thành công!");
		return "redirect:/admin/product";
	}

	@GetMapping("/{id}/upload")
	public String getUpload(@PathVariable("id") int id, Model model) {
		model.addAttribute("product", productService.findOne(id));
		return "admin/product_upload";
	}

	@PostMapping("/{id}/upload")
	public String postUpload(@PathVariable("id") int id, @RequestParam("image") MultipartFile imageFile,
			RedirectAttributes redirect) {
		Product product = productService.findOne(id);
		productService.upload(product, imageFile);

		redirect.addFlashAttribute("success", "Upload ảnh thành công!");
		return "redirect:/admin/product";
	}

}
