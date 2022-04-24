/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.senac.dao;

import br.com.senac.entidade.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Pericleitonrasta
 */
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Long> implements UsuarioDao {

    @Override
    public Usuario pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.get(Usuario.class, id);
    }

    @Override
    public List<Usuario> pesquisarTodos(Session sessao) throws HibernateException {
        Query<Usuario> consulta = sessao.createQuery("Select u from Usuario u");
        return consulta.getResultList();
    }

    @Override
    public List<Usuario> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query<Usuario> consulta = sessao.createQuery("from Usuario u where u.nome like :nomeHql");
        consulta.setParameter("nomeHql", "%" + nome + "%");
        return consulta.getResultList();
    }
    
}
