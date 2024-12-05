package org.example.coffee.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.coffee.dao.ProductDAO;
import org.example.coffee.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/list")
    public String product(Model model) {

        ArrayList<ProductDTO> list = productDAO.productList();
        model.addAttribute("list", list);

        return "admin_list";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {

        return "admin_add";
    }

    @PostMapping("/add_ok")
    public String addProduct(HttpServletRequest request,
                             @RequestParam("upload") MultipartFile upload,
                             Model model) {

        ProductDTO dto = new ProductDTO();
        dto.setProduct_name(request.getParameter("product_name"));
        dto.setPrice(Integer.parseInt(request.getParameter("price")));
        dto.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        String category = request.getParameter("category");

        if (category.equals("coffee")) {
            dto.setCategory_id(1);
        } else if (category.equals("coffeeBean")) {
            dto.setCategory_id(2);
        } else if (category.equals("tea")) {
            dto.setCategory_id(3);
        }

        dto.setImagename(request.getParameter("image_name"));

        if(!upload.isEmpty()) {
            try {
                String fileName = upload.getOriginalFilename();

                // fileName 중복제거
                String name = fileName.substring(0, fileName.lastIndexOf("."));
                String ext = fileName.substring(fileName.lastIndexOf("."));

                fileName = name + "_" + System.nanoTime() + ext;

                // 경로 주의
                if (category.equals("coffee")) {
                    upload.transferTo(new File("/Users/yousuho/study/springEx/coffee/src/main/resources/static/upload/coffee", fileName));
                } else if (category.equals("coffeeBean")) {
                    upload.transferTo(new File("/Users/yousuho/study/springEx/coffee/src/main/resources/static/upload/coffeebean", fileName));
                } else if (category.equals("tea")) {
                    upload.transferTo(new File("/Users/yousuho/study/springEx/coffee/src/main/resources/static/upload/tea", fileName));
                }

                dto.setImagename(fileName);

            } catch (IOException e) {
                System.out.println("IO Exception " + e.getMessage());
            }
        }

        int flag = productDAO.insert(dto);

        System.out.println(flag);

        model.addAttribute("flag", flag);

        return "admin_add_ok";
    }

    @GetMapping("/modify")
    public String modifyProduct(@RequestParam String productId, Model model) {

        System.out.println(productId);

        return "admin_modify";
    }
}
