package taskmanager.core;

import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

public final class MongoDBConnection {
    private static volatile Datastore datastore;
    private MongoDBConnection() {}

    // Singleton method to get Datastore instance
    public synchronized static Datastore getDatastore() throws IOException, URISyntaxException, ClassNotFoundException {
        if (datastore == null) {
            synchronized (MongoDBConnection.class) {
                if (datastore == null) {
                    Properties properties = new Properties();
                    InputStream inputStream = MongoDBConnection.class.getClassLoader()
                            .getResourceAsStream("application.properties");
                    properties.load(inputStream);

                    String connectionString = properties.getProperty("mongodb.host");
                    String dbName = properties.getProperty("mongodb.db-name");

                    datastore = Morphia.createDatastore(MongoClients.create(connectionString), dbName);
                    datastore.getMapper().mapPackage("taskmanager.model");
                }
            }
        }
        return datastore;
    }

}
