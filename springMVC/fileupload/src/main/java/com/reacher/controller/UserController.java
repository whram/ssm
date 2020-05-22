package com.reacher.controller;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    private final String success = "success";

    /*
    * 文件上传
    * */
    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传...");

        //使用fileupload组件完成文件上传
        //上传的位置
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file = new File(realPath);
        if (!file.exists()){
            //不存在则创建文件夹
            file.mkdirs();
        }

        //获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();//文件项工厂
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request对象
        List<FileItem> items = upload.parseRequest(request);
        //遍历
        for (FileItem item : items) {
            //进行判断，当前item对象是否是上传文件项
            if(item.isFormField()){
                //说明是普通的表单项
            }else {
                //说明是上传文件项
                //获取到上传文件的名称
                String fileName = item.getName();
                //把文件名设置唯一
                String uuid = UUID.randomUUID().toString().replace("-", "");
                //完成文件上传
                item.write(new File(realPath,uuid+fileName));
                //删除临时文件
                item.delete();
            }
        }

        return success;
    }

    /*
     * springmvc文件上传
     * */
    @RequestMapping("/fileUpload2")//                     MultipartFile upload 变量名必须和前端表单名称一致
    public String fileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springmvc文件上传...");

        //使用fileupload组件完成文件上传
        //上传的位置
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file = new File(realPath);
        if (!file.exists()){
            //不存在则创建文件夹
            file.mkdirs();
        }

        //说明是上传文件项
        //获取到上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件名设置唯一
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        //完成文件上传
        upload.transferTo(new File(realPath,filename));

        return success;
    }

    /*
    * springmvc跨服务器文件上传
    * */
    @RequestMapping("/fileUpload3")
    public String fileUpload3(MultipartFile upload) throws Exception {
        System.out.println("springmvc跨服务器文件上传...");

        //定义上传文件服务区的路径
        String path = "http://localhost:8080/fileupload_server/uploads/";

        //说明是上传文件项
        //获取到上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件名设置唯一
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        //完成跨服务器文件上传
        //创建客户端对象
        Client client = Client.create();
        //和文件服务器连接
        WebResource webResource = client.resource(path + filename);
        //上传文件
        webResource.put(upload.getBytes());

        return success;
    }

}
