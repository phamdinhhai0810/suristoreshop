package com.suristore.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suristore.shop.domain.requestvo.GoodsReceiptNoteRequestVO;
import com.suristore.shop.service.CustomerService;
import com.suristore.shop.service.GoodsReceiptNoteService;
import com.suristore.shop.service.ProductService;
import com.suristore.shop.utils.CustomerType;

@Controller
@RequestMapping(value = "/admin/goods_receipt_note")
public class AdminGoodsReceiptNoteController {

	@Autowired
	private GoodsReceiptNoteService goodsReceiptNoteService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("")
	public String index(Model model) {

		model.addAttribute("goods_receipt_notes", goodsReceiptNoteService.findAll());
		return "admin/goods_receipt_notes_list";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("goods_receipt_note", goodsReceiptNoteService.findOne(id));
		return "admin/goods_receipt_notes_detail";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id, RedirectAttributes redirect) {
		goodsReceiptNoteService.delete(id);

		redirect.addFlashAttribute("success",
				new StringBuilder().append("Xóa đơn hàng ").append(id).append(" thành công").toString());
		return "redirect:/admin/goods_receipt_note";
	}

	@GetMapping("/add")
	public String add(Model model) {

		List<CustomerType> listCustomerType = new ArrayList<>();
		listCustomerType.add(CustomerType.KHACH_BAN);
		listCustomerType.add(CustomerType.KHACH_BAN_VA_MUA);

		model.addAttribute("good_receipt_note", new GoodsReceiptNoteRequestVO());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("customers", customerService.findByCustomerType(listCustomerType));

		return "admin/goods_receipt_notes_form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @RequestBody GoodsReceiptNoteRequestVO goodsReceiptNoteRequestVO, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "admin/goods_receipt_notes_form";
		}
		goodsReceiptNoteService.save(goodsReceiptNoteRequestVO);

		redirect.addFlashAttribute("success", "Nhập đơn hàng thành công!");
		return "redirect:/admin/goods_receipt_note";
	}
}
