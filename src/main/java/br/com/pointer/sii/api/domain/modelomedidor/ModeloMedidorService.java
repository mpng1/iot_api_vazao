package br.com.pointer.sii.api.domain.modelomedidor;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ModeloMedidorService {
	
	@PersistenceContext
	EntityManager entityManager; 
	
	@Transactional
	public void updateMedicaoValorLitrosAndValorMetrosCubicos() {
        String updateQuery = "UPDATE medicao " +
                "SET valor_litros = m.valor / mm.fator_impulso, " +
                "valor_metros_cubicos = (m.valor / mm.fator_impulso) * 0.001 " +
                "FROM medicao m " +
                "JOIN ponto_de_coleta pc ON m.ponto_de_coleta_id = pc.id " +
                "JOIN modelo_medidor mm ON pc.modelo_medidor_id = mm.id " +
                "WHERE medicao.id = m.id";

       
        entityManager.createNativeQuery(updateQuery).executeUpdate();
    }

}
