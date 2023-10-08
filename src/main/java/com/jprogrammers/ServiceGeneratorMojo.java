package com.jprogrammers;

import com.jprogrammers.core.Entity;
import com.jprogrammers.impl.EntityInspectorImpl;
import com.jprogrammers.impl.RepositoryGeneratorImpl;
import com.jprogrammers.impl.ServiceGeneratorImpl;
import com.x5.template.Chunk;
import com.x5.template.Theme;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.COMPILE)
public class ServiceGeneratorMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Parameter(defaultValue = "${entity}", readonly = true)
    String entity;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        try {
            new EntityInspectorImpl(
                    new ServiceGeneratorImpl(),
                    new RepositoryGeneratorImpl())
                    .inspect(
                            getEntityAbsolutePath(getEntityPackage(), getEntityName())
                    )
                    .createRepository()
                    .createService();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String getEntityAbsolutePath(String entityPackage, String entityName) {
        return project.getBasedir() + "/src/main/java/" + entityPackage.replace(".", "/")
                + "/" + entityName + ".java";
    }

    private String getEntityPackage() {
        return entity.substring(0, entity.lastIndexOf("."));
    }

    private String getEntityName() {
        return entity.substring(entity.lastIndexOf(".") + 1);
    }

}