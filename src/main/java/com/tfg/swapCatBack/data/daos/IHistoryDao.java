package com.tfg.swapCatBack.data.daos;

import com.tfg.swapCatBack.data.entities.HistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHistoryDao extends JpaRepository<HistoryModel, Long> {

    Optional<HistoryModel> findById(long id);

    List<HistoryModel> findAll();

    List<HistoryModel> findAllByUser_Id(long id);

    List<HistoryModel> findAllByUser_Username(String name);

    void deleteById(long id);

    List<HistoryModel> findAllByUser_UsernameAndDateAfterAndDateBefore(String username, LocalDate start, LocalDate end);


}
