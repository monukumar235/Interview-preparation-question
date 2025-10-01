package lld;

import java.util.ArrayList;
import java.util.List;

public class StockAlertService {
    public interface Stock{
        void subscribe(Investors investors);
        void unsubscribe(Investors investors);
        void notifyInvestors(String msg);
    }
    public static class StockImpl implements Stock{

        int prices=0;
        public String  name;
        StockImpl(String name)
        {
            this.name = name;
        }

        public void setPrices(int prices)
        {
            this.prices+=prices;
            notifyInvestors(name+" "+"stock has been changes by :"+prices);
        }
        List<Investors> investorsList = new ArrayList<>();
        @Override
        public void subscribe(Investors investors) {
            investorsList.add(investors);
        }

        @Override
        public void unsubscribe(Investors investors) {
            investorsList.remove(investors);
        }

        @Override
        public void notifyInvestors(String msg) {
            for(Investors investors : investorsList)
            {
                investors.update(msg);
            }
        }
    }
    public interface Investors{
        void update(String msg);
    }
    public static class InvestorsImpl implements Investors{

        public String name;
        InvestorsImpl(String name)
        {
            this.name=name;
        }
        @Override
        public void update(String msg) {
            System.out.println(name+" "+msg);
        }
    }
    public static void main(String[] args) {
        StockImpl stock = new StockImpl("Google");
        Investors investors = new InvestorsImpl("monu");
        Investors investors1 = new InvestorsImpl("samrat");
        stock.subscribe(investors);
        stock.subscribe(investors1);

        stock.setPrices(1000);

        stock.unsubscribe(investors1);

        stock.setPrices(300);
    }
}
