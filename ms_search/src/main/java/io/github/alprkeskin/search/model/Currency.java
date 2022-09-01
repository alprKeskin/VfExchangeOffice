package io.github.alprkeskin.search.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "currency")
@Setting(settingPath = "static/es-settings.json")
public class Currency {
    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(type = FieldType.Integer)
    private Integer queryNumber;


    public void incrementQueryNumber() {
        queryNumber++;
    }
    public static Currency getDefault(String id) {
        return Currency.builder().id(id).queryNumber(0).build();
    }
}
