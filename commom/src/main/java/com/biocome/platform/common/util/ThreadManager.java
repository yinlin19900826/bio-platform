package com.biocome.platform.common.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hxy
 * @date 2019/5/22 11:29
 */
public class ThreadManager {
    public static ThreadPool instance;
    private ThreadPoolExecutor longExecutor; // 耗时比较长的线程池   用来请求网络
    private ThreadPoolExecutor shortExecutor; // 比较短的线程池    用来加载本地数据

    // 获取单例的线程池对象
    public static ThreadPool getInstance() {
        if (instance == null) {
            synchronized (ThreadManager.class) {
                if (instance == null) {
                    int cpuNum = Runtime.getRuntime().availableProcessors();// 获取处理器数量
                    int threadNum = cpuNum * 2 + 1;// 根据cpu数量,计算出合理的线程并发数
                    instance = new ThreadPool(threadNum-1, threadNum, 86400000);//默认是双核的cpu 每个核心走一个线程 一个等待线程
                }
            }
        }
        return instance;
    }
    public static class ThreadPool {
        private ThreadPoolExecutor mExecutor;
        private int corePoolSize;
        private int maxinumPoolSize;
        private long keepAliveTime;

        private ThreadPool(int corePoolSize, int maxinumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maxinumPoolSize = maxinumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        public void execute(Runnable runnable) {
            if (runnable == null) {
                return;
            }
            if (mExecutor == null) {
                mExecutor = new ThreadPoolExecutor(corePoolSize,
                        maxinumPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<Runnable>(Integer.MAX_VALUE),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy() {
                            @Override
                            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                                super.rejectedExecution(r, e);
                            }
                        }
                );
            }
            mExecutor.execute(runnable);
        }
        public void cancel(Runnable runnable){
            if (mExecutor != null){
                mExecutor.getQueue().remove(runnable);
            }
        }
    }
}
