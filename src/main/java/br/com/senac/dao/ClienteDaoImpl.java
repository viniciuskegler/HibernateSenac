/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Pericleitonrasta
 */
public class ClienteDaoImpl extends BaseDaoImpl<Cliente, Long> implements ClienteDao {

    @Override
    public Cliente pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.get(Cliente.class, id);
    }

    @Override
    public List<Cliente> pesquisarTodos(Session sessao) throws HibernateException {
        Query<Cliente> consulta = sessao.createQuery("Select c from Cliente c");
        return consulta.getResultList();
    }

    @Override
    public List<Cliente> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query<Cliente> consulta = sessao.createQuery("from Cliente c where c.nome like :nomeHql");
        consulta.setParameter("nomeHql", "%" + nome + "%");
        return consulta.getResultList();
    }

}
