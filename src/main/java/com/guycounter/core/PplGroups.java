package com.guycounter.core;

import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ppl_groups")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.example.guycounter.core.PplGroups.findAll",
                        query = "SELECT p FROM PplGroups p"
                )
        })
public class PplGroups {
    @Column(name = "groups_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long groupId;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PplGroups)) {
            return false;
        }

        PplGroups pplGroups = (PplGroups) o;

        return groupId == pplGroups.groupId &&
                Objects.equals(name, pplGroups.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, name);
    }

}
