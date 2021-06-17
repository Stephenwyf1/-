package com.company.project.common.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class PDFHelper {

    public static void fillPDFTemplate(Map<String, Object> EntitiesMap, String OutputPath) throws DocumentException, IOException {
        System.out.println("Map:"+EntitiesMap);
        // 模板路径
        String templatePath = "D:\\Program Files (x86)\\Project\\WebProject\\zwens-springboot-manager-simple\\springboot-manager\\src\\main\\resources\\PDF\\template.pdf";

        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;

        BaseFont bfChinese = BaseFont.createFont("c://windows//fonts//simkai.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bfChinese);

        try {
            out = new FileOutputStream(OutputPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            form.setSubstitutionFonts(fontList);
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next();
                if(name == null) break;
                if(EntitiesMap.get(name) == null) continue;
                System.out.println("Field: "+name);
                form.setField(name, EntitiesMap.get(name).toString());
            }
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage1 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage1);

            PdfImportedPage importPage2 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 2);
            copy.addPage(importPage2);
            doc.close();
        } catch (IOException e) {
            System.out.println("------IOException------\n"+e.getMessage());
        } catch (DocumentException e) {
            System.out.println("------DocumentException------\n"+e.getMessage());
        }
    }

    public static Map<String, Object> preProcess(Map<String, Object> EntitiesMap) {
        if(EntitiesMap.get("Eye_red") != null) {
            if(EntitiesMap.get("Eye_red").equals("1")){
                EntitiesMap.put("Eye_red", "红");
            }
            else{
                EntitiesMap.put("Eye_red", "");
            }
        }
        if(EntitiesMap.get("Eye_green") != null) {
            if (EntitiesMap.get("Eye_green").equals("1")) {
                EntitiesMap.put("Eye_green", "绿");
            } else {
                EntitiesMap.put("Eye_green", "");
            }
        }
        if(EntitiesMap.get("Eye_purple") != null) {
            if (EntitiesMap.get("Eye_purple").equals("1")) {
                EntitiesMap.put("Eye_purple", "紫");
            } else {
                EntitiesMap.put("Eye_purple", "");
            }
        }
        if(EntitiesMap.get("Eye_blue") != null) {
            if (EntitiesMap.get("Eye_blue").equals("1")) {
                EntitiesMap.put("Eye_blue", "蓝");
            } else {
                EntitiesMap.put("Eye_blue", "");
            }
        }
        if(EntitiesMap.get("Eye_yellow") != null) {
            if (EntitiesMap.get("Eye_yellow").equals("1")) {
                EntitiesMap.put("Eye_yellow", "黄");
            } else {
                EntitiesMap.put("Eye_yellow", "");
            }
        }

        return EntitiesMap;
    }


    public static ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".pdf");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", LocalDateTime.now().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity .ok() .headers(headers) .contentLength(file.length()) .contentType(MediaType.parseMediaType("application/octet-stream")) .body(new FileSystemResource(file));
    }

}
