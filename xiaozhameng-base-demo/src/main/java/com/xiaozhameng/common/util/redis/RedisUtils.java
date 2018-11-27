package com.xiaozhameng.common.util.redis;

import com.google.common.base.Strings;
import com.xiaozhameng.common.util.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by qianhao on 17/4/1.
 */
public class RedisUtils {
    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    private static String ENCODE = "UTF-8";

    private static Pool<Jedis> pool;

    private static String APP_NAME = "xiaozhameng";

    private static String ERROR_MONITOR_MSG = "redis expection monitor";

    static{
        try {

            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("runtimecfg/redis-conf.properties");
            Properties prop = new Properties();
            try {
            	 prop.load(is);
            } catch (Exception e) {
                throw new RuntimeException("redis.properties is not exist!");
            }
            if (pool == null) {
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(Integer.parseInt(prop.getProperty("maxTotal")));
                config.setMaxIdle(Integer.parseInt(prop.getProperty("maxIdle")));
                config.setMaxWaitMillis(Long.parseLong(prop.getProperty("maxWaitMillis")));
                config.setTestOnBorrow(Boolean.parseBoolean(prop.getProperty("testOnBorrow")));
                config.setTestOnReturn(Boolean.parseBoolean(prop.getProperty("testOnReturn")));
                String[] hostAndPorts = prop.getProperty("sentinels").trim().split(",");
                logger.info("Redis address " + Arrays.toString(hostAndPorts));

                String mode = prop.getProperty("mode");
                logger.info("RedisPool connection mode" + mode);
                if (!Strings.isNullOrEmpty(mode) && "sentinel".equals(mode)) {
                    HashSet hostAndPort = new HashSet(Arrays.asList(hostAndPorts));
                    pool = new JedisSentinelPool(prop.getProperty("masterName").trim(), hostAndPort, config, Integer.parseInt(prop.getProperty("timeout")));
                } else {
                    String[] hostAndPortPair = hostAndPorts[0].split(":");
                    String host = hostAndPortPair[0];
                    int port = Integer.parseInt(hostAndPortPair[1]);
                    pool = new JedisPool(config, host, port, Integer.parseInt(prop.getProperty("timeout")));
                }
                
                logger.info("RedisPool connection success");
            }
        }
        catch (Throwable e){
            logError("redis connection failed",e);
        }
    }
    
    private static void logError(String errorMsg,Throwable e){
        logger.error(ERROR_MONITOR_MSG + ": " + errorMsg,e);
    }

