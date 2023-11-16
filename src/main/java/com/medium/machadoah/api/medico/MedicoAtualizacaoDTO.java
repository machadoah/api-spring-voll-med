package com.medium.machadoah.api.medico;

import com.medium.machadoah.api.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record MedicoAtualizacaoDTO(
        @NotNull Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
