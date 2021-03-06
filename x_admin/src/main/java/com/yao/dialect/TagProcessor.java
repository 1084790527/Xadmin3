package com.yao.dialect;
/**
 * @author 妖妖
 * @date 17:42 2021/2/25
 */

import com.yao.service.AuthorityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * 标签拦截器
 * 填充菜单什么的
 */
public class TagProcessor extends AbstractElementTagProcessor {
    private static Log log = LogFactory.getLog(TagProcessor.class);

    private static final int PRECEDENCE = 10000;
    private static final String TAG_NAME = "myTag";
    private AuthorityService authorityService;

    public TagProcessor(String dialectPrefix, AuthorityService authorityService) {
        super(
                // 此处理器将仅应用于HTML模式
                TemplateMode.HTML,
                // 要应用于名称的匹配前缀
                dialectPrefix,
                // 标签名称：匹配此名称的特定标签
                TAG_NAME,
                // 没有要应用于标签名称的前缀
                false,
                // 属性名称：将通过标签名称匹配
                null,
                // 没有要应用于属性名称的前缀
                false,
                // 优先(内部方言自己的优先)
                PRECEDENCE);
        this.authorityService = authorityService;
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
        //获取元素名称
        log.info(tag.getElementCompleteName());
//        WebEngineContext context2 = (WebEngineContext)context;
//        HttpServletRequest request = context2.getRequest();
//        log.info("用户:" + request.getSession().getAttribute("username"));
        IAttribute authorize = tag.getAttribute("authorize");
        log.info("匹配上:" + authorize.getValue());
        if (authorize.getValue().equals("/haha")) {
            structureHandler.removeElement();
        } else {
            structureHandler.removeTags();
        }
    }

}
