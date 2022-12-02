import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private Date created;
    private List<LineItem> lineItemList;
    private Account account;
    private User user;
    //we removed the creation date

    public ShoppingCart( Account account, User user) {
        this.created =new Date();
        this.account = account;
        this.user = user;
        lineItemList = null;
    }

    public User getUser() {
        return user;
    }

    public boolean addUser(User user) {
        if (user != null) {
            if (this.user == null){
                this.user = user;
                this.user.addShoppingCart(this);
                return true;
            }
            if (this.user.getShoppingCart() != null && !equals(this.user.getShoppingCart())) {
                return false;
            }
            User old = this.user;
            this.user.addShoppingCart(this);
            old.deleteShoppingCart(this);
            //old.deleteShoppingCart(old.getShoppingCart());
            return true;
        }
        return false;
    }
    public Account getAccount() {
        return account;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    public boolean addLineItem(LineItem lineItem){
        boolean added = false;
        if (lineItemList.contains(lineItem)){
            return false;
        }
        lineItemList.add(lineItem);
        added = lineItem.addShoppingCart(this);
        if (added == false)
            lineItemList.remove(lineItem);
        return added;
    }
    public boolean deleteLineItem(LineItem li){
        boolean deleted = false;
        int ind = 0;
        if(!lineItemList.contains(li)){
            return false;
        }
        ind = lineItemList.indexOf(li);
        lineItemList.remove(ind);
        deleted = li.addShoppingCart(this);
        if (deleted == false){
            lineItemList.add(ind,li);
        }
        return deleted;
    }
    public boolean setAccount(Account account){
        if (account == null) {
            return false;
        }
        this.account=account;
        return true;
    }

}
