/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pjt_eric.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rui
 */
public class Conexao implements Serializable {

    private static final long serialVersionUID = 1L;

    private Connection cx = null;

    public Connection GetConnection() throws IOException {
        if (cx == null) {

            //String filename = "C:/Projeto_eric/bd/banco.accdb";
            String filename = "";
            filename = DbUtil.lerArquivoDeConexao();

            File arquivo = new File(filename);

            // Verifica se o arquivo não existe.
            if (!arquivo.exists()) {
                JOptionPane.showMessageDialog(null, "Banco de dados não encontrado!");
            } else {

                String database = "jdbc:ucanaccess://" + filename.trim();

                System.out.println(database);
                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    // Realiza a conexão com o banco de dados
                    cx = DriverManager.getConnection(database);
                } catch (SQLException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (cx != null) {
                    JOptionPane.showMessageDialog(null, "Conectado!");
                }

            }

        }

        return cx;
    }

    public Statement getPreparedStatement(String sql) throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException {

        return GetConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

    }

    public void closeAll() throws SQLException {
        System.out.println("Fechou a conexao!");
        if (cx != null) {
            cx.close();
        }

    }

}
