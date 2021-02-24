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
@Table(name = "counter")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.example.guycounter.core.Counter.findAll",
                        query = "SELECT p FROM Counter p"
                )
        })
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long counter_id;

    @Column(name = "count")
    @Min(value = 0)
    @Getter
    @Setter
    private long count;

    @Column(name = "people_id")
    @Getter
    @Setter
    private long peopleId;

    @Column(name = "group_id")
    @Getter
    @Setter
    private long groupId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Counter)) {
            return false;
        }

        Counter counter = (Counter) o;

        return counter_id == counter.counter_id &&
                peopleId == counter.peopleId &&
                groupId == counter.groupId &&
                count == counter.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter_id, count, peopleId, groupId);
    }
}