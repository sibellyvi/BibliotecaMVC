package com.example.bliblioteca.controller;

import com.example.bliblioteca.model.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private List<Categoria> categorias = DadosBiblioteca.categorias;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("indice", null);
        return "categorias/inicio";
    }

    @PostMapping
    public String cadastrar(@ModelAttribute Categoria categoria) {
        categorias.add(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{indice}")
    public String editar(@PathVariable int indice, Model model) {
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoria", categorias.get(indice));
        model.addAttribute("indice", indice);
        return "categorias/inicio";
    }

    @PostMapping("/editar/{indice}")
    public String atualizar(@PathVariable int indice, @ModelAttribute Categoria categoria) {
        categorias.set(indice, categoria);
        return "redirect:/categorias";
    }

    @DeleteMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        if (indice >= 0 && indice < categorias.size()) {
            categorias.remove(indice);
        }
        return "redirect:/categorias";
    }
}