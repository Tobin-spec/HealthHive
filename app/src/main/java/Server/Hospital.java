package Server;

import java.util.ArrayList;

public class Hospital{
    private ArrayList<HospitalStaff> staffs;
    private ArrayList<Patient> patients;
    private ArrayList<Appointment> appointments;
    
    public Hospital() {
    }

    public ArrayList<HospitalStaff> getAllStaffs(){
        return this.staffs;
    }

    public ArrayList<Patient> getAllPatients(){
        return this.patients;
    }

    public ArrayList<Appointment> getAllAppointments() {
        return this.appointments;
    }
}