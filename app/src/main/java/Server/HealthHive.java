package Server;

import java.io.IOException;
import java.util.ArrayList;

import Client.UserInterface;

public class HealthHive {
    static Hospital hospital = new Hospital();

    public void addPatient(String name, String email, Integer age, String issue) {
        Patient patient =  new Patient(name, email, age, issue);
        hospital.AddPatient(patient);
    }

    public ArrayList<Patient> getAllPatients() {
        return hospital.getAllPatients();
    }

    public ArrayList<Doctor> getAllDoctors() {
        return hospital.getAllDoctors();
    }

    public void deletePatient(Patient patient) {
        hospital.deletePatient(patient);
    }

    public ArrayList<Appointment> getAllAppointments() {
        return hospital.getAllAppointments();
    }

    public void createAppointment(Patient patient, Doctor doctor, String time){
        Appointment appointment = new Appointment(patient, doctor, time);
        hospital.createAppointment(appointment);
    }

    public void editPatient(Patient patient, String name, String email, Integer age, String issue) {
        for (Patient p: getAllPatients()) {
            if (p.equals(patient)) {
                p.setName(name);
                p.setEmail(email);
                p.setAge(age);
                p.setIssue(issue);
            }
        }
    }

    
    public void editAppointment(Appointment appointment, Doctor doctor, String time) {
        for (Appointment a: getAllAppointments()) {
            if (a.equals(appointment)){
                a.setDoctor(doctor);
                a.setTime(time);
            }
        }
    }

    public void deleteAppointment(Appointment appointment) {
        hospital.deleteAppointment(appointment);
    }

    public void addDoctor(String name, String speciality) {
        Doctor doctor = new Doctor(name, speciality);
        hospital.addDoctor(doctor);
    }

    public void deleteDoctor(Doctor doctor) {
        hospital.deleteDoctor(doctor);
    }

    public void editDoctor(Doctor doctor, String name, String speciality) {
        for (Doctor d: getAllDoctors()) {
            if (d.equals(doctor)) {
                d.setName(name);
                d.setDepartment(speciality);
            }
        }
    }

    public void addItem(String name, Integer quantity) {
        Item item  = new Item(name, quantity);
        hospital.addItem(item);
    }

    public ArrayList<Item> getAllItems() {
        return hospital.getInventory().getItems();
    }


    public void donothing() {
    }

    public void editItem(Item item, Integer quantity) {
        for (Item i : getAllItems()) {
            if (i.equals(item)) {
                i.setCount(quantity);
            }
        }
    }

    public Inventory getInventory() {
        return hospital.getInventory();
    }

    public void useItem(Item item, Integer quantity) {
        for (Item i : getAllItems()) {
            if (i.equals(item)) {
                i.useItem(quantity);
            }
        }
    }

    public String getAllData() {
        String data = "";
        for (Patient p : getAllPatients()) {
            data += p.getName() + ":" + p.getEmail() + ":" + p.getAge() + ":" + p.getIssue() + "\n";
        }
        for (Item itm : getAllItems()) {
            data += "-" + itm.getName() + ":" + itm.getCount() + "\n";
        }
        for (Doctor d : getAllDoctors()) {
            data += "--" + d.getName() + ":" + d.getDepartment() + "\n";
        }

        return data;
    }
    
    public static void main(String[] args) throws IOException {
        HealthHive healthHive = new HealthHive();
        DataManagement dataManagement = new DataManagement(healthHive);
        dataManagement.getData();
        UserInterface start = new UserInterface(healthHive);
        start.loginWindow();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            dataManagement.saveData(healthHive.getAllData());
        }));
    }

}
