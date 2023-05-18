package Server;

public class Patient{
    private String name;
    private String email;
    private Integer age;
    private String issue;
    
    public Patient() {}

    public Patient(String name, String email, Integer age, String issue) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.issue = issue;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getIssue() {
        return this.issue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
} 