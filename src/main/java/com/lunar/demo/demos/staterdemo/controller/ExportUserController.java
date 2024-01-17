package com.lunar.demo.demos.staterdemo.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.lunar.demo.demos.staterdemo.entity.Student;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: hehsmk
 * @Date: 2023/12/29 16:40
 */
@RestController
public class ExportUserController {


    /**
     * 导入
     * @param file
     */
    @SneakyThrows
    @PostMapping(value = "/exportStudents")
    public void exportStudent(MultipartFile file){
        ImportParams params = new ImportParams();
        try {
            List<Student> students = ExcelImportUtil.importExcel(file.getInputStream(), Student.class, params);
            System.out.println(students);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/testgithub")
    public String testGithub(){
        return "github";
    }
}
