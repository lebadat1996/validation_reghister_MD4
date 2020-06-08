package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Table
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private String phone;
    private int age;
    private String email;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String phone = user.getPhone();
        int age = user.getAge();
        String email = user.getEmail();

        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "phone", "phone.empty");
        ValidationUtils.rejectIfEmpty(errors, "age", "age.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
        if (lastName.length() > 45 || lastName.length() < 5) {
            errors.rejectValue("lastName", "lastName.length");
        }
        if (firstName.length() > 45 || firstName.length() < 5) {
            errors.rejectValue("firstName", "firstName.length");
        }
        if (phone.length() > 11 || phone.length() < 10) {
            errors.rejectValue("phone", "phone.length");
        }
        if (!phone.startsWith("0")) {
            errors.rejectValue("phone", "phone.startsWith");
        }
        if (!phone.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("phone", "phone.matches");
        }
        if (age < 18) {
           errors.rejectValue("age","age.length");
        }
        if (!email.matches("(^$|[0-9A-Za-z_]+@[A-Za-z0-9]+(.[A-Za-z0-9]+)+$)")) {
            errors.rejectValue("email", "email.matches");
        }
    }
}
