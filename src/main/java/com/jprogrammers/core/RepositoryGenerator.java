package com.jprogrammers.core;

import java.io.IOException;

public interface RepositoryGenerator {

    void generate(Entity entity) throws IOException;
}
