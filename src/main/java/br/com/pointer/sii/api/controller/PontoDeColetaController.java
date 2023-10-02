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

import br.com.pointer.sii.api.domain.pontodecoleta.DadosAtualizacaoPontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.DadosCadastroPontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.DadosDetalhamentoPontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.DadosListagemPontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.PontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.PontoDeColetaRepository;
import br.com.pointer.sii.api.domain.pontodecoleta.PontoDeColetaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pontodecoleta")
public class PontoDeColetaController {
	
	@Autowired
	private PontoDeColetaRepository repository;
	
	@Autowired
	private PontoDeColetaService pontoDeColetaService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPontoDeColeta dados, UriComponentsBuilder uriBuilder) {
		
		var pontodecoleta = new PontoDeColeta(dados);
		
		repository.save(pontodecoleta);
		
		var uri = uriBuilder.path("/pontodecoleta/{id}").buildAndExpand(pontodecoleta.getId()).toUri();
		
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPontoDeColeta(pontodecoleta));
		
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPontoDeColeta>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPontoDeColeta::new);
		return ResponseEntity.ok(page);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPontoDeColeta dados) {
		var pontodecoleta = repository.getReferenceById(dados.id());
		pontodecoleta.atualizarInformacoes(dados);
		
		pontoDeColetaService.updateMedicaoValorLitrosAndValorMetrosCubicos();
		
		return ResponseEntity.ok(new DadosDetalhamentoPontoDeColeta(pontodecoleta));
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/troca")
	@Transactional
	public ResponseEntity atualizarSemAlterar(@RequestBody @Valid DadosAtualizacaoPontoDeColeta dados) {
		var pontodecoleta = repository.getReferenceById(dados.id());
		pontodecoleta.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoPontoDeColeta(pontodecoleta));
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		var pontodecoleta = repository.getReferenceById(id);
		pontodecoleta.excluir();
		return ResponseEntity.noContent().build();
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var pontodecoleta = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoPontoDeColeta(pontodecoleta));
	}
		
}


