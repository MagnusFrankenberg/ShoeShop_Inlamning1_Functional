package Main;

import DBObjects.Customer;

import java.util.List;

@FunctionalInterface
public interface printCustomersInterface {
   void printCustomerList(List<Customer> cList, String filterString);
}
