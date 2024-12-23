package com.example.step05.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@RequiredArgsConstructor
@Slf4j
@Component
public class MessageTaskExecutorHealthCheck {

    private final ThreadPoolTaskExecutor pollTaskExecutor;
    private final ThreadPoolTaskExecutor pubSubTaskExecutor;
    private final ThreadPoolTaskExecutor inBoundTaskExecutor;

    @Scheduled(cron = "0/10 * * * * *")
    public void messageExecutorHealth() {
        ThreadPoolExecutor pollTaskExecutorThreadPoolExecutor = pollTaskExecutor.getThreadPoolExecutor();
        ThreadPoolExecutor pubSubTaskExecutorThreadPoolExecutor = pubSubTaskExecutor.getThreadPoolExecutor();
        ThreadPoolExecutor inBoundTaskExecutorThreadPoolExecutor = inBoundTaskExecutor.getThreadPoolExecutor();

        // Poll Task Executor 상태 로그
        log.info(
                "[ThreadPool Status]\tPOLL_TASK_EXECUTOR: pool size = {}, active threads = {}, queued tasks = {}, remaining queued capacity = {}",
                pollTaskExecutorThreadPoolExecutor.getPoolSize(),
                pollTaskExecutorThreadPoolExecutor.getActiveCount(),
                pollTaskExecutorThreadPoolExecutor.getQueue().size(),
                pollTaskExecutorThreadPoolExecutor.getQueue().remainingCapacity());

        // Inbound Task Executor 상태 로그
        log.info(
                "[ThreadPool Status]\tPUBSUB_TASK_EXECUTOR: pool size = {}, active threads = {}, queued tasks = {}, remaining queued capacity = {}",
                pubSubTaskExecutorThreadPoolExecutor.getPoolSize(),
                pubSubTaskExecutorThreadPoolExecutor.getActiveCount(),
                pubSubTaskExecutorThreadPoolExecutor.getQueue().size(),
                pubSubTaskExecutorThreadPoolExecutor.getQueue().remainingCapacity());

        // Inbound Task Executor 상태 로그
        log.info(
                "[ThreadPool Status]\tINBOUND_TASK_EXECUTOR: pool size = {}, active threads = {}, queued tasks = {}, remaining queued capacity = {}",
                inBoundTaskExecutorThreadPoolExecutor.getPoolSize(),
                inBoundTaskExecutorThreadPoolExecutor.getActiveCount(),
                inBoundTaskExecutorThreadPoolExecutor.getQueue().size(),
                inBoundTaskExecutorThreadPoolExecutor.getQueue().remainingCapacity());
    }

}
