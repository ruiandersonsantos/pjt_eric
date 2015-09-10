/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pjt_eric.dao;

import br.com.pjt_eric.model.Usuario;
import br.com.pjt_eric.util.Conexao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui
 */
public class UsuarioDAO {

    public int inserirUsuario(Usuario usr, Connection con) throws IOException {

        int retorno = 0;

        try {

            PreparedStatement ps;

            ps = (PreparedStatement) con.prepareStatement("insert into usuario (login, senha, nome)values(?, ?, ?)");

            ps.setString(1, usr.getLogin());
            ps.setString(2, usr.getSenha());
            ps.setString(3, usr.getNome());

            //Executando o camando no banco
            ps.executeUpdate();

            // pegando a chave do registro inserido
            ResultSet retornoDoID = ps.getGeneratedKeys();

            // Verificando se houve retorno e atrinuido a variavel de retorno.
            if (retornoDoID.next()) {
                retorno = retornoDoID.getInt(1);
            }

            ps.close();
            retornoDoID.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

}
