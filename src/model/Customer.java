package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    final String firstName;
    final String lastName;
    final String email;

    String ReGex = "^(.+)@(.+).com$";
    Pattern pattern = Pattern.compile(ReGex);

    public Customer(String firstName, String lastName, String email){
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("This is an invalid Email");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email= email;
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public String toString(){
        return "First Name: " + firstName + " Last Name: " +lastName + " Email: " + email;
    }

    @Override
    public int hashCode(){
        return Objects.hash(email);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customer.getEmail().equals(email);
    }
}
