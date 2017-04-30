package soaiknowdossier.model;
import javax.persistence.*;
import java.util.List;

/**
 * Created by SimonaS on 15/04/2017.
 */
@Entity
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String field;
    private String quota;
    private double price;
    private double paid;
    private boolean areTaxesPaid;
    private boolean isValid;
    private boolean isRegistered;

    private List<Long> userIDs;
    private List<Long> subjectIDs;

    public Semester(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setAreTaxesPaid(boolean areTaxesPaid) {
        this.areTaxesPaid = areTaxesPaid;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public String getQuota() {
        return quota;
    }

    public double getPrice() {
        return price;
    }

    public double getPaid() {
        return paid;
    }

    public boolean isAreTaxesPaid() {
        return areTaxesPaid;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean getIsRegistered() {
        return isRegistered;
    }

    public List<Long> getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(List<Long> userIDs) {
        this.userIDs = userIDs;
    }

    public List<Long> getSubjectIDs() {
        return subjectIDs;
    }

    public void setSubjectIDs(List<Long> subjectIDs) {
        this.subjectIDs = subjectIDs;
    }
}

