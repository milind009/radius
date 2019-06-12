package com.crawler.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final int ONE_MB = 1000000;

    // Temporary location where files will be stored
    private static final String LOCATION = System.getProperty("java.io.tmpdir");
    // 5MB : Max file size.Beyond that size Spring will throw exception.
    private static final long MAX_FILE_SIZE = 10 * ONE_MB;
    // 20MB : Total request size containing Multipart.
    private static final long MAX_REQUEST_SIZE = 20 * ONE_MB;
    // Size threshold after which files will be written to disk
    private static final int FILE_SIZE_THRESHOLD = 1 * ONE_MB;

    /*
     * We will not use any RootConfig class. For simplicity, assume only one
     * servlet dispatcher and use it
     */

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE,
                MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        registration.setMultipartConfig(multipartConfigElement);
    }
}
