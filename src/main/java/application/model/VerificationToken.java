package application.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken
{
    private static final int EXPIRATION_TIME = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String token;
    
    private Date expirationTime;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false,
    foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN"))
    private UserEntity user;
    
    public VerificationToken(UserEntity user, String token)
    {
	super();
	this.token = token;
	this.user = user;
	this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }
    
    public VerificationToken(String token)
    {
	this(null,token);
    }
    
    private Date calculateExpirationTime(int expirationTime)
    {
	Calendar cal = Calendar.getInstance();
	long currentTime = new Date().getTime();
	cal.setTimeInMillis(currentTime);
	
	cal.add(Calendar.MINUTE, expirationTime);
	return cal.getTime();
    }
}
