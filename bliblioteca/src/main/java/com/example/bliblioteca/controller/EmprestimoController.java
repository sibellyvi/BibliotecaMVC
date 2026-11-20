
package com.example.bliblioteca.controller;

import com.example.bliblioteca.model.Emprestimo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private List<Emprestimo> emprestimos = DadosBiblioteca.emprestimos;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimos);
        model.addAttribute("livros", DadosBiblioteca.livros);
        model.addAttribute("usuarios", DadosBiblioteca.usuarios);
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("indice", null);
        return "emprestimos/inicio";
    }

    @PostMapping
    public String cadastrar(@RequestParam String dataEmprestimo,
                            @RequestParam String dataPrevisaoDevolucao,
                            @RequestParam(required = false) String dataDevolucao,
                            @RequestParam int livro,
                            @RequestParam int usuario) {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataPrevisaoDevolucao(dataPrevisaoDevolucao);
        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setLivro(DadosBiblioteca.livros.get(livro));
        emprestimo.setUsuario(DadosBiblioteca.usuarios.get(usuario));

        emprestimos.add(emprestimo);

        return "redirect:/emprestimos";
    }

    @GetMapping("/editar/{indice}")
    public String editar(@PathVariable int indice, Model model) {
        model.addAttribute("emprestimos", emprestimos);
        model.addAttribute("livros", DadosBiblioteca.livros);
        model.addAttribute("usuarios", DadosBiblioteca.usuarios);
        model.addAttribute("emprestimo", emprestimos.get(indice));
        model.addAttribute("indice", indice);
        return "emprestimos/inicio";
    }

    @PostMapping("/editar/{indice}")
    public String atualizar(@PathVariable int indice,
                            @RequestParam String dataEmprestimo,
                            @RequestParam String dataPrevisaoDevolucao,
                            @RequestParam(required = false) String dataDevolucao,
                            @RequestParam int livro,
                            @RequestParam int usuario) {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataPrevisaoDevolucao(dataPrevisaoDevolucao);
        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setLivro(DadosBiblioteca.livros.get(livro));
        emprestimo.setUsuario(DadosBiblioteca.usuarios.get(usuario));

        emprestimos.set(indice, emprestimo);

        return "redirect:/emprestimos";
    }

    @DeleteMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        if (indice >= 0 && indice < emprestimos.size()) {
            emprestimos.remove(indice);
        }
        return "redirect:/emprestimos";
    }
}
