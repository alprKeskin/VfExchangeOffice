package io.alprkeskin.wallet.repository;

import io.alprkeskin.wallet.model.document.Assets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends MongoRepository<Assets, String> {
}