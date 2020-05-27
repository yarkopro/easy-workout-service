package org.yarkopro.conf;

/**
 * Holds a configuration loaded from the {@code application.yml} file on the classpath.
 */
public enum ConfigurationHolder {
    INSTANCE;

    private final Configuration configuration = load();

    private Configuration load() {
        Configuration.DataSource dataSource = new Configuration.DataSource();
        String db_url = System.getenv("DB_URL");
        String db_username = System.getenv("DB_USER_NAME");
        String db_pass = System.getenv("DB_PASSWORD");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername(db_username);
        dataSource.setPassword(db_pass);
        dataSource.setUrl(db_url);
        Configuration dbConf = new Configuration();
        dbConf.setDatasource(dataSource);

        return dbConf;
    }

    public Configuration configuration() {
        return this.configuration;
    }
}
