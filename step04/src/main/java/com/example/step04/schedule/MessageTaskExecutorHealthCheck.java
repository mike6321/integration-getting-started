package com.example.step04.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageTaskExecutorHealthCheck {

    private final ThreadPoolTaskExecutor pollTaskExecutor;
    private final ThreadPoolTaskExecutor inBoundTaskExecutor;
    private final ThreadPoolTaskExecutor messageTaskExecutor;

    @Scheduled(cron = "0/20 * * * * *")
    public void messageExecutorHealth() {
        ThreadPoolExecutor pollTaskExecutorThreadPoolExecutor = pollTaskExecutor.getThreadPoolExecutor();
        ThreadPoolExecutor inBoundTaskExecutorThreadPoolExecutor = inBoundTaskExecutor.getThreadPoolExecutor();
        ThreadPoolExecutor messageTaskExecutorThreadPoolExecutor = messageTaskExecutor.getThreadPoolExecutor();

        // Poll Task Executor 상태 로그
        log.info(
                "[ThreadPool Status]\tPOLL_TASK_EXECUTOR: pool size = {}, active threads = {}, queued tasks = {}, remaining queued capacity = {}",
                pollTaskExecutorThreadPoolExecutor.getPoolSize(),
                pollTaskExecutorThreadPoolExecutor.getActiveCount(),
                pollTaskExecutorThreadPoolExecutor.getQueue().size(),
                pollTaskExecutorThreadPoolExecutor.getQueue().remainingCapacity());

        // message Task Executor 상태 로그
        log.info(
                "[ThreadPool Status]\tMESSAGE_TASK_EXECUTOR: pool size = {}, active threads = {}, queued tasks = {}, remaining queued capacity = {}",
                messageTaskExecutorThreadPoolExecutor.getPoolSize(),
                messageTaskExecutorThreadPoolExecutor.getActiveCount(),
                messageTaskExecutorThreadPoolExecutor.getQueue().size(),
                messageTaskExecutorThreadPoolExecutor.getQueue().remainingCapacity());

        // Inbound Task Executor 상태 로그
        log.info(
                "[ThreadPool Status]\tINBOUND_TASK_EXECUTOR: pool size = {}, active threads = {}, queued tasks = {}, remaining queued capacity = {}",
                inBoundTaskExecutorThreadPoolExecutor.getPoolSize(),
                inBoundTaskExecutorThreadPoolExecutor.getActiveCount(),
                inBoundTaskExecutorThreadPoolExecutor.getQueue().size(),
                inBoundTaskExecutorThreadPoolExecutor.getQueue().remainingCapacity());
    }

}
