package com.shop.order.service;

import com.shop.order.config.CustomerClient;
import com.shop.order.config.ProductClient;
import com.shop.order.exception.BusinessException;
import com.shop.order.mapper.OrderMapper;
import com.shop.order.payload.rq.OrderLineRequest;
import com.shop.order.payload.rq.OrderRequest;
import com.shop.order.payload.rq.PurchaseRequest;
import com.shop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public Integer createdOrder(OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exist provider ID"));

        this.productClient.purchaseProducts(orderRequest.products());

        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest: orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        return null;
    }
}
