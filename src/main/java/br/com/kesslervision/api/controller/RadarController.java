package br.com.kesslervision.api.controller;

import br.com.kesslervision.api.model.Satelite;
import br.com.kesslervision.api.repository.SateliteRepository;
import br.com.kesslervision.api.service.PythonIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/radar")
@CrossOrigin(origins = "*") // Permite o React acessar
public class RadarController {

    private final SateliteRepository sateliteRepository;
    private final PythonIntegrationService pythonService;

    public RadarController(SateliteRepository sateliteRepository, PythonIntegrationService pythonService) {
        this.sateliteRepository = sateliteRepository;
        this.pythonService = pythonService;
    }

    // O React vai chamar: GET http://localhost:8080/api/radar/varredura/1
    @GetMapping("/varredura/{idSatelite}")
    public ResponseEntity<?> executarVarredura(@PathVariable Long idSatelite) {
        
        // Busca o satélite no banco de dados
        Optional<Satelite> sateliteOpt = sateliteRepository.findById(idSatelite);
        
        if (sateliteOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro: Satélite não encontrado.");
        }

        Satelite satelite = sateliteOpt.get();

        // Chama o Python passando a altitude real do satélite
        String resultadoDaFisica = pythonService.solicitarAnaliseDeRisco(satelite.getAltitudeAtual());

        // Devolve a resposta do Python direto para o React
        return ResponseEntity.ok(resultadoDaFisica);
    }
}