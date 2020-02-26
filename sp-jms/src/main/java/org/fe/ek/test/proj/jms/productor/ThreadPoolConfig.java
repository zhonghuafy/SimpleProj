package org.fe.ek.test.proj.jms.productor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @program: SimpleProj
 * @description: ThreadPoolConfig
 * @author: Wang Zhenhua
 * @create: 2020-02-26
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-26
 **/
@Component
@EnableAsync
public class ThreadPoolConfig {

    private static final int DEFAULT_COREPOOLSIZE = 1;

    private static final int DEFAULT_MAXPOOLSIZE = 10;

    private static final int DEFAULT_QUEUECAPACITY = 10;

    private static final int DEFAULT_KEEPALIVESECONDS = 60;

    private static final int DEFAULT_AWAITTERMINATIONSECONDS = 60;

    @Bean("threadPool")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(DEFAULT_COREPOOLSIZE);
        executor.setMaxPoolSize(DEFAULT_MAXPOOLSIZE);
        executor.setQueueCapacity(DEFAULT_QUEUECAPACITY);
        executor.setKeepAliveSeconds(DEFAULT_KEEPALIVESECONDS);
        //shutdown的时候等待任务处理完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(DEFAULT_AWAITTERMINATIONSECONDS);
        executor.initialize();
        return executor;
    }
}
