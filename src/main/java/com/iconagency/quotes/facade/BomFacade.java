package com.iconagency.quotes.facade;

import com.iconagency.quotes.dto.BomDTO;
import com.iconagency.quotes.dto.BomHdrDTO;
import com.iconagency.quotes.entity.BOM;
import org.springframework.stereotype.Component;

@Component
public class BomFacade {
    public BomHdrDTO bomToBomHdrDTO(BOM bom) {
        BomHdrDTO bomHdrDTO = new BomHdrDTO();
        bomHdrDTO.setPart(bom.getPart());
        bomHdrDTO.setDescription(bom.getDescription());
        return bomHdrDTO;
    }

    public BomDTO bomToBomDTO(BOM bom) {
        BomDTO bomDTO = new BomDTO();
        bomDTO.setPart(bom.getPart());
        bomDTO.setSku(bom.getSku());
        bomDTO.setDescription(bom.getDescription());
        bomDTO.setCategory(bom.getCategory());
        bomDTO.setNonCalculatedPrice(bom.getNonCalculatedPrice());
        bomDTO.setListPrice(bom.getListPrice());
        bomDTO.setDiscountPercentage(bom.getDiscountPercentage());
        return bomDTO;
    }
}
