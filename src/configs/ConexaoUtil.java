package configs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoUtil {

	private static ConexaoUtil conexaoutil;
	
	//instancia somente um objeto para conexao com bd
	public static ConexaoUtil getInstance() {
		if(conexaoutil == null) {
			conexaoutil = new ConexaoUtil();
		}
		
		return conexaoutil;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		//drive da conexao com MySQL
		Class.forName("com.mysql.jdbc.Driver");
		//retorna conexao com BD
			//jdbc:mysql://localhost:3306/nome_do_bd
			//usuáiro bd
			//senha bd
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/divulgacao_automatica", "root", "123456");
	}

	
}
