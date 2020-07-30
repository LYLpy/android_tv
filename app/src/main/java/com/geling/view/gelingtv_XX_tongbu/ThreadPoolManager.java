package com.geling.view.gelingtv_XX_tongbu;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池管理类
 */
public class ThreadPoolManager {
    private static ThreadPoolManager mInstance = new ThreadPoolManager();
    private int corePoolSize ;//核心线程池大小，就是允许同时执行的任务数量
    private int maximumPoolSize;//最大能允许的任务数量,这个数是包含了corePoolSize在内的数量
    private long keepAliveTime = 1;//最大缓冲任务的存活时间，并不是缓冲队列中等待任务的存活时间
    private TimeUnit unit = TimeUnit.HOURS;//时间单位
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();//缓冲队列，用来存放等待的任务对象
    private ThreadFactory threadFactory = Executors.defaultThreadFactory();//创建线程的工厂类
    private RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();//最大任务超时的拒绝执行的处理策略

    private ThreadPoolExecutor executor;
    private ThreadPoolManager(){
        corePoolSize = 6;
        maximumPoolSize = corePoolSize;
        executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );
    }
    public static ThreadPoolManager create(){
        return mInstance;
    }

    /**
     * 执行任务
     * @param runnable
     */
    public void execute(Runnable runnable){
        executor.execute(runnable);
    }

    /**
     * 移除任务
     * @param runnable
     */
    public void remove(Runnable runnable){
        executor.remove(runnable);
    }

    public void clear(){
        executor.shutdown();
    }

}
