package com.example.step04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.time.Duration;
import java.util.concurrent.Executor;

@SpringBootTest
public class SchedulingTest {

    private final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler(); // 명시적 선언
    private final Executor taskExecutor = new SyncTaskExecutor();
    private final TaskExecutor customTaskExecutor = createCustomTaskExecutor();

    /**
     * @see org.springframework.integration.endpoint.AbstractPollingEndpoint#doStart()
     * @see org.springframework.integration.endpoint.AbstractPollingEndpoint#createPoller()
     * @see org.springframework.integration.endpoint.AbstractPollingEndpoint#DEFAULT_POLLING_PERIOD
     * */
    @DisplayName("QueueChannel 기본 동작 방식 - SyncTaskExecutor")
    @Test
    void test1() throws InterruptedException {
        this.taskScheduler.initialize();
        var trigger = new PeriodicTrigger(Duration.ofMillis(1000));
        this.taskScheduler.schedule(this.doSomethingWithSyncTaskExecutor(),trigger);
        Thread.sleep(5000);
        taskScheduler.shutdown();
    }

    private Runnable doSomethingWithSyncTaskExecutor() {
        return () -> this.taskExecutor.execute(() -> System.out.println("doSomething"));
    }

    @DisplayName("QueueChannel 기본 동작 방식 - CustomTaskExecutor")
    @Test
    void test2() throws InterruptedException {
        this.taskScheduler.initialize();
        var trigger = new PeriodicTrigger(Duration.ofMillis(1000));
        this.taskScheduler.schedule(this.doSomethingWithCustomTaskExecutor(),trigger);
        Thread.sleep(5000);
        taskScheduler.shutdown();
    }

    private Runnable doSomethingWithCustomTaskExecutor() {
        return () -> this.customTaskExecutor.execute(() -> System.out.println("doSomething"));
    }

    private TaskExecutor createCustomTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 스레드 풀 크기
        executor.setMaxPoolSize(10); // 최대 스레드 개수
        executor.setQueueCapacity(25); // 작업 대기열 크기
        executor.setThreadNamePrefix("CustomExecutor-");
        executor.initialize(); // 초기화
        return executor;
    }

}
