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
@Table(name = "notes")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.example.guycounter.core.Notes.findAll",
                        query = "SELECT p FROM Notes p"
                )
        })
public class Notes {

    @Column(name = "notes", nullable = false)
    @Getter
    @Setter
    private String notes;

    @Column(name = "counter_id", nullable = false)
    @Getter
    @Setter
    private String counterId;

    @Column(name = "count")
    @Min(value = 0)
    @Getter
    @Setter
    private long count;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof People)) {
            return false;
        }

        Notes notes = (Notes) o;

        return counterId == notes.counterId &&
                Objects.equals(notes, notes.notes) &&
                count == notes.count;

    }

    @Override
    public int hashCode() {
        return Objects.hash(counterId, count, notes);
    }
}