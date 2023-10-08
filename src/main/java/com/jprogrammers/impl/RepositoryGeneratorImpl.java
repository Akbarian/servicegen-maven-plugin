package com.jprogrammers.impl;

import com.jprogrammers.core.Entity;
import com.jprogrammers.core.RepositoryGenerator;
import com.x5.template.Chunk;
import com.x5.template.Theme;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RepositoryGeneratorImpl implements RepositoryGenerator {
    @Override
    public void generate(Entity entity) throws IOException {
        Theme theme = new Theme();
        Chunk chunk = theme.makeChunk("repository", "txt");

        // replace static values below with user input
        chunk.set("package", entity.getPackageName() + ".repository");
        chunk.set("entityPackage", entity.getPackageName() + "." + entity.getName());
        chunk.set("entity", entity.getName());

        File file = new File(entity.getAbsolutePath().substring(0, entity.getAbsolutePath().lastIndexOf("/")));
        if (!file.exists()) {
            file.mkdirs();
        }
        FileWriter out = new FileWriter(file.getAbsolutePath() + "/" + entity.getName() + "Repository.java");

        chunk.render(out);

        out.flush();
        out.close();
    }
}
