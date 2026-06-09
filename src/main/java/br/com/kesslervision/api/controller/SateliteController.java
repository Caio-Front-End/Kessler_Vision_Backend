package br.com.kesslervision.api.controller;

import br.com.kesslervision.api.model.Satelite;
import br.com.kesslervision.api.repository.SateliteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/satelites")
@CrossOrigin(origins = "*")
public class SateliteController {

    private final SateliteRepository repository;

    public SateliteController(SateliteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Satelite cadastrar(@RequestBody Satelite satelite) {
        return repository.save(satelite);
    }

    @GetMapping
    public List<Satelite> listar() {
        return repository.findAll();
    }
}