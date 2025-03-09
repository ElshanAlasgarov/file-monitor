package com.az.edu.turing.filemonitor.loader;

import java.nio.file.Path;

public interface FileLoader {

    void load(Path path) throws Exception;

}
