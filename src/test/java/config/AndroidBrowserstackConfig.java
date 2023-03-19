package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:android.properties"
})
public interface AndroidBrowserstackConfig extends Config {

    @Key("user")
    String getUser();

    @Key("key")
    String getKey();

    @Key("app")
    String getApp();

    @Key("device")
    String getDevice();

    @Key("os_version")
    String getOsVersion();

    @Key("project")
    String getProject();

    @Key("build")
    String getBuild();

    @Key("name")
    String getName();

    @Key("remoteURL")
    String getRemoteURL();

}
