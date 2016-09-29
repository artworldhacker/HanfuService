package com.dexingworld.hanfu.web.configration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.util.Assert;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;

/**
 * Created by wonpera on 2016/9/29.
 */
public class HanfuAsyncSupportConfigurer extends AsyncSupportConfigurer {

    private AsyncTaskExecutor taskExecutor;

    private Long timeout;

    private final List<CallableProcessingInterceptor> callableInterceptors = new ArrayList<CallableProcessingInterceptor>();

    private final List<DeferredResultProcessingInterceptor> deferredResultInterceptors = new ArrayList<DeferredResultProcessingInterceptor>();

    /**
     * Set the default {@link AsyncTaskExecutor} to use when a controller method
     * returns a {@link Callable}. Controller methods can override this default
     * on a per-request basis by returning a {@link WebAsyncTask}.
     *
     * <p>
     * By default a {@link SimpleAsyncTaskExecutor} instance is used, and it's
     * highly recommended to change that default in production since the simple
     * executor does not re-use threads.
     *
     * @param taskExecutor
     *            the task executor instance to use by default
     */
    public AsyncSupportConfigurer setTaskExecutor(AsyncTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
        return this;
    }

    /**
     * Specify the amount of time, in milliseconds, before asynchronous request
     * handling times out. In Servlet 3, the timeout begins after the main
     * request processing thread has exited and ends when the request is
     * dispatched again for further processing of the concurrently produced
     * result.
     * <p>
     * If this value is not set, the default timeout of the underlying
     * implementation is used, e.g. 10 seconds on Tomcat with Servlet 3.
     *
     * @param timeout
     *            the timeout value in milliseconds
     */
    public AsyncSupportConfigurer setDefaultTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Configure lifecycle interceptors with callbacks around concurrent request
     * execution that starts when a controller returns a
     * {@link java.util.concurrent.Callable}.
     *
     * @param interceptors
     *            the interceptors to register
     */
    public AsyncSupportConfigurer registerCallableInterceptors(
            CallableProcessingInterceptor... interceptors) {
        Assert.notNull(interceptors, "Interceptors are required");
        this.callableInterceptors.addAll(Arrays.asList(interceptors));
        return this;
    }

    /**
     * Configure lifecycle interceptors with callbacks around concurrent request
     * execution that starts when a controller returns a {@link DeferredResult}.
     *
     * @param interceptors
     *            the interceptors to register
     */
    public AsyncSupportConfigurer registerDeferredResultInterceptors(
            DeferredResultProcessingInterceptor... interceptors) {
        Assert.notNull(interceptors, "Interceptors are required");
        this.deferredResultInterceptors.addAll(Arrays.asList(interceptors));
        return this;
    }

    protected AsyncTaskExecutor getTaskExecutor() {
        return this.taskExecutor;
    }

    protected Long getTimeout() {
        return this.timeout;
    }

    protected List<CallableProcessingInterceptor> getCallableInterceptors() {
        return this.callableInterceptors;
    }

    protected List<DeferredResultProcessingInterceptor> getDeferredResultInterceptors() {
        return this.deferredResultInterceptors;
    }
}
