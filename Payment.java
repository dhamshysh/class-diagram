import java.util.Date;

public abstract class Payment {
    private String id;
    private Date paid;
    private float total;
    private String details;
    private Order order;
    private Account account;

    public Payment(String id, float total, String details, Order order, Account account) {
        this.id = id;
        this.paid = new Date();
        this.total = total;
        this.details = details;
        this.order = order;
        this.account = account;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPaid() {
        return paid;
    }

    public void setPaid(Date paid) {
        this.paid = paid;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Order getOrder() {
        return order;
    }

    public boolean setOrder(Order order) {
        if (order != null){
            Order currentOrder = this.order;
            this.order = order;
            if (order.equals(currentOrder)){
                return true;
            }
            this.order.addPayment(this);
            return true;
        }
        return false;



    }

    public Account getAccount() {
        return account;
    }

    public boolean setAccount(Account account) {
        if (account != null) {
            Account currentAcc = this.account;
            this.account = account;
            if (account.equals(currentAcc)) {
                return true;

            }
            this.account.addPayment(this);
            return true;
        }
        return false;
    }
}
