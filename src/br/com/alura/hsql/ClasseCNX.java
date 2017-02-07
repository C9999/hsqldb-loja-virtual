package br.com.alura.hsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClasseCNX {
	static public Connection cnx(Connection cc) throws SQLException{
		cc = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
		
		return cc;
	}
	
}
/*
Connection cn = cnx(cn);

*/
