package com.kvtsoft.fileuploader.repository;

import com.kvtsoft.fileuploader.model.RecoveryModel;
import com.kvtsoft.fileuploader.model.SampleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoveryInterface extends JpaRepository<SampleModel, Integer> {
}
