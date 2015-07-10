package crossover.social.media.domain;

/**
 * Email
 * Created by bazzoni on 10/07/2015.
 */
public class Email {
    private String email;
    private boolean primary;

    public Email() {
    }

    public Email(String email, boolean primary) {
        this.email = email;
        this.primary = primary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
