package com.techtalk.productsservice.interfaces.rest;

import com.techtalk.productsservice.application.commandgateway.ProductsCommandService;
import com.techtalk.productsservice.domain.commands.CreateProductCommand;
import com.techtalk.productsservice.interfaces.rest.exceptions.CreateProductControllerExceptionHandler;
import com.techtalk.productsservice.interfaces.rest.transform.assembler.CreateProductCommandDTOassembler;
import com.techtalk.productsservice.interfaces.rest.transform.dto.CreateProductRestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private ProductsCommandService productsCommandService;

    public ProductCommandController(ProductsCommandService productsCommandService) {
        this.productsCommandService = productsCommandService;
    }


    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody final CreateProductRestModel createProductRestModel,
                                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException(CreateProductControllerExceptionHandler.getListErrors(bindingResult).toString());
        }

        CreateProductCommand createProductCommand= CreateProductCommandDTOassembler.toCommandFromDTO(createProductRestModel);

        String productId= productsCommandService.createProduct(createProductCommand);

        return ResponseEntity
                    .created(URI.create("" + productId))
                    .body(productId);
    }
}
