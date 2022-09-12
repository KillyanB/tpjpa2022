package archi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Patient extends User {

    public Patient() {

    }

    public Patient(String login, String pwd) {
        super(login, pwd);
    }
}
