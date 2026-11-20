package com.example.bliblioteca.controller;

import com.example.bliblioteca.model.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private List<Autor> autores = DadosBiblioteca.autores;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autores", autores);
        model.addAttribute("autor", new Autor());
        model.addAttribute("indice", null);
        return "autores/inicio";
    }

    @PostMapping
    public String cadastrar(@ModelAttribute Autor autor) {
        autores.add(autor);
        return "redirect:/autores";
    }

    @GetMapping("/editar/{indice}")
    public String editar(@PathVariable int indice, Model model) {
        model.addAttribute("autores", autores);
        model.addAttribute("autor", autores.get(indice));
        model.addAttribute("indice", indice);
        return "autores/inicio";
    }

    @PostMapping("/editar/{indice}")
    public String atualizar(@PathVariable int indice, @ModelAttribute Autor autor) {
        autores.set(indice, autor);
        return "redirect:/autores";
    }

    @DeleteMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        if (indice >= 0 && indice < autores.size()) {
            autores.remove(indice);
        }
        return "redirect:/autores";
    }
}