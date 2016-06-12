package com.bnc.util;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
  
/**
 * �ѱ� ���ڵ� ���� Ŭ����
 */
public class EncodingFilter implements Filter{
    private String encoding = null;
    protected FilterConfig filterConfig = null;
    
    /**
     * �Ҹ� �޼ҵ�
     */
    public void destroy(){
        this.encoding = null;
        this.filterConfig = null;
    }
    /**
     * ���� �޼ҵ�
     * @param request ���� ��ü
     * @param response ���� ��ü
     * @param chain ����ü�� ��ü
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        if (request.getCharacterEncoding() == null){
            if (encoding != null){
                request.setCharacterEncoding(encoding);
            }
        }
        chain.doFilter(request,response);
    }
    /**
     * �ʱ�ȭ �޼ҵ�
     * @param filterConfig ���ͼ��� ��ü
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException{
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }
    /**
     * ���ͼ��� ��ü ��ȯ
     * @return ���ͼ��� ��ü
     */
    public FilterConfig getFilterConfig(){
        return filterConfig;
    }
    /**
     * ���ͼ�����ü ����
     * @param cfg ���� ������ü
     */
    public void setFilterConfig(FilterConfig cfg){
        filterConfig = cfg;
    }
} 