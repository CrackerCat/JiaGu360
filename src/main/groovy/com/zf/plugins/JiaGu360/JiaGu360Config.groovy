package com.zf.plugins.JiaGu360

import com.sun.org.apache.xpath.internal.operations.Bool
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project;

class JiaGu360Config {

    String userName
    String passWord
    String jiaGuJarPath

    String signingName

    String outputApkDirPath

    boolean openOutputDir //打开目录

    boolean isSupportAll

    boolean isSupportCrashLong //崩溃日志分析
    boolean isSupportX86; //x86支持
    boolean isSupportAnalyse;//加固数据分析
    boolean isNocert //跳过签名校验

    boolean isSupportVmp //全VMP保护
    boolean isSupportDataProtection//本地数据文件保护
    boolean isSupportAssetsProtection//资源文件保护
    boolean isSupportFileCheck//文件完整性校验
    boolean isSupportPtrace//Ptrace防注入
    boolean isSupportSoProtection//SO文件保护
    boolean isSupportDex2cProtection//【dex2C保护】
    boolean isSupportStringObfusProtection//【字符串加密】
    boolean isSupportDexShadowProtection//【DexShadow】
    boolean isSupportSoPrivateProtection//【SO防盗用】

    boolean autosign //自动签名

    String mulpkgFilePath //自定义文件生成多渠道

    SigningInfo signingInfo

    NamedDomainObjectContainer<JiaGuInfo> items

    JiaGu360Config(Project project) {
        NamedDomainObjectContainer<JiaGuInfo> itemsContainer = project.container(JiaGuInfo)
        items = itemsContainer
    }

    void userName(String userName) {
        this.userName = userName
    }

    void passWord(String passWord) {
        this.passWord = passWord
    }

    void isSupportAll(boolean isSupportAll) {
        this.isSupportAll = isSupportAll
    }

    void outputApkDirPath(String outputApkDirPath) {
        this.outputApkDirPath = outputApkDirPath
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

    void items(Action<NamedDomainObjectContainer<JiaGuInfo>> action) {
        action.execute(items)
    }


    public static JiaGu360Config getConfig(Project project) {
        JiaGu360Config config =
                project.getExtensions().findByType(JiaGu360Config.class);
        if (config == null) {
            config = new JiaGu360Config()
        }
        return config;
    }


}
