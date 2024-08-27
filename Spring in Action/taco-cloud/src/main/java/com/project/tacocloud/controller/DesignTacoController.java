package com.project.tacocloud.controller;

import com.project.tacocloud.data.IngredientRepository;
import com.project.tacocloud.model.Ingredient;
import com.project.tacocloud.model.Ingredient.Type;
import com.project.tacocloud.model.Taco;
import com.project.tacocloud.model.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(
                type.toString().toLowerCase(),
                filterByType(ingredients, type)
            );
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(
        @Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder
    ) {
        if (errors.hasErrors()) return "design";

        tacoOrder.addTacos(taco);
        log.info("Processing taco : {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
        List<Ingredient> ingredients, Type type
    ) {
        return ingredients.stream()
            .filter(x -> x.getType().equals(type))
            .toList();
    }
}










