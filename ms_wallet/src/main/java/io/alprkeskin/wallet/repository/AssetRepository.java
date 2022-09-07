package io.alprkeskin.wallet.repository;

import io.alprkeskin.wallet.model.document.Assets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends CrudRepository<Assets, String> {
}
