package com.iconagency.quotes.web;

import com.iconagency.quotes.dto.BomHdrDTO;
import com.iconagency.quotes.entity.BOM;
import com.iconagency.quotes.facade.BomFacade;
import com.iconagency.quotes.service.BomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/bom")
@CrossOrigin
public class BomController {
    @Autowired
    private BomFacade bomFacade;
    @Autowired
    private BomService bomService;

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> res = bomService.getAllCategories();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<BomHdrDTO>> getByCategory(@PathVariable("category") String category) {
        List<BomHdrDTO> boms = bomService.findAllByCategory(category)
                .stream()
                .map(bomFacade::bomToBomHdrDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(boms, HttpStatus.OK);
    }

    //@GetMapping("/part/{part}")
    //public ResponseEntity<BOM> getByPart(@PathVariable("part") String part) throws UnsupportedEncodingException {
    @GetMapping("/part")
    public ResponseEntity<BOM> getByPart(@RequestParam("part") String part) throws UnsupportedEncodingException {
        part = URLDecoder.decode(part, "UTF-8");
        BOM bom = bomService.findBomByPart(part);
        return new ResponseEntity<>(bom, HttpStatus.OK);
    }

    //@GetMapping("/part/{partNo}/inUse")
    //public ResponseEntity<Boolean> isPartNoInUse(@PathVariable("partNo") String partNo) {
    @GetMapping("/part/inUse")
    public ResponseEntity<Boolean> isPartNoInUse(@RequestParam("partNo") String partNo) {
        Boolean res = bomService.isPartNoInUse(partNo);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BOM> saveBom(@RequestBody BOM bom) {
        BOM createdBom = bomService.createBom(bom);
        return new ResponseEntity<>(createdBom, HttpStatus.CREATED);
    }
}
