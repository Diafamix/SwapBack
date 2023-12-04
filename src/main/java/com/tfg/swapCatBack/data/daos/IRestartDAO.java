package com.tfg.swapCatBack.data.daos;

import com.tfg.swapCatBack.data.entities.RestartModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestartDAO extends JpaRepository<RestartModel, Long> {

    Optional<RestartModel> findByMonthAndAndYear(String month, String year);

}
