package com.company.project.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 */
public class ExcelUtil {

    /**
     * 模板路径
     */
    private static final String TEMPLATE_PATH = "excel/";

    /**
     * 生成excel对象
     *
     * @param params       模板导出参数设置
     * @param data         模板导出数据
     * @param templateName 模板名称
     * @return workBook对象
     * @throws Exception 异常抛出
     */
    public static Workbook getWorkbook(TemplateExportParams params, Map<String, Object> data, String templateName) throws Exception {
        String templatePath = TEMPLATE_PATH + templateName;
        File file = getTemplateFile(templatePath);
        params.setTemplateUrl(file.getAbsolutePath());
        Workbook book = ExcelExportUtil.exportExcel(params, data);
        if (file.exists()) {
            file.delete();
        }
        return book;
    }

    /**
     * 导出excel对象
     *
     * @param response httpResponse对象
     * @param workbook workBook对象
     * @param fileName 导出文件名
     * @throws Exception 异常抛出
     */
    public static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String[] filenameAndSuffix = fileName.split("\\.");
        String fileNameX = filenameAndSuffix[0] + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + filenameAndSuffix[1];
        fileNameX = new String(fileNameX.getBytes("UTF-8"), "ISO-8859-1");
        response.setHeader("Content-disposition", "attachment; filename=" + fileNameX);
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        ServletOutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            workbook.write(outStream);
        } finally {
            workbook.close();
            if (outStream != null) {
                outStream.flush();
                outStream.close();
            }
        }
    }

    /**
     * 获取模板文件
     *
     * @param templatePath 模板路径
     * @return 模板文件
     * @throws Exception 异常抛出
     */
    public static File getTemplateFile(String templatePath) throws Exception {
        File file = File.createTempFile("temp", "");
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(templatePath);
        if (resources.length == 1) {
            InputStream inputStream = resources[0].getInputStream();
            inputStreamToFile(inputStream, file);
        } else {
            System.out.println("请检查模板文件是否存在");
        }
        return file;
    }

    /**
     * InputStream 转file
     *
     * @param ins  输入流
     * @param file 目标文件
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出到文件
     *
     * @param workbook
     * @param path
     * @param fileName
     * @return
     * @throws Exception
     */
    public static File export(Workbook workbook, String path, String fileName) throws Exception {
        String[] filenameAndSuffix = fileName.split("\\.");
        String fileNameX = filenameAndSuffix[0] + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + filenameAndSuffix[1];
        File file = new File(path + File.separator + fileNameX);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        OutputStream outStream = new BufferedOutputStream(new FileOutputStream(file));
        try {
            workbook.write(outStream);
        } finally {
            workbook.close();
            outStream.flush();
            outStream.close();
        }
        return file;
    }
}