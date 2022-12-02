import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private String id;
    private String name;
    private List<Product> products;

    public Supplier(String id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Product> getProducts(){return this.products;}
    public boolean addProduct(Product p){
        if (p == null || this.products.contains(p)){
            return false;
        }
        if (this.equals(p.getSupplier())){
            products.add(p);
            return true;
        }
        if (p.getSupplier() != null){
            p.setSupplier(this);
            products.add(p);
            return true;
        }
        return false;
    }
    public boolean removeProduct(Product p){
        if (p == null || !products.contains(p)){
            return false;
        }
        products.remove(p);
        return true;
    }
}
