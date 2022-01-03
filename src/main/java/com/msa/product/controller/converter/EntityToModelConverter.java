package com.msa.product.controller.converter;

import com.msa.product.controller.IndexController;
import com.msa.product.link.LinkList;
import com.msa.product.domain.Product;
import com.msa.product.handler.ErrorHolder;
import com.msa.product.handler.ErrorResponse;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static com.msa.product.util.PageLimitNormalizer.normalize;

@Component
public class EntityToModelConverter {

    // create, update 이후 노출
    public EntityModel<Product> toModel(Product entity) {
        return EntityModel.of(entity
                , getDetailLink(entity.getId())
                , getPagingLink(0, 0, "list"));
    }

    // detail 페이지에서 노출
    public EntityModel<Product> toModel(Product entity, int offset, int limit) {
        Long id = entity.getId();
        return EntityModel.of(entity
                , getDetailLink(id)
                , getPagingLink(offset, limit, "list")
                , getDetailOrderLink(id));
    }

    // list 페이지에서 products의 링크 표현
    public EntityModel<Product> toModelWithPage(Product entity, int offset, int limit) {
        return EntityModel.of(entity,
                linkTo(methodOn(IndexController.class).productDetailWithPageInfo(entity.getId(), offset
                        , normalize(limit))).withSelfRel(), getPagingLink(offset, limit, "list"));
    }

    // delete 이후 노출
    public EntityModel getListLink() {
        return EntityModel.of(getPagingLink(0, 0, "list"));
    }

    // error 코드와 list 링크를 함께 표현
    public EntityModel<ErrorHolder> getListLink(ErrorResponse error, String details) {
        return EntityModel.of(new ErrorHolder(error, details), getPagingLink(0, 0, "list"));
    }

    // list 페이지에서 페이징 링크 표현
    public CollectionModel<EntityModel<Product>> toCollectionModel(List<EntityModel<Product>> products,
                                                                   Page<Product> pagedProduct, int offset, int limit) {
        CollectionModel<EntityModel<Product>> model = CollectionModel.of(products,
                linkTo(methodOn(IndexController.class).pagingProducts(offset, normalize(limit))).withSelfRel());

        if(pagedProduct.hasPrevious()) {
            model.add(getPagingLink(0, limit, "first"));
            model.add(getPagingLink(offset-1, limit, "previous"));
        }
        if(pagedProduct.hasNext()) {
            model.add(getPagingLink(offset+1, limit, "next"));
            model.add(getPagingLink(pagedProduct.getTotalPages()-1, limit, "last"));
        }
        return model;
    }

    private Link getPagingLink(int offset, int limit, String rel) {
        return linkTo(methodOn(IndexController.class).pagingProducts(offset, normalize(limit))).withRel(rel);
    }

    private Link getDetailLink(Long id) {
        return linkTo(methodOn(IndexController.class).productDetail(id)).withSelfRel();
    }

    private Link getDetailOrderLink(Long id) {
        return Link.of(LinkList.checkOut.getLink() + id).withRel("check_out");
    }
}
