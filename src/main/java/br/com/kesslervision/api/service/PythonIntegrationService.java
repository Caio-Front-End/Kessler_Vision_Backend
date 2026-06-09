package br.com.kesslervision.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PythonIntegrationService {

    // URL do script Python
    private final String PYTHON_API_URL = "http://localhost:8000/api/analisar";

    public String solicitarAnaliseDeRisco(Double altitudeSatelite) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            // Monta a URL
            String url = PYTHON_API_URL + "?altitude=" + altitudeSatelite;

            // Faz um GET para o Python e guarda a resposta (que será uma String JSON)
            String respostaDoPython = restTemplate.getForObject(url, String.class);
            
            return respostaDoPython;
            
        } catch (Exception e) {
            System.out.println("Erro ao contactar o Python: " + e.getMessage());
            return "{\"erro\": \"Motor de física (Python) offline ou inacessível.\"}";
        }
    }
}