package com.example.bliblioteca.controller;
import com.example.bliblioteca.model.Autor;
import org.springframework.ui.Model;
import com.example.bliblioteca.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @GetMapping
    public String inicio(){
        return "livros/inicio";
    }

    @GetMapping("/autores")
    public String autores() {
        return "livros/autores";
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
    @GetMapping("/autor")
    public String exibirAutor(
            Model model) {
        Autor autor =  new Autor();
        autor.setNome("Machado de Assis");
        autor.setNacionalidade("Brasileiro");
        autor.setObrasPublicadas("978-85-00-00000-0");

        model.addAttribute("autor", autor);
        return "autor";
    }
}
