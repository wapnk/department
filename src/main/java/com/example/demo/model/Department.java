package com.example.demo.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departmentName;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Person> personList;

    @Override
    public String toString() {
        Set<Long> persons = personList.stream().map(p -> p.getId()).collect(Collectors.toSet());
        return "Department id = " + id
                + " name = " + departmentName
                + " persons " + " p "//persons
                +" \n";
    }
}