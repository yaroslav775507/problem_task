package org.example;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            DataBase dataBase = new DataBase();
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(dataBase.URL_CON, dataBase.USERNAME, dataBase.PASSWORD)) {
                System.out.println("Подключение к базе данных установленно!");
                conn.close();
            }
        } catch (Exception ex) {
            System.out.println("Есть проблемки(");
            System.out.println(ex);
        }
        DataBase dataBase = new DataBase();
        Scanner s = new Scanner(System.in);
        System.out.println("Введите ваше имя");
        String name = s.nextLine();
        System.out.println("Введите вашу фамилию");
        String lastName = s.nextLine();
        User user = new User(name, lastName);
        String sqlCommand = " INSERT INTO TASK.User (name,lastname) value ( ? , ?)";
        try (Connection con = DriverManager.getConnection(dataBase.URL_CON, dataBase.USERNAME, dataBase.PASSWORD)) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCommand);
            {
                //Добавление данных в БД
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                try {
                    preparedStatement.executeUpdate(); // выполняем запрос
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Данные успешно добавлены!");
                con.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sqlCommand2 = " INSERT INTO TASK.task (important, medium_important, low_important) VALUES (?, ?, ?)";
        try (Connection cone = DriverManager.getConnection(dataBase.URL_CON, dataBase.USERNAME, dataBase.PASSWORD)) {
            PreparedStatement preparedStatementt = cone.prepareStatement(sqlCommand2);
            // запрашиваем у пользователя тип данных для добавления
            int choice;
            do {
                System.out.println("Выберите тип данных для добавления: 1 - Важные, 2 - Средней важности, 3 - Не важные, 0 - Посмотреть существующие задачи");
                choice = Integer.parseInt(s.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Введите информацию о важной задаче: ");
                        String important = s.nextLine();
                        preparedStatementt.setString(1, important);
                        preparedStatementt.setString(2, null);
                        preparedStatementt.setString(3, null);
                        preparedStatementt.executeUpdate(); // выполняем запрос
                        System.out.println("Информация о важной задаче успешно добавлена!");
                        break;
                    case 2:
                        System.out.println("Введите информацию о задаче средней важности: ");
                        String mediumImportant = s.nextLine();
                        preparedStatementt.setString(1, null);
                        preparedStatementt.setString(2, mediumImportant);
                        preparedStatementt.setString(3, null);
                        preparedStatementt.executeUpdate(); // выполняем запрос
                        System.out.println("Информация о задаче средней важности успешно добавлена!");
                        break;
                    case 3:
                        System.out.println("Введите информацию о не важной задаче: ");
                        String lowImportant = s.nextLine();
                        preparedStatementt.setString(1, null);
                        preparedStatementt.setString(2, null);
                        preparedStatementt.setString(3, lowImportant);
                        preparedStatementt.executeUpdate(); // выполняем запрос
                        System.out.println("Информация о не важной задаче успешно добавлена!");
                        break;
                    case 0:
                        System.out.println("Выход из программы");
                        break;
                    default:
                        System.out.println("Некорректный выбор данных");
                }
            } while (choice != 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Ваш список задач");
        try (Connection resultConn = DriverManager.getConnection(dataBase.URL_CON, dataBase.USERNAME, dataBase.PASSWORD)) {
            Statement statements = resultConn.createStatement();
            ResultSet resultSet = statements.executeQuery("SELECT * FROM task");
            Scanner scanner = new Scanner(System.in);
            int choose;
            do {
                System.out.println("Выберите тип данных для просмотра: 1 - Важные, 2 - Средней важности, 3 - Не важные, 0 - Выход");
                choose = scanner.nextInt();
                while (resultSet.next()) {
                    switch (choose) {
                        case 1:
                            int id = resultSet.getInt(1);
                            String important = resultSet.getString(2);
                            System.out.printf("%d. %s \n", id, important);
                            break;
                        case 2:
                            int id2 = resultSet.getInt(1);
                            String med_important = resultSet.getString(3);
                            System.out.printf("%d. %s \n", id2, med_important);
                            break;
                        case 3:
                            int id3 = resultSet.getInt(1);
                            String low_important = resultSet.getString(3);
                            System.out.printf("%d. %s \n", id3, low_important);
                            break;
                    }
                }
            } while (choose != 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Очистить список? (Yes/No)");
        String clean = s.nextLine();
        if(clean.equals("Yes")){
            try (Connection conn = DriverManager.getConnection(dataBase.URL_CON, dataBase.USERNAME, dataBase.PASSWORD)){
                Statement statement = conn.createStatement();
                int rows = statement.executeUpdate("DELETE FROM TASK.task ");
                System.out.println("Список очищен");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }if(clean.equals("No")){
            System.out.println("Остановка работы");
        }
    }
    }

