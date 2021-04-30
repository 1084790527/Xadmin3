package com.yao.interceptor;
/**
 * @author 妖妖
 * @date 16:19 2021/3/8
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

public class XssHttpWrapper extends HttpServletRequestWrapper {
    private static Log log = LogFactory.getLog(XssHttpWrapper.class);
    private HttpServletRequest orgRequest;
    private Pattern sp1 = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
    private Pattern sp2 = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private Pattern sp3 = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private Pattern sp5 = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private Pattern sp4 = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
    private Pattern sp6 = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private Pattern sp7 = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private Pattern sp8 = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
    private Pattern sp9 = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
    private Pattern sp10 = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private Pattern sp11 = Pattern.compile("<iframe>(.*?)</iframe>", Pattern.CASE_INSENSITIVE);
    private Pattern sp12 = Pattern.compile("</iframe>", Pattern.CASE_INSENSITIVE);
    private Pattern sp13 = Pattern.compile("<iframe(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    public XssHttpWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {

        String[] values = super.getParameterValues(name);
        if(values != null) {
            int length = values.length;
            String[] escapseValues = new String[length];
            for(int i = 0; i < length; i++){
                escapseValues[i] = xssEncode(values[i]);
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }

    @Override
    public String getQueryString() {
        return xssEncode(super.getQueryString());
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpWrapper) {
            return ((XssHttpWrapper) req).getOrgRequest();
        }
        return req;
    }

    public String escape(String s)
    {
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            switch (c)
            {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
                case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '%':
                    sb.append('％'); // 全角冒号
                    break;
                default:
                    sb.append(c);
                    break;
            }

        }
        return sb.toString();
    }


    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    public String xssEncode(String s)
    {
        if (s == null || s.isEmpty())
        {
            return s;
        }

        String result = stripXSS(s);
        if (null != result)
        {
            result = escape(result);
        }

        return result;
    }

    private String stripXSS(String value) {
        if (value != null) {
            value = value.replaceAll("", "");
            value = sp1.matcher(value).replaceAll("");
            value = sp2.matcher(value).replaceAll("");
            value = sp3.matcher(value).replaceAll("");
            value = sp4.matcher(value).replaceAll("");
            value = sp5.matcher(value).replaceAll("");
            value = sp6.matcher(value).replaceAll("");
            value = sp7.matcher(value).replaceAll("");
            value = sp8.matcher(value).replaceAll("");
            value = sp9.matcher(value).replaceAll("");
            value = sp10.matcher(value).replaceAll("");
            value = sp11.matcher(value).replaceAll("");
            value = sp12.matcher(value).replaceAll("");
            value = sp13.matcher(value).replaceAll("");
        }
        return value;
    }
}
