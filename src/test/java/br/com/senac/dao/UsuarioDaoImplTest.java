/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.senac.dao;

import br.com.senac.entidade.Usuario;
import br.com.senac.util.GeradorUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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

//    @Test
    public void testSalvar() {
        System.out.println("salvar");
        usuario = buscarUsuario();
        sessao = HibernateUtil.abrirConexao();
        usuarioDao.salvarOuAlterar(usuario, sessao);
        sessao.close();
        assertNotNull(usuario.getId());
    }

//    @Test
    public void testAlterar() {
        System.out.println("alterar");
        usuario = buscarUsuario();
        sessao = HibernateUtil.abrirConexao();
        usuarioDao.salvarOuAlterar(usuario, sessao);
        Usuario usuarioAlterado = sessao.find(Usuario.class, usuario.getId());
        assertEquals(usuario.getNome(), usuarioAlterado.getNome());

    }

    @Test
    public void testExcluir() {
        System.out.println("excluir");
        usuario = buscarUsuario();
        sessao = HibernateUtil.abrirConexao();
        usuarioDao.excluir(usuario, sessao);
        Usuario usuarioExcluido = sessao.find(Usuario.class, usuario.getId());
        assertNull(usuarioExcluido);
    }

    private Usuario buscarUsuario() {
        usuario = new Usuario(null,
                GeradorUtil.gerarNomeCompleto(),
                GeradorUtil.gerarLogin(),
                GeradorUtil.gerarSenha(5),
                Double.parseDouble(GeradorUtil.gerarNumero(4)));
        sessao = HibernateUtil.abrirConexao();
        usuarioDao.salvarOuAlterar(usuario, sessao);
        sessao.close();
        return usuario;
    }
}
