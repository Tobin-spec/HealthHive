package Server;

import java.util.ArrayList;

public class Hospital{
    private ArrayList<HospitalStaff> staffs;
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<Patient>();
    private ArrayList<Appointment> appointments = new ArrayList<>();
    
    public Hospital() {
    }

    public ArrayList<HospitalStaff> getAllStaffs(){
        return this.staffs;
    }

    public ArrayList<Doctor> getAllDoctors() {
        return this.doctors;
    }

    public ArrayList<Patient> getAllPatients(){
        return this.patients;
    }

    public ArrayList<Appointment> getAllAppointments() {
        return this.appointments;
    }

    public void AddPatient(Patient patient) {
        this.patients.add(patient);
    }

    public void deletePatient(Patient patient) {
        this.patients.remove(patient);
    }

    public void createAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
    }
}