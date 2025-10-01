package lld.practise;

import java.util.HashMap;
import java.util.Map;

public class AtmMachine {
    public static class BankAccount{
        public String accountNumber;
        public String accountHolderName;
        public double balance;

        BankAccount(String accountNumber,String accountHolderName,double balance)
        {
            this.accountNumber=accountNumber;
            this.accountHolderName=accountHolderName;
            this.balance=balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getAccountHolderName() {
            return accountHolderName;
        }

        public boolean withDraw(double amount){
            if(balance>=amount){
                balance-=amount;
                return true;
            }
            return false;
        }

        public void deposite(double amount)
        {
            balance+=amount;
        }

        public double getBalance() {
            return balance;
        }
    }

    public static class Card{
        String cardNumber;
        public BankAccount linkedAccount;
        public String pin;
        Card(String cardNumber,BankAccount linkedAccount,String pin)
        {
            this.cardNumber=cardNumber;
            this.linkedAccount=linkedAccount;
            this.pin=pin;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public BankAccount getLinkedAccount() {
            return linkedAccount;
        }

        public String getPin() {
            return pin;
        }
        public boolean validatePin(String pin){
            return this.pin.equalsIgnoreCase(pin);
        }
    }
    public static   abstract class Transaction{
        public BankAccount bankAccount;
        public double amount;
         abstract void process();
         Transaction(BankAccount bankAccount,double amount){
             this.bankAccount=bankAccount;
             this.amount=amount;
         }
    }
    public static class WithDrawTransaction extends Transaction{

        WithDrawTransaction(BankAccount bankAccount, double amount) {
            super(bankAccount, amount);
        }

        @Override
        void process() {
            if(bankAccount.withDraw(amount)){
                System.out.println(amount+" successfully credited from your account "+bankAccount.accountNumber+" remaining balance "+bankAccount.getBalance());
            }else{
                System.out.println("Insufficient Balance "+bankAccount.getBalance());
            }
        }
    }
    public static class DepositeTransaction extends Transaction{

        DepositeTransaction(BankAccount bankAccount, double amount) {
            super(bankAccount, amount);
        }
        @Override
        void process() {
            bankAccount.deposite(amount);
            System.out.println(amount+" successfully deposited on your account "+bankAccount.getAccountNumber()+" remaining balances "+bankAccount.getBalance());
        }
    }
    public static class BalanceTransaction extends Transaction{

        BalanceTransaction(BankAccount bankAccount, double amount) {
            super(bankAccount, 0);
        }

        @Override
        void process() {
            System.out.println("Current Balance is "+bankAccount.getBalance());
        }
    }

    public class TransactionFactory{
        public static Transaction createTransaction(String type,BankAccount bankAccount,double amount)
        {
            if(type==null || type.isEmpty())
            {
                return null;
            }
            switch (type.toLowerCase())
            {
                case "withDraw":
                    return new WithDrawTransaction(bankAccount,amount);
                case "deposite" :
                    return new DepositeTransaction(bankAccount,amount);
                case "balance":
                    return new BalanceTransaction(bankAccount,amount);
                default:
                    return null;
            }
        }
    }
    public static class ATM{
        public static ATM instance;
        private ATM(){};
        public static ATM getInstance()
        {
            if (instance == null) {
                instance=new ATM();
            }
            return instance;
        }
        Map<String,Card> cards = new HashMap<>();

        public void registryCard(Card card){
            cards.put(card.getCardNumber(),card);
        }

        public void performTransaction(String type,String pin,String cardNumber,double amount){
            Card card = cards.get(cardNumber);
            if(card==null)
            {
                System.out.println("Invalid card details "+cardNumber);
                return;
            }
            if(!card.validatePin(pin))
            {
                System.out.println("Invalid Pin "+pin+" card number "+cardNumber);
                return;
            }
            Transaction transaction = TransactionFactory.createTransaction(type, card.getLinkedAccount(), amount);
            transaction.process();
        }
    }
    public static void main(String[] args) {
        BankAccount account = new BankAccount("acc1","monu",800);
        BankAccount bankAccount = new BankAccount("acc2","samrat",500);

        Card card = new Card("card123",account,"123");
        Card card1 = new Card("card234",bankAccount,"908");

        ATM atm = ATM.getInstance();
        atm.registryCard(card);
        atm.registryCard(card1);

        atm.performTransaction("deposite","123","card123",400);
        atm.performTransaction("deposite","143","card123",900);
    }
}
