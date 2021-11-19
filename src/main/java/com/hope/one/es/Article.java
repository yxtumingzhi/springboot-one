package com.hope.one.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-25 15:21
 */
//注意indexName要小写
@Document(indexName = "blog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    private String id;

    private String title;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authors;

    public Article(String title) {
        this.title = title;
    }

    public void init(){
        System.out.println("这是一个初始化的方法。。。");
    }

    @PostConstruct
    public void init2(){
        System.out.println("这呦呦呦是一个初始化的方法。。。");
    }

    @Data
    public static class Author {
        private String name;

        public Author(String name) {
            this.name = name;
        }
    }
}

