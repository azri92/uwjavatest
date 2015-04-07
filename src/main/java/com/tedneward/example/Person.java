package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
  }

  public static class AgeComparator implements Comparator<Person>{
    @Override
    public int compare(Person p1, Person p2) {
      return Integer.compare(p1.getAge(), p2.getAge());
    }
  }

  /** getter methods **/
  public int getAge() {
    return age;
  }
  
  public String getName() {
    return name;
  }
  
  public double getSalary() {
    return salary;
  }
  
  public String getSSN() {
    return ssn;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  /** setter methods **/
  public void setAge(int age) {
    if (age < 0)
      throw new IllegalArgumentException();

    this.age = age;
  }

  public void setName(String name) {
    if (name == null)
      throw new IllegalArgumentException();

    this.name = name;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  /** other methods **/
  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person)other;
      return (this.name.equals(p.name) && this.age == p.age);
    }
    return false;
  }

  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> list = new ArrayList<>();
    Person ted = new Person("Ted", 41, 250000);
    Person charlotte = new Person("Charlotte", 43, 150000);
    Person michael = new Person("Michael", 22, 10000);
    Person matthew = new Person("Matthew", 15, 0);
    list.add(ted);
    list.add(charlotte);
    list.add(michael);
    list.add(matthew);
    return list;
  }

  @Override
  public int compareTo(Person other) {
    if (this.salary > other.getSalary()) {
      return -1;
    } else if (this.salary < other.getSalary()) {
      return 1;
    } else {
      return 0;
    }
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
