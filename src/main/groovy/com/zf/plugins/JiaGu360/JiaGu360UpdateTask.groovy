package com.zf.plugins.JiaGu360

import groovy.text.SimpleTemplateEngine
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

public class JiaGu360UpdateTask extends DefaultTask {


    def checkJiaGu360Config(JiaGu360Config config) {
        if (!config.jiaGuJarPath) {
            throw new GradleException("360 hardening jar file path cannot be empty")
        }

        File jiaGuJiaFile = new File(config.jiaGuJarPath)
        if (!jiaGuJiaFile.exists()) {
            throw new GradleException("360 hardened jar file does not exist")
        }
    }

    @TaskAction
    public void run() throws Exception {
        JiaGu360Config config = JiaGu360Config.getConfig(project)
        checkJiaGu360Config(config)
        JiaGu360 jiaGu360 = new JiaGu360(new File(config.jiaGuJarPath), project.logger)
        jiaGu360.update()
    }
}
