package com.bnc.util;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
  
/**
 * 한글 엔코딩 서블릿 클레스
 */
public class EncodingFilter implements Filter{
    private String encoding = null;
    protected FilterConfig filterConfig = null;
    
    /**
     * 소멸 메소드
     */
    public void destroy(){
        this.encoding = null;
        this.filterConfig = null;
    }
    /**
     * 실행 메소드
     * @param request 질의 객체
     * @param response 응답 객체
     * @param chain 필터체인 객체
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
     * 초기화 메소드
     * @param filterConfig 필터설정 객체
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException{
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }
    /**
     * 필터설정 객체 반환
     * @return 필터설정 객체
     */
    public FilterConfig getFilterConfig(){
        return filterConfig;
    }
    /**
     * 필터설정객체 설정
     * @param cfg 필터 설정객체
     */
    public void setFilterConfig(FilterConfig cfg){
        filterConfig = cfg;
    }
} 