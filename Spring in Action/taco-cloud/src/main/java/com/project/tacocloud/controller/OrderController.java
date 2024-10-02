package com.project.tacocloud.controller;

import com.project.tacocloud.data.OrderRepository;
import com.project.tacocloud.model.TacoOrder;
import com.project.tacocloud.model.TacoUser;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(
        @AuthenticationPrincipal TacoUser user, @ModelAttribute TacoOrder order
    ) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullName());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
        @Valid TacoOrder order, Errors errors, SessionStatus sessionStatus,
        @AuthenticationPrincipal TacoUser user
    ) {
        if (errors.hasErrors()) return "orderForm";

        order.setTacoUser(user);
        orderRepository.save(order);

        sessionStatus.setComplete();
        return "redirect:/";
    }
}
