package archi;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "APPOINTMENT")
@NamedQuery(name="Appointment.findAllNone", query = "SELECT a FROM Appointment a where a.patient is null")
public class Appointment {

    private Long id;

    private int date;

    private int duration;

    private String entitled;

    private Professional professional;

    private Patient patient;

    public Appointment() {

    }

    public Appointment(Professional professional, int date, int duration) {
        this.professional = professional;
        this.patient = null;
        this.date = date;
        this.duration = duration;
        this.entitled = null;
    }

    public Appointment(Professional professional, Patient patient, int date, int duration, String entitled) {
        this.professional = professional;
        this.patient = patient;
        this.date = date;
        this.duration = duration;
        this.entitled = entitled;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEntitled() {
        return this.entitled;
    }

    public void setEntitled(String entitled) {
        this.entitled = entitled;
    }

    @ManyToOne
    public Professional getProfessional() {
        return this.professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    @ManyToOne
    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Appointment> reserveAppointment(Patient patient) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("Appointment.findAllNone", Appointment.class);
        return query.getResultList();
    }
}
