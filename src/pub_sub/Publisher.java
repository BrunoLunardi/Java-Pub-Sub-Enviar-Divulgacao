package pub_sub;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;
import configs.ConfigServer;


public class Publisher {
	
	//String que enviará a conta para o outro sistema
    String payload;
    //string para topicos
    String topico;
    //construtor da classe para enviar a mensagem
	public Publisher(String topico, String payload){
		this.topico = topico;
		this.payload = payload;
	}
    
	//Método para enviar a mensagem para o mosquitto
	public void enviarMensagem() throws Exception {
Connection connection = null;
		
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://localhost:61616");

        //cria conexão
        connection = connectionFactory.createConnection();
		
        //cria sessão
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        
        //cria tópico para enviar mensagem nele
        Topic topic = session.createTopic(topico); 
        
        //inicia conexão para envio de mensagem
        connection.start(); 
        //payload da mensagem
        Message msg = session.createTextMessage(payload);
        
        //Define propriedade para ser filtrada no cliente
        msg.setStringProperty("valor", "20.00");
        
        //cria tópico
        MessageProducer producer = session.createProducer(topic);
        
        System.out.println("Sending text '" + payload + "'");
        //envia mensagem
        producer.send(msg);
         
        Thread.sleep(3000);
        //Fecha conexão
        session.close();        
        
        connection.close();
	}	

}