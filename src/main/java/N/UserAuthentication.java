package N;


import java.util.InputMismatchException;
import java.util.Scanner;

public class UserAuthentication {
    private static final int ERROR = -1;

    public String identifyUser(){
        System.out.print("ユーザーIDを入力してください：");
        Scanner id = new Scanner(System.in);
        String userId = id.nextLine();
        System.out.println("-----------------------------");
        if (!Table.containsAccount(userId)) {
            System.out.println("不明なユーザーIDです");
            System.out.println("最初からやり直してください");
            System.exit(1);
        }
        return userId;
    }

    public void checkPassword(String userId){
        int i = 0;
        int password;
        while (true){
            password = inputPassword();

            int correctPassword = Table.getPassword(userId);
            if (correctPassword == password) {
                return;
            }else if(password == -1){
                continue;
            }
            System.out.println("パスワードが間違っています");
            i++;

            if(i == 3){
                System.out.println("3回パスワードを間違えたため操作を終了します");
                System.exit(1);
            }
            System.out.println("もう一度入力してください");
            System.out.println("-----------------------------");
        }
    }

    private int inputPassword() {
        int password;
        System.out.print("パスワードを入力してください：");
        Scanner pass = new Scanner(System.in);
        try {
            password = pass.nextInt();
            System.out.println("-----------------------------");
            return password;
        } catch (InputMismatchException e) {
            System.out.println("-----------------------------");
            System.out.println("数字を入力してください。");
            System.out.println("-----------------------------");
            return ERROR;
        }
    }
}
