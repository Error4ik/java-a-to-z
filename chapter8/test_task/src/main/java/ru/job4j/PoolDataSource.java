package ru.job4j;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;

/**
 * Create a ConnectionFactory that the pool will use to create Connections.
 *
 * @author Alexey Voronin.
 * @since 28.06.2017.
 */
public class PoolDataSource {

    /**
     * Create a ConnectionFactory that the pool will use to create Connections.
     * Create the PoolableConnectionFactory, which wraps the "real" Connections.
     * Set the factory's pool property to the owning pool.
     * Now we'll need a ObjectPool that serves as the actual pool of connections.
     * Create the PoolingDriver itself, passing in the object pool we created.
     *
     * @param url      url.
     * @param userName user name.
     * @param password password.
     * @return dataSource.
     */
    public static DataSource setupDataSource(final String url, final String userName, final String password) {
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, userName, password);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        return new PoolingDataSource<>(connectionPool);
    }
}
