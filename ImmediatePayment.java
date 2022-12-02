import java.util.Date;

public class ImmediatePayment extends Payment{
    private boolean PhoneConfirmation;

    public ImmediatePayment(String id, float total, String details, Order order, Account account, boolean phoneConfirmation) {
        super(id, total, details, order, account);
        this.PhoneConfirmation = phoneConfirmation;
    }

    public boolean isPhoneConfirmation() {
        return PhoneConfirmation;
    }

    public void setPhoneConfirmation(boolean phoneConfirmation) {
        PhoneConfirmation = phoneConfirmation;
    }
}
