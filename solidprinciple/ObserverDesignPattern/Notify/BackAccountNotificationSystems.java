package solidprinciple.ObserverDesignPattern.Notify;

import java.util.ArrayList;
import java.util.List;

public class BackAccountNotificationSystems {
    public interface BankAccount{
        void addChannels(CustomersChannels channels);
        void removeChannels(CustomersChannels channels);
        void notifyCustomers(String msg);
    }
    public static class BankAccountImpl implements BankAccount{

        public int amount=0;
        public final List<CustomersChannels> customersChannels = new ArrayList<>();
        @Override
        public void addChannels(CustomersChannels channels) {
            customersChannels.add(channels);
        }

        @Override
        public void removeChannels(CustomersChannels channels) {
            customersChannels.remove(channels);
        }

        @Override
        public void notifyCustomers(String msg) {
            for(CustomersChannels channels : customersChannels)
            {
                channels.updates(msg);
            }
        }
        public void credit(int amount){
            this.amount+=amount;
            String msg = String.valueOf(amount) + " Successfully  credited! Remaining amount is: " + String.valueOf(this.amount);
            notifyCustomers(msg);
        }
        public void debit(int amount)
        {
            if(amount>0){
                this.amount-=amount;
                String msg = String.valueOf(amount)+" Successfully debited! Remaining amount is: "+ String.valueOf(this.amount);
                notifyCustomers(msg);
            }
        }
    }
    public interface CustomersChannels{
        void updates(String msg);
    }

    public static class EmailChannel implements CustomersChannels{

        public String name;

        EmailChannel(String name)
        {
            this.name=name;
        }
        @Override
        public void updates(String msg) {
            System.out.println("Emailed to "+ name +" "+ msg);
        }
    }
    public static class PushChannel implements CustomersChannels{

        public String name;
        PushChannel(String name)
        {
            this.name=name;
        }
        @Override
        public void updates(String msg) {
            System.out.println("Pushed to "+name+ " "+msg);
        }
    }
    public static void main(String[] args) {
        BankAccountImpl bankAccount = new BankAccountImpl();
        EmailChannel emailChannel = new EmailChannel("monu");
        PushChannel pushChannel = new PushChannel("monu");

        bankAccount.addChannels(emailChannel);
        bankAccount.addChannels(pushChannel);

        bankAccount.credit(1000);
        bankAccount.debit(200);

        bankAccount.removeChannels(pushChannel);

        bankAccount.credit(1000);

    }
}
