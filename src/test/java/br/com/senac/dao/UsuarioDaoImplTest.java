/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.senac.dao;

import br.com.senac.entidade.Usuario;
import br.com.senac.util.GeradorUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pericleitonrasta
 */
public class UsuarioDaoImplTest {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private Session sessao;

    public UsuarioDaoImplTest() {
        usuarioDao = new UsuarioDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("salvar");
        usuario = new Usuario(null, GeradorUtil.gerarNomeCompleto(),
                GeradorUtil.gerarLogin(), GeradorUtil.gerarSenha(5), Double.valueOf(GeradorUtil.gerarNumero(5)));
        sessao = HibernateUtil.abrirConexao();
        usuarioDao.salvarOuAlterar(usuario, sessao);
        sessao.close();
        assertNotNull(usuario.getId());
    }

    @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarUsuarioBD();
        usuario.setNome(GeradorUtil.gerarNomeCompleto());
        usuario.setLogin(GeradorUtil.gerarLogin());
        sessao = HibernateUtil.abrirConexao();
        usuarioDao.salvarOuAlterar(usuario, sessao);
        sessao.close();
        sessao = HibernateUtil.abrirConexao();
        Usuario usuarioAlterado = usuarioDao.pesquisarPorId(usuario.getId(), sessao);
        sessao.close();
        assertEquals(usuario.getNome(), usuarioAlterado.getNome());
        assertEquals(usuario.getLogin(), usuarioAlterado.getLogin());
    }

    @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarUsuarioBD();
        sessao = HibernateUtil.abrirConexao();
        usuarioDao.excluir(usuario, sessao);
        Usuario usuarioExcluido = usuarioDao.pesquisarPorId(usuario.getId(), sessao);
        assertNull(usuarioExcluido);
    }

    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisadPorId");
        buscarUsuarioBD();
        sessao = HibernateUtil.abrirConexao();
        Usuario usuarioPesquisado = usuarioDao.pesquisarPorId(usuario.getId(), sessao);
        assertNotNull(usuarioPesquisado);
    }
    
    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisadPorNome");
        buscarUsuarioBD();
        sessao = HibernateUtil.abrirConexao();
        String nome = separarNomes(usuario.getNome());
        List<Usuario> usuarios = usuarioDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        assertFalse(usuarios.isEmpty());
    }
    
    @Test
    public void TestPesquisaTodos() {
        System.out.println("pesquisarTodos");
        buscarUsuarioBD();
        sessao = HibernateUtil.abrirConexao();
        List<Usuario> usuarios = usuarioDao.pesquisarTodos(sessao);
        sessao.close();
        assertFalse(usuarios.isEmpty());
    }

    private Usuario buscarUsuarioBD() {
        String hql = "from Usuario";
        sessao = HibernateUtil.abrirConexao();
        Query<Usuario> consulta = sessao.createQuery(hql);
        List<Usuario> usuarios = consulta.list();
        sessao.close();
        if (usuarios.isEmpty()) {
            testSalvar();
        } else {
            usuario = usuarios.get(0);
        }
        return usuario;
    }

    private String separarNomes(String nome) {
        int index = nome.indexOf(" ");
        return nome.substring(0, index);
    }

}
