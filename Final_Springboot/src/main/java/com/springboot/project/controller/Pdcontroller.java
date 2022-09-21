package com.springboot.project.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.project.model.Pdboard;
import com.springboot.project.sevice.PdService;

import net.bytebuddy.utility.RandomString;

//아래 기본 시큐리티 적용안되게하는구문 시큐리티 구현시 삭제 
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Controller
public class Pdcontroller {

//    @RequestMapping("/testBoard")
//    public String testBoardList(Model model) {
//        List<Pdboard> pdlist = new ArrayList<Pdboard>();
//
//        // 임시로 게시물 10개
//        for (int i = 0; i < 9; i++) {
//        	Pdboard pdboard = new Pdboard();
//        	pdboard.setPdcode(new Long(i));
//        	pdboard.setTitle("제목   " + i);
//        	pdboard.setAdmin("작성자 " + i);
//        	pdboard.setContent("글내용  " + i);
//        	pdboard.setPrice(new Long(i));
//        	pdboard.setRegdate(new Date());
//        	pdlist.add(pdboard);
//        }
//        
//        model.addAttribute("pdlist", pdlist);
//        return "testBoard"; // jsp 파일 이름
//    }
    
    @Autowired
    private PdService pdService;
    
    @RequestMapping("/getBoardList")
    public String getpdBoardList(Model model, Pdboard pdboard){
        List<Pdboard> pdlist = pdService.getpdBoardList(pdboard);
        
        model.addAttribute("pdlist", pdlist);
        return "getBoardList";
    }

    /**
     * 글쓰기 화면
     * @return
    @RequestMapping("/insertBoardView")
    public String insertBoardView() {
        return "pdboard/insertpdBoard";
    }
     */
    
    /**
     * 글쓰기 화면
     * @return
     */
    @RequestMapping("/insertBoardView")
    public String insertBoardView() {
        return "pdboard/insertpdBoard";
    }

    /**
     * 글쓰기 처리
     * @param Pdboard
     * @return
     */
    @RequestMapping("/insertBoard")
    public String insertBoard(HttpServletRequest request,Pdboard pdboard,@RequestPart MultipartFile files) throws Exception{
    	
    	String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        
        String fileUrl = "C:\\SPRINGBOOT_STUDY\\Final_Boot2-master\\src\\main\\resources\\static\\uploadFiles\\uploadFiles";
        
        do {
        	destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
        	destinationFile = new File(fileUrl + destinationFileName);
        } while(destinationFile.exists());
        
        destinationFile.getParentFile().mkdir();
        files.transferTo(destinationFile);
    	
        pdService.insertpdBoard(pdboard);
        return "redirect:getBoardList";
    }
    
    /**
            상세 글 보기
      @param Pdboard
      @param model
      @return
     */
    @RequestMapping("/getBoard")
    public String getpdBoard(Pdboard pdboard, Model model) {
        model.addAttribute("pdread", pdService.getpdBoard((pdboard)));
        return "getBoard";
    }
    
    /**
     * 글 수정 처리 후 목록으로 이동
     * @param Pdboard
     * @return
     */
    
    @RequestMapping("/updateBoard")
    public String updateBoard(Pdboard pdboard) {
        pdService.updatepdBoard(pdboard);
        return "forward:getBoardList";
    }
    
    /**
     * 글 삭제 처리 후 목록으로 이동
     * @param Pdboard
     * @return
     */
    
    @RequestMapping("/deleteBoard")
    public String deletepdBoard(Pdboard pdboard) {
        pdService.deletepdBoard(pdboard);
        return "forward:getBoardList";
    }
    
    
    
    
}