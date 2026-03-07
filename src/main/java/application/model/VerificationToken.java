package application.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import security.config.Constants;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken
{
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
	this.expirationTime = calculateExpirationTime(Constants.EXPIRATION_TIME);
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
