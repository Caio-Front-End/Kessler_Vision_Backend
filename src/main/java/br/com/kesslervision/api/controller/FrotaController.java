package br.com.kesslervision.api.controller;

import br.com.kesslervision.api.model.Frota;
import br.com.kesslervision.api.repository.FrotaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frotas")
@CrossOrigin(origins = "*")
public class FrotaController {

    private final FrotaRepository repository;

    public FrotaController(FrotaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Frota cadastrar(@RequestBody Frota frota) {
        return repository.save(frota);
    }

    @GetMapping
    public List<Frota> listar() {
        return repository.findAll();
    }
}