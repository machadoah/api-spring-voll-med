package com.medium.machadoah.api.controller;

import com.medium.machadoah.api.medico.Medico;
import com.medium.machadoah.api.medico.MedicoListagemDTO;
import com.medium.machadoah.api.medico.MedicoRegistroDTO;
import com.medium.machadoah.api.medico.MedicoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoRegistroDTO medicoRegistroDTO) {
        repository.save(new Medico(medicoRegistroDTO));
    }

    @GetMapping
    public Page<MedicoListagemDTO> listar(Pageable pageable) {
        // http://localhost:8080/medicos?size=1
        // parameter size defines the number of elements per page
        return repository.findAll(pageable).map(MedicoListagemDTO::new);
    }
}
