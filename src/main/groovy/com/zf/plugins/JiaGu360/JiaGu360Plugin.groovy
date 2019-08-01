package com.zf.plugins.JiaGu360


import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException

class JiaGu360Plugin implements Plugin<Project> {

    public static final String sPluginExtensionName = "jiaGu360Config";

    @Override
    void apply(Project project) {

        if (!project.plugins.hasPlugin("com.android.application")) {
            throw new ProjectConfigurationException("Plugin requires the 'com.android.application' plugin to be configured.", null);
        }

        project.extensions.create(sPluginExtensionName, JiaGu360Config, project);

        createJiaGuTask(project)
        createJiaGuUpdateTask(project)
    }


    def createJiaGuTask(Project project) {
        project[sPluginExtensionName].items.all { _item ->
            project.tasks.create("jiagu360${_item.name.capitalize()}", JiaGu360Task) {
                description "360JiaGu"
                group "JiaGu_360"
                jiaGuInfo _item
            }
        }

    }

    def createJiaGuUpdateTask(Project project) {
        project.tasks.create("jiagu360Update", JiaGu360UpdateTask) {
            description "360JiaGu update version"
            group "JiaGu_360"
        }
    }
}

