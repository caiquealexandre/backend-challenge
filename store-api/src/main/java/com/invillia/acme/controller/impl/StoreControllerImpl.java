package com.invillia.acme.controller.impl;

import com.invillia.acme.controller.StoreController;
import com.invillia.acme.dto.CreateStoreDTO;
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
import java.util.List;
import java.util.Optional;

@Api(value = "ACME - Store Rest API")
@RestController
@RequestMapping(value = "/api/store")
public class StoreControllerImpl implements StoreController {

    @Autowired
    private StoreService service;

    @Override
    @ApiOperation(value = "Cadastro de Store")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid CreateStoreDTO dto, HttpServletResponse response) {
        Store store = new Store();
        this.copyProperties(dto, store);
        service.save(store);

        return ResponseEntity.status(HttpStatus.CREATED).body(store.toStoreDTO());
    }

    @Override
    @ApiOperation(value = "Consultar Store por ID", notes = "Exemplo para consumo: /store/22 (/store/{id})")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getStoreById(Long id) {
        return service.findById(id).map(store -> ResponseEntity.ok(store.toStoreDTO())).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @ApiOperation(value = "Consultar Store por parâmetros")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getStoreByParameters(StoreDTO storeDTO) {
        List<Store> listResult = service.findPaginated(storeDTO);
        return !listResult.isEmpty() ? new ResponseEntity<>(listResult, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @Override
    @ApiOperation(value = "Atualizar Store")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid CreateStoreDTO dto, @PathVariable Long id, HttpServletResponse response) {

        Optional<Store> optional = service.findById(id);

        if(!optional.isPresent()) {
            throw new NoResultException("A loja informada não foi encontrada.");
        }

        Store store = optional.get();
        this.copyProperties(dto, store);
        service.save(store);

        return ResponseEntity.status(HttpStatus.OK).body(store.toStoreDTO());
    }

    private void copyProperties(CreateStoreDTO dto, Store store) {
        BeanUtils.copyProperties(dto, store);
    }

}
