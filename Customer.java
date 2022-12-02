import java.util.Objects;

public class Customer {
    private String id;
    private String address;
    private String phone;
    private String email;
    private User user;
    private Account account;


    public Customer(User user,String id, String address, String phone, String email, Account account) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.account = account;
        this.user = user;
    }

    public Customer(String id, String address, String phone, String email,String billing_address,int balance,boolean premium) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        if (premium){
            account = new PremiumAccount(this,billing_address,balance);
        }
        else
            account = new Account(this,billing_address,balance);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public boolean deleteUser(User u){
        if (user == u) {
            user = null;
            return true;
        }
        return false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }



    public boolean addUser(User u){
        if (this.user != null || Objects.equals(this.user, u))
            return false;
        this.user=u;
        return true;

//        if (u != null){
//            if (u.getCustomer() == this || u.getCustomer() == null ){
//                this.user = u;
//                Customer old = u.getCustomer();
//                if (!Objects.equals(old,this)){
//                    old.user = null;
//                    this.user.setCustomer(this);
//                    return true;
//                }
//            }
//        }
 //       return false;
    }
    public boolean deleteAccount(Account a){
        if (account == a) {
            account = null;
            return true;
        }
        return false;
    }
}
