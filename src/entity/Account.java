package entity;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static service.Menu.*;

public class Account {
    private static final String FILE_PATH = "accounts.txt";
    private static final Map<String, String> accounts = new HashMap<>();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    public boolean registerUser() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("-----------------------------------------");
            System.out.println("|            ĐĂNG KÝ TÀI KHOẢN          |");
            System.out.println("-------------------***-------------------");
            System.out.print("Nhập tên đăng nhập: ");
            String username = reader.readLine().trim();

            while (username.length() < 6 || username.length() > 12) {
                System.out.println("Tên đăng nhập phải có độ dài từ 6 đến 12 ký tự. Vui lòng nhập lại.");
                System.out.print("Nhập tên đăng nhập: ");
                username = reader.readLine().trim();
            }
            System.out.print("Nhập mật khẩu: ");
            String password = reader.readLine().trim();

            while (password.length() < 6 || password.length() > 12) {
                System.out.println("Mật khẩu phải có độ dài từ 6 đến 12 ký tự. Vui lòng nhập lại.");
                System.out.print("Nhập mật khẩu: ");
                password = reader.readLine().trim();
            }

            System.out.print("Nhập lại mật khẩu: ");
            String confirmPassword = reader.readLine().trim();

            while (!password.equals(confirmPassword)) {
                System.out.println("Mật khẩu nhập lại không khớp. Đăng ký không thành công.");
                System.out.print("Nhập lại mật khẩu: ");
                confirmPassword = reader.readLine().trim();
            }

            String captcha = generateCaptcha();
            System.out.println("Mã Captcha: " + captcha);
            System.out.print("Nhập mã Captcha: ");
            String inputCaptcha = reader.readLine().trim();

            if (accounts.containsKey(username) || username.equals(ADMIN_USERNAME)) {
                System.out.println("Tài khoản đã tồn tại hoặc không hợp lệ. Đăng ký không thành công.");
                registerUser();
                return false;
            }

            while (!validateCaptcha(inputCaptcha, captcha)) {
                System.out.println("Mã Captcha không khớp. Vui lòng nhập lại");
                System.out.print("Nhập mã Captcha: ");
                inputCaptcha = reader.readLine().trim();
            }

            // luu tai khoan vao Map
            accounts.put(username, password);
            // luu tai khoan vao tep tin
            saveAccountsToFile(username, password);
            System.out.println("Đăng ký thành công!");

            return true;

        } catch (IOException e) {
            System.out.println("Lỗi khi đọc tài khoản.");
            return false;
        }
    }


    public static boolean login() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("-----------------------------------------");
            System.out.println("|               ĐĂNG NHẬP               |");
            System.out.println("-------------------***-------------------");
            System.out.print("Nhập tên đăng nhập: ");
            String username = reader.readLine().trim();

            System.out.print("Nhập mật khẩu: ");
            String password = reader.readLine().trim();

            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                System.out.println("----------------------------------------");
                System.out.println("          CHÀO MỨNG QUẢN TRỊ VIÊN      ");
                System.out.println("-------------------***------------------");
                adminMenu();
                return true;
            } if((accounts.containsKey(username) && accounts.get(username).equals(password))){
                System.out.println("----------------------------------------");
                System.out.println("  ĐĂNG NHẬP THÀNH CÔNG. CHÀO MỪNG BẠN   ");
                System.out.println("-------------------***------------------");
                memberMenu();
                return true;
            }

            else {
                System.out.println("Tài khoản hoặc mật khẩu không đúng.");
                login();
                return false;
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đăng nhập.");
            return false;
        }
    }

    private static String generateCaptcha() {
        Random random = new Random();
        int captcha = random.nextInt(900) + 100;
        return String.valueOf(captcha);
    }

    private static boolean validateCaptcha(String inputCaptcha, String currentCaptcha) {
        return inputCaptcha.equals(currentCaptcha);
    }
    private static void saveAccountsToFile(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Không thể lưu tài khoản vào tệp tin.");
        }
    }

    public static void readAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] account = line.split(":");
                accounts.put(account[0], account[1]);
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc tệp tin tài khoản.");
        }
    }


}
