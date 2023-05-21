package Server;

import java.util.ArrayList;

import Client.LoginWindow;

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


    public void donothing() {
    }
    public static void main(String[] args) {
        LoginWindow start = new LoginWindow();
        start.loginWindow();
    }

}
