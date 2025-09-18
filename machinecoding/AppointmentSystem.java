package machinecoding;

import javax.print.Doc;
import java.util.*;

public class AppointmentSystem {
    public static class Doctor{
        private String name;
        private int doctorId;
        Set<String> availableSlots;
        Doctor(String name , int doctorId,List<String> availableSlots){
            this.doctorId = doctorId;
            this.name = name;
            this.availableSlots = new HashSet<>(availableSlots);
        }

        public Set<String> getAvailableSlots() {
            return availableSlots;
        }

        public String getName() {
            return name;
        }

        public int getDoctorId() {
            return doctorId;
        }
    }
    public static class Appointment{
        private int doctorId;
        private String patient;
        private String slots;
        Appointment(int doctorId,String patient,String slots){
            this.doctorId = doctorId;
            this.patient=patient;
            this.slots = slots;
        }

        @Override
        public String toString() {
            return "Appointment{" +
                    "doctorId=" + doctorId +
                    ", patient='" + patient + '\'' +
                    ", slots='" + slots + '\'' +
                    '}';
        }

        public int getDoctorId() {
            return doctorId;
        }

        public String getPatient() {
            return patient;
        }

        public String getSlots() {
            return slots;
        }
    }
    public static class HospitalAppointmentService{
        Map<Integer, Doctor> doctorMap = new HashMap<>();
        Map<Integer ,List<Appointment>> appointments = new HashMap<>();

        public void addDoc(String docName,int docId,List<String> slots){
            doctorMap.put(docId,new Doctor(docName,docId,slots));
            appointments.put(docId,new ArrayList<>());
            System.out.println("Doctors added: "+docName);
        }

        public boolean bookAppointment(int docId,String patientName,String slot){
            Doctor doctor = doctorMap.get(docId);
            if(doctor==null){
                System.out.println("Docter Not found with docId"+docId);
                return false;
            }
            if(!doctor.availableSlots.contains(slot)){
                System.out.println("Slot not found "+slot);
                return false;
            }
            doctor.availableSlots.remove(slot);
            appointments.get(docId).add(new Appointment(docId,patientName,slot));
            System.out.println("Appointment booked for "+patientName+" with Dr "+ doctor.name);
            return true;
        }

        public void  viewAvailableSlots(int docId){
            Doctor doctor = doctorMap.get(docId);
            if(doctor == null){
                System.out.println("Doctor not found!"+docId);
                return;
            }
            System.out.println("Available slot for Doc "+ doctor.name+ "  is " + doctor.getAvailableSlots());
        }

        public void viewAppointment(int docId){
            if(!appointments.containsKey(docId)){
                System.out.println("No Appointment found!");
                return;
            }
            for (Appointment appointment : appointments.get(docId)){
                System.out.println("Appointments for doc "+ docId+ " "+ appointment);
            }
        }

    }
    public static void main(String[] args) {

        HospitalAppointmentService service = new HospitalAppointmentService();

        service.addDoc("Dr Naina",105, Arrays.asList("10AM","11AM","12AM","1PM"));
        service.bookAppointment(105,"Mohit Agarwal","1PM");
        service.viewAppointment(105);
        service.viewAvailableSlots(105);
    }
}
