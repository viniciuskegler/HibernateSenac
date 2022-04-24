/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.util.GeradorUtil;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pericleitonrasta
 */
public class ClienteDaoImplTest {

    private Cliente cliente;
    private ClienteDao clienteDao;
    private Session sessao;

    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("salvar");
        try {
            cliente = new Cliente(null, GeradorUtil.gerarNomeCompleto(), GeradorUtil.gerarLogin(), GeradorUtil.gerarCpf(), GeradorUtil.gerarRg());
        } catch (ParseException ex) {
            System.out.println("Erro ao salvar cliente:" + ex.getMessage());
        }
        sessao = HibernateUtil.abrirConexao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        sessao.close();
        assertNotNull(cliente.getId());
    }

    @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarUsuarioBD();
        cliente.setNome(GeradorUtil.gerarNomeCompleto());
        cliente.setEmail(GeradorUtil.gerarLogin());
        sessao = HibernateUtil.abrirConexao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        sessao.close();
        sessao = HibernateUtil.abrirConexao();
        Cliente clienteAlterado = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        sessao.close();
        assertEquals(cliente.getNome(), clienteAlterado.getNome());
        assertEquals(cliente.getEmail(), clienteAlterado.getEmail());
    }

    @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarUsuarioBD();
        sessao = HibernateUtil.abrirConexao();
        clienteDao.excluir(cliente, sessao);
        Cliente clienteExcluido = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        assertNull(clienteExcluido);
    }

    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisadPorId");
        buscarUsuarioBD();
        sessao = HibernateUtil.abrirConexao();
        Cliente clientePesquisado = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        assertNotNull(clientePesquisado);
    }

    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisadPorNome");
        buscarUsuarioBD();
        sessao = HibernateUtil.abrirConexao();
        String nome = separarNomes(cliente.getNome());
        List<Cliente> clientes = clienteDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        assertFalse(clientes.isEmpty());
    }
    
    @Test
    public void TestPesquisaTodos() {
        System.out.println("pesquisarTodos");
        buscarUsuarioBD();
        sessao = HibernateUtil.abrirConexao();
        List<Cliente> clientes = clienteDao.pesquisarTodos(sessao);
        sessao.close();
        assertFalse(clientes.isEmpty());
    }

    private Cliente buscarUsuarioBD() {
        String hql = "from Cliente";
        sessao = HibernateUtil.abrirConexao();
        Query<Cliente> consulta = sessao.createQuery(hql);
        List<Cliente> clientes = consulta.list();
        sessao.close();
        if (clientes.isEmpty()) {
            testSalvar();
        } else {
            cliente = clientes.get(0);
        }
        return cliente;
    }

    private String separarNomes(String nome) {
        int index = nome.indexOf(" ");
        return nome.substring(0, index);
    }

}
