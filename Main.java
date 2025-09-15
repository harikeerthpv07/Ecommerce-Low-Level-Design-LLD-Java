import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) {
	    Store st1=new Store();
		Manager m1 = new Manager("Ravi", 120000); 
		SalesPerson s1 = new SalesPerson("Priya", 30000);
		SalesPerson s2 = new SalesPerson("Aman", 25000);
		
		st1.addEmployee(m1);
		st1.addEmployee(s1);
		st1.addEmployee(s2);
		
		
		st1.addProduct(new Electronics("Nothing Earbuds", 4000, 15));
        st1.addProduct(new Electronics("Samsung TV 43inch", 32000, 5));
        st1.addProduct(new Electronics("HP Laptop", 55000, 7));

        
        st1.addProduct(new Clothing("Zudio Jeans", 2000, 20));
        st1.addProduct(new Clothing("Nike Hoodie", 3500, 12));
        st1.addProduct(new Clothing("Puma T-Shirt", 1500, 25));

       
        st1.addProduct(new Food("Masala Dosa", 100, 30));
        st1.addProduct(new Food("Cheese Sandwich", 150, 20));
        st1.addProduct(new Food("Cold Coffee", 80, 40));
        
     
        st1.listProducts();

        st1.listEmployees();


        st1.sellProduct("HP Laptop", 2);
        st1.sellProduct("Cold Coffee", 5);
	
	}
}

abstract class Products{
   String name;
   double price;
   int quantity;
   Products(String name,double price,int quantity){
       this.name=name;
       this.price=price;
       this.quantity=quantity;
   }
   abstract void getInfo();
}

interface Discountable{
    double getDiscountedPrice();
}




class Electronics extends Products implements Discountable{
    Electronics(String name,double price,int quantity){
        super(name,price,quantity);
    }
    
    public void getInfo(){
        System.out.println("Name: "+super.name+", Price: "+super.price+", Quantity: "+super.quantity);
    }
    
    public double getDiscountedPrice(){
        return super.price-(super.price*.1);
    }
}

class Clothing extends Products implements Discountable{
    Clothing(String name,double price,int quantity){
        super(name,price,quantity);
    }
    
    public void getInfo(){
        System.out.println("Name: "+super.name+", Price: "+super.price+", Quantity: "+super.quantity);
    }
    
    public double getDiscountedPrice(){
        return super.price-(super.price*.15);
    }
}

class Food extends Products{
    Food(String name,double price,int quantity){
        super(name,price,quantity);
    }
    
    public void getInfo(){
        System.out.println("Name: "+super.name+", Price: "+super.price+", Quantity: "+super.quantity);
    }
}

interface Payable{
    double calculatePay();
    default void printPayStatus(){
        System.out.println("Payment is being calculated....");
    }
}


abstract class Employee{
    String name;
    double salary;
    Employee(String name,double salary){
        this.name=name;
        this.salary=salary;
    }
}

class Manager extends Employee implements Payable{
    Manager(String name,double salary){
        super(name,salary);
    }
    
    public double calculatePay(){
        return super.salary+(super.salary*.2);
    }
}

class SalesPerson extends Employee implements Payable{
    SalesPerson(String name,double salary){
        super(name,salary);
    }
    public double calculatePay(){
        return super.salary+(super.salary*.1);
    }
}


class Store{
    ArrayList<Products> pr=new ArrayList<>();
    ArrayList<Payable> em=new ArrayList<>();
    
    
    
    
    public void addEmployee(Payable p){
        em.add(p);
    }
    
    public void addProduct(Products p){
        pr.add(p);
    }
    
    public void sellProduct(String name,int quantity){
        for(Products prod:pr){
            if(prod.name.equals(name)){
                if(prod.quantity<quantity){
                    System.out.println("Not that many number of products are available :(");
                    return;
                }
                double discounted=prod.price;
                if(prod instanceof Electronics){
                    Discountable p = (Discountable) prod;
                    discounted=p.getDiscountedPrice();  
                    System.out.println("Discounted price: "+discounted);
                }
                else if(prod instanceof Clothing){
                    Discountable p = (Discountable) prod;
                    discounted=p.getDiscountedPrice();
                    System.out.println("Discounted price: "+discounted);
                }
                System.out.println("Product: "+prod.name+", Quantity: "+quantity+", Price: "+discounted*quantity);
                prod.quantity-=quantity;
                return;
            }
        }
        System.out.println("Item not found :(");
    }
    
    public void listProducts(){
        for(Products p:pr){
            p.getInfo();
        }
    }
    
    public void listEmployees(){
        for(Payable P:em){
            Employee e=(Employee) P;
            System.out.println(e.name+", Pay: "+P.calculatePay());
        }
    }
    
}


