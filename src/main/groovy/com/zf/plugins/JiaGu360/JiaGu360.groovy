package com.zf.plugins.JiaGu360

import org.gradle.api.GradleException
import org.gradle.api.logging.Logger

import java.util.regex.Pattern

public class JiaGu360 {

    private final static String VIP_PERMISSION_ERROR_FLAG = "上传失败200VIP权限不足"

    Logger logger
    public File jiaGuJarDir
    public String jiaGuJarName
    public def ALLATORILOG = '''################################################
#                                              #
#        ## #   #    ## ### ### ##  ###        #
#       # # #   #   # #  #  # # # #  #         #
#       ### #   #   ###  #  # # ##   #         #
#       # # ### ### # #  #  ### # # ###        #
#                                              #
# Obfuscation by Allatori Obfuscator v5.6 DEMO #
#                                              #
#           http://www.allatori.com            #
#                                              #
################################################
'''


    JiaGu360(File jiaGuJarFile, Logger logger) {
        this.jiaGuJarDir = jiaGuJarFile.getParentFile();
        this.jiaGuJarName = jiaGuJarFile.getName();
        this.logger = logger;
    }


    void loginInternal(String userName, String passWord) {
        String cmd = "java -jar ${jiaGuJarName} -login ${userName} ${passWord}"
        exec(cmd, { commandLine ->
            logger.quiet(commandLine.replaceAll(Pattern.quote("${userName}"), "XXXXXX").replaceAll(Pattern.quote("${passWord}"), "XXXXXX"))
        })
    }

