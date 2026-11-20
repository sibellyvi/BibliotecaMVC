
package com.example.bliblioteca.controller;

import com.example.bliblioteca.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private List<Livro> livros = DadosBiblioteca.livros;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", livros);
        model.addAttribute("autores", DadosBiblioteca.autores);
        model.addAttribute("categorias", DadosBiblioteca.categorias);
        model.addAttribute("livro", new Livro());
        model.addAttribute("indice", null);
        return "livros/inicio";
    }

    @PostMapping
    public String cadastrar(@RequestParam String titulo,
                            @RequestParam int autor,
                            @RequestParam int categoria,
                            @RequestParam String isbn) {

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(DadosBiblioteca.autores.get(autor));
        livro.setCategoria(DadosBiblioteca.categorias.get(categoria));
        livro.setIsbn(isbn);

        livros.add(livro);

        return "redirect:/livros";
    }

    @GetMapping("/editar/{indice}")
    public String editar(@PathVariable int indice, Model model) {
        model.addAttribute("livros", livros);
        model.addAttribute("autores", DadosBiblioteca.autores);
        model.addAttribute("categorias", DadosBiblioteca.categorias);
        model.addAttribute("livro", livros.get(indice));
        model.addAttribute("indice", indice);
        return "livros/inicio";
    }

    @PostMapping("/editar/{indice}")
    public String atualizar(@PathVariable int indice,
                            @RequestParam String titulo,
                            @RequestParam int autor,
                            @RequestParam int categoria,
                            @RequestParam String isbn) {

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(DadosBiblioteca.autores.get(autor));
        livro.setCategoria(DadosBiblioteca.categorias.get(categoria));
        livro.setIsbn(isbn);

        livros.set(indice, livro);

        return "redirect:/livros";
    }

    @DeleteMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        if (indice >= 0 && indice < livros.size()) {
            livros.remove(indice);
        }
        return "redirect:/livros";
    }
}
