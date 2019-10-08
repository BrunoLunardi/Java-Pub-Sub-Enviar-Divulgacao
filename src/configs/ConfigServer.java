package configs;

public class ConfigServer {
	String enderecoServidor = "tcp://localhost:61616";
	//recupera endereco ip
	public String getEnderecoServidor() {
		return enderecoServidor;
	}
	//altera endereco ip
	public void setEnderecoServidor(String enderecoServidor) {
		this.enderecoServidor = enderecoServidor;
	}
}
