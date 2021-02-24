package com.guycounter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guycounter.core.People;
import com.guycounter.core.PplGroups;
import com.guycounter.db.GroupDao;
import com.guycounter.db.PeopleDao;
import com.guycounter.resources.GroupResource;
import com.guycounter.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class GuyCounterApplication extends Application<GuyCounterConfiguration> {

    private final HibernateBundle<GuyCounterConfiguration> hibernate = new HibernateBundle<GuyCounterConfiguration>(People.class, PplGroups.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(GuyCounterConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new GuyCounterApplication().run(args);
    }

    @Override
    public void run(GuyCounterConfiguration guyCounterConfiguration, Environment environment) throws Exception {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, guyCounterConfiguration.getDataSourceFactory(), "mysql");
        System.out.println(jdbi.toString());
//        environment.jersey().register(new UserResource(jdbi));
        final PeopleDao peopleDao = new PeopleDao(hibernate.getSessionFactory());
        environment.jersey().register(new PersonResource(peopleDao));

        final GroupDao groupDao = new GroupDao(hibernate.getSessionFactory());
        environment.jersey().register(new GroupResource(groupDao));
    }

    @Override
    public void initialize(Bootstrap<GuyCounterConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<GuyCounterConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(GuyCounterConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernate);
    }
}
