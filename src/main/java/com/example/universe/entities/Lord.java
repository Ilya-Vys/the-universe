package com.example.universe.entities;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "lords")
public class Lord {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "lord_id")
    private Long id;
    @Column(name = "lord_name")
    private String name;
    @Column(name = "lord_age")
    private Integer age;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lord")
    private List<Planet> planets;

    public Lord() {
    }

    public Lord(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.planets = new ArrayList<>();
    }

    public Lord(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.planets = new ArrayList<>();
    }

    public void addPlanet(Planet planet){
        planets.add(planet);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Lord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", planets=" + planets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lord lord = (Lord) o;
        return Objects.equals(name, lord.name) &&
                Objects.equals(age, lord.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
