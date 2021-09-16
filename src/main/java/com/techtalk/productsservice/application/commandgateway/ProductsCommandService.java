package com.techtalk.productsservice.application.commandgateway;

import com.techtalk.productsservice.domain.commands.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class ProductsCommandService {

    private final CommandGateway commandGateway;

    public ProductsCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public String createProduct(CreateProductCommand createProductCommand){
        commandGateway.sendAndWait(createProductCommand);

        return createProductCommand.getProductId();
    }
}
