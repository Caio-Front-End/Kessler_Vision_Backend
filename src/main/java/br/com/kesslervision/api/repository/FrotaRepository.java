package br.com.kesslervision.api.repository;

import br.com.kesslervision.api.model.Frota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrotaRepository extends JpaRepository<Frota, Long> {
}