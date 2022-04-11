/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author silvio.junior
 */
public class GeradorUtil {

    public static String gerarNumero(int qtde) {
        String numeroGerado = "";
        int indice;
        for (int i = 0; i < qtde; i++) {
            indice = (int) (Math.random() * 10);
            numeroGerado += indice;
            //numeroGerado = numeroGerado + indice;
        }
        return numeroGerado;
    }

    public static String gerarCpf() throws ParseException {
        MaskFormatter mascara = new MaskFormatter("###.###.###-##");
        mascara.setValueContainsLiteralCharacters(false);
        return mascara.valueToString(gerarNumero(11));
    }

    public static String gerarCnpj() throws ParseException {
        MaskFormatter mascara = new MaskFormatter("##.###.###/####-##");
        mascara.setValueContainsLiteralCharacters(false);
        return mascara.valueToString(gerarNumero(14));
    }

    public static String gerarTelefoneFixo() {
        return "(48)3" + gerarNumero(3) + "-" + gerarNumero(4);
    }

    public static String gerarCelular() {
        return "(48)9" + gerarNumero(4) + "-" + gerarNumero(4);
    }

    public static String gerarCep() {
        return gerarNumero(5) + "-" + gerarNumero(3);
    }

    public static String gerarNome() {
        List<String> nomes = Arrays.asList("Junior", "Marcos", "Ana", "Maria", "Silvio", "Suelen", "Joana", "Mateus",
                "Lúcio", "João", "Leandro", "Soeli");
        Collections.shuffle(nomes);
        return nomes.get(0);
    }

    private static String gerarSobrenome() {
        List<String> sobreNomes = Arrays.asList("Pereira", "Oliveira", "Antunes", "da Silva", "Santos", "Rocha", "Moura",
                "Dias", "Mendes", "Albino", "Dutra", "Mendonça");
        Collections.shuffle(sobreNomes);
        return sobreNomes.get(0);
    }

    public static String gerarNomeCompleto() {
        return (gerarNome() + " " + gerarSobrenome());
    }

    public static String gerarCidade() {
        String[] cidades = {"São José", "Palhoça", "Florianópolis", "Criciuma", "Chapecó", "Curitiba",
            "Porto Alegre", "São Paulo", "Máceio", "Biguaçú", "Belo Horizonte", "Pinhais"};
        int indice = (int) (Math.random() * cidades.length);
        return cidades[indice];
    }

    public static String gerarLogin() {
        String nome = gerarNome();
        return nome.toLowerCase() + "@";
    }

    public static String gerarSenha(int qtde) {
        String[] letras = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0",
            "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int indice;
        String senha = "";
        for (int i = 0; i < qtde; i++) {
            indice = (int) (Math.random() * letras.length);
            senha += letras[indice];
        }
        return senha;

    }

    public static BigDecimal gerarSalario(int numeros, int decimais) {
        return new BigDecimal(gerarNumero(numeros)).setScale(decimais);
        //setScale é a quantidade de decimais.
    }

    public static void main(String[] args) {
//        int res = JOptionPane.showOptionDialog(null, "Hello", "Test", JOptionPane.DEFAULT_OPTION,
//                JOptionPane.INFORMATION_MESSAGE, null, null, null);
//        int questao = JOptionPane.showOptionDialog(cmpnt, res, string, res, res, icon, args, res);
//        int aa = JOptionPane.showop
//        System.out.println(res);
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Quanto deseja adicionar?").replace(",", "."));
        System.out.println(valor);
    }
}
