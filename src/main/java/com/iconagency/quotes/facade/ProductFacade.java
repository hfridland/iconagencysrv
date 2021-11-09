package com.iconagency.quotes.facade;

import com.iconagency.quotes.dto.BomDTO;
import com.iconagency.quotes.dto.BomHdrDTO;
import com.iconagency.quotes.dto.ProductDTO;
import com.iconagency.quotes.dto.ProductHdrDTO;
import com.iconagency.quotes.entity.BOM;
import com.iconagency.quotes.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductFacade {
    @Autowired
    private BomFacade bomFacade;

    public ProductHdrDTO productToProductHdrDTO(Product product) {
        ProductHdrDTO productHdrDTO = new ProductHdrDTO();
        productHdrDTO.setProduct(product.getProduct());
        productHdrDTO.setDescription(product.getProductDescription());
        return productHdrDTO;
    }

    public ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct(product.getProduct());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setHtmlLink(product.getHtmlLink());
        productDTO.setCategory(product.getCategory());
        productDTO.setSubtotal(product.getSubtotal());
        productDTO.setNotes(product.getNotes());
        productDTO.setProductListPrice(product.getProductListPrice());

        productDTO.getParts().clear();
        product.getBomProducts()
                .stream()
                .forEach(bomProducts -> {
                    BOM bom = bomProducts.getPart();
                    BomDTO bomDTO = bomFacade.bomToBomDTO(bom);
                    productDTO.getParts().add(bomDTO);
                    /*BomDTO bomDTO = bomFacade.bomToBomDTO(bomProducts.getPart());
                    productDTO.getParts().add(bomDTO);*/
                });
        return productDTO;
    }
}
