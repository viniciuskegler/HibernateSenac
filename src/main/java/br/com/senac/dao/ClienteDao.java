/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pericleitonrasta
 */
public interface ClienteDao extends BaseDao<Cliente, Long> {

    List<Cliente> pesquisarTodos(Session sessao) throws HibernateException;
    List<Cliente> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
}
