package Server;

import java.util.ArrayList;

import Client.LoginWindow;

public class HealthHive {
    Hospital hospital = new Hospital();

    public void addPatient(String name, String email, Integer age, String issue) {
        Patient patient =  new Patient(name, email, age, issue);
        hospital.AddPatient(patient);
    }

    public ArrayList<Patient> getAllPatients() {
        return hospital.getAllPatients();
    }

    public void deletePatient(Patient patient) {
        hospital.deletePatient(patient);
    }

    public void donothing() {
    }
    public static void main(String[] args) {
        LoginWindow start = new LoginWindow();
        start.loginWindow();
    }
}
