package com.jprogrammers.core;

import java.io.IOException;

public interface EntityInspector {

    EntityOperations inspect(String entityAbsolutePath) throws IOException;
}
