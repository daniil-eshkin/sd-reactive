package sd.lab4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sd.lab4.autoincrement.IdCounterService;
import sd.lab4.model.Product;
import sd.lab4.model.ProductDto;
import sd.lab4.model.RegionalPriceMark;
import sd.lab4.repository.ProductRepository;
import sd.lab4.util.PriceUtils;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IdCounterService idCounterService;
    private final ProductRepository productRepository;

    public Flux<ProductDto> getProducts(RegionalPriceMark mark) {
        return productRepository.findAll()
                .map(p -> p.toDto(mark));
    }

    public Mono<Product> saveProduct(Product product, RegionalPriceMark mark) {
        product.setUsdPrice(PriceUtils.convertPrice(mark, RegionalPriceMark.USD, product.getUsdPrice()));
        return idCounterService.setProductId(product)
                .flatMap(productRepository::save);
    }
}
