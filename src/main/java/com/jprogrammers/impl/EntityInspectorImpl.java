package com.jprogrammers.impl;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.jprogrammers.core.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntityInspectorImpl implements EntityInspector {

    private ServiceGenerator serviceGenerator;
    private RepositoryGenerator repositoryGenerator;

    public EntityInspectorImpl(ServiceGenerator serviceGenerator, RepositoryGenerator repositoryGenerator) {
        this.serviceGenerator = serviceGenerator;
        this.repositoryGenerator = repositoryGenerator;
    }

    @Override
    public EntityOperations inspect(String entityAbsolutePath) throws IOException {

        CompilationUnit cu = StaticJavaParser.parse(new File(entityAbsolutePath));

        Entity entity = new Entity();
        entity.setName(getClassName(cu));
        entity.setProperties(getProperties(cu));
        entity.setAbsolutePath(entityAbsolutePath);
        cu.getPackageDeclaration().ifPresent(packageDeclaration ->
                entity.setPackageName(packageDeclaration.getNameAsString()));

        return new EntityOperations(entity, repositoryGenerator, serviceGenerator);
    }

    private String getClassName(CompilationUnit cu) {
        List<ClassOrInterfaceDeclaration> classes = cu.findAll(ClassOrInterfaceDeclaration.class);
        if (!classes.isEmpty()) {
            ClassOrInterfaceDeclaration classDeclaration = classes.get(0);
            return classDeclaration.getNameAsString();
        }
        return null;
    }

    private List<EntityProperty> getProperties(CompilationUnit cu) {

        List<EntityProperty> properties = new ArrayList<>();

        List<FieldDeclaration> fields = cu.findAll(FieldDeclaration.class);
        for (FieldDeclaration field : fields) {
            properties.add(new EntityProperty(
                    field.getVariable(0).getNameAsString(),
                    field.getVariable(0).getTypeAsString()
            ));
        }

        return properties;
    }
}
