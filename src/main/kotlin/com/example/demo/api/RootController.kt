package com.example.demo.api

import com.example.demo.data.ShoppingService
import com.example.demo.resource.Product
import com.fasterxml.jackson.annotation.JsonInclude
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.mediatype.hal.HalModelBuilder
import org.springframework.hateoas.server.core.Relation
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val API_PATH = ""

@RestController
@RequestMapping(value = [API_PATH])
class RootController(
        @Autowired
        private var shoppingService: ShoppingService
) {
    private val logger: Logger = LoggerFactory.getLogger(RootController::class.java);

    @GetMapping()
    fun index(): HttpEntity<RepresentationModel<IndexResource>> {
        val model = HalModelBuilder.halModel()
                .link(WebMvcLinkBuilder.linkTo(RootController::class.java).withSelfRel())
                .link(WebMvcLinkBuilder.linkTo(CartController::class.java).withRel("dm:cart"))

        if (shoppingService.hasItemsInCart()) {
            model.link(WebMvcLinkBuilder.linkTo(CheckoutController::class.java).withRel("dm:checkout"))
        }

        if (shoppingService.hasPendingOrders()) {
            model.link(WebMvcLinkBuilder.linkTo(OrderController::class.java).withRel("dm:order"))
        }

        shoppingService.getProducts().forEach {
            val productLink = linkTo<ProductController> { show(it.id) }
            val product = Product(it.id, it.name)

            product.add(productLink.withSelfRel())
            model.embed(product)
        }


        return ResponseEntity(model.build(), HttpStatus.OK)
    }
}

@Relation(IanaLinkRelations.INDEX_VALUE)
@JsonInclude(JsonInclude.Include.NON_NULL)
class IndexResource : RepresentationModel<IndexResource>()
