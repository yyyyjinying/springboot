package jedis.cluster;

public interface JedisClusterDao {
    Boolean exists(String key);

    String get(String key);

    void set(String key,String value);
}

