package N;


public class Main {
    public static void main(String[] args) {
        String userId;
        TransactionMenu transactionMenu = new TransactionMenu();
        UserAuthentication userAuthentication = new UserAuthentication();
        AmountInput amountInput = new AmountInput();
        Transaction transaction = new Transaction();

        int operation = transactionMenu.start();

        if (operation == 1) {
            userId = userAuthentication.identifyUser();

            int depositAmount = amountInput.start(operation, userId);

            transaction.displayResult(userId, depositAmount,operation);
            transaction.adjustBalance(userId,depositAmount,operation);
        }

        else if (operation == 2) {
            userId = userAuthentication.identifyUser();
            userAuthentication.checkPassword(userId);

            int withdrawalAmount = amountInput.start(operation, userId);

            transaction.displayResult(userId,withdrawalAmount,operation);
            transaction.adjustBalance(userId,withdrawalAmount,operation);
        }
    }
}
