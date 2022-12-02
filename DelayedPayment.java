import java.util.Date;

public class DelayedPayment extends Payment{
    private Date PaymentDate;

    public DelayedPayment(String id, float total, String details, Order order, Account account, Date paymentDatete) {
        super(id, total, details, order, account);
        this.PaymentDate = paymentDatete;
    }
    public Date getPaymentDate(){
        return this.PaymentDate;
    }
    public void setPaymentDate(Date paymentDate){
        this.PaymentDate = paymentDate;
    }
}
