package dev.saif.productservice.inheritanceStrategies.joinedTable;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "jt_base")
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseModel {
    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID uuid;
}

// One table for each class (parent and its children)
// parent will have its attributes and children will have their
// identifier will repeat in each table to be able to join the tables

// Pros: Fast queries of individual tables
// Cons: Requires join between different tables, slow for more than one table-query
