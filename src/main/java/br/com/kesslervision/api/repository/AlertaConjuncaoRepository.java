package br.com.kesslervision.api.repository;

import br.com.kesslervision.api.model.AlertaConjuncao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaConjuncaoRepository extends JpaRepository<AlertaConjuncao, Long> {
    
}