package com.medium.machadoah.api.controller;

import com.medium.machadoah.api.medico.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoRepository repository;

    @PostMapping
    @Transactional // Transactional é necessário para o Spring gerenciar a transação com o banco de dados
    public void cadastrar(@RequestBody @Valid MedicoRegistroDTO medicoRegistroDTO) {
        repository.save(new Medico(medicoRegistroDTO));
    }

    @GetMapping
    public Page<MedicoListagemDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        // return repository.findAll(pageable).map(MedicoListagemDTO::new);
        return repository.findAllByAtivoTrue(pageable).map(MedicoListagemDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoAtualizacaoDTO medicoAtualizacaoDTO) {
        var medico = repository.getReferenceById(medicoAtualizacaoDTO.id());
        medico.atualizarInformacoes(medicoAtualizacaoDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        // repository.deleteById(id);
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }


}