    void importSignInternal(SigningInfo signingInfo) {
        String cmd = "java -jar ${jiaGuJarName} -importsign ${signingInfo.storeFilePath} ${signingInfo.storePassword} ${signingInfo.keyAlias} ${signingInfo.keyPassword}"
        StringBuilder resultBuild = new StringBuilder();
        exec(cmd, { commandLine ->
            logger.quiet(commandLine.replaceAll(Pattern.quote("${signingInfo.storePassword}"), "XXXXXX").replaceAll(Pattern.quote("${signingInfo.keyPassword}"), "XXXXXX"))

        }, { type, text ->
            if (!((text ==~ /\s?/) || (text ==~ /^#.*#$/))) {
                logger.quiet("${type} > ${text}")
            }
            resultBuild.append(text)
        })

        if (resultBuild.contains("saving signer failed")) {
            throw new GradleException("ERROR!")
        }

    }

    private void configInternal(JiaGuInfo jiaGuInfo) {
        StringBuilder cmdBuilder = new StringBuilder("java -jar ${jiaGuJarName} -config")
        if (jiaGuInfo.isSupportCrashLong) {
            cmdBuilder.append(" -crashlog")
        }

        if (jiaGuInfo.isSupportX86) {
            cmdBuilder.append(" -x86")
        }

        if (jiaGuInfo.isSupportAnalyse) {
            cmdBuilder.append(" -analyse")
        }

        if (jiaGuInfo.isNocert) {
            cmdBuilder.append(" -nocert")
        }

        if (jiaGuInfo.isSupportVmp) {
            cmdBuilder.append(" -vmp")
        }

        if (jiaGuInfo.isSupportDataProtection) {
            cmdBuilder.append(" -data")
        }

        if (jiaGuInfo.isSupportAssetsProtection) {
            cmdBuilder.append(" -assets")
        }

        if (jiaGuInfo.isSupportFileCheck) {
            cmdBuilder.append(" -filecheck")
        }

        if (jiaGuInfo.isSupportPtrace) {
            cmdBuilder.append(" -ptrace")
        }

        if (jiaGuInfo.isSupportSoProtection) {
            cmdBuilder.append(" -so")
        }

        if (jiaGuInfo.isSupportDex2cProtection) {
            cmdBuilder.append(" -dex2c")
        }

        if (jiaGuInfo.isSupportStringObfusProtection) {
            cmdBuilder.append(" -string_obfus")
        }

        if (jiaGuInfo.isSupportDexShadowProtection) {
            cmdBuilder.append(" -dex_shadow")
        }

        if (jiaGuInfo.isSupportSoPrivateProtection) {
            cmdBuilder.append(" -so_private")
        }
        exec(cmdBuilder.toString())
    }

    void showConfig() {
        String cmd = "java -jar ${jiaGuJarName} -showconfig"
        exec(cmd)
    }

    void update() {
        String cmd = "java -jar ${jiaGuJarName} -update"
        exec(cmd)
    }

    private void openDir(dir) {
        if (isWindowSystem()) {
            String cmd = "explorer.exe ${dir}"
            exec(cmd)
        } else {
            logger.quiet("打开输出文件夹，暂只支持windows")
        }
    }

    void jiaGu(String usreName, String passWord, JiaGuInfo jiaGuInfo) {
        loginInternal(usreName, passWord)

        if (jiaGuInfo.autosign) {
            importSignInternal(jiaGuInfo.signingInfo)
        }

        configInternal(jiaGuInfo)

        showConfig()

        jiaGuInternal(jiaGuInfo.inputApkFilePath, jiaGuInfo.outputApkDirPath, jiaGuInfo.autosign, jiaGuInfo.mulpkgFilePath)

        if (jiaGuInfo.openOutputDir) {
            openDir(jiaGuInfo.outputApkDirPath)
        }
        logger.quiet('')
        logger.quiet("-------------------------------------------------------------")
        logger.quiet("apk签名：${jiaGuInfo.autosign}")
        logger.quiet("加固文件输出目录：${jiaGuInfo.outputApkDirPath}")
        logger.quiet("--------------------------------------------------------------")
    }

    void jiaGuInternal(inputApkFilePath, outputApkDirPath, boolean autoSign, String mulpkgFilePath) {
        StringBuilder cmdBuilder = new StringBuilder("java -jar ${jiaGuJarName} -jiagu ${inputApkFilePath} ${outputApkDirPath}");
        if (autoSign) {
            cmdBuilder.append(" -autosign")
        }

        if (mulpkgFilePath) {
            cmdBuilder.append(" -pkgparam ${mulpkgFilePath}")
        }

        exec(cmdBuilder.toString())
    }

    String[] getCmd(String cmd) {
        String[] cmdArr = ["/bin/sh", "-c", cmd]
        if (isWindowSystem()) {
            cmdArr[0] = "cmd"
            cmdArr[1] = "/C"
        }

        return cmdArr
    }

    void exec(cmd, Closure cmdClosure, Closure closure) {

        String[] cmdArr = getCmd(cmd);

        if (cmdClosure) {
            cmdClosure(cmdArr[2])
        } else {
            this.logger.quiet(cmdArr[2].toString())
        }

        Process process = Runtime.runtime.exec(cmdArr, null, this.jiaGuJarDir)
        StreamConsumer infoStream = new StreamConsumer(process.inputStream, 'output', 'gbk', closure)
        infoStream.start()
        process.waitFor()

        infoStream.getInputText().each { line ->
            if (line.contains(VIP_PERMISSION_ERROR_FLAG)) {
                throw new GradleException(line)
            }
        }

        def errorText = process.err.getText("gbk")

        if (errorText != null && errorText.trim().length() > 0) {
            String text = errorText.replace(ALLATORILOG, "")
            throw new GradleException("${text},errorCode=${process.exitValue()}")
        }
    }

    void exec(cmd) {
        exec(cmd, null, { type, text ->
            if (!((text ==~ /\s?/) || (text ==~ /^#.*#$/))) {
                this.logger.quiet("${type} > ${text}")
            }
        })
    }

    void exec(cmd, Closure cmdClosure) {
        exec(cmd, cmdClosure, { type, text ->
            if (!((text ==~ /\s?/) || (text ==~ /^#.*#$/))) {
                this.logger.quiet("${type} > ${text}")
            }
        })
    }

    static boolean isWindowSystem() {
        return System.getProperty("file.separator") == "\\";
    }

}
