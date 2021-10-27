package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {
        private PersonRepository repo= new PersonRepository();

        @GetMapping("/index")
        public String getAll(Model model) {

            model.addAttribute("persons", repo.findAll());

            return "index";
        }

        @GetMapping("/person")
        public String getPerson(Model model,
                                @RequestParam(required = false) Long id) {

            Person person = new Person();
            if (id != null) {
                person = repo.findById(id);
            }
            model.addAttribute("person", person);

            return "person";
        }

        @PostMapping("/person")
        public String postPerson(@ModelAttribute Person person) {

        if (person.getId() != null) {
            repo.update(person);
        } else {
            repo.save(person);
        }
            return "redirect:/index";
        }

    @GetMapping("/person/delete")
    public String deletePerson(@RequestParam Long id) {

        repo.deleteById(id);

        return "redirect:/index";
    }
    }

