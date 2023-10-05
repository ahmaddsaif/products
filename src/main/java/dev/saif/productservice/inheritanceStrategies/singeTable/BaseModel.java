package dev.saif.productservice.inheritanceStrategies.singeTable;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "st_base_model")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER)
public class BaseModel {
    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID uuid;
}
// One big table representing parent and children classes
// Differentiated by a discriminator column (called 'DTYPE' by default)

// Pros: Simplest of all strategies, fast queries due to no joins and single table
// Cons: Wastage of space, can't have not-null constraints on children