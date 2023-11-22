package application.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import application.model.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent
{
    private UserEntity user;
    private String applicationURL;
    
    public RegistrationCompleteEvent(UserEntity source,String applicationURL)
    {
	super(source);
	this.user = source;
	this.applicationURL = applicationURL;
    }
}
