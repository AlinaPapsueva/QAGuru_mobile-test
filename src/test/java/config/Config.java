package config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:${env}.properties",
                "classpath:credentials.properties"})

public interface Config extends org.aeonbits.owner.Config {
    @Key("appURL")
    String appUrl();

    @Key("baseURL")
    String baseUrl();

    @Key("device")
    String device();

    @Key("os_version")
    String osVersion();

    @Key("project")
    String projectName();

    @Key("build")
    String buildName();

    @Key("name")
    String testName();

    @Key("login")
    String login();

    @Key("password")
    String password();

}