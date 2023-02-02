package org.example.dao.config;

import org.example.dao.Dao;
import org.example.models.Order;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum ConnectionPool{
    INSTANCE;
    public static final int poolSize = 10;
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String URL = "jdbc:postgresql://localhost:5432/car_sharing_service";
    public static final String LOGIN = "postgres";
    public static final String PASSWORD = "toor";
    private Pool pool;

    ConnectionPool(){
        pool = new Pool();
    }

    public Pool getPool(){
        return pool;
    }

    public final class Pool{
        private List<ProxyConnection> freeConnections;
        private List<ProxyConnection> usedConnections;
        Pool(){
            freeConnections = new ArrayList<>(poolSize);
            usedConnections = new ArrayList<>(poolSize);
            try {
                Class.forName(DRIVER);
                initialize();
            } catch (ClassNotFoundException | SQLException e){
                e.printStackTrace();
            }
        }

        public synchronized Connection getConnection() throws SQLException {
            if(freeConnections.isEmpty()){
                throw new RuntimeException("No available connections!");
            }
            ProxyConnection connection = freeConnections.get(freeConnections.size()-1);
            moveConnectionToUsed(connection);
            return connection;
        }

        public synchronized void moveConnectionToFree(ProxyConnection usedConnection){
            freeConnections.add(usedConnection);
            usedConnections.remove(usedConnection);
        }

        private void moveConnectionToUsed(ProxyConnection freeConnection){
            usedConnections.add(freeConnection);
            freeConnections.remove(freeConnection);
        }

        private void initialize() throws SQLException {
            for(int i = 0; i<poolSize; i++)
                freeConnections.add(new ProxyConnection(DriverManager.getConnection(URL, LOGIN, PASSWORD)));
        }

        @Override
        protected void finalize() throws Throwable {
            /*for(int i = 0; i<poolSize; i++){
                if(i<freeConnections.size())
                    freeConnections.get(i).finish();
                if(i<usedConnections.size())
                    usedConnections.get(i).finish();
            }
            System.out.println("finalize in ConnectionPool!");
            */
            super.finalize();

        }

        public void closePool() throws SQLException {
            for(int i = 0; i<poolSize; i++){
                if(i<freeConnections.size())
                    freeConnections.get(i).finish();
                if(i<usedConnections.size())
                    usedConnections.get(i).finish();
            }
            System.out.println("finalize in ConnectionPool!");
        }
    }

}
