package Server;

public class HospitalStaff {
    protected String name;
    protected String title;
    protected String department;

    public HospitalStaff() {

    }

    public HospitalStaff(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDepartment() {
        return this.department;
    }

}
