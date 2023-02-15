package sqlServices;

import dao.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    public long create(String name) {
        String query = "INSERT INTO hw6d.client (NAME) VALUES (?);";
        String queryR = "SELECT * from hw6d.client where name = ?;";
        long id;
        if (name.isBlank() || name.length() < 2 || name.length() > 1000) throw new IllegalArgumentException();

        try (Connection connection = new Database().getConnection();
             PreparedStatement statementWrite = connection.prepareStatement(query);
             PreparedStatement statementRead = connection.prepareStatement(queryR)) {

            statementWrite.setString(1, name);
            statementRead.setString(1, name);
            statementWrite.execute();
            ResultSet rs = statementRead.executeQuery();
            rs.next();
            id = rs.getLong("id");

            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;

    }

    public String getById(long id) {
        if (id < 0) throw new IllegalArgumentException();
        String queryR = "SELECT * from hw6d.client where id = ?;";
        String name;
        try (Connection connection = new Database().getConnection();
             PreparedStatement statementRead = connection.prepareStatement(queryR)) {

            statementRead.setLong(1, id);
            ResultSet rs = statementRead.executeQuery();
            rs.next();
            name = rs.getString("name");

            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    void setName(long id, String name) {
        if (name.isBlank() || name.length() < 2 || name.length() > 1000) throw new IllegalArgumentException();
        if (id < 0) throw new IllegalArgumentException();

        String query = "Update hw6d.client set name = ? where id = ?;";

        try (Connection connection = new Database().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setLong(2, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteById(long id) {
        if (id < 0) throw new IllegalArgumentException();
        String query = "Delete from hw6d.client where id = ?;";
        try (Connection connection = new Database().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    List<Client> listAll() {
        String query = "SELECT * FROM hw6d.client;";
        List<Client> clientsList = new ArrayList<>();
        try (Connection connection = new Database().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                clientsList.add(new Client(id,name));
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientsList;
    }
}
