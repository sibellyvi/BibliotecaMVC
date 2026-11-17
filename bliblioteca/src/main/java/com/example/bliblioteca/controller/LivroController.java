package com.example.bliblioteca.controller;
import com.example.bliblioteca.model.Autor;
import org.springframework.ui.Model;
import com.example.bliblioteca.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private List<Livro> livros = new ArrayList<>();


    @GetMapping
    public String inicio(){
        return "livros/inicio";
    }

    @GetMapping("/livros")
    public String exibirLivro(
            Model model) {
        Livro livro =  new Livro();
        livro.setTitulo("Dom Casmurro");
        livro.setAutor("Machado de Assis");
        livro.setIsbn("978-85-00-00000-0");

        model.addAttribute("livro", livro);
        return "livro";
    }

    @PostMapping
    public String cadastrar(@ModelAttribute Livro livro){
        livros.add(livro);
        return "redirect:/livros";
    }

}
