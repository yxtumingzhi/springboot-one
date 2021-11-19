package com.hope.one.config;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-29 9:51
 */

import com.hope.one.es.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {


    @Bean(
            initMethod = "init"
    )
    public Article article(){
        Article article = new Article();
        article.setAuthors(null);
        article.setTitle("asdfasdfasdf");
        return article;
    }

}
