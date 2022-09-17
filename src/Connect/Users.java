/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

/**
 *
 * @author Mohamed
 */
public class Users {

    private String name, address, specialization, qualification, std_class, subject, class_name, s1, s2, s3, s4, s5, s6, s7, phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getStd_class() {
        return std_class;
    }

    public void setStd_class(String std_class) {
        this.std_class = std_class;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Users(int id,String name, String address, int age, String std_class, String subject, int mark1, int mark2, int result) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.std_class = std_class;
        this.subject = subject;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.result = result;

    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getid() {
        return id;
    }
    public String getS1() {
        return s1;
    }

    public void setid(int id) {
        this.id = id;
    }
    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public Users(int id,String std_class,  String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        this.std_class = std_class;
        this.id = id;
        this.s1 = s1;
        this.id = id;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
    }
    private int id,age, mark1, mark2, result, salary;

    public Users(int id,String name, String address, String specialization, String qualification, int age, String phone, int salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.specialization = specialization;
        this.qualification = qualification;
        this.age = age;
        this.phone = phone;
        this.salary = salary;
    }

}
