package com.iconagency.quotes.web;

import com.iconagency.quotes.entity.*;
import com.iconagency.quotes.exceptions.ProductNotFound;
import com.iconagency.quotes.exceptions.QuoteNotFound;
import com.iconagency.quotes.repitems.QuoteWithoutSDPriceItem;
import com.iconagency.quotes.repository.ProductRepository;
import com.iconagency.quotes.repository.QuoteDetailsRepository;
import com.iconagency.quotes.repository.QuotesRepository;
import com.iconagency.quotes.security.JWTTokenProvider;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/report")
@CrossOrigin
public class ReportController {
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private QuotesRepository quotesRepository;
    @Autowired
    private QuoteDetailsRepository quoteDetailsRepository;
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/QuoteWithoutSDPrice/{token}")
    public ResponseEntity<byte[]> getReport(@PathVariable("token") String token) {
        Map<String, Object> params = jwtTokenProvider.getParams(token);
        Long quoteId = Long.parseLong((String)params.get("quoteId"));

        try {

            InputStream ioJasper = getClass().getResourceAsStream("/reports/QuoteWithoutSDPrice_test.jrxml");

            /*String filePath = ResourceUtils.getFile("classpath:reports/QuoteWithoutSDPrice_test.jrxml")
                .getAbsolutePath();*/

            Quotes quotes = quotesRepository.findQuotesByQuote(quoteId)
                    .orElseThrow(() -> new QuoteNotFound("Quote not found"));

            List<QuoteWithoutSDPriceItem> list = new ArrayList<>();

            List<QuoteDetails> quoteDetails = quoteDetailsRepository.findByQuoteOrderByDetail(quoteId);
            quoteDetails.stream()
                    .forEach(quoteDetail -> {
                        QuoteWithoutSDPriceItem item = new QuoteWithoutSDPriceItem();
                        item.setTag(quoteDetail.getTag());
                        item.setProduct(quoteDetail.getProduct());

                        Product product = productRepository.findProductByProduct(quoteDetail.getProduct())
                                .orElseThrow(() -> new ProductNotFound(""));
                        item.setDescription(product.getProductDescription());


                        double price = product.getBomProducts().stream()
                                .map(bomProducts -> {
                                    return bomProducts.getPart();
                                })
                                .map(bom -> bom.getListPrice() * bom.getDiscountPercentage())
                                .reduce(0.0, (s, i) -> s + i);
                        item.setPrice(price);

                        list.add(item);
                    });
            /*quotes.getQuoteDetails().stream()
                    .forEach(quoteDetails -> {
                        QuoteWithoutSDPriceItem item = new QuoteWithoutSDPriceItem();
                        item.setTag(quoteDetails.getTag());
                        item.setProduct(quoteDetails.getProduct());
                        list.add(item);
                    });*/

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("FileNo", "" + quotes.getQuote());
            parameters.put("QuoteNo", quotes.getFile());
            parameters.put("JobName", quotes.getJobName());
            parameters.put("Engineer", quotes.getEngineer());
            parameters.put("Date", quotes.getDate());

            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(list);


            //JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperReport report = JasperCompileManager.compileReport(ioJasper);
            report.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

            JasperPrint print =
                    JasperFillManager.fillReport(report, parameters, dataSource);

            byte[] byteArray = JasperExportManager.exportReportToPdf(print);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "QuoteWithoutSDPrice.pdf");
            headers.setContentDisposition(ContentDisposition.inline().build());

            return new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);

        } catch(Exception e) {
            System.out.println("Exception while creating report");
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
