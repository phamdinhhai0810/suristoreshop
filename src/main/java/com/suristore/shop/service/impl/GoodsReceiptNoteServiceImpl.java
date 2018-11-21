package com.suristore.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suristore.shop.domain.GoodsReceiptNote;
import com.suristore.shop.domain.Product;
import com.suristore.shop.domain.requestvo.GoodsReceiptNoteRequestVO;
import com.suristore.shop.repo.CustomerRepository;
import com.suristore.shop.repo.GoodsReceiptNoteRepository;
import com.suristore.shop.repo.ProductRepository;
import com.suristore.shop.service.GoodsReceiptNoteService;

@Service
public class GoodsReceiptNoteServiceImpl implements GoodsReceiptNoteService {

	@Autowired
	private GoodsReceiptNoteRepository goodsReceiptNoteRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<GoodsReceiptNote> findAll() {
		// TODO Auto-generated method stub
		return goodsReceiptNoteRepository.findAll(new Sort(Sort.Direction.DESC, "createdAt"));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<GoodsReceiptNote> findLatest(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public GoodsReceiptNote findOne(int id) {
		// TODO Auto-generated method stub
		return goodsReceiptNoteRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public long countAll() {
		// TODO Auto-generated method stub
		return goodsReceiptNoteRepository.count();
	}

	@Override
	@Transactional
	public void save(GoodsReceiptNoteRequestVO goodsReceiptNoteRequestVO) {
		
		Product product = productRepository.findOne(goodsReceiptNoteRequestVO.getId_product());
		// tăng số lượng sản phẩm trong kho
		product.setTotal(product.getTotal() + goodsReceiptNoteRequestVO.getQuantity());
		productRepository.save(product);
		
		GoodsReceiptNote goodsReceiptNote = new GoodsReceiptNote();
		goodsReceiptNote.setCustomer(customerRepository.findOne(goodsReceiptNoteRequestVO.getId_customer()));
		goodsReceiptNote.setProduct(product);
		goodsReceiptNote.setNote(goodsReceiptNoteRequestVO.getNote());
		goodsReceiptNote.setPrice(goodsReceiptNoteRequestVO.getPrice());
		goodsReceiptNote.setQuantity(goodsReceiptNoteRequestVO.getQuantity());
		
		goodsReceiptNoteRepository.save(goodsReceiptNote);

	}

	@Override
	@Transactional
	public void delete(int id) {
		GoodsReceiptNote goodsReceiptNote = goodsReceiptNoteRepository.findOne(id);
		if(goodsReceiptNote != null){
			// giảm số lượng sản phẩm trong kho khi xóa đơn nhập đó
			Product product = goodsReceiptNote.getProduct();
			product.setTotal(product.getTotal() - goodsReceiptNote.getQuantity());
			productRepository.save(product);
		}
		goodsReceiptNoteRepository.delete(id);

	}

	@Override
	@Transactional
	public Iterable<GoodsReceiptNote> find5GoodsReceiptNoteLatest() {
		PageRequest pageRequest = new PageRequest(0, 5, Sort.Direction.DESC, "createdAt");
		return goodsReceiptNoteRepository.findAll(pageRequest);
	}

}
