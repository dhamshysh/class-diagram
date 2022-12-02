import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String number;
    private Date ordered;
    private Date shipped;
    private String ship_to;
    private OrderStatus status;
    private float total;
    private List<LineItem> lineItems;
    private List<Payment> payments;
    private Account account;

    public Order(String number, String ship_to, Account account) {
        this.number = number;
        this.ordered = new Date();
        this.shipped = null;
        this.ship_to = ship_to;
        this.status = OrderStatus.New;
        this.total = 0;
        this.account = account;
        this.payments = new ArrayList<>();
        this.lineItems = new ArrayList<>();
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
    public List<Payment> getPayments() {
        return payments;
    }

//    public boolean addLineItem(LineItem lineItem) {
//        if (lineItem == null || this.lineItems.contains(lineItem)){
//            return false;
//        }
//        if (this.equals(lineItem.getMyOrder())){
//            lineItems.add(lineItem);
//            return true;
//        }
//        if (lineItem.getMyOrder() != null){
//            lineItem.addOrder(this);
//            lineItems.add(lineItem);
//            return true;
//        }
//        return false;
//    }
//    public boolean deleteLineItem(LineItem lineItem){
//        if (lineItems.contains(lineItem)){
//            lineItems.remove(lineItem);
//            return true;
//        }
//        return false;
//    }
//    public boolean deletePayment(Payment payment){
//        if (payments.contains(payment)){
//            payments.remove(payment);
//            return true;
//        }
//        return false;
//    }
//    public boolean addPayment(Payment payment) {
//        if (payment == null || this.payments.contains(payment)){
//            return false;
//        }
//        if (this.equals(payment.getOrder())){
//            payments.add(payment);
//            return true;
//        }
//        if (payment.getOrder() != null){
//            payment.setOrder(this);
//            payments.add(payment);
//            return true;
//        }
//        return false;
//    }

public boolean addLineItem(LineItem lineItem) {
    if (lineItem != null && lineItem.getMyOrder() != null){
        this.lineItems.add(lineItem);
        lineItem.addOrder(this);
        return true;
    }
    return false;
}
    public boolean addPayment(Payment payment) {
        if (payment != null && payment.getOrder() != null){
            payment.setOrder(this);
            payments.add(payment);
            return true;
        }
        return false;
    }
    public boolean deleteLineItem(LineItem li){
        if(li != null && lineItems.contains(li)){
            li.removeOrder();
            lineItems.remove(li);
            return true;
        }
        return false;
    }
    public boolean deletePayment(Payment p){
        if (p!= null && payments.contains(p)){
            payments.remove(p);
            return true;
        }
        return false;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getOrdered() {
        return ordered;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public Date getShipped() {
        return shipped;
    }

    public void setShipped(Date shipped) {
        this.shipped = shipped;
    }

    public String getShip_to() {
        return ship_to;
    }

    public void setShip_to(String ship_to) {
        this.ship_to = ship_to;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Account getAccount() {
        return account;
    }

    public boolean setAccount(Account account) {
        if (account != null) {
            Account currentAcc = this.account;
            this.account = account;
            if (account.equals(currentAcc)){
                return true;

            }
            this.account.addOrder(this);
            return true;
        }
        return false;
    }
    public void removeOrder() {
        for (LineItem lineItem : lineItems) {
            lineItems.remove(lineItem);
        }
        for (Payment payment:payments){
            payments.remove(payment);
        }
    }
}
