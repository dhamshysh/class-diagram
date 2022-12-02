import java.util.Objects;

public class User {
    private String login_id;
    private String password;
    private UserState state;
    private ShoppingCart shoppingCart;
    private Customer customer;
    private boolean ActiveUser;

    public User(String login_id, String password, UserState state, Customer customer) {
        this.login_id = login_id;
        this.password = password;
        this.state = state;
        this.customer = customer;
        this.shoppingCart = null;
        ActiveUser=false;
    }
    public User(String login_id, String password, ShoppingCart shoppingCart, Customer customer) {
        this.login_id = login_id;
        this.password = password;
        this.state = UserState.New;
        this.shoppingCart = shoppingCart;
        this.customer = customer;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


    public void logOut(){
        ActiveUser=false;
    }
    public void Login(){ActiveUser=true;}
    public boolean IsActive(){return ActiveUser;}

    public Customer getCustomer() {
        return customer;
    }

    public boolean addShoppingCart(ShoppingCart sc) {
        if (this.shoppingCart != null || Objects.equals(this.shoppingCart, sc))
            return false;

        this.shoppingCart = sc;
        return true;
    }

    public boolean deleteShoppingCart(ShoppingCart sc){
        if (shoppingCart == sc) {
            shoppingCart = null;
            return true;
        }
        return false;

    }
    public boolean setCustomer(Customer c){
        if (c== null && Objects.equals(c,this.customer)){
            return false;
        }
        Customer old = c;
        this.customer = c;
        old.addUser(null);
        this.customer.addUser(this);
        return true;


    }

}
