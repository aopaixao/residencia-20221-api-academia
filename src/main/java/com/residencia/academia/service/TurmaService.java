package com.residencia.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.TurmaRepository;

@Service
public class TurmaService {
	@Autowired
	TurmaRepository turmaRepository;

	public List<Turma> findAllTurma() {
		return turmaRepository.findAll();
	}

	public Turma findTurmaById(Integer id) {
		return turmaRepository.findById(id).isPresent() ?
			turmaRepository.findById(id).get() :
				null;
	}

	public Turma findTurmaByIdSemVerificacao(Integer id) {
		return turmaRepository.findById(id).get();
	}
	
	public Turma saveTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public Turma updateTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public void deleteTurma(Integer id) {
		turmaRepository.deleteById(id);
	}
	/*
	public Boolean deleteTurmaComConferencia(Integer id) {
		if(turmaRepository.findById(id).isPresent()) {
			turmaRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	*/
	
}
