package org.example;

public class Task {
    private String important;
    private String medium_important;
    private String low_important;

    public String getImportant() {

        return important;
    }

    public void setImportant(String important) {
        if(important.length()<3){
            throw new Error("Допустимая длинна должна быть больше 3 символов");
        }
        this.important = important;
    }

    public String getMedium_important() {
        return medium_important;
    }

    public void setMedium_important(String medium_important) {
        if(medium_important.length()<3){
            throw new Error("Допустимая длинна должна быть больше 3 символов");
        }
        this.medium_important = medium_important;
    }

    public String getLow_important() {
        return low_important;
    }

    public void setLow_important(String low_important) {
        if(low_important.length()<3){
            throw new Error("Допустимая длинна должна быть больше 3 символов");
        }
        this.low_important = low_important;
    }

    public Task(String important, String medium_important, String low_important) {
        setImportant(important);
        setMedium_important(medium_important);
        setLow_important(low_important);
        this.important = important;
        this.medium_important = medium_important;
        this.low_important = low_important;
    }

}
