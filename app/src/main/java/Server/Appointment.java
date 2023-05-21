package Server;


public class Appointment{
    private String id;
    private String time;
    private Patient patient;
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(Patient patient, Doctor doctor, String time) {
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
        this.id = this.patient.getName() + " - " + this.doctor.getName();
    }

    public String getId() {
        return this.id;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getTime() {
        return this.time;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }
 
}