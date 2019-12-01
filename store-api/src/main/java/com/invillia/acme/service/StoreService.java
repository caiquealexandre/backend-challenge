package com.invillia.acme.service;

import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StoreService {

    public Store save(Store store);

    public Optional<Store> findById(Long id);

    public Page<Store> findPaginated(StoreDTO storeDTO, Pageable pageable);

}
