package org.Testing.practic.client;

import org.Testing.practic.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        Connection connection = Database.getInstance().getConnection();
        String sql = "select * from Client";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                clients.add(new Client(name,id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clients;
    }
    public Client getById(int id) {
        Client client = null;
        Connection connection = Database.getInstance().getConnection();
        String sql = "select id,name from Client where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int newId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                client = new Client(name,id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }
    public void setName(int id,String name){
        Connection connection = Database.getInstance().getConnection();
        String sql = "update client set name = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteById(int id) {
        Connection connection = Database.getInstance().getConnection();
        String sql = "delete from client where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int create(String name) {
        if (name.length()< 3 || name.length()> 100) {
            throw new IllegalArgumentException("name is not valid");

        }
        int id = -1;
        Connection connection = Database.getInstance().getConnection();
        String sql = "insert into client (name) values(?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            int executed = statement.executeUpdate();
            if (executed > 0) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  id;
    }
}
