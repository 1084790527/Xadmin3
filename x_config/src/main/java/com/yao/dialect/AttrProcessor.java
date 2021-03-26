package com.yao.dialect;
/**
 * @author 妖妖
 * @date 9:48 2021/2/26
 */

import com.yao.service.AuthorityService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * 标签属性拦截器
 * 判断是否有权限
 */

public class AttrProcessor extends AbstractAttributeTagProcessor {
    private static Log log = LogFactory.getLog(AttrProcessor.class);
    private static final int PRECEDENCE = 10000;
    private static final String ATTR_NAME = "perm";
    private AuthorityService authorityService;

    protected AttrProcessor(String dialectPrefix, AuthorityService authorityService) {
        super(
                // 此处理器将仅应用于HTML模式
                TemplateMode.HTML,
                // 要应用于名称的匹配前缀
                dialectPrefix,
                // 标签名称：匹配此名称的特定标签
                null,
                // 没有要应用于标签名称的前缀
                false,
                // 属性名称：将通过标签名称匹配
                ATTR_NAME,
                // 没有要应用于属性名称的前缀
                false,
                // 优先(内部方言自己的优先)
                PRECEDENCE,
                // 是否删除标签
                true);
        this.authorityService = authorityService;
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName name, String value, IElementTagStructureHandler handler) {
//        log.info(name.getAttributeName()+"   "+value);
        if (StringUtils.isBlank(value)){
            handler.removeElement();
        }else {
            if (!authorityService.isPri(value)){
                handler.removeElement();
            }
        }

    }
}
