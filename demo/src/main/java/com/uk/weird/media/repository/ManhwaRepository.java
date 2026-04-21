package com.uk.weird.media.repository;

import com.uk.weird.media.entity.Manhwa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManhwaRepository extends JpaRepository<Manhwa, Long> {
}
