package com.suristore.shop.service;

import org.springframework.data.domain.Page;

import com.suristore.shop.domain.GoodsReceiptNote;
import com.suristore.shop.domain.requestvo.GoodsReceiptNoteRequestVO;

public interface GoodsReceiptNoteService {

	Iterable<GoodsReceiptNote> findAll();

	Page<GoodsReceiptNote> findLatest(int page, int size);

	GoodsReceiptNote findOne(int id);

	long countAll();

	void save(GoodsReceiptNoteRequestVO goodsReceiptNoteRequestVO);

	void delete(int id);

	Iterable<GoodsReceiptNote> find5GoodsReceiptNoteLatest();

}
