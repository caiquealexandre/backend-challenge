package com.invillia.acme.service.impl;

import com.invillia.acme.dto.OrderDTO;
import com.invillia.acme.entity.Order;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    public static String DATE_ATRIBUTE_ORDER = "confirmationDate";

    @Override
    public Order save(Order order) {
        return repository.saveAndFlush(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Order> findPaginated(OrderDTO orderDTO, Pageable pageable) {
        Sort sort = Sort.by(DATE_ATRIBUTE_ORDER).ascending();
        pageable.getSortOr(sort);
        return repository.findByParameters(orderDTO.getOrderStatus(), orderDTO.getStreet(), orderDTO.getZipCode(),
                                           orderDTO.getCity(), orderDTO.getState(), orderDTO.getIdStore(), pageable);
    }

}
