package application.model;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
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
    private String matchingPassword;//It is to check if the password are matching in case of a reset password

}
