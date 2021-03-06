/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2016-12-23 20:55 创建
 */
package org.bekit.flow.boot;

import org.bekit.event.boot.EventBusConfiguration;
import org.bekit.flow.FlowEngine;
import org.bekit.flow.engine.DefaultFlowEngine;
import org.bekit.flow.flow.FlowsHolder;
import org.bekit.flow.listener.DefaultFlowListener;
import org.bekit.flow.processor.ProcessorsHolder;
import org.bekit.flow.transaction.FlowTxsHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

/**
 * 流程引擎配置类
 * （非spring-boot项目需手动引入本配置类完成流程引擎配置）
 */
@Configuration
@Import({EventBusConfiguration.class,
        FlowsHolder.class,
        ProcessorsHolder.class,
        FlowTxsHolder.class,
        DefaultFlowListener.class})
public class FlowEngineConfiguration {

    // 流程引擎
    @Bean
    @DependsOn({"org.bekit.flow.flow.FlowsHolder", "org.bekit.flow.transaction.FlowTxsHolder"})     // 保证出现循环引用时不会出错
    public FlowEngine flowEngine(FlowsHolder flowsHolder, FlowTxsHolder flowTxsHolder) {
        return new DefaultFlowEngine(flowsHolder, flowTxsHolder);
    }
}
