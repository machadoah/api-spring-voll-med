package com.medium.machadoah.api.medico;

import com.medium.machadoah.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(MedicoRegistroDTO medicoRegistroDTO) {
        this.nome = medicoRegistroDTO.nome();
        this.email = medicoRegistroDTO.email();
        this.telefone = medicoRegistroDTO.telefone();
        this.crm = medicoRegistroDTO.crm();
        this.especialidade = medicoRegistroDTO.especialidade();
        this.endereco = new Endereco(medicoRegistroDTO.endereco());
    }
}
