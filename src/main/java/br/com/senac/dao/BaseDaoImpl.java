package br.com.senac.dao;


import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.senac.dao.BaseDao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author Pericleitonrasta
 */
public abstract class BaseDaoImpl<T, ID> implements BaseDao<T, ID> {

    private Transaction transaction;

    @Override
    public void salvarOuAlterar(Object entidade, Session sessao) throws HibernateException {
        transaction = sessao.beginTransaction();
        sessao.saveOrUpdate(entidade);
        transaction.commit();
    }

    @Override
    public void excluir(Object entidade, Session sessao) throws HibernateException {
        transaction = sessao.beginTransaction();
        sessao.delete(entidade);
        transaction.commit();
    }

}
