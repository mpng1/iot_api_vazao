package br.com.pointer.sii.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pointer.sii.api.domain.modelomedidor.DadosAtualizacaoModeloMedidor;
import br.com.pointer.sii.api.domain.modelomedidor.DadosCadastroModeloMedidor;
import br.com.pointer.sii.api.domain.modelomedidor.DadosDetalhamentoModeloMedidor;
import br.com.pointer.sii.api.domain.modelomedidor.DadosListagemModeloMedidor;
import br.com.pointer.sii.api.domain.modelomedidor.ModeloMedidor;
import br.com.pointer.sii.api.domain.modelomedidor.ModeloMedidorRepository;
import br.com.pointer.sii.api.domain.modelomedidor.ModeloMedidorService;
import br.com.pointer.sii.api.domain.pontodecoleta.DadosCadastroPontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.DadosDetalhamentoPontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.DadosListagemPontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.PontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.PontoDeColetaRepository;
import br.com.pointer.sii.api.domain.pontodecoleta.PontoDeColetaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("modelomedidor")
public class ModeloMedidorController {
	
	@Autowired
	private ModeloMedidorRepository repository;
	
	@Autowired
	private ModeloMedidorService modeloMedidorService;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroModeloMedidor dados, UriComponentsBuilder uriBuilder) {
		var modelomedidor = new ModeloMedidor(dados);
		
		repository.save(modelomedidor);
		
		var uri = uriBuilder.path("/modelomedidor/{id}").buildAndExpand(modelomedidor.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoModeloMedidor(modelomedidor));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemModeloMedidor>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemModeloMedidor::new);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoModeloMedidor dados) {
		var modelomedidor = repository.getReferenceById(dados.id());
		modelomedidor.atualizarInformacoes(dados);
		
		modeloMedidorService.updateMedicaoValorLitrosAndValorMetrosCubicos();
		
		return ResponseEntity.ok(new DadosDetalhamentoModeloMedidor(modelomedidor));
	}
	
	@PutMapping("/troca")
	@Transactional
	public ResponseEntity atualizarSemAlterar(@RequestBody @Valid DadosAtualizacaoModeloMedidor dados) {
		var modelomedidor = repository.getReferenceById(dados.id());
		modelomedidor.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoModeloMedidor(modelomedidor));
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		var modelomedidor = repository.getReferenceById(id);
		modelomedidor.excluir();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var modelomedidor = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoModeloMedidor(modelomedidor));
	}
}
