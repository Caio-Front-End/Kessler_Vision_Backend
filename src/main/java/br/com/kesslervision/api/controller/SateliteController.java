package br.com.kesslervision.api.controller;

import br.com.kesslervision.api.model.Satelite;
import br.com.kesslervision.api.service.SateliteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/satelites")
@CrossOrigin(origins = "*")
public class SateliteController {

    private final SateliteService service; 

    public SateliteController(SateliteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Satelite satelite) {
        try {
            // Service faz as validações
            Satelite sateliteSalvo = service.cadastrarSatelite(satelite);
            return ResponseEntity.ok(sateliteSalvo);
        } catch (IllegalArgumentException e) {
            // Se o Service atirar um erro (ex: NORAD duplicado), devolve um erro 400 (Bad Request)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Satelite> listar() {
        return service.listarTodos();
    }
}