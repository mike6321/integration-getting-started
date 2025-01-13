package com.example.step05;

import datadog.trace.api.GlobalTracer;
import datadog.trace.api.interceptor.MutableSpan;
import datadog.trace.api.interceptor.TraceInterceptor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomTraceInterceptor implements TraceInterceptor {

    @Override
    public Collection<? extends MutableSpan> onTraceComplete(Collection<? extends MutableSpan> trace) {
        trace.forEach(span -> {
            System.out.println("Span details:");
            System.out.println("Resource Name: " + span.getResourceName());
            System.out.println("Operation Name: " + span.getOperationName());
            System.out.println("Span Type: " + span.getSpanType());
            System.out.println("Tags: " + span.getTags());
            System.out.println("-----------------------------------");
//            if (span.getOperationName() != null && span.getOperationName().contains("AbstractPollingEndpoint")) {
//            }
        });
        return trace;
    }

    @Override
    public int priority() {
        return Integer.MAX_VALUE; // 우선순위를 가장 높게 설정
    }

    static {
        // GlobalTracer에 등록
        GlobalTracer.get().addTraceInterceptor(new CustomTraceInterceptor());
    }

}

