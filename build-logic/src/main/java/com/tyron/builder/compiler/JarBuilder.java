package com.tyron.builder.compiler;

import com.tyron.builder.compiler.incremental.java.IncrementalJavaTask;
import com.tyron.builder.compiler.java.JarTask;
import com.tyron.builder.log.ILogger;
import com.tyron.builder.project.api.JavaModule;

import java.util.ArrayList;
import java.util.List;

public class JarBuilder extends BuilderImpl<JavaModule> {
    public JarBuilder(JavaModule project, ILogger logger) {
        super(project, logger);
    }

    @Override
    public List<Task<? super JavaModule>> getTasks(BuildType type) {
        List<Task<? super JavaModule>> tasks = new ArrayList<>();
        tasks.add(new IncrementalJavaTask(getProject(), getLogger()));
        tasks.add(new JarTask(getProject(), getLogger()));
        return tasks;
    }
}
