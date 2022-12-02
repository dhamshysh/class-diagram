public class LineItem {
    private int quantity;
    private int price;
    private Product product;
    private ShoppingCart shoppingCart;
    private Order myOrder;

    public LineItem(Order order ,int quantity, int price,Product product, ShoppingCart shoppingCart) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.shoppingCart = shoppingCart;
        this.myOrder = order;

    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Order getMyOrder() {
        return myOrder;
    }
    public void removeProduct(){
        Product p = this.product;
        this.product = null;
        if (p!=null){
            p.removeLineItem(this);
        }
    }
    public boolean setProduct(Product p){
        if (p==null)
            return false;
        Product current = this.product;
        this.product = p;
        if (current != null){
            if (!p.equals(current)){
                p.removeLineItem(this);
            }
        }
        this.product.addLineItem(this);
        return true;
    }
    public void removeOrder(){
        Order o = this.myOrder;
        this.myOrder = null;
        if (o!=null){
            o.deleteLineItem(this);
        }
    }
    public boolean addOrder(Order o){
        if (o==null)
            return false;

        myOrder=o;
        return true;
    }
    public boolean addShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart==null)
            return false;
        ShoppingCart current = this.shoppingCart;
        this.shoppingCart = shoppingCart;
        if (current != null){
            if (!shoppingCart.equals(current)){
                shoppingCart.deleteLineItem(this);
            }
        }
        this.shoppingCart.addLineItem(this);
        return true;
    }
}
