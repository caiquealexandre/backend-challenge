package com.invillia.acme.service;

import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    public Store save(Store store);

    public Optional<Store> findById(Long id);

    public List<Store> findPaginated(StoreDTO storeDTO);

}
