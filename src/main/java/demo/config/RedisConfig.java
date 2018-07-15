package demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by PC on 2017/12/5. redis数据库配置配置了多组数据源
 */
@Configuration
public class RedisConfig {
    private final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
//        @Value("${redis.sentinel.group}")
//        protected String sentinelGroupName;
//
//        @Value("${redis.sentinel.nodes}")
//        protected String sentinelNodes;


        @Value("${spring.redis.maxTotal}")
        protected int maxTotal;

        @Value("${spring.redis.minIdle}")
        protected int minIdle;

        @Value("${spring.redis.maxIdle}")
        protected int maxIdle;

        @Value("${spring.redis.maxWaitMillis}")
        protected long maxWaitMillis;

        @Value("${spring.redis.testOnBorrow}")
        protected boolean testOnBorrow;

        @Value("${spring.redis.testOnReturn}")
        protected boolean testOnReturn;

        @Value("${spring.redis.password}")
        protected String password;

        @Value("${spring.redis.timeout}")
        protected int timeout;

        @Value("${spring.redis.host}")
        protected String host;

        @Value("${spring.redis.port}")
        protected int port;

//        @Bean
//        public RedisSentinelConfiguration redisSentinelConfiguration() {
//                 String[] nodes = sentinelNodes.split(",");
//                 Set<String> setNodes = new HashSet<>();
//                 for (String n : nodes) {
//                         setNodes.add(n.trim());
//                     }
//
//                 RedisSentinelConfiguration configuration = new RedisSentinelConfiguration(sentinelGroupName, setNodes);
//                 return configuration;
//             }

        @Bean
        public JedisPoolConfig jedisPoolConfig() {
                 JedisPoolConfig poolConfig = new JedisPoolConfig();
                 poolConfig.setMaxTotal(maxTotal);
                 poolConfig.setMinIdle(minIdle);
                 poolConfig.setMaxIdle(maxIdle);
                 poolConfig.setMaxWaitMillis(maxWaitMillis);
                 poolConfig.setTestOnBorrow(testOnBorrow);
                 poolConfig.setTestOnReturn(testOnReturn);
                 return poolConfig;
             }

        @Bean
        public StringRedisSerializer stringRedisSerializer() {
                 return new StringRedisSerializer();
             }

        @Bean(name = "redisTemplate00")
        @Primary
        public StringRedisTemplate redisTemplate00() {
                 return buildRedisTemplate(buildConnectionFactory(0));
             }

        @Bean(name = "redisTemplate01")
        public StringRedisTemplate redisTemplate01() {
            // System.out.println("************************************ ");
            // Logger.getLogger(getClass().info("Retis链接池建立成功"));
            logger.info("Retis链接池建立成功");
                 return buildRedisTemplate(buildConnectionFactory(6));
             }

        @Bean(name = "redisTemplate02")
        public StringRedisTemplate redisTemplate02() {
                 return buildRedisTemplate(buildConnectionFactory(2));
             }

        @Bean(name = "redisTemplate03")
        public StringRedisTemplate redisTemplate03() {
                 return buildRedisTemplate(buildConnectionFactory(3));
             }

        @Bean(name = "redisTemplate04")
        public StringRedisTemplate redisTemplate04() {
                 return buildRedisTemplate(buildConnectionFactory(4));
             }

        private JedisConnectionFactory buildConnectionFactory(int database) {
            try {
                JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig());
                connectionFactory.setUsePool(true);
                connectionFactory.setTimeout(timeout);
                connectionFactory.setDatabase(database);
                connectionFactory.setPassword(password);
                connectionFactory.setHostName(host);
                connectionFactory.setPort(port);
                connectionFactory.afterPropertiesSet();
                return connectionFactory;
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
         }

        protected StringRedisTemplate buildRedisTemplate(RedisConnectionFactory connectionFactory) {
            try{
                StringRedisTemplate template = new StringRedisTemplate();
                template.setConnectionFactory(connectionFactory);
                template.setValueSerializer(stringRedisSerializer());
                template.afterPropertiesSet();
                return template;
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
         }
}
