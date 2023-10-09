package br.edu.ifal.mpng1.api.domain.pontodecoleta;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoDeColetaRepository extends JpaRepository<PontoDeColeta, Long> {

	Page<PontoDeColeta> findAllByAtivoTrue(Pageable paginacao);

	Optional<PontoDeColeta> findById(PontoDeColeta ponto_de_coleta_id);
	
}
