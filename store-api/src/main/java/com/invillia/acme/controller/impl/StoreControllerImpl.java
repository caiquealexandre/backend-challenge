package com.invillia.acme.controller.impl;

import com.invillia.acme.controller.StoreController;
import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.entity.Store;
import com.invillia.acme.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Api(value = "ACME - Store API")
@RestController
@RequestMapping(value = "/api/store")
public class StoreControllerImpl implements StoreController {

    @Autowired
    private StoreService service;

    @Override
    @ApiOperation(value = "Serviço de cadastro de Store")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid StoreDTO storeDTO, HttpServletResponse response) {
        Store store = new Store();
        this.copyProperties(storeDTO, store);
        service.save(store);

        return ResponseEntity.status(HttpStatus.CREATED).body(store.toStoreDTO());
    }

    @Override
    @ApiOperation(value = "Consultar Store por ID", response = Store.class, notes = "Retorna o Store a partir do ID", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getStoreById(Long id) {
        return service.findById(id).map(store -> ResponseEntity.ok(store.toStoreDTO())).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getStoreByParameters(StoreDTO storeDTO, Pageable pageable) {
        Page<Store> pageResult = service.findPaginated(storeDTO, pageable);

        if(pageResult == null) {
            throw new NoResultException("There is no Store with the informed data.");
        }
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid StoreDTO storeDTO, @PathVariable Long id, HttpServletResponse response) {

        Optional<Store> optional = service.findById(id);

        if(optional == null) {
            throw new NoResultException("There is no Store with the informed data.");
        }

        Store store = optional.get();
        this.copyProperties(storeDTO, store);
        service.save(store);

        return ResponseEntity.status(HttpStatus.CREATED).body(store.toStoreDTO());
    }

    private void copyProperties(StoreDTO dto, Store store) {
        BeanUtils.copyProperties(dto, store);
    }

}
