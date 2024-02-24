import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DatabasePopulateService {


    public void insertWorker() throws SQLException {

        try {
            Connection conn = Database.getInstance().getConnection();
            String sql = "INSERT INTO megasoft.worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            String[] names = {"John Doe", "Jane Smith", "Robert Johnson", "Emily Davis", "Michael Brown", "Sophia Wilson", "Daniel Taylor", "Olivia Lee", "David Miller", "Ava Anderson"};
            String[] bd = {"1990-05-15", "1988-08-22", "1995-02-10", "1992-11-05", "1987-04-18", "1993-07-30", "1989-09-12", "1998-01-25", "1991-06-08", "1985-12-03"};
            String[] lvl = {"Trainee", "Junior", "Middle", "Senior", "Trainee", "Junior", "Middle", "Senior", "Trainee", "Junior"};
            int[] salary = {700, 22000, 55000, 90000, 1500, 15500, 35000, 90000, 1000, 10800};


            for (int i = 0; i < names.length; i++) {
                preparedStatement.setString(1, names[i]);
                preparedStatement.setDate(2, Date.valueOf(LocalDate.parse(bd[i])));
                preparedStatement.setString(3, lvl[i]);
                preparedStatement.setLong(4, salary[i]);
                preparedStatement.addBatch();
            }

            try {
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                System.out.println("Error message 1: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error message 2: " + e.getMessage());
        }

    }

    public void insertClient() throws SQLException {

        try {
            Connection conn = Database.getInstance().getConnection();
            String sql = "INSERT INTO megasoft.client (name) VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            String[] names = {"BMW", "VOLVO", "TOYOTA", "HYUNDAI", "MERCEDES"};


            for (int i = 0; i < names.length; i++) {
                preparedStatement.setString(1, names[i]);
                preparedStatement.addBatch();
            }

            try {
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                System.out.println("Error message 1: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error message 2: " + e.getMessage());
        }

    }

    public void insertProject() {

        try {
            Connection conn = Database.getInstance().getConnection();
            String sql = "INSERT INTO megasoft.project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            int[] clientID = {3, 5, 1, 2, 2, 4, 5, 1, 2, 3};
            String[] startDate = {"2022-11-05", "2019-05-14", "2020-04-21", "2015-02-03", "2016-04-18", "2010-12-03", "2023-02-13", "2012-04-16", "2014-01-06", "2022-08-16"};
            String[] finishDate = {"2023-09-15", "2023-12-13", "2022-08-16", "2018-10-05", "2020-09-15", "2018-07-29", "2023-03-15", "2019-07-21", "2021-10-07", "2023-09-19"};

            for(int i=0;i<clientID.length;i++){
                preparedStatement.setInt(1, clientID[i]);
                preparedStatement.setDate(2, Date.valueOf(LocalDate.parse(startDate[i])));
                preparedStatement.setDate(3, Date.valueOf(LocalDate.parse(finishDate[i])));
                preparedStatement.addBatch();
            }

            try{
                preparedStatement.executeBatch();
            }catch (SQLException e){
                System.out.println("Error message 1: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertProjectWorker() {

        try {
            Connection conn = Database.getInstance().getConnection();
            String sql = "INSERT INTO megasoft.project_worker (PROJECT_ID, WORKER_ID) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            int[] projectID = {5, 4, 9, 5, 10, 6, 4, 7, 10, 9, 2, 3, 1, 2};
            int[] workerID = {3, 4, 10, 2, 8, 5, 8, 5, 3, 5, 1, 2, 5, 9};


            for(int i=0;i<projectID.length;i++){
                preparedStatement.setInt(1, projectID[i]);
                preparedStatement.setInt(2, workerID[i]);
                preparedStatement.addBatch();
            }

            try{
                preparedStatement.executeBatch();
            }catch (SQLException e){
                System.out.println("Error message 1: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws SQLException {
        DatabasePopulateService databasePopulateService = new DatabasePopulateService();
        databasePopulateService.insertWorker();
        databasePopulateService.insertClient();
        databasePopulateService.insertProject();
        databasePopulateService.insertProjectWorker();
    }

}
