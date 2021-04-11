package com.demo.cassandra.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.StringJoiner;

@Table("person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Person {

    @PrimaryKey
    @Column(value = "id")
    Long id;

    @Column(value = "full_name")
    String fullName;

    @Column(value = "age")
    Integer age;

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("fullName='" + fullName + "'")
                .add("age=" + age)
                .toString();
    }
}
