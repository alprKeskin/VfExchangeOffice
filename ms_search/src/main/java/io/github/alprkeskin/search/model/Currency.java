package io.github.alprkeskin.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@AllArgsConstructor
@Document(indexName = "currency")
@Setting(settingPath = "static/es-settings.json")
public class Currency {
    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(type = FieldType.Integer)
    private Integer queryNumber;
}
