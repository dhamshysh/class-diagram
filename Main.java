import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.System;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //////////////////////////////////////////////





        String id,password,address,productName,o_id,u_id;
       SystemManger manger=new SystemManger();
        Scanner myObj = new Scanner(System.in);
        int choice = -1;
        while (choice != 0){
            System.out.println("#############################");
            System.out.println("1. Add User *login_id* ");
            System.out.println("2. Remove User *login_id* ");
            System.out.println("3. login user *Login_id*   *password* ");
            System.out.println("4. Logout user *Login_id*");
            System.out.println("5. Create new order *address*");
            System.out.println("6. Add product to order *Order_ID* *Login_ID* *Product Name*");
            System.out.println("7. Display order");
            System.out.println("8. Link Product *Product_Name* *Price* *Quantity*");
            System.out.println("9. Add Product *Product_Name* *Supplier_Name*");
            System.out.println("10. Delete Product *Product_name*");
            System.out.println("11. ShowAllObjects");
            System.out.println("12. ShowObjectId *id*");
            choice =scanner.nextInt();
            switch (choice){
                // add user
                case 1:
                    System.out.println("Enter your ID :");
                    id=myObj.nextLine();
                    manger.AddUser(id);

                    break;
                case 2:
                    System.out.println("Enter your ID :");
                     id=myObj.nextLine();

                     manger.RemoveUser(id);



                    break;
                case 3:
                    System.out.println("Enter your ID :");
                    id=myObj.nextLine();
                    System.out.println("Enter your Password :");
                    password=myObj.nextLine();
                    manger.LoginUser(id,password);
                    break;
                case 4:
                    System.out.println("Enter your ID :");
                    id=myObj.nextLine();
                    if (manger.getActiveUser().getLogin_id().equals(id)){
                        manger.logout(id);
                        System.out.println("User has logged out");

                    }
                    else
                        System.out.println(id + " is not the active user");
                    break;
                case 5:
                    System.out.println("Enter your Address :");
                    address=myObj.nextLine();
                    manger.CreateNewOrder(address);
                    break;
                case 6:
                    System.out.println("Enter order number :");
                    o_id=myObj.nextLine();
                    System.out.println("Enter your ID :");
                    u_id=myObj.nextLine();
                    System.out.println("Enter product name :");
                    productName=myObj.nextLine();
                    manger.AddProductToOrder(o_id,u_id,productName);

                    break;
                case 7:
                    manger.DisplayOrder();
                    break;
                case 8:
                    System.out.println("Enter your Product Name :");
                    id=myObj.nextLine();
                    System.out.println("Enter your Price :");
                    int p=myObj.nextInt();
                    System.out.println("Enter your Quantity :");
                    int q=myObj.nextInt();
                    manger.LinkProduct(id,p,q);
                    break;
                case 9:
                    System.out.println("Enter your Product Name :");
                     productName=myObj.nextLine();
                    System.out.println("Enter your Supplier Name :");
                    String Sname=myObj.nextLine();
                    manger.AddProduct(productName,Sname);
                    break;
                case 10:
                    System.out.println("Enter your Product Name :");
                    productName=myObj.nextLine();
                    manger.DeleteProduct(productName);
                    break;
                case 11:
                    manger.ShowAllObjects();
                    break;
                case 12:
                    System.out.println("Enter ID:");
                    id = myObj.nextLine();
                    manger.ShowObjectId(id);
                    break;

                default:
                    System.out.println("Please pick a number between 1 to 12");
            }
        }
    }
}
