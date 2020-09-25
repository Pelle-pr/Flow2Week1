package facades;

import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;
import entities.Customer;
import entities.ItemType;
import entities.OrderLine;
import entities.Orders;
import org.glassfish.jersey.internal.inject.Custom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.Order;
import java.sql.SQLOutput;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    


        


    public static void createCustomer(String name, String email){

        EntityManager em = emf.createEntityManager();
        Customer customer = new Customer(name, email);
        try{

            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();


        }finally {
            em.close();
        }

    }
    public static void createItemType(String name, String description, int price){
        EntityManager em = emf.createEntityManager();
        ItemType itemType = new ItemType(name,description,price);

        try{

            em.getTransaction().begin();
            em.persist(itemType);
            em.getTransaction().commit();
        }finally {
            em.close();
        }



    }

    public static void createOrder (long id){

        EntityManager em = emf.createEntityManager();
        Orders order = new Orders();

        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        customer.getOrderList().add(order);
        order.setCustomer(customer);

        em.persist(customer);

        em.getTransaction().commit();

        em.close();

    }

    public static void createOrderLine (long o_id, long i_id, int quantity ){
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        ItemType itemType = em.find(ItemType.class, i_id);
        OrderLine orderLine = new OrderLine(quantity,itemType);

        Orders order = em.find(Orders.class,o_id);
        order.getOrderLineList().add(orderLine);

        orderLine.setOrder(order);

        em.persist(order);

        em.getTransaction().commit();

        em.close();



    }
    public static void findCustomerById (long id){

        EntityManager em = emf.createEntityManager();

        Customer customer = em.find(Customer.class, id);

        System.out.println(customer.getName());



    }


    public static  void getAllCustomers (){

        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT c FROM Customer c");
        List<Customer> list = query.getResultList();


        for (Customer customer : list) {

            System.out.println(customer.getName());

        }



    }
    public static void findItemType(long id){
        EntityManager em = emf.createEntityManager();

        ItemType itemType = em.find(ItemType.class, id);


        System.out.println(itemType.getName());


    }

    public static void findAllOrdersBycID (long c_id){

        EntityManager em = emf.createEntityManager();


        Query query = em.createQuery("select o from Orders o where o.customer.id = :id");
        query.setParameter("id", c_id);
        List<Orders> ordersList = query.getResultList();


        for (Orders orders : ordersList) {
            System.out.println(orders.getId());

        }

    }

    public static void main(String[] args) {

        createCustomer("Pelle", "Pelle@mail.dk");
        createCustomer("John", "John@mail.dk");
        createCustomer("Henning", "Henning@mail.dk");

        createItemType("Booster", "Energi drik", 20);
        createItemType("Royal Export", "Øl", 15);
        createItemType("Pepsi Max", "Sodavand", 10);


        createOrder(1);
        createOrder(2);

        createOrderLine(1,1,20);
        createOrderLine(1,2,55);
        createOrderLine(1,3,1);

        createOrderLine(2,1,44);


        findCustomerById(1);
        getAllCustomers();

        findItemType(1);

        findAllOrdersBycID(1);



    }



}
