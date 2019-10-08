package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import configs.ConexaoUtil;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					
					//Conexão com o BD
					ConexaoUtil conexao = new ConexaoUtil();
					System.out.println(conexao.getInstance().getConnection());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Cadastrar Tópico");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//chama tela de cadastrar topicos
				CadastrarTopico frame = new CadastrarTopico();
				frame.setVisible(true);
				//fecha esta tela para deixar só a de cadastrar topicos aberta
				dispose();
			}
		});
		btnNewButton.setBounds(126, 148, 191, 25);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Enviar Mensagem");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//chama tela de cadastrar topicos
				EnviarMensagem frame = new EnviarMensagem();
				frame.setVisible(true);
				//fecha esta tela para deixar só a de cadastrar topicos aberta
				dispose();				
				
			}
		});
		button.setBounds(126, 86, 191, 25);
		contentPane.add(button);
		
	}

}
