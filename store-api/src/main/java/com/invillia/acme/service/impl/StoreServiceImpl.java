package com.invillia.acme.service.impl;

import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.entity.Store;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository repository;

    public static String NAME_ATRIBUTE_ORDER = "name";

    @Override
    public Store save(Store store) {
        return repository.saveAndFlush(store);
    }

    @Override
    public Optional<Store> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Store> findPaginated(StoreDTO storeDTO, Pageable pageable) {
        Sort sort = Sort.by(NAME_ATRIBUTE_ORDER).ascending();
        pageable.getSortOr(sort);
        return repository.findByParameters(storeDTO.getName(), storeDTO.getStreet(), storeDTO.getCity(), storeDTO.getZipCode(), storeDTO.getState(), pageable);
    }


}
