package application.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // Since this is my model class
@Table(name = "PRODUCT")
public class Product
{
    @Id
    @GeneratedValue    
    private UUID id;
    
    public UUID getID()
    {
	return id;
    }
    
    private String name;
    private int quantity;
    private double price;
}
