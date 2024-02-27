package org.example.IMPL;

import org.example.CONEXION.ConexionBD;
import org.example.MODELS.PRODUCTO;
import org.example.REPOSITORIO.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class RepositoryIMPL  implements Repository {
    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
}
    private PRODUCTO createProduct(ResultSet resultSet) throws
            SQLException {
        PRODUCTO producto = new PRODUCTO();
        producto.setId(resultSet.getLong("id"));
        producto.setName(resultSet.getString("name"));
        producto.setPrice(resultSet.getDouble("price"));
        producto.setRegister_date(
                resultSet.getDate("register_date")!=null?
                        LocalDate.from(resultSet.getDate("register_date")
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()) : null);
        return producto;
    }

    public List<PRODUCTO> list() {
        List<PRODUCTO> productoList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from productos")) {
            while (resultSet.next()) {
                PRODUCTO producto = createProduct(resultSet);
                productoList.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productoList;
    }

    public PRODUCTO byId(Long id) {
        PRODUCTO producto = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM productos WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                producto = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void delete(Long id) {

    }
    }
