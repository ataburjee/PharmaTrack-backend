package com.pharmatrack.service;

import com.pharmatrack.dto.ColumnDTO;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColumnService {

    public List<ColumnDTO> getColumnsForEntity(Class<?> entityClass, List<String> disincludeColumnList) {
        List<ColumnDTO> columns = new ArrayList<>();

        for (Field field : entityClass.getDeclaredFields()) {
            String fieldName = field.getName();

            if (disincludeColumnList.contains(fieldName)) {
                continue;
            }
            String header = toTitleCase(fieldName);
            columns.add(new ColumnDTO(fieldName, header));
        }
        return columns;
    }

    private String toTitleCase(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) return fieldName;
        String header = fieldName.replaceAll("([A-Z])", " $1").trim();
        return header.substring(0, 1).toUpperCase() + header.substring(1);
    }

}
