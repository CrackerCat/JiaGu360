package com.zf.plugins.JiaGu360

import org.gradle.api.Action

class JiaGuInfo {

    String name
    String inputApkFilePath
    String outputApkDirPath
    String channelFilePath

    String signingName

    Boolean openOutputDir

    Boolean isSupportCrashLong //崩溃日志分析
    Boolean isSupportX86; //x86支持
    Boolean isSupportAnalyse;//加固数据分析
    Boolean isNocert //跳过签名校验

    Boolean isSupportVmp //全VMP保护
    Boolean isSupportDataProtection//本地数据文件保护
    Boolean isSupportAssetsProtection//资源文件保护
    Boolean isSupportFileCheck//文件完整性校验
    Boolean isSupportPtrace//Ptrace防注入
    Boolean isSupportSoProtection//SO文件保护
    Boolean isSupportDex2cProtection//【dex2C保护】
    Boolean isSupportStringObfusProtection//【字符串加密】
    Boolean isSupportDexShadowProtection//【DexShadow】
    Boolean isSupportSoPrivateProtection//【SO防盗用】

    Boolean autosign //自动签名

    String mulpkgFilePath //自定义文件生成多渠道

    SigningInfo signingInfo

    JiaGuInfo(String name) {
        this.name = name
    }

    void userName(String userName) {
        this.userName = userName
    }

    void passWord(String passWord) {
        this.passWord = passWord
    }

    void isSupportCrashLong(boolean isSupportCrashLong) {
        this.isSupportCrashLong = isSupportCrashLong
    }

    void isSupportX86(boolean isSupportX86) {
        this.isSupportX86 = isSupportX86
    }

    void isSupportAnalyse(boolean isSupportAnalyse) {
        this.isSupportAnalyse = isSupportAnalyse
    }

    void isNocert(boolean isNocert) {
        this.isNocert = isNocert
    }

    void isSupportVmp(boolean isSupportVmp) {
        this.isSupportVmp = isSupportVmp
    }

    void isSupportDataProtection(boolean isSupportDataProtection) {
        this.isSupportDataProtection = isSupportDataProtection
    }

    void isSupportAssetsProtection(boolean isSupportAssetsProtection) {
        this.isSupportAssetsProtection = isSupportAssetsProtection
    }

    void isSupportFileCheck(boolean isSupportFileCheck) {
        this.isSupportFileCheck = isSupportFileCheck
    }

    void isSupportPtrace(boolean isSupportPtrace) {
        this.isSupportPtrace = isSupportPtrace
    }

    void isSupportSoProtection(boolean isSupportSoProtection) {
        this.isSupportSoProtection = isSupportSoProtection
    }

    void isSupportDex2cProtection(boolean isSupportDex2cProtection) {
        this.isSupportDex2cProtection = isSupportDex2cProtection
    }

    void isSupportStringObfusProtection(boolean isSupportStringObfusProtection) {
        this.isSupportStringObfusProtection = isSupportStringObfusProtection
    }

    void isSupportDexShadowProtection(boolean isSupportDexShadowProtection) {
        this.isSupportDexShadowProtection = isSupportDexShadowProtection
    }

    void isSupportSoPrivateProtection(boolean isSupportSoPrivateProtection) {
        this.isSupportSoPrivateProtection = isSupportSoPrivateProtection
    }

    void autosign(boolean autosign) {
        this.autosign = autosign
    }

    void mulpkgFilePath(String mulpkgFilePath) {
        this.mulpkgFilePath = mulpkgFilePath
    }

    void jiaGuJarPath(String jiaGuJarPath) {
        this.jiaGuJarPath = jiaGuJarPath
    }

    void channelFilePath(String channelFilePath) {
        this.channelFilePath = channelFilePath
    }

    void openOutputDir(boolean openOutputDir) {
        this.openOutputDir = openOutputDir
    }

    void inputApkFilePath(String inputApkFilePath) {
        this.inputApkFilePath = inputApkFilePath
    }

    void outputApkDirPath(String outputApkDirPath) {
        this.outputApkDirPath = outputApkDirPath
    }

    void signingInfo(Action<SigningInfo> action) {

        if (this.signingInfo == null) {
            this.signingInfo = new SigningInfo()
        }

        action.execute(this.signingInfo);
    }

    void signingInfo(Closure c) {
        if (this.signingInfo == null) {
            this.signingInfo = new SigningInfo()
        }
        org.gradle.util.ConfigureUtil.configure(c, this.signingInfo);
    }
}
