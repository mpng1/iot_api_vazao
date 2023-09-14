package br.com.pointer.sii.api.domain.pontodecoleta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoDeColetaRepository extends JpaRepository<PontoDeColeta, Long> {

	Page<PontoDeColeta> findAllByAtivoTrue(Pageable paginacao);
	
}
