package com.tfg.swapCatBack.core.controllers.services;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


public interface IExcelService {

    void downloadHistory(LocalDate start, LocalDate end, HttpServletResponse response) throws IOException;

}
