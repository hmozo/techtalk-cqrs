package com.techtalk.productsservice.interfaces.eventhandlers;

import com.techtalk.productsservice.application.commandgateway.ProductsEventService;
import com.techtalk.productsservice.domain.events.ProductCreatedEvent;
import com.techtalk.productsservice.domain.projection.ProductEntity;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventHandler {

    private final ProductsEventService productsEventService;

    public ProductEventHandler(ProductsEventService productsEventService) {
        this.productsEventService = productsEventService;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductEntity productEntity= new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);

        productsEventService.saveProduct(productEntity);
    }
}
