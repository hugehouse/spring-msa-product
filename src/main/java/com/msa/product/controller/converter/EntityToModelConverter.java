package com.msa.product.controller.converter;

import com.msa.product.controller.IndexController;
import com.msa.product.domain.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;

@Component
public class EntityToModelConverter implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product entity) {
        return EntityModel.of(entity);
    }

//    public EntityModel<Product> toModelWithPage(Product entity, int offset, int limit) {
//        return EntityModel.of(entity,
//                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(IndexController.class).productDetail(entity.getId(), offset, limit)).withSelfRel(),
//                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(IndexController.class).pagingProducts(offset, limit)).withRel("list"));
//    }
}
