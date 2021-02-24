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
@Table(name = "people_group_mapping")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.example.guycounter.core.PeopleGroupMapping.findAll",
                        query = "SELECT p FROM PeopleGroupMapping p"
                )
        })
public class PeopleGroupMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long people_group_id;

    @Column(name = "people_id")
    @Getter
    @Setter
    private long peopleId;

    @Column(name = "group_id")
    @Getter
    @Setter
    private long groupId;

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

        PeopleGroupMapping peopleGroupMapping = (PeopleGroupMapping) o;

        return people_group_id == peopleGroupMapping.people_group_id &&
                peopleId == peopleGroupMapping.peopleId &&
                groupId == peopleGroupMapping.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(people_group_id, peopleId, groupId, createdAt, updatedAt);
    }
}
