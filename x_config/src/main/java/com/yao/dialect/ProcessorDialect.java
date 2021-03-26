package com.yao.dialect;
/**
 * h5 自定义标签处理 自定义权限拦截器
 * @author 妖妖
 * @date 17:41 2021/2/25
 */

import com.yao.service.AuthorityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.dialect.IProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProcessorDialect extends AbstractProcessorDialect implements IProcessorDialect {
    private static Log log = LogFactory.getLog(ProcessorDialect.class);
    private static final String PREFIX = "authority";
    @Autowired
    private AuthorityService authorityService;

    protected ProcessorDialect() {
        super("MyTag", PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String s) {
        Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new TagProcessor(PREFIX,authorityService));
        processors.add(new AttrProcessor(PREFIX,authorityService));
        return processors;
    }
}
