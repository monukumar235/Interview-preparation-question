package abstractfactory;

import java.util.HashMap;
import java.util.Map;

public class Products {
    public static class Product{
        public int productId;
        public String name;
        public int price;

        @Override
        public String toString() {
            return "Product{" +
                    "productId=" + productId +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }

        public Product(int productId, String name, int price) {
            this.productId = productId;
            this.name = name;
            this.price = price;
        }

        public int getProductId() {
            return productId;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }
    }
    public static class CartItem{
        public Product product;
        public int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "CartItem{" +
                    "product=" + product +
                    ", quantity=" + quantity +
                    '}';
        }
        public int getTotalPrice(){
            return product.getPrice()*quantity;
        }
    }
    public static class Cart{
        Map<Integer,CartItem> card = new HashMap<>();

        public void addToCard(Product product,int quantity){
            if(card.containsKey(product.getProductId())){
                CartItem existingItem = card.get(product.getProductId());
                existingItem.setQuantity(existingItem.getQuantity()+quantity);
            }else{
                card.put(product.getProductId(),new CartItem(product,quantity));
            }
        }

        public void remove(int productId){
            if(card.containsKey(productId)){
                card.remove(productId);
            }
        }

        public void viewCard(){
            if(card.isEmpty()){
                System.out.println("Cart is empty!");
                return;
            }
            else{
             for (CartItem cartItem : card.values()){
                 System.out.println(cartItem);
             }
            }
        }
        public void updateCart(int productId,int quantity)
        {
            if(card.containsKey(productId))
            {
                CartItem existingItems = card.get(productId);
                existingItems.setQuantity(quantity);
            }
        }
        public int getTotalPrice(){
            return card.values().stream()
                    .mapToInt(CartItem::getTotalPrice)
                    .sum();
        }
    }
    public static void main(String[] args) {
        Cart cart = new Cart();
        Product p1 = new Product(1,"s24",1000);
        Product p2 = new Product(2,"s25",2000);
        cart.addToCard(p1,2);
        cart.addToCard(p2,1);
        System.out.println(cart.getTotalPrice());

        cart.viewCard();
    }
}
