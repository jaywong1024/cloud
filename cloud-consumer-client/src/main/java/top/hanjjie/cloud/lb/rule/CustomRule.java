package top.hanjjie.cloud.lb.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义一个（简易的）负载均衡轮询策略
 */
@Slf4j
@Component
public class CustomRule extends AbstractLoadBalancerRule {

    /**
     * 统计请求次数第一次为 0
     */
    private final AtomicInteger requestCount = new AtomicInteger(0);

    @Override
    public Server choose(Object key) {
        log.info("开始选择服务...");
        // 1.获取可用服务列表 allServers 和可用服务总数 allServersSize
        ILoadBalancer loadBalancer = getLoadBalancer();
        List<Server> reachableServers = loadBalancer.getReachableServers();
        int reachableServersSize = reachableServers.size();
        log.info("服务总数：{}", reachableServersSize);
        // 2.获取实际调用服务的下标
        int index = this.getIndexBySpinLockAndCas(reachableServersSize);
        log.info("实际调用的服务下标为：{}", index);
        // 3.返回服务
        Server server = reachableServers.get(index);
        log.info("实际调用服务地址：{}", server.getHostPort());
        return server;
    }

    /**
     * 利用自旋锁和乐观锁，返回实际调用服务的下标
     * @param reachableServersSize 可用服务总数
     * @return 实际调用服务的下标
     */
    private int getIndexBySpinLockAndCas(int reachableServersSize) {
        int current, next;
        do {
            current = this.requestCount.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!requestCount.compareAndSet(current, next));
        return current % reachableServersSize;
    }


    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {}

}
