import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateServiceNew {

    public void executeFile(String path) throws IOException {

        try {
            Connection conn = Database.getInstance().getConnection();
            String query = new String(Files.readAllBytes(Paths.get(path)));
            PreparedStatement preparedStatement = conn.prepareStatement(query);


            String[] commands = query.split(";",0);

            for (String sqlCommand: commands){
                preparedStatement.executeUpdate(sqlCommand);
                System.out.println("Sql Command: " + sqlCommand);
                System.out.println("Table is successfully populated! \n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        DatabasePopulateServiceNew databasePopulateServiceNew = new DatabasePopulateServiceNew();
        databasePopulateServiceNew.executeFile("C:\\Users\\yudy1117\\IdeaProjects\\Developer\\Module6\\SQL\\populate_db.sql");
    }

}
