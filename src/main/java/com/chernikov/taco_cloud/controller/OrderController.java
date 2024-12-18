package com.chernikov.taco_cloud.controller;

import ch.qos.logback.core.model.Model;
import com.chernikov.taco_cloud.data.Taco;
import com.chernikov.taco_cloud.data.TacoOrder;
import com.chernikov.taco_cloud.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@Slf4j
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            @Valid TacoOrder tacoOrder, Errors errors,
            SessionStatus sessionStatus) {

        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepository.save(tacoOrder);
        //log.info("Order submitted: {}", tacoOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
