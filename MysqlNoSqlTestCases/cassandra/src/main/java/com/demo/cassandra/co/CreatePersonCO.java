package com.demo.cassandra.co;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreatePersonCO {

    Long id;

    String fullName;

    Integer age;

    @Override
    public String toString() {
        return new StringJoiner(", ", CreatePersonCO.class.getSimpleName() + "[", "]")
                .add("id="+id)
                .add("fullName='" + fullName + "'")
                .add("age=" + age)
                .toString();
    }
}
