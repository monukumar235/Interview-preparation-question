package solidprinciple.ObserverDesignPattern.Notify;

import java.util.ArrayList;
import java.util.List;

public class StockAlertSystem {
    public interface Stock{
        void addInvestor(Investor investor);
        void removeInvestor(Investor investor);
        void send();
    }

    public static class Treader implements Stock{

        public int prices;
        public String name;
        public List<Investor> investors = new ArrayList<>();
        Treader(String name)
        {
            this.name=name;
        }
        @Override
        public void addInvestor(Investor investor) {
            investors.add(investor);
        }

        @Override
        public void removeInvestor(Investor investor) {
            investors.remove(investor);
        }

        @Override
        public void send() {
            for (Investor investor: investors)
            {
                investor.updates(name+ " prices has been changed "+prices);
            }
        }
        public void setPrices(int prices)
        {
            this.prices = prices;
            send();
        }
    }

    public interface Investor{
        void updates(String msg);
    }
    public static class InvestorImpl implements Investor{

        public String name;

        InvestorImpl(String name)
        {
            this.name=name;
        }
        @Override
        public void updates(String msg) {
            System.out.println("notified "+ name + " "+msg);
        }
    }
    public static void main(String[] args) {
        Treader treader = new Treader("Google");
        treader.addInvestor(new InvestorImpl("bob"));
        treader.addInvestor(new InvestorImpl("alices"));

        treader.setPrices(3000);
    }
}
