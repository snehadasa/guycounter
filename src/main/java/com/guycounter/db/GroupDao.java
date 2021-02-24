package com.guycounter.db;

//import com.guycounter.core.People;
import com.guycounter.core.PplGroups;
import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class GroupDao extends AbstractDAO<PplGroups> {
    public GroupDao(SessionFactory factory) {
        super(factory);
    }

    public Optional<PplGroups> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

//    public Optional<People> findByName(String id) {
//        return
//    }

    public PplGroups create(PplGroups pplGroups) {
        return persist(pplGroups);
    }
}
