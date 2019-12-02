package com.invillia.acme.service;

import com.invillia.acme.dto.OrderDTO;
import com.invillia.acme.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public Order save(Order order);

    public Optional<Order> findById(Long id);

    public List<Order> findPaginated(OrderDTO orderDTO);
}
