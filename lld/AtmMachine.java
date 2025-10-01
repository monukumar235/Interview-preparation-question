package lld;

import java.util.HashMap;
import java.util.Map;

public class AtmMachine {
    public static class BankAccount{
        private String accountNumber;
        private String accountHolder;
        private double balance;

        public BankAccount(String accountNumber, String accountHolder, double balance) {
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.balance = balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getAccountHolder() {
            return accountHolder;
        }

        public double getBalance() {
            return balance;
        }
        public boolean withdraw(double amount)
        {
            if(balance>=amount)
            {
                balance-=amount;
                return true;
            }
            return false;
        }
        public void deposite(double amount)
        {
            balance+=amount;
        }
    }
    public static class Card{
        private String cardNumber;
        private BankAccount linkedAccount;
        private String pinCode;

        public Card(String cardNumber, BankAccount linkedAccount, String pinCode) {
            this.cardNumber = cardNumber;
            this.linkedAccount = linkedAccount;
            this.pinCode = pinCode;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public BankAccount getLinkedAccount() {
            return linkedAccount;
        }
        public boolean validatePin(String pinCode)
        {
            return this.pinCode.equalsIgnoreCase(pinCode);
        }
    }

    public static abstract class Transaction{
        public BankAccount bankAccount;
        public double amount;

        public Transaction(BankAccount bankAccount, double amount) {
            this.bankAccount = bankAccount;
            this.amount = amount;
        }
        abstract void process();
    }

    public static class WithDrawalProcess extends Transaction{

        public WithDrawalProcess(BankAccount bankAccount, double amount) {
            super(bankAccount, amount);
        }

        @Override
        void process() {
            if(bankAccount.withdraw(amount)){
                System.out.println(amount+" Withdraw from you account "+bankAccount.getAccountNumber() +" remaining balance is "+bankAccount.getBalance());
            }else {
                System.out.println("Insufficient balance"+bankAccount.getBalance());
            }
        }
    }
    public static class DepositeAmount extends Transaction{

        public DepositeAmount(BankAccount bankAccount, double amount) {
            super(bankAccount, amount);
        }

        @Override
        void process() {
            bankAccount.deposite(amount);
            System.out.println(amount+" deposited on you account "+bankAccount.getAccountNumber()+" Balance is "+bankAccount.getBalance());
        }
    }
    public static class BalanceCheck extends Transaction{

        public BalanceCheck(BankAccount bankAccount, double amount) {
            super(bankAccount, 0);
        }

        @Override
        void process() {
            System.out.println("Your current balance is "+bankAccount.getBalance());
        }
    }

    public class TransactionFactory{
        public  static Transaction createTransaction(String type,BankAccount accNumber,double amount){
            if(type==null || type.isEmpty())
            {
                throw new IllegalArgumentException("Type can not be null");
            }
            switch (type.toLowerCase()){
                case "withdraw":
                    return new WithDrawalProcess(accNumber,amount);
                case "deposite":
                    return new DepositeAmount(accNumber,amount);
                case "balance":
                    return new BalanceCheck(accNumber,amount);
                default:
                    throw new UnsupportedOperationException("Unsupported Type "+type);
            }
        }
    }

    public static class ATM{
        public static ATM instance;
        private ATM(){}
        public static ATM getInstance(){
            if (instance==null)
            {
                instance =new ATM();
            }
            return instance;
        }
        Map<String,Card> cards= new HashMap<>();

        public void registryCards(Card card){
            cards.put(card.getCardNumber(),card);
        }
        public void performTransaction(String cardNumber,String pin,String type,double amount){
            Card card = cards.get(cardNumber);
            if(card==null){
                System.out.println("Invalid Card Details "+cardNumber);
                return;
            }
            if(!card.validatePin(pin))
            {
                System.out.println("Invalid Pin "+pin);
                return;
            }
            BankAccount linkedAccount = card.getLinkedAccount();
            Transaction transaction = TransactionFactory.createTransaction(type, linkedAccount, amount);
            transaction.process();
        }
    }
    public static void main(String[] args) {

        BankAccount acc1 = new BankAccount("acc123","mohit",900);
        BankAccount acc2 = new BankAccount("acc124","rohit",800);

        Card card = new Card("card123",acc1,"123");
        Card card1 = new Card("card124",acc2,"1998");

        ATM atm = ATM.getInstance();

        atm.registryCards(card);
        atm.registryCards(card1);

        atm.performTransaction("card123","123","deposite",3000);
        atm.performTransaction("card123","123","balance",0);
        atm.performTransaction("card123","123","withdraw",700);


    }
}
