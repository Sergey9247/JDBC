package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;

public class Main {
    public static void main(String[] args) {
        final UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 18);
        userService.saveUser("Sergey", "Makarov", (byte) 30);
        userService.saveUser("Oleg", "Ivanov", (byte) 44);
        userService.saveUser("Elena", "Novikova", (byte) 20);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
