package view;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;


import dao.TopicoDAO;
import dto.TopicoDTO;
import pub_sub.Publisher;

public class EnviarMensagem extends JFrame{


	private JPanel contentPane;
	private JTextField textValor;
	private JComboBox jcombobox;
	//objeto para executar sql de insert no bd
	TopicoDAO topicoDAO = new TopicoDAO();
	//cria objeto do tipo TopicoDTO com o nome do topico passado
	TopicoDTO topicoDTO;
	
	/**
	 * Create the frame.
	 */
	public EnviarMensagem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tópico:");
		lblNewLabel.setBounds(12, 15, 322, 15);
		contentPane.add(lblNewLabel);
		
		jcombobox = new JComboBox();
		/////////////////JComboBox recebe dados do BD, tabela topicos
		try {
			for(TopicoDTO t: topicoDAO.listaTopicos()) {
				jcombobox.addItem(t.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jcombobox.setBounds(12, 42, 426, 19);
		contentPane.add(jcombobox);
		//jcombobox.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(12, 70, 322, 15);
		contentPane.add(lblValor);

		textValor = new JTextField("0");
		textValor.setBounds(12, 85, 426, 19);
		contentPane.add(textValor);
		textValor.setColumns(10);
		
		JLabel lblTexto = new JLabel("Texto:");
		lblTexto.setBounds(12, 120, 322, 15);
		contentPane.add(lblTexto);
		
		JTextArea area = new JTextArea();
		area.setText("Coloque seu texto aqui");
		//area.setBorder(new EmptyBorder(20, 20, 20, 20));
		//area.setBounds(12, 140, 426, 40);

		JScrollPane jScrollPane = new JScrollPane(area); //jTextArea dentro do JScrollPane
		///Rectangle(int x, int y, int width, int height)
		jScrollPane.setBounds(new Rectangle(53, 140, 361, 80)); // tamanho do jScrollPane
		jScrollPane.setVerticalScrollBarPolicy(jScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // só mostra a barra vertical se necessário
		jScrollPane.setHorizontalScrollBarPolicy(jScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // nunca mostra a barra de rolagem horizontal
		area.setWrapStyleWord(true);
		area.setLineWrap(true); // quebra a linha
		//area.getText();
			
        contentPane.add(jScrollPane);			
		
		JButton btnEnviarMensagem = new JButton("Enivar Mensagem");
		
		btnEnviarMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String topicoSelecionado = (String) jcombobox.getSelectedItem();
				
				
				//chamada para validar campos para aceitarem apenas número
				if( ValidarCampoNumerico(textValor) ) { 
					System.out.println(textValor.getText() + " " + 
							topicoSelecionado + " " +
							area.getText()
							);

					//monta a string que será enviada para o broker
					String mensagem =  
							textValor.getText()  + " " + 
							area.getText();
					
					//instancia o objeto para publicar as mensagens
					Publisher publisher = new Publisher(topicoSelecionado, mensagem );
					
					//tratamento para envio de mensagem
					try {
						//chama método da classe Publihser para enviar a mensagem
						publisher.enviarMensagem();	
					}catch(Exception e) {
						//imprime exceção no console
						//System.out.println(e);
					}					
					
				}				
				
				
				/*
				//exibe resultado na tela
				JOptionPane.showMessageDialog(null, topicoSelecionado + " " + 
						textField.getText() + " " + 
						area.getText(),"Tópico cadastrado",
						JOptionPane.INFORMATION_MESSAGE);	
						*/
				
				Main frame = new Main();
				frame.setVisible(true);
				dispose();				
				
			}
		});		

		btnEnviarMensagem.setBounds(242, 230, 170, 25);
		contentPane.add(btnEnviarMensagem);

		
		
		JButton btnCancelar = new JButton("Cancelar");
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main frame = new Main();
				frame.setVisible(true);
				dispose();
			}
		});				
		
		btnCancelar.setBounds(53, 230, 114, 25);
		contentPane.add(btnCancelar);
	}	
		
	//Método para validar JTextField como número
	public boolean ValidarCampoNumerico(JTextField TextoCampo) {
		Double valor;
		if (TextoCampo.getText().length() != 0){
			try {
				valor = Double.parseDouble(TextoCampo.getText());
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "Digite um valor válido!" ,"Erro de valor",
						JOptionPane.INFORMATION_MESSAGE);
				TextoCampo.grabFocus();
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(null, "Digite um número" ,"Erro de valor",
					JOptionPane.INFORMATION_MESSAGE);
			TextoCampo.grabFocus();			
			return false;
		}
		return true;
	}		
	
}
