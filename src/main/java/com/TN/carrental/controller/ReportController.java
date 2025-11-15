package com.TN.carrental.controller; // (1) Đặt trong package controller

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.TN.carrental.model.CarRental;
import com.TN.carrental.service.CarRentalService;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
// Import thêm Alert nếu bạn muốn thông báo lỗi
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ReportController {
    
    @FXML private DatePicker dpStartDate;
    @FXML private DatePicker dpEndDate;
    @FXML private TableView<CarRental> rentalTable;
    
    private CarRentalService carRentalService;
    
    public ReportController() {
        this.carRentalService = new CarRentalService();
    }
    
    @FXML
    private void handleGenerateReport() {
        // (2) Lấy LocalDate từ DatePicker
        LocalDate ldStart = dpStartDate.getValue();
        LocalDate ldEnd = dpEndDate.getValue();

        // (3) Kiểm tra người dùng đã chọn ngày chưa
        if (ldStart == null || ldEnd == null) {
            showAlert("Lỗi", "Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.");
            return;
        }

        // (4) Chuyển đổi từ LocalDate (JavaFX) sang java.util.Date (Hibernate/Service)
        Date startDate = Date.from(ldStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(ldEnd.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // (5) Kiểm tra logic ngày
        if (startDate.after(endDate)) {
            showAlert("Lỗi", "Ngày bắt đầu không thể sau ngày kết thúc.");
            return;
        }
        
        // (6) Chỉ cần gọi 1 hàm Service
        List<CarRental> reportData = carRentalService.getStatisticsReport(startDate, endDate);
        
        // (7) Đổ dữ liệu vào bảng
        rentalTable.getItems().setAll(reportData);
        
        if (reportData == null || reportData.isEmpty()) {
            showAlert("Thông báo", "Không tìm thấy dữ liệu cho khoảng thời gian này.");
        }
    }
    
    // (Helper method) Hàm trợ giúp để hiển thị thông báo
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}