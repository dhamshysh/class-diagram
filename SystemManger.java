
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class SystemManger {

    private List<Order> orderList;
    private List<User>userList;
    private List<Product>products;
    private List<Supplier> suppliers;
    private User ActiveUser;
    private int orderNumber;

    public User getActiveUser() {
        return ActiveUser;
    }

    public SystemManger() {
        this.orderList = new ArrayList<Order>();
        this.userList=new ArrayList<User>();
        this.products=new ArrayList<Product>();
        suppliers=new ArrayList<Supplier>();
        ActiveUser=null;
        orderNumber=1;

        // Supplier Osem
        Supplier Osem = new Supplier("Osem","Osem");
        suppliers.add(Osem);

        Supplier EastWest = new Supplier("EastWest","EastWest");
        suppliers.add(EastWest);
        Product Bamba = new Product("Bamba","Bamba",Osem);
        products.add(Bamba);
        Osem.addProduct(Bamba);
        Product Ramen = new Product("Ramen","Ramen",EastWest);
        products.add(Ramen);
        EastWest.addProduct(Ramen);
        Customer daniCustomer = new Customer("Dani", "", "","","",0,false);
        ShoppingCart daniSC = daniCustomer.getAccount().getShoppingCart();
        User daniUser = new User("Dani","Dani123",daniSC,daniCustomer);
        daniCustomer.addUser(daniUser);
        daniSC.addUser(daniUser);
        userList.add(daniUser);

        Customer danaCustomer = new Customer("Dana","","","","",0,true);
        ShoppingCart DanaSC = danaCustomer.getAccount().getShoppingCart();
        User DanaUser = new User("Dana","Dana123",daniSC,danaCustomer);
        danaCustomer.addUser(DanaUser);//
        DanaSC.addUser(DanaUser);
        ((PremiumAccount)danaCustomer.getAccount()).addProduct(Bamba);
        userList.add(DanaUser);
    }


    public boolean AddUser(String id){

      // CHICK IF USER EXIST
        for (User user : userList) {
            if (id.equals(user.getLogin_id())) {
                System.out.println("The user is exists");
                return false;
            }

        }

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter your password");
        String password = myObj.nextLine();
        UserState state=UserState.New;
        //create customer
        System.out.println("Enter your address");
        String address=myObj.nextLine();
        System.out.println("Enter your phone number");
        String phone=myObj.nextLine();
        System.out.println("Enter your email");
        String email=myObj.nextLine();
        System.out.println("Enter your billing_address");
        String billing_address=myObj.nextLine();
        System.out.println("Enter your balance");
        int balance= myObj.nextInt();
        boolean premium=false;
        myObj.nextLine();
        System.out.println("Premium Account? yes/no");
        String temp=myObj.nextLine();

        if(temp.equals("yes")) {
            premium = true;
        }

        Customer customer=new Customer(id,address,phone,email,billing_address,balance,premium);

        User user=new User(id,password,state,customer);
        customer.getAccount().getShoppingCart().addUser(user);
        user.addShoppingCart(customer.getAccount().getShoppingCart());
        userList.add(user);

        return true;
    }

    public void RemoveUser(String id) {
        for (User user : userList) {
            if (id.equals(user.getLogin_id())) {
                if(user.IsActive()) {
                    ActiveUser = null;
                }
                orderList.remove(user.getCustomer().getAccount().getLastOrder());
                user.getCustomer().getAccount().closeTheAccount();
                userList.remove(user);

                return;

            }


        }
    }


    public void LoginUser(String id,String password){

        for (User user : userList) {
            if (id.equals(user.getLogin_id())){
                if(user.getPassword().equals(password)){
                    if(ActiveUser==null) {
                        user.Login();
                        user.setState(UserState.Active);
                        ActiveUser=user;
                        break;
                    }else {
                        ActiveUser.logOut();
                        ActiveUser.setState(UserState.Blocked);
                        user.Login();
                        user.setState(UserState.Active);
                        ActiveUser=user;
                    }
                }
                }
            }
    }
    public void logout(String id){
        for (User user : userList) {
            if (id.equals(user.getLogin_id())){
                if(user.IsActive()){
                    user.setState(UserState.Blocked);
                    user.logOut();
                    ActiveUser=null;
                }
            }
    }}

    public void CreateNewOrder(String address){
        Scanner myObj=new Scanner(System.in);
        if (ActiveUser == null){
            System.out.println("There is no active user");
            return;
        }
        Order order=new Order(String.valueOf(orderNumber),address,ActiveUser.getCustomer().getAccount());
        ActiveUser.getCustomer().getAccount().addOrder(order);
        System.out.println("Your Order Number Is : "+String.valueOf(orderNumber));
        orderNumber++;
        ActiveUser.getCustomer().getAccount().setLastOrder(order);
        orderList.add(order);
    }

    public  void AddProductToOrder(String Order_ID ,String Login_ID,String PName){
        for (User user : userList) {
            if (Login_ID.equals(user.getLogin_id())){
                if(!user.IsActive()){
                    user.Login();
                    if (ActiveUser!=null)
                          ActiveUser.logOut();
                    ActiveUser=user;

                }}
            Product temp_product=null;
            for (Product product:products){
                if(product.getName().equals(PName)){
                    temp_product=product;
                }
            }

            if (temp_product==null){
                System.out.println("No Such Prouduct");
                return;
            }
        for (Order order : orderList) {
            if (order.getNumber().equals(Order_ID)){

                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                System.out.println("Enter price of the product");
                int price=myObj.nextInt();
                System.out.println("Enter price of the quantity");
                int quantity=myObj.nextInt();
                ShoppingCart shoppingCart=ActiveUser.getCustomer().getAccount().getShoppingCart();
                LineItem lineItem=new LineItem(order,quantity,price, temp_product,shoppingCart);
                temp_product.addLineItem(lineItem);
                order.addLineItem(lineItem);
                return;
            }

            }
        System.out.println("No such order with this number");
        }
    }

    public void DisplayOrder(){
        if(ActiveUser!=null){
            Order last=ActiveUser.getCustomer().getAccount().getLastOrder();
            if (last == null){
                System.out.println("Order does not exist!");
            }
            else {
                System.out.println(last.getNumber() + " " + last.getOrdered() + " " + last.getShipped() + "  " + last.getShip_to()
                        + " " + last.getStatus() + " " + last.getTotal());
            }
            return;
        }
        System.out.println("There is no active user in the system.");
    }

   public void LinkProduct (String Product_Name, int Price,int Quantity){
        if (ActiveUser == null){
            System.out.println("There is no active user");
            return;
        }
        if(ActiveUser.getCustomer().getAccount() instanceof PremiumAccount){
            for (Product product:products){
                if(product.getName().equals(Product_Name)){
                    ((PremiumAccount) ActiveUser.getCustomer().getAccount()).addProduct(product);
                        return;
                        }
                    }

        }
       System.out.println("This account is not a Premium Account!");

   }
   public void  AddProduct (String Product_Name ,String Supplier_Name){

        for (Product product:products){
            if(product.getName().equals(Product_Name)){
                System.out.println("Product Exist");
            }

            for(Supplier supplier1:suppliers){
                if(supplier1.getName().equals(Supplier_Name)){
                    Product product1=new Product(Product_Name,Product_Name,supplier1);
                    //
                    supplier1.addProduct(product1);
                    //
                    products.add(product1);
                    return;
                }
            }

                System.out.println("Supplier Not Exist");



        }
   }

   public void DeleteProduct (String Product_name) {
       for (Product product : products) {
           if (product.getName().equals(Product_name)) {
               products.remove(product);
               product.getSupplier().removeProduct(product);
               System.out.println("Product has been deleted!");
               return;
           }
       }
       System.out.println("This product is not in the system!");
   }
   public void ShowAllObjects(){
       System.out.println("Users:");

       for (User user:userList){
           System.out.println(user.getLogin_id());
       }
       System.out.println("Customers:");
       for (User user:userList){
           System.out.println(user.getCustomer().getId());
       }

       System.out.println("Accounts:");
       {
           for (User user:userList){
               System.out.println(user.getCustomer().getAccount().getId());
           }

       }
       System.out.println("Products :");
       for (Product product:products){
           System.out.println(product.getId());
       }
       System.out.println("Supplier :");
       for (Supplier supplier:suppliers){
           System.out.println(supplier.getId());
       }
       System.out.println("Orders:");{
           for (Order order:orderList){
               System.out.println(order.getNumber());
           }
       }

       System.out.println("Payments:");
       {
           for (Order order:orderList){

               System.out.println(order.getNumber());
           }
       }
       }









    public void ShowObjectId(String id){


        for (User user:userList){
            if(user.getLogin_id().equals(id)){
                System.out.println("User Details : "+user.getLogin_id()+ " " + user.getPassword()+ " " + user.getState() );
                System.out.println("User's Customer: " + user.getCustomer().getId());
               return;
            }
        }
        for (Supplier supplier:suppliers){
            if(supplier.getId().equals(id)){
                System.out.println("Suppliers Details : " + supplier.getName() + " " +supplier.getId() );
                System.out.println("Products: ");
                for (Product p : supplier.getProducts()){
                    System.out.println(p.getName());
                }
                return;
            }
        }
        for (Order order:orderList){
            if(order.getNumber().equals(id)){
                System.out.println("Order Details : "+order.getNumber() +" " + order.getAccount());
                System.out.println("Order's account: " + order.getAccount());
                if (order.getPayments().size() != 0){
                    System.out.println("Order's Payments: ");
                    for (Payment l : order.getPayments()){
                        System.out.println(l.getId());
                    }
                }
                return;
            }
        }

        for (Product product:products){
            if (product.getId().equals(id)){
                System.out.println("Products Details : "+product.getId()+ " "+product.getName());
                if (product.getPremiumAccount() != null){
                    System.out.println("Product's account: " + product.getPremiumAccount().getId());
                }
                return;
            }
        }
        System.out.println("The ID is not exists in the system.");






    }


}



