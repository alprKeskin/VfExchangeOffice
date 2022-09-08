package io.alprkeskin.wallet.model.document;

import io.alprkeskin.wallet.model.Asset;
import io.alprkeskin.wallet.model.AssetTransaction;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("ASSETS")
public class Assets {
    @Id
    @MongoId(targetType = FieldType.STRING)
    private String email;
    private Map<String, Asset> assets;

    public static Assets getDefault(String email) {
        return Assets.builder().email(email).assets(new HashMap<>()).build();
    }
}
