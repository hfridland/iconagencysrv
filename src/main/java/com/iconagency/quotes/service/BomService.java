package com.iconagency.quotes.service;

import com.iconagency.quotes.entity.BOM;
import com.iconagency.quotes.exceptions.PartNotFoundException;
import com.iconagency.quotes.repository.BomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BomService {
    public static final Logger LOG = LoggerFactory.getLogger(BomService.class);

    @Autowired
    private BomRepository bomRepository;

    public List<String> getAllCategories() {
        return bomRepository.findAllCategories()
                .stream()
                .collect(Collectors.toList());
    }

    public List<BOM> findAllByCategory(String category) {
        return bomRepository.findAllByCategoryList(category);
    }

    public BOM findBomByPart(String part) {
        return bomRepository.findBomByPart(part)
                .orElseThrow(()-> new PartNotFoundException("Part not found"));
    }

    public boolean isPartNoInUse(String partNo) {
        return bomRepository.findBomByPart(partNo).isPresent();
    }

    public BOM createBom(BOM bom) {
        LOG.info("Saving BOM for PArt: {}", bom.getPart());
        return bomRepository.save(bom);
    }
}
