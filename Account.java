import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;


public class Account {
    private String id;
    private String billing_address;
    private boolean is_closed;
    private Date open;
    private Date closed;
    private int balance;
    private ShoppingCart shoppingCart;
    private Customer customer;
    private List<Order> orderList;
    private List<Payment> paymentList;
    private static int counter = 1;


    private Order lastOrder;


    //we change the open date and the closed date that we remove it from constructor
    public Account(Customer customer, String billing_address,  int balance) {
        this.id = String.valueOf(counter);
        counter++;
        this.billing_address = billing_address;
        this.is_closed = false;
        this.open=new Date();

        this.balance = balance;
        this.customer = customer;
        this.shoppingCart = new ShoppingCart(this,customer.getUser());
        this.orderList = new ArrayList<>();
        this.paymentList = new ArrayList<>();
        lastOrder=null;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public boolean setShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart == null)
            return false;
        if (shoppingCart.getAccount() == null){
            shoppingCart.setAccount(this);
            this.shoppingCart = shoppingCart;
            return true;
        }
        return false;
    }
    public Customer getCustomer() {
        return customer;
    }

    public boolean setCustomer(Customer customer) {
        if (customer == null)
            return false;
        this.customer = customer;
        return true;
    }
    public boolean addPayment(Payment p){
        if ( p == null)
            return false;
        if (this.paymentList.contains(p)){
            return false;
        }
        if (p.getAccount() == null){
            p.setAccount(this);
        }
        if (this.equals(p.getAccount())){
            paymentList.add(p);
        }
        else{
            p.setAccount(this);
            paymentList.add(p);

        }
        return true;
    }
    public boolean deletePayment(Payment p){
        if (paymentList.contains(p)){
            paymentList.remove(p);
            p.setAccount(null);//check
            return true;
        }
        return false;
    }
    public boolean addOrder(Order o){
        if ( o == null)
            return false;
        if (this.orderList.contains(o)){
            return false;
        }
        if (o.getAccount() == null){
            o.setAccount(this);
        }
        if (this.equals(o.getAccount())){
            orderList.add(o);
        }
        else {
            o.setAccount(this);
            orderList.add(o);
        }
        return true;
    }
    public boolean deleteOrder(Order o){
        if (o != null && orderList.contains(o)){
            orderList.remove(o);
            o.setAccount(null);// check
            return true;
        }
        return false;
    }

    public Order getLastOrder() {
        return lastOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public Date getOpen() {
        return open;
    }

    public void setOpen(Date open) {
        this.open = open;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void closeTheAccount(){
        this.setIs_closed(true);
        ArrayList<Order> t1 = new ArrayList<>();
        ArrayList<Payment> t2 = new ArrayList<>();

        for (Order order:orderList){
            t1.add(order);
        }
        for (Payment payment:paymentList){
            t2.add(payment);
        }
        for (Order order:t1){
            order.removeOrder();
            orderList.remove(order);
        }
        for (Payment payment:t2){
            paymentList.remove(payment);
        }
    }
    public void setLastOrder(Order lastOrder) {
        this.lastOrder = lastOrder;
    }


}
