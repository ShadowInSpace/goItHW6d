package sqlServices;

import dao.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientService {

    public long create(String name) {
        String query = "INSERT INTO hw6d.client (NAME) VALUES (?);";
        String queryR = "SELECT * from hw6d.client where name = ?;";
        long id;
        if(name.isBlank() || 2<=name.length() || name.length()<=1000) throw new IllegalArgumentException();

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

    public String getById(long id){
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

    void setName(long id, String name){

    }

    void deleteById(long id){

    }

    List<Client> listAll(){
        return null;
    }
}
