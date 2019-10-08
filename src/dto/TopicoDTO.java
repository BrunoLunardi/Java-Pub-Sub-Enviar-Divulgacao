package dto;

public class TopicoDTO {

	//atributos do BD da tabela topicos
	int id;
	String topico;
	
	//construtor
	public TopicoDTO(String topico) {
		this.topico = topico;
	}
	
	//construtor
	public TopicoDTO() {
	}	
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopico() {
		return topico;
	}
	public void setTopico(String topico) {
		this.topico = topico;
	}
	
	//Formata para String
	@Override
	public String toString() {
		return getTopico();
	}
	
}
