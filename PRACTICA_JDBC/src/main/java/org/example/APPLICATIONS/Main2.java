package org.example.APPLICATIONS;

import org.example.CONEXION.ConexionBD;
import org.example.IMPL.RepositoryIMPL;
import org.example.MODELS.PRODUCTO;
import org.example.REPOSITORIO.Repository;

import java.sql.Connection;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args){
        try (Connection conn = ConexionBD.getInstance()){
            Repository<PRODUCTO>repository = new RepositoryIMPL();
            System.out.println("***** List products from database");
            repository.list().stream().forEach(System.out::println);
            System.out.println("***** Get by Id: 1");
            System.out.println(repository.byId(1L).toString());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
