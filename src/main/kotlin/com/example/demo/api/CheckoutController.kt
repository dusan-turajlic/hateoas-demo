package com.example.demo.api

import com.example.demo.data.ShoppingService
import com.example.demo.resource.Payment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.mediatype.hal.HalModelBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

const val CHECKOUT_PATH = "$API_PATH/checkout"

@RestController
@RequestMapping(value = [CHECKOUT_PATH])
class CheckoutController(
        @Autowired
        private var shoppingService: ShoppingService
) {
    @GetMapping()
    fun index(): HttpEntity<*> {
        val model = HalModelBuilder.halModel()
                .link(WebMvcLinkBuilder.linkTo(RootController::class.java).withRel(IanaLinkRelations.INDEX))
                .link(WebMvcLinkBuilder.linkTo(CheckoutController::class.java).withRel("dm:cancel"))

        model.embed(listOf("paypal", "creditcard").map {
            Payment(it).add(WebMvcLinkBuilder.linkTo(CheckoutController::class.java).slash(it).withSelfRel())
        })


        return ResponseEntity(model.build<Payment>(), HttpStatus.OK)
    }

    @DeleteMapping()
    fun destroy(): HttpEntity<*> {
        shoppingService.clearCart()
        return ResponseEntity("Checkout canceled", HttpStatus.OK)
    }

    @PostMapping("/{payment}")
    fun store(): HttpEntity<*> {
        shoppingService.purchaseSuccess()

        return ResponseEntity("Payment Success", HttpStatus.ACCEPTED)
    }
}