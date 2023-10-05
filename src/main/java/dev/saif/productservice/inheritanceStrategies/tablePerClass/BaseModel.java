package dev.saif.productservice.inheritanceStrategies.tablePerClass;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "tpc_base_model")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseModel {
    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID uuid;
}

// Very similar to mapped superclass
// except in this case parent also has its own table
// Children will have attributes of parent and their own attributes

// Pros: query on parent unions and returns children as well (polymorphic queries)
