package com.invillia.acme.controller;

import com.invillia.acme.dto.StoreDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface StoreController {

    public ResponseEntity<?> create(@RequestBody @Valid StoreDTO storeDTO, HttpServletResponse response);

    public ResponseEntity<?> getStoreById(@PathVariable Long id);

    public ResponseEntity<?> getStoreByParameters(StoreDTO storeDTO, @PageableDefault(size = 15, page = 0) Pageable pageable);

    public ResponseEntity<?> update(@RequestBody @Valid StoreDTO storeDTO, @PathVariable Long id, HttpServletResponse response);

}
