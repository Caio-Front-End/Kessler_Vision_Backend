package br.com.kesslervision.api.repository;

import br.com.kesslervision.api.model.Satelite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SateliteRepository extends JpaRepository<Satelite, Long> {
    // O Spring Boot cria a query (SELECT count(*) WHERE cd_norad = ?) automaticamente só de ler o nome do método!
    boolean existsByCdNorad(Integer cdNorad);
}