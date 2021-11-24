package com.projeto.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.model.Funcionario;
import com.projeto.repository.FuncionarioRepository;

@RestController
public class ControllerFuncionario {
	@Autowired
	private FuncionarioRepository repositorio;

	@RequestMapping(value = "/funcionario", method = RequestMethod.GET)
	public List<Funcionario> Get() {
		return repositorio.findAll();
	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
	public ResponseEntity<Funcionario> GetById(@PathVariable long id) {
		Optional<Funcionario> funcionario = repositorio.findById(id);
		if (funcionario.isPresent()) {
			return new ResponseEntity<Funcionario>(funcionario.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/funcionario", method = RequestMethod.POST)
	public Funcionario Post(@Valid @RequestBody Funcionario funcionario) {
		return repositorio.save(funcionario);
	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Funcionario> Put(@PathVariable(value = "id") long id,
			@Valid @RequestBody Funcionario newFuncionario) {
		Optional<Funcionario> oldFuncionario = repositorio.findById(id);
		if (oldFuncionario.isPresent()) {
			Funcionario funcionario = oldFuncionario.get();
			funcionario.setNome(newFuncionario.getNome());
			repositorio.save(funcionario);
			return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> DeleteById(@PathVariable(value = "id") long id) {
		Optional<Funcionario> funcionario = repositorio.findById(id);
		if (funcionario.isPresent()) {
			repositorio.delete(funcionario.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping
	public void DeleteAll(@RequestBody Funcionario funcionario) {
		repositorio.delete(funcionario);
	}
}
