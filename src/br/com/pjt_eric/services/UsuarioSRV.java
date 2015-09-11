/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pjt_eric.services;

import br.com.pjt_eric.dao.UsuarioDAO;
import br.com.pjt_eric.model.Usuario;
import br.com.pjt_eric.util.Conexao;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author Rui
 */
public class UsuarioSRV {
    
    public static int inserirUsuario(Usuario usr, Connection con) throws IOException{
        
        int retorno = 0;
        
        UsuarioDAO dao = new UsuarioDAO();
        retorno = dao.inserirUsuario(usr, con);
        
        return retorno;
        
    }
    
    public static Usuario efetuaLogin(Connection con, String login, String senha){
        
        UsuarioDAO dao = new UsuarioDAO();
        return dao.efetuarLogin(con, login, senha);
        
    }
    
}
