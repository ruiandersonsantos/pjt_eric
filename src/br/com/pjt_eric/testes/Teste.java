/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pjt_eric.testes;

import br.com.pjt_eric.model.Usuario;
import br.com.pjt_eric.services.UsuarioSRV;
import br.com.pjt_eric.util.Conexao;
import br.com.pjt_eric.util.DbUtil;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rui
 */
public class Teste {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

       //criaArquivoConexao();
        
       // lerArquivoConexao();
        
       inserirUsuario();
    }

    private static void lerArquivoConexao() throws IOException {
        System.out.println(DbUtil.lerArquivoDeConexao());
    }

    private static void criaArquivoConexao() throws HeadlessException {
     
        
        String caminhoBD = "C:/Projeto_eric/bd/banco.accdb";
        try {
            if(DbUtil.criarArquivoDeConexao(caminhoBD)){
                JOptionPane.showMessageDialog(null, "Arqui de conexão criado com sucesso!");
            }
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void inserirUsuario() throws IOException, SQLException, ClassNotFoundException {
        Conexao conect = new Conexao();
        Connection con = conect.GetConnection();
        Usuario usuario = new Usuario();

        usuario.setLogin("rui");
        usuario.setNome("Rui Anderson");
        usuario.setSenha("123");

        int chave = 0;
        chave = UsuarioSRV.inserirUsuario(usuario, con);

        if (chave > 0) {
            System.out.println("Usuario inserido com sucess!");
        }
    }

}
