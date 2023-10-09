package br.edu.ifal.mpng1.api.domain.medicao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicaoRepository extends JpaRepository<Medicao, Long> {
	
	Medicao findByPontoDeColetaId(Long pontoDeColetaId);

}
