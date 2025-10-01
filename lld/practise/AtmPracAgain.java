package lld.practise;

import java.util.HashMap;
import java.util.Map;

public class AtmPracAgain {
    public static class BankAccount{
        public String accountNumber;
        public String accountHolder;
        public double balance;

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
        public boolean withdrawal(double amount){
            if(amount<=balance){
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
        public String cardNumber;
        public BankAccount linkedAccount;
        public String pinCode;

        public Card(String cardNumber, BankAccount linkedAccount, String  pinCode) {
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

        public String getPinCode() {
            return pinCode;
        }

        public boolean validatePinCode(String  pincode){
            return this.pinCode.equalsIgnoreCase(pincode);
        }
    }
    public static abstract class Transaction{
        public BankAccount account;
        public double amount;

        public Transaction(BankAccount account, double amount) {
            this.account = account;
            this.amount = amount;
        }
        public abstract void process();
    }
    public static class WithDrawalAmount extends Transaction{

        public WithDrawalAmount(BankAccount account, double amount) {
            super(account, amount);
        }

        @Override
        public void process() {
            if(account.withdrawal(amount)){
                System.out.println("Amount withdrawal "+amount+" from account "+account+" remaining balace "+ account.getBalance());
            }else{
                System.out.println("Insufficient balance please check your balance "+account.getBalance());
            }
        }
    }
    public static class DepositeAmount extends Transaction{

        public DepositeAmount(BankAccount account, double amount) {
            super(account, amount);
        }

        @Override
        public void process() {
            account.deposite(amount);
            System.out.println("Amount deposited successfully "+amount+" Available balance is "+account.getBalance());
        }
    }
    public static class BalanceCheckAmount extends Transaction{

        public BalanceCheckAmount(BankAccount account, double amount) {
            super(account, amount);
        }

        @Override
        public void process() {
            System.out.println("Current Balance is "+account.getBalance());
        }
    }

    public class TransactionFactory{
        public static Transaction getTransactionType(String type,double amount,BankAccount bankAccount){
            if(type==null || type.isEmpty()){
                throw new IllegalArgumentException("type can not be null");
            }
            switch (type.toLowerCase()){
                case "withdrawal":
                    return new WithDrawalAmount(bankAccount,amount);
                case "balancecheck":
                    return new BalanceCheckAmount(bankAccount,amount);
                case "deposite":
                    return new DepositeAmount(bankAccount,amount);
                default:
                    throw new UnsupportedOperationException("Type not supported "+type);
            }
        }
    }
    public static class ATM{
        public static ATM instance;
        private ATM(){}
        public static ATM getInstance(){
            if(instance==null){
                instance = new ATM();
            }
            return instance;
        }
        Map<String,Card> cards =new HashMap<>();

        public void registerCard(Card card){
            cards.put(card.getCardNumber(),card);
        }

        public void performTransaction(String cardNumber,String type,double amount,String pin){
            Card card = cards.get(cardNumber);
            if (card==null)
            {
                System.out.println("Invalid card");
                return;
            }
            if (!card.validatePinCode(pin)){
                System.out.println("Invalid pin!");
                return;
            }
            Transaction transactionType = TransactionFactory.getTransactionType(type,amount,card.getLinkedAccount());
            transactionType.process();
        }
    }
    public static void main(String[] args) {
        ATM atm = ATM.getInstance();
        BankAccount account = new BankAccount("1234","mk",0);
        Card card = new Card("1234-card",account,"123");

        atm.registerCard(card);
        atm.performTransaction("1234-card","balancecheck",0,"123");
    }
}
