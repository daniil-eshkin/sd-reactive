package sd.lab4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sd.lab4.model.Product;
import sd.lab4.model.ProductDto;
import sd.lab4.model.User;
import sd.lab4.service.ProductService;
import sd.lab4.service.UserService;

@Controller
@RequiredArgsConstructor
public class ShopController {
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/{userId}/get-products/")
    public Flux<ProductDto> getProducts(@PathVariable long userId) {
        return userService.getUser(userId)
                .flatMapMany(u -> productService.getProducts(u.getPricePreferences()));
    }

    @PostMapping("/register")
    public Mono<User> register(@ModelAttribute User user) {
        return userService.registerUser(user);
    }

    @PostMapping("{userId}/save-product")
    public Mono<Product> saveProduct(@PathVariable long userId, @ModelAttribute Product product) {
        return userService.getUser(userId)
                .flatMap(u -> productService.saveProduct(product, u.getPricePreferences()));
    }
}
