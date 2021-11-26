package com.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.model.Produto;
import com.projeto.repository.ProdutoRepository;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@RequestMapping(value = "/produto", method = RequestMethod.GET)
	public List<Produto> Get() {

		return repository.findAll();

	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> GetById(@PathVariable long id) {

		Optional<Produto> produto = repository.findById(id);
		if (produto.isPresent()) {

			return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value = "/lucro", method = RequestMethod.GET)
	public float Lucro() {

		List<Produto> produtos = repository.findAll();
		float lucrototal = 0;

		for (int i = 0; i < produtos.size(); i++) {

			lucrototal += produtos.get(i).getLucro();

		}
		return lucrototal;

	}

	@RequestMapping(value = "/lucro/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> LucroById(@PathVariable long id) {

		// return repository.findById(id).get().getLucro();
		Optional<Produto> produto = repository.findById(id);

		if (produto.isPresent()) {

			return new ResponseEntity<>(produto.get().getLucro(), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value = "/produto", method = RequestMethod.POST)
	public void Post(@RequestBody Produto produto) {

		repository.save(produto);

	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Produto> Atualiza(@PathVariable long id, @RequestBody Produto produto) {
		Optional<Produto> oldproduto = repository.findById(id);

		if (oldproduto.isPresent()) {
			repository.findById(id).get().setNome(produto.getNome());
			repository.findById(id).get().setPreco_venda(produto.getPreco_venda());
			repository.findById(id).get().setPreco_compra(produto.getPreco_compra());
			repository.findById(id).get().setQuantidade(produto.getQuantidade());
			repository.findById(id).get().setVendidos(produto.getVendidos());

			repository.save(repository.findById(id).get());
			
			return new ResponseEntity<Produto>(oldproduto.get(), HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}

	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Deleta(@PathVariable long id) {

		Optional<Produto> produto = repository.findById(id);
		if(produto.isPresent()) {
			
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		}
		else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}

	}

}
