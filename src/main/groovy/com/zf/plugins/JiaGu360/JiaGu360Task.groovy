package com.zf.plugins.JiaGu360


import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

public class JiaGu360Task extends DefaultTask {

    private static final String DOT_APK = ".apk";
    private final static String DEFALUT_SIGNING_CONFIG = "debug"

    @Input
    JiaGuInfo jiaGuInfo


    def getAppPluginExtension() {
        def appPlugin = project.plugins.getPlugin("com.android.application")
        return appPlugin.extension
    }

    SigningInfo getSigningConfig(def appExtension, String name) {
        def signingConfig = appExtension["signingConfigs"].find {
            return it['name'] == name
        }

        if (signingConfig == null) {
            return null
        }

        String storeFilePath = null;
        def storeFile = signingConfig['storeFile'];
        if (storeFile) {
            storeFilePath = storeFile.absolutePath
        }
        def storePassword = signingConfig['storePassword']
        def keyAlias = signingConfig['keyAlias']
        def keyPassword = signingConfig['keyPassword']
        def v1SigningEnabled = signingConfig['v1SigningEnabled']
        def v2SigningEnabled = signingConfig['v2SigningEnabled']
        if (v1SigningEnabled == false) {
            project.logger.warn("The default signature does not support V1 signature")
        }
        if (v2SigningEnabled == false) {
            project.logger.warn("The default signature does not support V2 signature")
        }
        return new SigningInfo(storeFilePath, storePassword, keyAlias, keyPassword)
    }


    void checkConfig(JiaGu360Config jiaGu360Config) {

        if (!jiaGu360Config.userName) {
            throw new GradleException("userName cannot be empty")
        }

        if (!jiaGu360Config.passWord) {
            throw new GradleException("passWord cannot be empty")
        }

        if (!jiaGu360Config.jiaGuJarPath) {
            throw new GradleException("jiaGuJarPath cannot be empty")
        }

        File jiaGuJiaFile = new File(jiaGu360Config.jiaGuJarPath)
        if (!jiaGuJiaFile.exists()) {
            throw new GradleException("360 hardened jar file does not exist.jiaGuJiaFile=${jiaGuJiaFile.absolutePath}")
        }


        if (jiaGu360Config.signingInfo == null) {

            if (!jiaGu360Config.signingName) {
                jiaGu360Config.signingName = DEFALUT_SIGNING_CONFIG
            }

            def extension = getAppPluginExtension()
            jiaGu360Config.signingInfo = getSigningConfig(extension, jiaGu360Config.signingName)
            if (jiaGu360Config.signingInfo == null) {
                throw new GradleException("No signature configuration information named ${jiaGuInfo.signingName} was found")
            }
        }
    }

