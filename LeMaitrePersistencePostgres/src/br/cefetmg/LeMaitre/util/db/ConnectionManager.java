package br.cefetmg.LeMaitre.util.db;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
  
    private static ConnectionManager conexao;
    private static ConnectionFactory cf;

    private ConnectionManager() {
         ConnectionManager.cf = new PostgresqlConnection();
    }

    public static ConnectionManager getInstance() {
        if(conexao == null)
            conexao = new ConnectionManager();

        return conexao;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        return ConnectionManager.cf.getConnection();
    }
}
