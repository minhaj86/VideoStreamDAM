package com.videostream.dam;

import com.videostream.dam.dao.VideoDAO;
import com.videostream.dam.orm.Video;
import com.videostream.dam.orm.VideoGenre;
import com.videostream.dam.resources.VideoResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class VideoStreamDAMApplication extends Application<VideoStreamDAMConfiguration> {
    private final HibernateBundle<VideoStreamDAMConfiguration> hibernateBundle =
            new HibernateBundle<VideoStreamDAMConfiguration>(Video.class, VideoGenre.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(VideoStreamDAMConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(final String[] args) throws Exception {
        new VideoStreamDAMApplication().run(args);
    }

    @Override
    public String getName() {
        return "VideoStreamDAM";
    }

    @Override
    public void initialize(final Bootstrap<VideoStreamDAMConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(hibernateBundle);
        
        /* 
         * Database migration
         */
        /*  
        bootstrap.addBundle(new MigrationsBundle<VideoStreamDAMConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(VideoStreamDAMConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
         */
        
    }

    @Override
    public void run(final VideoStreamDAMConfiguration configuration,
                    final Environment environment) {
        final VideoDAO dao = new VideoDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new VideoResource(dao));
        
        /*
         * Health check 
         */
		/*
		 * final Template template = configuration.buildTemplate();
		 * environment.healthChecks().register("template", new
		 * TemplateHealthCheck(template));
		 */
        
        /* 
         * Admin 
         */
		/* environment.admin().addTask(new EchoTask()); */

        /* 
         * Authentication and authorization
         */
		/*
		 * environment.jersey().register(new AuthDynamicFeature(new
		 * BasicCredentialAuthFilter.Builder<User>() .setAuthenticator(new
		 * ExampleAuthenticator()) .setAuthorizer(new ExampleAuthorizer())
		 * .setRealm("SUPER SECRET STUFF") .buildAuthFilter()));
		 * environment.jersey().register(new
		 * AuthValueFactoryProvider.Binder<>(User.class));
		 * environment.jersey().register(RolesAllowedDynamicFeature.class);
		 */
    }

}
