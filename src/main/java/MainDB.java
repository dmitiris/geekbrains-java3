import java.sql.*;

public class MainDB {

    private  static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try {
            connect();
            drop();
            initialize();
            add("Bob", (float)200.50);
            add("Ben", (float)299.99);
            add(3, "Bill", 200);
            print(read());
            print(read(2));
            print(read(10));
            updateNameById(2, "Bro");
            updateSalaryById(1, 500);
            updateSalaryByName("Bro", 1000);
            print(read());
            add("vasya", 100);
            add("vasya", 100);
            add("vasya", 100);
            print(read());
            System.out.println(delete("vasya"));
            print(read());
//            System.out.println(delete());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:usersDB.db");
        statement = connection.createStatement();
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean initialize() {
        try {
            statement.executeUpdate("CREATE  TABLE employees (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "salary DECIMAL " +
                    ")");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void add(String name, float salary) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO employees (name, salary) VALUES (?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setFloat(2, salary);
        preparedStatement.execute();
    }

    public static void add(int id, String name, float salary) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO employees (id, name, salary) VALUES (?, ?, ?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setFloat(3, salary);
        preparedStatement.execute();
    }

    public static ResultSet read() throws SQLException {
        return statement.executeQuery("SELECT * FROM employees");
    }

    public static ResultSet read(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id=?");
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet read(String name) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE name=?");
        preparedStatement.setString(1, name);
        return preparedStatement.executeQuery();
    }

    public static ResultSet read(float salary) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE salary=?");
        preparedStatement.setFloat(1, salary);
        return preparedStatement.executeQuery();
    }

    public static int updateNameById(int id, String name) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE employees SET name=? WHERE id=?");
        preparedStatement.setInt(2, id);
        preparedStatement.setString(1, name);
        return preparedStatement.executeUpdate();
    }
    public static int updateSalaryById(int id, float salary) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE employees SET salary=? WHERE id=?");
        preparedStatement.setInt(2, id);
        preparedStatement.setFloat(1, salary);
        return preparedStatement.executeUpdate();
    }
    public static int updateSalaryByName(String name, float salary) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE employees SET salary=? WHERE name=?");
        preparedStatement.setFloat(1, salary);
        preparedStatement.setString(2, name);
        return preparedStatement.executeUpdate();
    }

    public static int delete() throws SQLException {
        return statement.executeUpdate("DELETE FROM employees");
    }

    public static int delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id=?");
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }

    public static int delete(String name) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE name=?");
        preparedStatement.setString(1, name);
        return preparedStatement.executeUpdate();
    }
    public static int delete(float salary) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE salary=?");
        preparedStatement.setFloat(1, salary);
        return preparedStatement.executeUpdate();
    }

    public static int drop() throws SQLException {
        return statement.executeUpdate("DROP TABLE IF EXISTS employees");
    }

    public static void print(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println(" ");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +rs.getString("name") + "\t" + rs.getFloat("salary"));
        }
        System.out.println(" ");
    }


}
