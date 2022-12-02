import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private String name;
    private Supplier supplier;
    private List<LineItem> lineItems;
    private PremiumAccount premiumAccount;

    public Product(String id, String name,Supplier supplier) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.lineItems = new ArrayList<>();
        this.premiumAccount = null;
    }

    public Product(String id, String name,Supplier supplier,List<LineItem> LI,PremiumAccount PR) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        lineItems = new ArrayList<>();
        for (LineItem l:LI
        ) {
            assert (l.getProduct() != null && l.getProduct()!= this);
            this.lineItems.add(l);
            l.setProduct(this);

        }
        this.premiumAccount = PR;
    }
    public boolean addLineItem(LineItem lineItem){
        if (lineItems.contains(lineItem)){
            return false;
        }
        if (!lineItem.getProduct().equals(this)) {
            lineItem.setProduct(this);
        }
        else{
            lineItems.add(lineItem);
        }
        return true;
    }
    public boolean removeLineItem(LineItem lineItem){
        if (lineItem == null){
            return false;
        }
        if(lineItems.contains(lineItem)) {
            lineItems.remove(lineItem);
            return true;
        }
        return false;
    }

    public PremiumAccount getPremiumAccount() {
        return premiumAccount;
    }

    public boolean setPremiumAccount(PremiumAccount premiumAccount) {
        if (premiumAccount==null)
            return false;
        PremiumAccount current = this.premiumAccount;
        this.premiumAccount = premiumAccount;
        if (current != null){
            if (!premiumAccount.equals(current)){
                premiumAccount.deleteProduct(this);
            }
        }
        this.premiumAccount.addProduct(this);
        return true;
    }
    public boolean deletePremiumAccount(PremiumAccount pa){
        assert(premiumAccount != pa);
        this.premiumAccount = null;
        return true;
    }
    public List<LineItem> getLineItems() {
        return lineItems;
    }


    public Supplier getSupplier() {
        return supplier;
    }

    public boolean setSupplier(Supplier supplier) {
        if (supplier==null)
            return false;
        Supplier current = this.supplier;
        this.supplier = supplier;
        if (current != null){
            if (!supplier.equals(current)){
                supplier.removeProduct(this);
            }
        }
        this.supplier.addProduct(this);
        return true;
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

    public void removeProductFromLineItem(){
        for (LineItem lineItem: lineItems){
            lineItems.remove(lineItem);
        }
    }


}
