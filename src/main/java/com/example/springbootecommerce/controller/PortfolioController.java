package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.pojo.entity.ChildrenPortfolio;
import com.example.springbootecommerce.pojo.entity.Portfolio;
import com.example.springbootecommerce.pojo.requests.ChildrenPortfolioRequest;
import com.example.springbootecommerce.pojo.requests.PortfolioRequest;
import com.example.springbootecommerce.pojo.responses.ObjectResponse;
import com.example.springbootecommerce.pojo.responses.PortfolioPageResponse;
import com.example.springbootecommerce.pojo.responses.PortfolioResponse;
import com.example.springbootecommerce.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin(maxAge = 3600)
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @PostMapping("/save")
    public ResponseEntity<ObjectResponse> savePortfolio(@RequestBody PortfolioRequest request){
        Portfolio portfolio = portfolioService.createPortfolio(request);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Create Portfolio successfully",portfolio)
        );
    }
    @PutMapping("/update")
    public ResponseEntity<ObjectResponse> updatePortfolio(@RequestBody PortfolioRequest request,@RequestParam("id") Long id){
        Portfolio portfolio = portfolioService.updatePortfolio(request,id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Update Portfolio successfully",portfolio)
        );
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ObjectResponse> deletePortfolio(@RequestParam("id") Long id){
        portfolioService.deletePortfolioById(id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Delete Portfolio Successfully",null)
        );
    }
    @GetMapping("")
    public ResponseEntity<ObjectResponse> getPortfolio(){
        List<PortfolioResponse> portfolioResponses = portfolioService.getAllPortfolio();
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.OK,"Query Portfolio Successfully",portfolioResponses)
        );
    }
    @GetMapping("/page")
    public ResponseEntity<ObjectResponse> getPortfolioPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        PortfolioPageResponse portfolioPageResponse = portfolioService.getAllPortfolioPage(page,limit);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.OK,"Query Portfolio Successfully",portfolioPageResponse)
        );
    }

    @PostMapping("/children/save")
    public ResponseEntity<ObjectResponse> savePortfolioChildren(@RequestBody ChildrenPortfolioRequest request){
        ChildrenPortfolio childPortfolio = portfolioService.createChildPortfolio(request);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Create Portfolio Children Successfully",childPortfolio )
        );
    }
    @PutMapping("/children/update")
    public ResponseEntity<ObjectResponse> updatePortfolioChildren(@RequestBody ChildrenPortfolioRequest request, @RequestParam("id") Long id){
        ChildrenPortfolio childPortfolio = portfolioService.updateChildPortfolio(request,id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Update Portfolio Children Successfully",childPortfolio )
        );
    }

    @DeleteMapping("/children/delete")
    public ResponseEntity<ObjectResponse> deletePortfolioChildren(@RequestParam("id") Long id){
        portfolioService.deletePortfolioChildrenById(id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Delete Portfolio Children Successfully",null)
        );
    }

}
