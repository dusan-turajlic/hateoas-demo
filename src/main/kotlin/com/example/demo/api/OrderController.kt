package com.example.demo.api

import com.example.demo.data.ShoppingService
import com.example.demo.resource.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.mediatype.hal.HalModelBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

const val ORDERS_PATH = "$API_PATH/orders"

@RestController
@RequestMapping(value = [ORDERS_PATH])
class OrderController(
        @Autowired
        private var shoppingService: ShoppingService
) {
    @GetMapping()
    fun index(): HttpEntity<*> {
        val model = HalModelBuilder.halModel()
                .link(WebMvcLinkBuilder.linkTo(RootController::class.java).withRel(IanaLinkRelations.INDEX))

        shoppingService.getOrderedItems().map {
            Product(it.id, it.name)
        }.forEach {
            model.embed(it)
        }

        return ResponseEntity(model.build<Product>(), HttpStatus.OK)
    }

    @PostMapping(consumes = ["application/json"])
    fun store(@RequestBody product: Product): HttpEntity<*> {
        shoppingService.addToOrder(product)

        return ResponseEntity("${product.id} added to order", HttpStatus.CREATED)
    }

    @DeleteMapping
    fun destroy(): HttpEntity<*> {
        shoppingService.clearOrder()

        return ResponseEntity("Order canceled", HttpStatus.OK)
    }
}