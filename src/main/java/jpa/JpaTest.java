package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import archi.Appointment;
import archi.Patient;
import archi.Professional;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createProfessional();
			test.createPatient();
			test.createAppointment();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		//test.reservedAppointment();

		manager.close();
		System.out.println(".. done");
	}

	private void createProfessional() {
		int numOfProfessional = manager.createQuery("Select p From Professional p", Professional.class).getResultList().size();
		if (numOfProfessional == 0) {
			ArrayList<String> appointmentName = new ArrayList<String>();
			appointmentName.add("A1");
			appointmentName.add("A2");

			manager.persist(new Professional("admin", "admin", appointmentName));
			manager.persist(new Professional("user", "user", appointmentName));
		}
	}

	private void createPatient() {
		int numOfPatient = manager.createQuery("Select pa From Patient pa", Patient.class).getResultList().size();
		if (numOfPatient == 0) {
			manager.persist(new Patient("user1", "pass1"));
			manager.persist(new Patient("user2", "pass2"));
			manager.persist(new Patient("user3", "pass3"));
		}
	}

	private void createAppointment() {
		List<Professional> resultListPro = manager.createQuery("Select p From Professional p", Professional.class).getResultList();
		for(Professional p : resultListPro) {
			manager.persist(new Appointment(p, 12, 35));
		}
	}

	private void reserveAppointment(Patient patient) {
		Appointment appointment = new Appointment();
		appointment.reserveAppointment(patient);
	}
}
