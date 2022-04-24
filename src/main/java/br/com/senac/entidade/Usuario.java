package br.com.senac.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuario")
@NoArgsConstructor

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 60)
    private String login;

    @Column(nullable = false, length = 30)
    private String senha;

    @Column(nullable = false)
    private Double saldo;

    private LocalDate ultimoAcesso;

    public Usuario(Long id, String nome, String login, String senha, Double saldo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.saldo = saldo;
    }




    
}
