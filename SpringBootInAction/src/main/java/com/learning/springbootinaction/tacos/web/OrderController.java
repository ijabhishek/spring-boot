package com.learning.springbootinaction.tacos.web;


import com.learning.springbootinaction.tacos.Taco;
import com.learning.springbootinaction.tacos.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "orderForm";
        }

        log.info("Order submitted: {}" , order);
        sessionStatus.setComplete();
        return "redirect:/";



    }

    @GetMapping("/delete")
    public String deleteTaco(@RequestParam("index") int index, Model model) {
        TacoOrder tacoOrder = (TacoOrder) model.getAttribute("tacoOrder");
        List<Taco> tacos = tacoOrder.getTacos();

        if (index >= 0 && index < tacos.size()) {
            Taco deletedTaco = tacos.remove(index);
            log.info("Deleted taco: {}", deletedTaco);

            if (tacos.isEmpty()) {
                return "redirect:/design";
            }
        }

        return "redirect:/orders/current";
    }


}


