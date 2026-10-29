package N;


import java.util.InputMismatchException;
import java.util.Scanner;

public class AmountInput {
    private static final int ERROR = -1;
    private final InputValidator inputValidator = new InputValidator();

    public int start(int operation, String userId){
        int inputAgain = 2;
        int flag = inputAgain;
        int amount = 0;
        while (flag == inputAgain) {
            System.out.println("現在の口座額：" + Table.getBalance(userId) + "円");
            if(operation == 1){
                System.out.print("入金金額を入れてください：");
            }else if(operation == 2) {
                System.out.print("出金金額を入れてください：");
            }
            amount = input(operation,userId);
            if (amount == ERROR) {
                continue;
            }

            flag = check(amount);
        }
        return amount;
    }


    private int input(int operation,String userId){
        int amount;
        Scanner input = new Scanner(System.in);
        try {
            amount = input.nextInt();
            System.out.println("入力額：" + amount + "円");
            System.out.println("-----------------------------");
            //int型の例外処理
            if(operation == 1){
                //入力金額が1,000,000円(お札100枚まで) or 0円未満の場合例外処理
                if(amount <= 0 || amount > 1000000){
                    System.out.println("金額が適切ではありません");
                    System.out.println("もう一度入力してください");
                    System.out.println("-----------------------------");
                    return ERROR;
                }
            }if(operation == 2){
                //入力金額が1,000,000円(お札100枚まで) or 0円未満 or 残高以上の場合例外処理
                if(amount <= 0 || amount > 1000000 || Table.getBalance(userId) < amount){
                    System.out.println("金額が適切ではありません");
                    System.out.println("もう一度入力してください");
                    System.out.println("-----------------------------");
                    return ERROR;
                }
            }
        } catch (InputMismatchException e) {    //int型以外が入ったときの例外処理
            System.out.println("-----------------------------");
            System.out.println("数字を入力してください。");
            System.out.println("-----------------------------");
            return ERROR;
        }
        return amount;
    }



    private int check(int amount){
        int setValue = 2;//setValueは選択肢(ボタン)の数
        int flag = 0;
        while (flag < 1 || setValue < flag) {
            System.out.println("入力内容を確認してください\n");
            System.out.println("入力金額：" + amount + "円\n");
            System.out.println("[1:次に進む][2:入力しなおす]");
            System.out.print("あなたの操作(数字を入力してください)：");
            flag = inputValidator.checkSelection(setValue);
        }
        return flag;
    }
}
