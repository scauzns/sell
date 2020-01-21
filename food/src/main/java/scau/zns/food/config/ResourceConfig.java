package scau.zns.food.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import scau.zns.common.constant.FileConstant;

/**
 *  图片绝对地址与虚拟地址映射
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(FileConstant.VIRTUAL_PATH + "**").addResourceLocations("file:"+ FileConstant.IMG_UPLOAD_PATH);
    }
}
