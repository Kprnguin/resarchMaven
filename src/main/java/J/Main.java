package J;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int flag;
        int operation;

        //Select an action
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("〇〇銀行ATM");
            System.out.println("何の操作を行いますか？");
            System.out.println("[1:入金][2:出金]");
            System.out.print("あなたの操作(数字を入力してください)：");
            Scanner input = new Scanner(System.in);
            try {
                operation = input.nextInt();
                System.out.println("-----------------------------");
                if(operation != 1 && operation != 2){
                    System.out.println("操作が間違っています");
                    System.out.println("もう一度操作を選択してください");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("-----------------------------");
                System.out.println("数字を入力してください。");
                continue;
            }
            break;
        }



        //If deposit is selected,
        if (operation == 1) {
            System.out.print("ユーザーIDを入力してください：");
            Scanner id = new Scanner(System.in);
            String userId = id.nextLine();
            System.out.println("-----------------------------");

            if (!Table.containsAccount(userId)) {
                System.out.println("不明なユーザーIDです");
                System.out.println("最初からやり直してください");
                return;
            }

            //Enter the deposit amount.
            int depositAmount;
            while (true) {
                System.out.println("現在の口座額：" + Table.getBalance(userId) + "円");
                System.out.print("入金金額を入れてください：");
                Scanner amount = new Scanner(System.in);
                try {
                    depositAmount = amount.nextInt();
                    System.out.println("入力額：" + depositAmount + "円");
                    System.out.println("-----------------------------");
                    if (depositAmount <= 0 || depositAmount > 1000000) {
                        System.out.println("金額が適切ではありません");
                        System.out.println("もう一度入力してください");
                        System.out.println("-----------------------------");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("-----------------------------");
                    System.out.println("数字を入力してください。");
                    System.out.println("-----------------------------");
                    continue;
                }

                //Check that the entered deposit amount is correct.
                while (true) {
                    System.out.println("入力内容を確認してください\n");
                    System.out.println("入力金額：" + depositAmount + "円\n");
                    System.out.println("[1:次に進む][2:入力しなおす]");
                    System.out.print("あなたの操作(数字を入力してください)：");
                    Scanner input2 = new Scanner(System.in);
                    try {
                        flag = input2.nextInt();
                        System.out.println("-----------------------------");
                        if (flag != 1 && flag != 2) {
                            System.out.println("操作が間違っています");
                            System.out.println("再度操作を入力してください");
                            System.out.println("-----------------------------");
                            continue;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("-----------------------------");
                        System.out.println("数字を入力してください。");
                        System.out.println("-----------------------------");
                        continue;
                    }
                    break;
                }
                if (flag == 2) {
                    continue;
                }
                break;
            }
            System.out.println("元の口座額：" + Table.getBalance(userId) + "円");
            System.out.println("入金額：" + depositAmount + "円");
            Table.updateBalance(userId, depositAmount);
            System.out.println("預け総額：" + Table.getBalance(userId) + "円");
        }




        //If withdrawal is selected,
        else if (operation == 2) {
            System.out.print("ユーザーIDを入力してください：");
            Scanner id = new Scanner(System.in);
            String userId = id.nextLine();
            System.out.println("-----------------------------");

            if (!Table.containsAccount(userId)) {
                System.out.println("不明なユーザーIDです");
                System.out.println("最初からやり直してください");
                return;
            }

            //Look up password by ID
            int i = 0;
            int password;
            while (true){
                System.out.print("パスワードを入力してください：");
                Scanner pass = new Scanner(System.in);
                try {
                    password = pass.nextInt();
                    System.out.println("-----------------------------");
                } catch (InputMismatchException e) {
                    System.out.println("-----------------------------");
                    System.out.println("数字を入力してください。");
                    System.out.println("-----------------------------");
                    continue;
                }

                int correctPassword = Table.getPassword(userId);
                if (correctPassword == password) {
                    break;
                }
                System.out.println("パスワードが間違っています");
                i++;

                if(i == 3){
                    System.out.println("3回パスワードを間違えたため操作を終了します");
                    return;
                }
                System.out.println("もう一度入力してください");
                System.out.println("-----------------------------");
            }

            //Enter the withdrawal amount.
            int withdrawalAmount;
            while (true) {
                System.out.println("現在の口座額：" + Table.getBalance(userId) + "円");
                System.out.print("出金金額を入れてください：");
                Scanner amount = new Scanner(System.in);
                try {
                    withdrawalAmount = amount.nextInt();
                    System.out.println("出金額：" + withdrawalAmount + "円");
                    System.out.println("-----------------------------");
                    if (withdrawalAmount <= 0 || withdrawalAmount > 1000000 || Table.getBalance(userId) < withdrawalAmount) {
                        System.out.println("金額が適切ではありません");
                        System.out.println("もう一度入力してください");
                        System.out.println("-----------------------------");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("-----------------------------");
                    System.out.println("数字を入力してください。");
                    System.out.println("-----------------------------");
                    continue;
                }

                //Check that the entered withdrawal amount is correct.
                while (true) {
                    System.out.println("入力内容を確認してください\n");
                    System.out.println("出金金額：" + withdrawalAmount + "円\n");
                    System.out.println("[1:次に進む][2:入力しなおす]");
                    System.out.print("あなたの操作(数字を入力してください)：");
                    Scanner input2 = new Scanner(System.in);
                    try {
                        flag = input2.nextInt();
                        System.out.println("-----------------------------");
                        if (flag != 1 && flag != 2) {
                            System.out.println("操作が間違っています");
                            System.out.println("再度操作を入力してください");
                            System.out.println("-----------------------------");
                            continue;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("-----------------------------");
                        System.out.println("数字を入力してください。");
                        System.out.println("-----------------------------");
                        continue;
                    }
                    break;
                }
                if(flag == 2){
                    continue;
                }
                break;
            }
            System.out.println("元の口座額：" + Table.getBalance(userId) + "円");
            System.out.println("出金額：" + withdrawalAmount + "円");
            Table.updateBalance(userId, -1*withdrawalAmount);
            System.out.println("預け総額：" + Table.getBalance(userId) + "円");
        }
    }
}