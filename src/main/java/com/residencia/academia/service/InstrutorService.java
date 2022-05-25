package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.InstrutorRepository;

@Service
public class InstrutorService {
	@Autowired
	InstrutorRepository instrutorRepository;
	
	@Autowired
	TurmaService turmaService;
	
	public List<Instrutor> findAllInstrutor(){
		return instrutorRepository.findAll();
	}
	
	public Instrutor findInstrutorById(Integer id) {
		return instrutorRepository.findById(id).isPresent() ?
				instrutorRepository.findById(id).get() : null;
	}

	/*
	//O metodo abaixo, qdo nao existir o instrutor com o id informado,
	//  disparara a excecao "NoSuchElementException: No value present.
	//  Essa excecao nao passara pelo tratamento especifico definido no CustomException.
	//  Ela sera recebida no tratamento geral, de erro 500, dentro do CustomException.
	public Instrutor findInstrutorById(Integer id) {
		return instrutorRepository.findById(id).get();
	}
	*/
	
	public InstrutorDTO findInstrutorDTOById(Integer id) {
		Instrutor instrutor = instrutorRepository.findById(id).isPresent() ?
				instrutorRepository.findById(id).get() : null;
		
		InstrutorDTO instrutorDTO = new InstrutorDTO();
		if(null != instrutor) {
			instrutorDTO = converterEntidadeParaDto(instrutor);
		}
		return instrutorDTO;
	}
	
	public Instrutor saveInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}
	
	public InstrutorDTO saveInstrutorDTO(InstrutorDTO instrutorDTO) {
			
		Instrutor instrutor = new Instrutor();
		
		instrutor.setIdInstrutor(instrutorDTO.getIdInstrutor());
		instrutor.setRgInstrutor(instrutorDTO.getRgInstrutor());
		instrutor.setNomeInstrutor(instrutorDTO.getNomeInstrutor());
		instrutor.setDataNascimento(instrutorDTO.getDataNascimento());
		instrutor.setTitulacaoInstrutor(instrutorDTO.getTitulacaoInstrutor());
		
		Instrutor novoInstrutor = instrutorRepository.save(instrutor);
		
		return converterEntidadeParaDto(novoInstrutor);
	}
	
	public Instrutor updateInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}
	
	public void deleteInstrutor(Integer id) {
		Instrutor inst = instrutorRepository.findById(id).get();
		instrutorRepository.delete(inst);
	}
	
	public void deleteInstrutor(Instrutor instrutor) {
		instrutorRepository.delete(instrutor);
	}
	
	private Instrutor convertDTOToEntidade(InstrutorDTO instrutorDTO){
		Instrutor instrutor = new Instrutor();
		
		instrutor.setIdInstrutor(instrutorDTO.getIdInstrutor());
		instrutor.setRgInstrutor(instrutorDTO.getRgInstrutor());
		instrutor.setNomeInstrutor(instrutorDTO.getNomeInstrutor());
		instrutor.setDataNascimento(instrutorDTO.getDataNascimento());
		instrutor.setTitulacaoInstrutor(instrutorDTO.getTitulacaoInstrutor());
		
		List<Turma> listTurma = new ArrayList<>();
		//TODO: Atentar para o fato de que a lista de turma pode estar vazia ou
		//  nao ter sido definida
		if(null != instrutorDTO.getTurmaDTOList()) {
			for(TurmaDTO turmaDTO : instrutorDTO.getTurmaDTOList()) {
				//Se for passado um id de turma, localiza e utiliza.
				//  Senao, cria uma nova (TODO)
				if(null != turmaDTO.getIdTurma()) {
					Turma turma = turmaService.findTurmaById(turmaDTO.getIdTurma());
					if(null != turma) {
						//turma.setDataFim(turmaDTO.getDataFim());
						//turma.setDataInicio(turmaDTO.getDataInicio());
						//turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
						//turma.setHorarioTurma(turmaDTO.getHorarioTurma());
						//turma.setIdTurma(turmaDTO.getIdTurma());
						
						//if(null != instrutorDTO.getIdInstrutor()) {
							//Instrutor instrutorBD = findInstrutorById(instrutorDTO.getIdInstrutor());
							//turma.setInstrutor(instrutorBD);
						//}
						
						listTurma.add(turma);						
					}
				}
				
				
			}
			instrutor.setTurmaList(listTurma);
		}
		
		return instrutor;
	}
		
	private InstrutorDTO converterEntidadeParaDto(Instrutor instrutor) {
		InstrutorDTO instrutorDTO = new InstrutorDTO();
		instrutorDTO.setDataNascimento(instrutor.getDataNascimento());
		instrutorDTO.setIdInstrutor(instrutor.getIdInstrutor());
		instrutorDTO.setNomeInstrutor(instrutor.getNomeInstrutor());
		instrutorDTO.setRgInstrutor(instrutor.getRgInstrutor());
		instrutorDTO.setTitulacaoInstrutor(instrutor.getTitulacaoInstrutor());
		
		List<TurmaDTO> listTurmaDTO = new ArrayList<>();
		//TODO: Atentar para o fato de que a lista de turma pode estar vazia ou
		//  nao ter sido definida
		if(null != instrutor.getTurmaList()) {
			for(Turma turma : instrutor.getTurmaList()) {
				TurmaDTO turmaDTO = new TurmaDTO();
				turmaDTO.setDataFim(turma.getDataFim());
				turmaDTO.setDataInicio(turma.getDataInicio());
				turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
				turmaDTO.setHorarioTurma(turma.getHorarioTurma());
				turmaDTO.setIdTurma(turma.getIdTurma());
				
				listTurmaDTO.add(turmaDTO);
			}
			instrutorDTO.setTurmaDTOList(listTurmaDTO);
		}
		
		return instrutorDTO;
	}
}
