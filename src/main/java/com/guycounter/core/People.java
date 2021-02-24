package com.guycounter.core;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "people")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.example.guycounter.core.Person.findAll",
                        query = "SELECT p FROM People p"
                )
        })
public class People {
    @Column(name = "people_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long peopleId;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Email
    @Column(name = "email_id", nullable = false)
    @Getter
    @Setter
    private String emailId;

    @Column(name = "phone_number")
    @Min(value = 0)
    @Getter
    @Setter
    private long phoneNumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @Getter
    @Setter
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @Getter
    @Setter
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof People)) {
            return false;
        }

        People people = (People) o;

        return peopleId == people.peopleId &&
                phoneNumber == people.phoneNumber &&
                Objects.equals(emailId, people.emailId) &&
                Objects.equals(name, people.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peopleId, name, phoneNumber, emailId, createdAt, updatedAt);
    }
}