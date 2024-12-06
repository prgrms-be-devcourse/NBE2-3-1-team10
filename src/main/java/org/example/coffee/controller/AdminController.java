package org.example.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import org.example.coffee.dao.ProductDAO;
import org.example.coffee.dto.ProductDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //TODO Config class 두는것 고려 (환경 변수를 final 로 하고 싶음)
    @Value("${my.path}")
    private String path;
    @Value("${my.image-path")
    private String imagePath;
    private String homeDir = System.getProperty("user.home");


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
        dto.setCategory_id(Integer.parseInt(request.getParameter("category")));

        if(!upload.isEmpty()) {
            try {
                String fileName = upload.getOriginalFilename();

                // fileName 중복제거
                String name = fileName.substring(0, fileName.lastIndexOf("."));
                String ext = fileName.substring(fileName.lastIndexOf("."));

                fileName = name + "_" + System.nanoTime() + ext;

                upload.transferTo(new File( homeDir + path + fileName));
                dto.setImagename(fileName);

            } catch (IOException e) {
                System.out.println("IO Exception " + e.getMessage());
            }
        }

        int flag = productDAO.insert(dto);

        model.addAttribute("flag", flag);

        return "admin_add_ok";
    }

    @GetMapping("/modify")
    public String modifyProduct(@RequestParam String productId, Model model) {

        System.out.println(productId);
        ProductDTO to = new ProductDTO();
        to.setProduct_id(Integer.parseInt(productId));
        ProductDTO productDTO = productDAO.getProduct(to);
        model.addAttribute("to", productDTO);
        model.addAttribute("imagePath", imagePath);
        return "admin_product_modify";
    }
}
