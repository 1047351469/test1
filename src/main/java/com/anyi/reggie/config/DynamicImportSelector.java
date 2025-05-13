package com.anyi.reggie.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class DynamicImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String fileName="custom-classes.txt";
        List<String> classNames = new ArrayList<>();
        try (InputStream inputStream = DynamicImportSelector.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                classNames.add(line.trim()); // 读取每一行，去除空格
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classNames.toArray(new String[0]);

    }
}
