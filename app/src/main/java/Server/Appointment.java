package Server;


import java.util.ArrayList;

public class Appointment{
    private String time;
    private Patient patient;
    private ArrayList<HospitalStaff> staffs;

    public Appointment() {
    }

    public Appointment(String time, Patient patient, ArrayList<HospitalStaff> staffs) {
        this.time = time;
        this.patient = patient;
        this.staffs = staffs;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setStaffs(ArrayList<HospitalStaff> staffs) {
        this.staffs = staffs;
    }

    public String getTime() {
        return this.time;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public ArrayList<HospitalStaff> getStaffs() {
        return this.staffs;
    }
 
}