    public static void putStr(String group,String key,String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.set(getRealKey(group,key).getBytes(ENCODE),value.getBytes(ENCODE));
        }
        catch (Exception e){
            logError("failed to set group:"+group+",key:"+key,e);
        }
        finally {
            closeResource(jedis);
        }
    }

    public static void putStr(String group,String key,String value,int timeToLive){
    	logger.info("redis start putStr param:group:{},key:{},value:{},timeToLive:{}",group,key,value,timeToLive);
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.setex(getRealKey(group,key).getBytes(ENCODE),timeToLive,value.getBytes(ENCODE));
            logger.info("redis putStr ok");
        }
        catch (Throwable e){
        	logger.error("redis putStr has exception:"+ group +"key:" + key + "value:" + value,e);
        }
        finally {
            closeResource(jedis);
        }
    }
    
    /**
     * 如果 key 不存在则存储
     * @param group
     * @param key
     * @param value
     * @param expireSecond 有效期
     */
    public static void putStrINX(String group,String key,String value,int expireSecond){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.setnx(getRealKey(group,key), value);
            logger.info("redisUtils setnx success:key:{},value:{}",getRealKey(group,key),value);
            setExpireTime(key, expireSecond);
        }
        catch (Throwable e){
        	logger.error("redis putStrNX has exception:"+ group +"key:" + key + "value:" + value,e);
        }
        finally {
            closeResource(jedis);
        }
    }
    
    /**
     * 获取key对应的值
     * @param key
     * @return
     */
    public static String getByKey(String group ,String key){
    	 Jedis jedis = null;
         try{
             jedis = pool.getResource();
             String value =  jedis.get(getRealKey(group,key));
             logger.info("redis getByKey value success:{}",value);
             if(Strings.isNullOrEmpty(value)){
             	logger.info("redis getByKey value is null:group:{},key:{}",group,key);
             }
             return value;
         }
         catch (Throwable e){
        	 logger.error("redis getByKey has exception:" + group + "key:" + key,e);
         }
         finally {
             closeResource(jedis);
         }
         return "";
    }
    
    /**
     * 设置 key过期时间   单位(秒)
     * @param key
     * @param seconds
     */
    public static void setExpireTime(String key, int seconds) {
    	logger.info("redis setExpireTime param:key:{},second:{}",key,seconds);
    	Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.expire(key, seconds);
        }
        catch (Throwable e){
        	logger.error("redis setExpireTime has exception:",e);
        }
        finally {
            closeResource(jedis);
        }
    }
    

    public static void putObject(String group,String key,Object value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.set(getRealKey(group,key).getBytes(ENCODE),SerializeUtils.encode(value));
        }
        catch (Exception e){
            logError("failed to set group:"+group+",key:"+key,e);
        }
        finally {
            closeResource(jedis);
        }
    }

    public static void putObject(String group,String key,Object value,int timeToLive){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.setex(getRealKey(group, key).getBytes(ENCODE), timeToLive, SerializeUtils.encode(value));
        }
        catch (Exception e){
            logError("failed to set group:"+group+",key:"+key,e);
        }
        finally {
            closeResource(jedis);
        }
    }

    public static String getStr(String group,String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            byte[] valueByte = jedis.get(getRealKey(group,key).getBytes(ENCODE));
            String value =  CheckUtils.isEmpty(valueByte) ? "":new String(valueByte,ENCODE);
            
            logger.info("redis getStr value success:group:{},key:{},value:{}",group,key,value);
            if(Strings.isNullOrEmpty(value)){
            	logger.info("redis getStr value is null:group:{},key:{}",group,key);
            }
            return value;
        }
        catch (Throwable e){
        	logger.error("redis getByKey has exception:" + group + "key:" + key,e);
        }
        finally {
            closeResource(jedis);
        }
        return "";
    }

    public static Object getObject(String group,String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            byte[] valueByte = jedis.get(getRealKey(group,key).getBytes(ENCODE));
            return CheckUtils.isEmpty(valueByte) ? null:SerializeUtils.decode(valueByte);
        }
        catch (Exception e){
            logError("failed to get group:"+group+",key:"+key,e);
        }
        finally {
            closeResource(jedis);
        }
        return null;
    }

    public static void remove(String group,String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.del(getRealKey(group,key).getBytes(ENCODE));
        }
        catch (Exception e){
            logError("failed to delete group:"+group+",key:"+key,e);
        }
        finally {
            closeResource(jedis);
        }
    }

    public static void clear(String group){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            String[] keys = keys(group);
            if(!CheckUtils.isEmpty(keys)){
                jedis.del(keys);
            }
        }
        catch (Exception e){
            logError("failed to delete group:"+group,e);
        }
        finally {
            closeResource(jedis);
        }
    }

    public static boolean contains(String group,String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.exists(getRealKey(group,key).getBytes(ENCODE));
        }
        catch (Exception e){
            logError("failed to check exist of group:"+group+",key:"+key,e);
        }
        finally {
            closeResource(jedis);
        }
        return false;
    }

    public static String[] keys(String group){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            Set<byte[]> keyBytes = jedis.keys((getRealKey(group)+ "*").getBytes(ENCODE));
            Set<String> keys = new HashSet<String>();
            if(!CheckUtils.isEmpty(keyBytes)){
                for(byte[] keyByte:keyBytes){
                    keys.add(new String(keyByte,ENCODE));
                }
            }
            return keys.toArray(new String[0]);
        }
        catch (Exception e){
            logError("failed to get key set of group:"+group,e);
        }
        finally {
            closeResource(jedis);
        }
        return null;
    }

    private static String getRealKey(String group, String key) {
        return APP_NAME+"/"+group+"/"+key;
    }

    private static String getRealKey(String group) {
        return APP_NAME+"/"+group;
    }

    private static void closeResource(Jedis jedis) {
        if (jedis != null) {
            if (jedis.isConnected()) {
                pool.returnResource(jedis);
            } else {
                pool.returnBrokenResource(jedis);
            }
        }
    }

    public static void main(String args[]){
        String groupA = "testGroup";
        String keyA = "testKey";
        String valueA = "banqianhao";
        String keyB = "testKey_2";
        String valueB = "banqianhao1";
        RedisUtils.remove(groupA, keyA);
        RedisUtils.remove(groupA, keyB);
        RedisUtils.putStr(groupA, keyA, valueA);
        //System.out.println("----"+ RedisUtils.getStr(groupA, keyA));
        RedisUtils.remove(groupA, keyA);
        //System.out.println("----"+ RedisUtils.getStr(groupA, keyA));
        String keys[] = RedisUtils.keys(groupA);
        for(String key:keys){
            //System.out.println("----" + key);
        }
        RedisUtils.putStr(groupA, keyA, valueA);
        RedisUtils.putStr(groupA, keyB, valueB);
        keys = RedisUtils.keys(groupA);
        for(String key:keys){

        }
        RedisUtils.remove(groupA, keyA);
        RedisUtils.putStr(groupA, keyA, valueA);
        RedisUtils.putStr(groupA, keyB, valueB);
        keys = RedisUtils.keys(groupA);
        for(String key:keys){
           // System.out.println("-----------------" + key);
        }
        RedisUtils.clear(groupA);
        RedisUtils.clear(groupA);
        keys = RedisUtils.keys(groupA);
        for(String key:keys){
            //System.out.println("----" + key);
        }

    }
}
