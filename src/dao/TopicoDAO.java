package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import configs.ConexaoUtil;
import dto.TopicoDTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

//classe para executar os comandos SQL 
public class TopicoDAO {
	
	//inserir topicos no BD
	public void insert(TopicoDTO topicoDTO) {
		
		try {
			// ativa conexão com BD
			Connection connection = ConexaoUtil.getInstance().getConnection();
			// código sql a ser executado
			// o ? será trocado, em tempo de execução, pelo valor a ser inserido no BD
			String sql = "INSERT INTO topicos (topico) VALUES (?)";
			// realiza uma ponte entre o java e o BD
			PreparedStatement statement = connection.prepareStatement(sql);
			// faz a alteração do ? da variavel sql para o valor a ser passado para o insert
			// primeiro parâmetro indica qual o ponto de interrogação será alterado (1 é o
			// primeiro, 2 é o segundo...)
			// segundo parâmetro é o valor a ser inserido
			statement.setString(1, topicoDTO.getTopico());
			// Executar o comando sql com os devidos valores
			statement.execute();
			// fechar conexao com bd
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Recupera lista de tópicos no BD
    public List<TopicoDTO> listaTopicos() throws ClassNotFoundException, SQLException{

		// ativa conexão com BD
		Connection connection = ConexaoUtil.getInstance().getConnection();
        
		PreparedStatement statement = null;
        ResultSet rs = null;

        List<TopicoDTO> topicos = new ArrayList<>();

        try {
        	
			String sql = "SELECT * FROM topicos";
			// realiza uma ponte entre o java e o BD
			statement = connection.prepareStatement(sql);        	
        	
            //stmt = connection.prepareStatement("SELECT * FROM topicos");
            rs = statement.executeQuery();

            while (rs.next()) {

            	TopicoDTO topico = new TopicoDTO();
            		
            	//recupera valores de acordo com as colunas do BD
            	topico.setId(rs.getInt("id"));
            	topico.setTopico(rs.getString("topico"));
            	//adiciona o topico na lista de topicos
            	topicos.add(topico);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TopicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //ConnectionFactory.closeConnection(con, stmt, rs);
        	statement.close();
        }

        return topicos;

    }	
	

}
