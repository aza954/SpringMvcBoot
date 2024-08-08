package ru.aza954.springproject.SpringMvcBoot.Controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.aza954.springproject.SpringMvcBoot.models.Person;
import ru.aza954.springproject.SpringMvcBoot.services.PeopleService;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PeopleService peopleService;
//    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PeopleService peopleService) {
        this.peopleService = peopleService;
//        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people",peopleService.findAll());
        return "Person/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model){
        model.addAttribute("person",peopleService.findOne(id));
        System.out.println(peopleService.findOne(id));

       model.addAttribute("books",peopleService.findBooks(id));
        System.out.println(peopleService.findBooks(id));
        return "Person/show";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("person",new Person());
        return "Person/new";
    }
    @PostMapping
    public String createPerson(@Valid @ModelAttribute Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "Person/new";
        }
        peopleService.save(person);

        return "redirect:/people";
    }


    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String toedit(@PathVariable("id") int id,Model model){
        model.addAttribute("person",peopleService.findOne(id));
        return "Person/edit";
    }
    @PatchMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,@ModelAttribute Person person){
        peopleService.update(id,person);
        return "redirect:/people";
    }
}
