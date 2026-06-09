package br.com.kesslervision.api.controller;

import br.com.kesslervision.api.model.Empresa;
import br.com.kesslervision.api.repository.EmpresaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*") // Permite que o React converse com o Java (porta 5173)
public class EmpresaController {

    private final EmpresaRepository repository;

    // Injeção de dependência via construtor
    public EmpresaController(EmpresaRepository repository) {
        this.repository = repository;
    }

    // Entrada de dados via Corpo da Requisição (JSON)
    @PostMapping
    public Empresa cadastrar(@RequestBody Empresa empresa) {
        return repository.save(empresa);
    }

    // Saída de dados (Retorna a lista de empresas salvas)
    @GetMapping
    public List<Empresa> listar() {
        return repository.findAll();
    }
}