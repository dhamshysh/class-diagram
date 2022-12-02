import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PremiumAccount extends Account{
    private List<Product> products;
    public PremiumAccount(Customer customer, String billing_address, int balance) {
        super(customer, billing_address, balance);
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p){
        if (p != null && !products.contains(p)){
            products.add(p);

            p.setPremiumAccount(this);
        }
    }
    public boolean deleteProduct(Product p){
        if (p != null && this.products.contains(p)){
            products.remove(p);
            return true;
        }
        return false;
    }
    @Override
    public void closeTheAccount() {
        super.closeTheAccount();
        List<Product> temp = new ArrayList<>();
        for (Product p : products){
            temp.add(p);
        }
        for (Product product:temp){
            product.removeProductFromLineItem();
            products.remove(product);
        }

    }


}

