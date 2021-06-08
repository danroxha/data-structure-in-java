package util;

public class People implements Comparable {
  public String name = "";
  public String lastName = "";

  public People(){}
  public People(String name, String lastName) {
    this.name = name;
    this.lastName = lastName;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  @Override
  public String toString() {
    return "{" +
      " name='" + getName() + "'" +
      ", lastName='" + getLastName() + "'" +
      "}";
  }
  
  public int compareTo(Object o) {
    return name.compareTo(((People)o).getName());
  }
}
