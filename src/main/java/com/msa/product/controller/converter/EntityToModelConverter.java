package com.msa.product.controller.converter;

import com.msa.product.controller.IndexController;
import com.msa.product.domain.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EntityToModelConverter implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product entity) {
        return EntityModel.of(entity);
    }

    public EntityModel<Product> toModel(Product entity, int offset, int limit) {
        return EntityModel.of(entity,
                linkTo(methodOn(IndexController.class).productDetail(entity.getId(), offset, limit)).withSelfRel(),
                linkTo(methodOn(IndexController.class).pagingProducts(offset, limit)).withRel("list"));
    }
}
