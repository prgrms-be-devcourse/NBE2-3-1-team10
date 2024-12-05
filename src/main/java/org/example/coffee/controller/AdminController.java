package org.example.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import org.example.coffee.config.PropertyConfig;
import org.example.coffee.dao.ProductDAO;
import org.example.coffee.dto.ProductDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PropertyConfig propertyConfig;
    @Autowired
    private ProductDAO productDAO;
    private final String dafaultIMG = "defaultIMG.png";


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
                upload.transferTo(new File(propertyConfig.getUploadPath() + fileName));

                System.out.println(fileName);
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

        ProductDTO to = new ProductDTO();
        to.setProduct_id(Integer.parseInt(productId));
        ProductDTO productDTO = productDAO.getProduct(to);

        model.addAttribute("to", productDTO);
        model.addAttribute("imagePath", propertyConfig.getUpload() + productDTO.getImagename());

        return "admin_product_modify";
    }

    @PostMapping("/modify_ok")
    public String modifyProductOk(HttpServletRequest request,
                                  @RequestParam("upload") MultipartFile upload,
                                  Model model
    ) {
        ProductDTO dto = new ProductDTO();
        dto.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
        dto.setProduct_name(request.getParameter("product_name"));
        dto.setPrice(Integer.parseInt(request.getParameter("price")));
        dto.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        dto.setCategory_id(Integer.parseInt(request.getParameter("category")));
        int deleteFlage = Integer.parseInt(request.getParameter("flag"));
        try {
            if (!upload.isEmpty()) {
                String fileName = upload.getOriginalFilename();
                String name = fileName.substring(0, fileName.lastIndexOf("."));
                String ext = fileName.substring(fileName.lastIndexOf("."));
                fileName = name + "_" + System.nanoTime() + ext;

                upload.transferTo(new File(propertyConfig.getHome() + propertyConfig.getPath() + fileName));
                dto.setImagename(fileName);
                productDAO.updateImage(dto);
            }

        } catch (IOException e) {
            System.out.println("[ERROR] : " + e.getMessage());
        }
        System.out.println("flag : " + deleteFlage);
        if (deleteFlage == 1) {
            System.out.println("이미지 삭제 로직");
            deleteImage(dto);
        }
        int flag = productDAO.updateProduct(dto);
        model.addAttribute("flag", flag);

        return "admin_product_modify_ok";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int productId, Model model) {
        int flag = productDAO.deleteProduct(productId);
        model.addAttribute("flag", flag);

        return "admin_delete";
    }

    public void deleteImage(ProductDTO dto) {

        String imageName = productDAO.getProduct(dto).getImagename();
        File fileToDelete = new File(propertyConfig.getHome() + propertyConfig.getPath() + imageName);

        if (fileToDelete.exists() && fileToDelete.isFile()) {
            boolean isDeleted = fileToDelete.delete();
            if (isDeleted) {
                System.out.println("이미지 파일 삭제 성공");
            } else {
                System.out.println("이미지 파일 삭제 실패");
            }
        } else {
            System.out.println("이미지 파일이 존재하지 않습니다");
        }
        // 기본 이미지 설정
        dto.setImagename(dafaultIMG);
        productDAO.updateImage(dto);
    }
}
