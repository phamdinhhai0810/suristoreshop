package com.suristore.shop.service.impl;

import com.suristore.shop.domain.Type;
import com.suristore.shop.repo.TypeRepository;
import com.suristore.shop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Type> findAll() {
        return typeRepository.findAll(new Sort(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    @Transactional(readOnly = true)
    public Type findOne(int id) {
        return typeRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countAll() {
        return typeRepository.count();
    }

    @Override
    @Transactional
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    @Transactional
    public void delete(int id) {
        typeRepository.delete(id);
    }
}
