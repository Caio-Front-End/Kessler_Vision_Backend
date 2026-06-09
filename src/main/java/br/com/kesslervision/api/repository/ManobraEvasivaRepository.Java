package br.com.kesslervision.api.repository;

import br.com.kesslervision.api.model.ManobraEvasiva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManobraEvasivaRepository extends JpaRepository<ManobraEvasiva, Long> {
    
}