    void checkJiaGuInfo(JiaGu360Config jiaGu360Config) {

        if (!jiaGuInfo.inputApkFilePath) {
            throw new GradleException("inputApkFilePath cannot be empty")
        }

        if (!jiaGuInfo.inputApkFilePath.endsWith(DOT_APK)) {
            throw new GradleException("Must be set to be augmented apk")
        }

        File inputApkFile = new File(jiaGuInfo.inputApkFilePath)
        if (!inputApkFile.exists()) {
            throw new GradleException("Did not find apk to be reinforced.jiaGuJiaFile=${inputApkFile.absolutePath}")
        }

        if (jiaGuInfo.outputApkDirPath) {
            File outputApkDir = new File(jiaGuInfo.outputApkDirPath)
            if (!outputApkDir.exists()) {
                throw new GradleException("The hard disk save directory does not exist.")
            }
        } else {
            if (jiaGu360Config.outputApkDirPath) {
                jiaGuInfo.outputApkDirPath = jiaGu360Config.outputApkDirPath
                File outputApkDir = new File(jiaGuInfo.outputApkDirPath)
                if (!outputApkDir.exists()) {
                    throw new GradleException("The hard disk save directory does not exist.")
                }
            } else {
                jiaGuInfo.outputApkDirPath = inputApkFile.parentFile.absolutePath
            }
        }


        if (jiaGuInfo.openOutputDir == null) {
            jiaGuInfo.openOutputDir = jiaGu360Config.openOutputDir;
        }


        if (jiaGuInfo.isSupportCrashLong == null) {
            jiaGuInfo.isSupportCrashLong = jiaGu360Config.isSupportCrashLong || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportAnalyse == null) {
            jiaGuInfo.isSupportAnalyse = jiaGu360Config.isSupportAnalyse || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportX86 == null) {
            jiaGuInfo.isSupportX86 = jiaGu360Config.isSupportX86 || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportAnalyse == null) {
            jiaGuInfo.isSupportAnalyse = jiaGu360Config.isSupportAnalyse || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isNocert == null) {
            jiaGuInfo.isNocert = jiaGu360Config.isNocert || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportVmp == null) {
            jiaGuInfo.isSupportVmp = jiaGu360Config.isSupportVmp || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportDataProtection == null) {
            jiaGuInfo.isSupportDataProtection = jiaGu360Config.isSupportDataProtection || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportAssetsProtection == null) {
            jiaGuInfo.isSupportAssetsProtection = jiaGu360Config.isSupportAssetsProtection || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportFileCheck == null) {
            jiaGuInfo.isSupportFileCheck = jiaGu360Config.isSupportFileCheck || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportPtrace == null) {
            jiaGuInfo.isSupportPtrace = jiaGu360Config.isSupportPtrace || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportSoProtection == null) {
            jiaGuInfo.isSupportSoProtection = jiaGu360Config.isSupportSoProtection || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportDex2cProtection == null) {
            jiaGuInfo.isSupportDex2cProtection = jiaGu360Config.isSupportDex2cProtection || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportStringObfusProtection == null) {
            jiaGuInfo.isSupportStringObfusProtection = jiaGu360Config.isSupportStringObfusProtection || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportDexShadowProtection == null) {
            jiaGuInfo.isSupportDexShadowProtection = jiaGu360Config.isSupportDexShadowProtection || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.isSupportSoPrivateProtection == null) {
            jiaGuInfo.isSupportSoPrivateProtection = jiaGu360Config.isSupportSoPrivateProtection || jiaGu360Config.isSupportAll;
        }

        if (jiaGuInfo.autosign == null) {
            jiaGuInfo.autosign = jiaGu360Config.autosign;
        }

        if (jiaGuInfo.channelFilePath) {
            File channelFile = new File(jiaGuInfo.channelFilePath)
            if (!channelFile.exists()) {
                throw new GradleException("Channel profile not found.jiaGuJiaFile=${channelFile.absolutePath}")
            }
        }

        if (jiaGuInfo.signingInfo == null) {

            if (jiaGuInfo.signingName) {
                def extension = getAppPluginExtension()
                jiaGuInfo.signingInfo = getSigningConfig(extension, jiaGuInfo.signingName)
                if (jiaGuInfo.signingInfo == null) {
                    throw new GradleException("No signature configuration information named ${jiaGuInfo.signingName} was found")
                }
            } else {
                jiaGuInfo.signingInfo=jiaGu360Config.signingInfo
            }
        }

        if (!jiaGuInfo.signingInfo.storeFilePath || jiaGuInfo.signingInfo.storeFilePath.trim().length() == 0) {
            throw new GradleException("The signature file storeFilePath cannot be empty ")
        }

        File storeFile = new File(jiaGuInfo.signingInfo.storeFilePath)
        if (!storeFile.exists()) {
            throw new GradleException("Signature file does not exist")
        }

        if (!jiaGuInfo.signingInfo.storePassword || jiaGuInfo.signingInfo.storePassword.trim().length() == 0) {
            throw new GradleException("The signature file storePassword cannot be empty ")
        }

        if (!jiaGuInfo.signingInfo.keyAlias || jiaGuInfo.signingInfo.keyAlias.trim().length() == 0) {
            throw new GradleException("Signature file keyAlias cannot be empty ")
        }

        if (!jiaGuInfo.signingInfo.keyPassword || jiaGuInfo.signingInfo.keyPassword.trim().length() == 0) {
            throw new GradleException("Signature file keyPassword cannot be empty ")
        }
    }


    @TaskAction
    public void run() throws Exception {

        JiaGu360Config jiaGu360Config = JiaGu360Config.getConfig(project)
        checkConfig(jiaGu360Config)
        checkJiaGuInfo(jiaGu360Config)

        JiaGu360 jiaGu360 = new JiaGu360(new File(jiaGu360Config.jiaGuJarPath), project.logger)
        jiaGu360.jiaGu(jiaGu360Config.userName, jiaGu360Config.passWord, jiaGuInfo)

    }
}
