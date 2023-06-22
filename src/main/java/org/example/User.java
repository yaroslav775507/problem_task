package org.example;

public class User {
    private String name;
    private String lastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length()<3){
            throw new Error("Имя должно быть длиннее 3 символов");
        }
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.length()<3){
            throw new Error("Фамилия должна быть длиннее 3 символов");
        }
        this.lastName = lastName;
    }

    public User(String name, String lastName) {
        setLastName(lastName);
        setName(name);
        this.name = name;
        this.lastName = lastName;
    }
}
