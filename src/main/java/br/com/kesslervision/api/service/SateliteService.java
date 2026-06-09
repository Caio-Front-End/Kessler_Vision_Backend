package br.com.kesslervision.api.service;

import br.com.kesslervision.api.model.Satelite;
import br.com.kesslervision.api.repository.FrotaRepository;
import br.com.kesslervision.api.repository.SateliteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica ao Spring que esta classe contém regras de negócio
public class SateliteService {

    private final SateliteRepository sateliteRepository;
    private final FrotaRepository frotaRepository;

    public SateliteService(SateliteRepository sateliteRepository, FrotaRepository frotaRepository) {
        this.sateliteRepository = sateliteRepository;
        this.frotaRepository = frotaRepository;
    }

    public Satelite cadastrarSatelite(Satelite satelite) {
        //Validação de Relacionamento
        if (satelite.getFrota() == null || satelite.getFrota().getIdFrota() == null) {
            throw new IllegalArgumentException("Erro: O satélite tem de estar vinculado a uma frota válida.");
        }

        if (!frotaRepository.existsById(satelite.getFrota().getIdFrota())) {
            throw new IllegalArgumentException("Erro: A frota informada não existe na base de dados.");
        }

        //Validação de Duplicidade
        if (sateliteRepository.existsByCdNorad(satelite.getCdNorad())) {
            throw new IllegalArgumentException("Erro: Já existe um satélite registado com o NORAD " + satelite.getCdNorad());
        }

        //Manipulação de Strings
        if (satelite.getNomeSatelite() != null) {
            // Remove espaços em branco nas pontas e força tudo para MAIÚSCULAS
            satelite.setNomeSatelite(satelite.getNomeSatelite().trim().toUpperCase());
        }

        //Salvar na base de dados
        return sateliteRepository.save(satelite);
    }

    public List<Satelite> listarTodos() {
        return sateliteRepository.findAll();
    }

    // --- PREPARAÇÃO PARA A INTEGRAÇÃO COM PYTHON ---
    public String analisarRiscoDeColisao(Long idSatelite) {
        // Fará o HTTP GET para o nosso ficheiro Python!
        return "Em breve: O Python vai analisar o satélite " + idSatelite;
    }
}