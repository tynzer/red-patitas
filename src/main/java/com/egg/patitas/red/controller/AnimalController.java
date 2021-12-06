package com.egg.patitas.red.controller;
import com.egg.patitas.red.model.Animal;
import com.egg.patitas.red.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
    @RequestMapping("/animals")
    public class AnimalController {

        @Autowired
        AnimalService animalService;



            @GetMapping
            public ModelAndView showAll(HttpServletRequest request) {

                ModelAndView mav = new ModelAndView("animals");
                Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);


                try {
                    if (flashMap != null) {
//                mav.addObject("successful", flashMap.get("successful"));
                        mav.addObject("error", flashMap.get("error"));
                    }
                    mav.addObject("animals", animalService.listAnimal());
                } catch (Exception e) {
                    mav.addObject("error", e.getMessage());
                }
                return mav;
            }





        @GetMapping("/create")
        public ModelAndView createAnimal() {
            ModelAndView mav = new ModelAndView("animal-form");

         mav.addObject("animal", new Animal());
            mav.addObject("title", "Crear Animal");
            mav.addObject("action", "save");
            return mav;
        }

        @PostMapping("/save")
        public RedirectView save(@RequestParam String name, RedirectAttributes attributes) {
                try {
                    animalService.createAnimal(name);
                    return new RedirectView("/animals");
                } catch (Exception e) {
                    attributes.addFlashAttribute("error", e.getMessage());
                    return new RedirectView("/animals");        }}




        // aca en adelante revisar


        //         redirectView.setUrl("/animals/create");


        @PostMapping("/delete/{id}")

        public RedirectView deleteAnimal(@PathVariable Integer id , RedirectAttributes attributes)  {

            try {
                animalService.deleteAnimal(id);
                attributes.addFlashAttribute("successful","Se borro el animal");
                return new RedirectView ("animals/list");
            } catch (Exception e) {
                attributes.addFlashAttribute("error", e.getMessage());
                return new RedirectView("/animals/list");
            }
        }

     //   @GetMapping("/modify/{id}")
      //  public RedirectView modifyAnimal(@PathVariable Integer id , @RequestParam String name  ) {
     //       Animal animal = animalService.modifyAnimal(id);
     //       modelo.addAttribute("libro", libro);
     //       return "modif-libro";
     //   }



    }


