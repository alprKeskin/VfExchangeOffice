package io.alprkeskin.wallet.model.document;

import io.alprkeskin.wallet.model.Asset;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("ASSETS")
public class Assets {
    @NotNull
    @PrimaryKey
    private String email;
    private Map<String, Asset> assets;
}
