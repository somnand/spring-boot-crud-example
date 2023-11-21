package application.model;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO : Is this class required ?
 * @author SOMSNAND
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel
{
    private String firstName,lastName;
    private String email;    
    private String password;
    private String matchingPassword;

}
