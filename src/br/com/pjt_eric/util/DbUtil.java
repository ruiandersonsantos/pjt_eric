/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pjt_eric.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rui
 */
public class DbUtil {

    public static boolean criarArquivoDeConexao(String caminhoBD) throws IOException {
        boolean retorno = false;
        String nomeArquivo = "C:\\Projeto_eric\\pjt_eric\\arquivos\\conect.con";
        Path path = Paths.get(nomeArquivo);

        Files.deleteIfExists(path);
        Files.createFile(path);
        Files.write(path, caminhoBD.getBytes());

        retorno = Files.exists(path);

        return retorno;
    }

    public static String lerArquivoDeConexao() throws IOException {
        String retorno = "";
        String nomeArquivo = "C:\\Projeto_eric\\pjt_eric\\arquivos\\conect.con";
        Path path = Paths.get(nomeArquivo);

        if (Files.exists(path)) {

            byte[] ret = null;
            try {
                ret = Files.readAllBytes(path);
            } catch (IOException ex) {
                Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            retorno = new String(ret);
        } else {
            JOptionPane.showMessageDialog(null, "Arquivo de conexão não encontrado!");
            String caminho = JOptionPane.showInputDialog("Informe o caminho do banco de dados");
            criarArquivoDeConexao(caminho);

            if (Files.exists(path)) {
                JOptionPane.showMessageDialog(null, "Arquivo de conexão criado com sucesso!");
                retorno = lerArquivoDeConexao();
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na criação do arquivo de conexão. Tente novamente!");
            }
        }

        return retorno;
    }

    public static String criptografaSenha(String senha) {
        String retorno = "";
        try {

            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
            retorno = new String(messageDigest);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

}
