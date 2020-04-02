package com.qlu.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlu.bean.Book;
import com.qlu.bean.BookDonation;
import com.qlu.bean.StackRoom;
import com.qlu.common.utils.ImageUtils;
import com.qlu.service.IBookDonationService;
import com.qlu.service.IBookService;
import com.qlu.service.IStackRoomService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.model.IModel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/admin/book")
@Controller
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IStackRoomService stackRoomService;

    @GetMapping("/add")
    public String to_add(Model model){
        List<StackRoom> list = stackRoomService.list();
        model.addAttribute("stackRoomList",list);
        return "admin/book/add";
    }

    @GetMapping("/list")
    public String to_list(@RequestParam(defaultValue = "1") int pageNumber,
                          @RequestParam(defaultValue = "4") int pageSize,
                          Model model){
        Page<Book> page = new Page<>(pageNumber,pageSize);
        page = bookService.page(page);
        model.addAttribute("page",page);
        return "admin/book/list";
    }
    @PostMapping("/search")
    public String to_list(@RequestParam(defaultValue = "1") int pageNumber,
                          @RequestParam(defaultValue = "4") int pageSize,
                          Book book,
                          Model model){
        Page<Book> page = new Page<>(pageNumber,pageSize);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (book!=null){
            if (book.getAuthor()!=null&&book.getAuthor()!=""){
                queryWrapper.like("author",book.getAuthor());
            }
            if (book.getBookName()!=null&&book.getBookName()!=""){
                queryWrapper.like("book_name",book.getBookName());
            }
            if (book.getType()!=null&&book.getType()!=""){
                queryWrapper.like("type",book.getType());
            }
        }

        page = bookService.page(page,queryWrapper);
        model.addAttribute("page",page);
        return "admin/book/list :: bookList";
    }

    @PostMapping("/add")
    public String add(Book book){
        book.setIsBorrow(0);
        book.setBorrowTimes(0);
        book.setPicture("/pic/library.jpg");
        bookService.save(book);
        return "redirect:/admin/book/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id){
        boolean b = bookService.removeById(id);
        return "redirect:/admin/book/list";
    }

    @GetMapping("/edit/{id}")
    public String to_editBook(@PathVariable int id,Model model){
        List<StackRoom> list = stackRoomService.list();
        model.addAttribute("stackRoomList",list);
        Book book = bookService.getById(id);
        model.addAttribute("book",book);
        return "/admin/book/edit";
    }

    @PostMapping("/edit")
    public String editBook(Book book){
        boolean b = bookService.updateById(book);
        return "redirect:/admin/book/list";
    }

    @GetMapping("/editPicture/{id}")
    public String to_editPicture(@PathVariable int id,Model model){
        Book book = bookService.getById(id);
        model.addAttribute("book",book);
        return "admin/book/editPicture";
    }



    @PostMapping("/editPicture")
    public String editPicture(int id, MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        int i = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(i);
        String uuid = UUID.randomUUID().toString();
        String newName = uuid+suffix;
        File dest = new File("D:/upload/"+newName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {

            String saveName = "/pic/"+newName;
            file.transferTo(dest);
            Book book = new Book();
            book.setId(id);
            book.setPicture(saveName);
            bookService.updateById(book);
            ImageUtils.zoomImage("D:/upload/"+newName,"D:/upload/"+newName,430,500);
        }catch (Exception e){
            e.fillInStackTrace();
        }

        return "redirect:/admin/book/list";
    }

    @GetMapping("/bookDonationList")
    public String bookDonationList(){
        return "/admin/book/bookDonationList";
    }

    @Autowired
    private IBookDonationService bookDonationService;

    @RequestMapping("/donationListTable")
    @ResponseBody
    public com.qlu.common.bean.Page<BookDonation> donationListTable(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize){
        Page<BookDonation> page = bookDonationService.page(new Page<BookDonation>(pageNum, pageSize), new QueryWrapper<BookDonation>().eq("status", 0));
       return new com.qlu.common.bean.Page<>(pageSize,page);
    }



    @PostMapping("/donationBookAgree")
    public String donationBookAgree(Integer id,Integer srid){
        BookDonation bookDonation = bookDonationService.getById(id);
        bookDonation.setStatus(1);
        bookDonationService.updateById((bookDonation));

        Book book = new Book();
        book.setIsBorrow(0);
        book.setBorrowTimes(0);
        book.setPicture(bookDonation.getPicture());
        book.setSrid(srid);
        book.setAuthor(bookDonation.getAuthor());
        book.setBookName(bookDonation.getBookName());
        book.setInfo(bookDonation.getInfo());
        book.setPublishDate(bookDonation.getPublishDate());
        book.setType(bookDonation.getType());

        bookService.save(book);
        return "/admin/book/bookDonationList";
    }


    @RequestMapping("/exportBookInfo")
    public void exportBookInfo(HttpServletResponse response){
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet();
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("书名");
        row.createCell(2).setCellValue("作者");
        row.createCell(3).setCellValue("类型");
        row.createCell(4).setCellValue("借阅次数");
        row.createCell(5).setCellValue("书库名称");
        row.createCell(6).setCellValue("书库位置");
        row.createCell(7).setCellValue("描述信息");
        row.createCell(8).setCellValue("出版日期");


        List<Book> list = bookService.list();

        int index = 1;
        for (Book book: list){

            String bookName = book.getBookName();
            String author = book.getAuthor();
            String type = book.getType();
            Integer borrowTimes = book.getBorrowTimes();
            Integer srid = book.getSrid();
            StackRoom byId = stackRoomService.getById(srid);
            String stackRoomName = byId.getName();
            String stackRoomLocation = byId.getLocation();
            String info = book.getInfo();
            Date publishDate = book.getPublishDate();


            XSSFRow row1 = sheet.createRow(index);

            row1.createCell(0).setCellValue(index);
            row1.createCell(1).setCellValue(bookName);
            row1.createCell(2).setCellValue(author);
            row1.createCell(3).setCellValue(type);
            row1.createCell(4).setCellValue(borrowTimes);
            row1.createCell(5).setCellValue(stackRoomName);
            row1.createCell(6).setCellValue(stackRoomLocation);
            row1.createCell(7).setCellValue(info);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
            String format = simpleDateFormat.format(publishDate);
            row1.createCell(8).setCellValue(format);
            index++;
        }


        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + "books.xlsx");

        try {
            xssfWorkbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            xssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
