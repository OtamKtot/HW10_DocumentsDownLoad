package guru.qa.model;

import java.util.List;

public class JsonModel {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;
    private List<String> interests;
    private int age;
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth(String dateOfBirth) {this.dateOfBirth = dateOfBirth;}
    public List<String> getInterests() {return interests;}
    public void setInterests(List<String> interests) {this.interests = interests;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
}