package com.seal.util.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * github:https://github.com/jasonhsu2017/happy-springboot/tree/master/happy-springboot/happy-springboot-service/src/main/java/com/happy/springboot/service/snowflake
 * @author fengzhiqiang
 * @date-time 2020/5/15 15:55
 **/
public class SnowflakeIdGenUtil {
    /**
     * 序列的初始值
     */
    private long sequence = 0L;
    /**
     * 序列位数
     */
    private final long sequenceBits = 12L;
    /**
     * 这是位运算，long用的是补码，-1L，就是64个1，这里使用-1是为了格式化所有位数，<<是左移运算，-1L左移五位，低位补零，
     * 也就是左移空出来的会自动补0，于是就低位五位是0，其余是1。然后^这个符号，是异或，也是位运算，位上相同则为0，不通则为1，
     * 和-1做异或，则把所有的0和1颠倒了一下
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 机器位数
     */
    private long workerId;
    private final long workerIdBits = 5L;
    /**
     * 向左偏移量
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long workerIdShift = sequenceBits;

    /**
     * 可以抽象为集群位数
     */
    private long datacenterId;
    private final long datacenterIdBits = 5L;
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /**
     * 向左偏移量
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间戳
     */
    private long lastTimestamp = -1L;
    /**
     * 基础值：默认是2017-07-11  2017-07-11
     */
    private final long twepoch = 1531287769216l;
    /**
     * 时间戳偏移总位数
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    public SnowflakeIdGenUtil(long datacenterId, long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        return genetatorId();
    }

    public synchronized List<Long> nextIdList(int qty) {
        List<Long> result = new ArrayList<Long>(qty);
        for (int i = 0; i < qty; i++) {
            result.add(genetatorId());
        }
        return result;
    }

    protected long genetatorId() {
        long timestamp = timeGen();
        // 如果发生了时间回拨，异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果当前毫秒已经生成过ID
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 如果序列号已经用光，要等到下一个毫秒
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 为了均衡改为每毫秒第一个序列号随机一位
            sequence = new SecureRandom().nextInt(10);
        }

        lastTimestamp = timestamp;

        //时间戳 | 应用号 |机器号 | 序列号
        long l1 = ((timestamp - twepoch) << timestampLeftShift);
        long l2 = (datacenterId << datacenterIdShift);
        long l3 = (workerId << workerIdShift);
        long l4 = sequence;
        System.err.println(l1);
        System.err.println(l2);
        System.err.println(l3);
        System.err.println(l4);
        System.err.println(l1 | l2 | l3 | l4);

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    public long timeGen() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {
        SnowflakeIdGenUtil utils = new SnowflakeIdGenUtil(1, 5);
        System.out.println(utils.genetatorId());
    }
}
