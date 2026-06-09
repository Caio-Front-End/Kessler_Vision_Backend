package br.com.kesslervision.api.controller;

import br.com.kesslervision.api.model.AlertaConjuncao;
import br.com.kesslervision.api.model.Satelite;
import br.com.kesslervision.api.repository.AlertaConjuncaoRepository;
import br.com.kesslervision.api.repository.SateliteRepository;
import br.com.kesslervision.api.service.PythonIntegrationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/radar")
@CrossOrigin(origins = "*") // Permite o React acessar
public class RadarController {

    private final SateliteRepository sateliteRepository;
    private final PythonIntegrationService pythonService;
    private final AlertaConjuncaoRepository alertaRepository; 

    public RadarController(SateliteRepository sateliteRepository, 
                           PythonIntegrationService pythonService,
                           AlertaConjuncaoRepository alertaRepository) {
        this.sateliteRepository = sateliteRepository;
        this.pythonService = pythonService;
        this.alertaRepository = alertaRepository;
    }

    @GetMapping("/varredura/{idSatelite}")
    public ResponseEntity<?> executarVarredura(@PathVariable Long idSatelite) {
        
        // Busca o satélite no banco
        Optional<Satelite> sateliteOpt = sateliteRepository.findById(idSatelite);
        if (sateliteOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro: Satélite não encontrado.");
        }

        Satelite satelite = sateliteOpt.get();

        // Chama o Python para fazer o cálculo
        String resultadoJsonDoPython = pythonService.solicitarAnaliseDeRisco(satelite.getAltitudeAtual());

        // Desserializa o JSON e salva no banco de dados se houver risco
        try {
            // ObjectMapper converte a String JSON num objeto que o Java consegue navegar
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(resultadoJsonDoPython);

            String nivelAlerta = jsonNode.path("nivelAlerta").asText();

            // Só registramos auditoria para eventos críticos ou de atenção
            if ("VERMELHO".equals(nivelAlerta) || "AMARELO".equals(nivelAlerta)) {
                Double probabilidadeReal = jsonNode.path("probabilidadeImpacto").asDouble();
                
                AlertaConjuncao novoAlerta = new AlertaConjuncao();
                novoAlerta.setSatelite(satelite);
                novoAlerta.setNivelAlerta(nivelAlerta);
                novoAlerta.setProbabilidade(probabilidadeReal); // Pegando o valor dinâmico gerado pelo algoritmo
                novoAlerta.setDataGeracao(LocalDateTime.now());
                
                // Como o Python atualmente não devolve o NORAD específico da ameaça, 
                // preenchemos com o ID genérico da nuvem do Cosmos-2251 para satisfazer o NOT NULL do banco.
                novoAlerta.setCdNoradDetrito(22675); 

                // Salva no PostgreSQL
                alertaRepository.save(novoAlerta);
                System.out.println("⚠️ Alerta " + nivelAlerta + " gravado no banco com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("Erro ao interpretar ou salvar o alerta: " + e.getMessage());
        }

        // Devolve o JSON original intacto para o Frontend mostrar na tela
        return ResponseEntity.ok(resultadoJsonDoPython);
    }
}