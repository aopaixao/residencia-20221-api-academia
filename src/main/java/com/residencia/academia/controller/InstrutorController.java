package com.residencia.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.exception.InstrutorNotFoundException;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.InstrutorService;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	@Autowired
	InstrutorService instrutorService;

	@GetMapping
	public ResponseEntity<List<Instrutor>> findAllInstrutor() {
		List<Instrutor> instrutorList = instrutorService.findAllInstrutor();
		return new ResponseEntity<>(instrutorList, HttpStatus.OK);
	}

	@GetMapping("/dto/{id}")
	public ResponseEntity<InstrutorDTO> findInstrutorDTOById(@PathVariable Integer id) {
		InstrutorDTO instrutorDTO = instrutorService.findInstrutorDTOById(id);
		return new ResponseEntity<>(instrutorDTO, HttpStatus.OK);
	}
	
	/*
	//Validacao simples com o IF
	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> findInstrutorById(@PathVariable Integer id) {
		Instrutor instrutor = instrutorService.findInstrutorById(id);
		if(null == instrutor)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(instrutor, HttpStatus.OK);
	}
	*/

	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> findInstrutorById(@PathVariable Integer id) {
		Instrutor instrutor = instrutorService.findInstrutorById(id);
		if(null == instrutor)
			throw new NoSuchElementFoundException("Não foi encontrado Instrutor com o id " + id);
		else
			return new ResponseEntity<>(instrutor, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Instrutor> saveInstrutor(@Valid @RequestBody Instrutor instrutor) {
		Instrutor novoInstrutor = instrutorService.saveInstrutor(instrutor);
		return new ResponseEntity<>(novoInstrutor, HttpStatus.CREATED);
	}

	@PostMapping("/dto")
	public ResponseEntity<InstrutorDTO> saveInstrutorDTO(@RequestBody InstrutorDTO instrutorDTO) {
		InstrutorDTO novoInstrutorDTO = instrutorService.saveInstrutorDTO(instrutorDTO);
		return new ResponseEntity<>(novoInstrutorDTO, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Instrutor> updateInstrutor(@RequestBody Instrutor instrutor) {
		Instrutor novoInstrutor = instrutorService.updateInstrutor(instrutor);
		return new ResponseEntity<>(novoInstrutor, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInstrutor(@PathVariable Integer id) {
		if(null == instrutorService.findInstrutorById(id))
			throw new InstrutorNotFoundException("Não foi encontrado Instrutor com o id " + id);
		
		instrutorService.deleteInstrutor(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/*
	//Tratamento Local de Exceção
	@ExceptionHandler(InstrutorNotFoundException.class)
	public ResponseEntity<String> handleNoInstrutorFoundException(InstrutorNotFoundException exception) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(exception.getErrMsg());
	}
	*/

}
