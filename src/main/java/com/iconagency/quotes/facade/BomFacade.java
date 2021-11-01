package com.iconagency.quotes.facade;

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
}
