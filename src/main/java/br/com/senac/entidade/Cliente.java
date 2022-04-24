package br.com.senac.entidade;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 60, unique = true)
    private String email;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    public Cliente(Long id, String nome, String email, String cpf, String rg) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
    }

    
    
}
