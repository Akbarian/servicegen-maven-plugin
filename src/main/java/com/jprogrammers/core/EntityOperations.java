package com.jprogrammers.core;

import com.jprogrammers.impl.RepositoryGeneratorImpl;
import com.jprogrammers.impl.ServiceGeneratorImpl;

import java.io.IOException;

public class EntityOperations {

    private Entity entity;
    private RepositoryGenerator repositoryGenerator;
    private ServiceGenerator serviceGenerator;

    public EntityOperations(Entity entity, RepositoryGenerator repositoryGenerator, ServiceGenerator serviceGenerator) {
        this.entity = entity;
        this.repositoryGenerator = repositoryGenerator;
        this.serviceGenerator = serviceGenerator;
    }

    public EntityOperations createRepository() throws IOException {
        repositoryGenerator.generate(entity);

        return this;
    }

    public EntityOperations createService() throws IOException {
        serviceGenerator.generate(entity);

        return this;
    }
}
