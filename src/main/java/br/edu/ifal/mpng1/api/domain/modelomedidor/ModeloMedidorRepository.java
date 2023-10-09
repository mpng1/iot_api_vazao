package br.edu.ifal.mpng1.api.domain.modelomedidor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloMedidorRepository extends JpaRepository<ModeloMedidor, Long> {

	Page<ModeloMedidor> findAllByAtivoTrue(Pageable paginacao);

}
