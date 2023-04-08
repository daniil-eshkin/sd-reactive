package sd.lab4.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sd.lab4.model.Product;
import sd.lab4.model.ProductDto;
import sd.lab4.model.RegionalPriceMark;
import sd.lab4.model.User;
import sd.lab4.autoincrement.IdCounterRepository;
import sd.lab4.repository.ProductRepository;
import sd.lab4.repository.UserRepository;

import java.util.List;

@SpringBootTest
public class ShopControllerTest {
    @Autowired
    IdCounterRepository idCounterRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShopController controller;

    private User createTestUser(String name, RegionalPriceMark mark) {
        User user = new User();
        user.setName(name);
        user.setPricePreferences(mark);

        return user;
    }

    private Product createTestProduct(String name, double price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription("Test product: " + name);
        product.setUsdPrice(price);

        return product;
    }

    @BeforeEach
    public void cleanup() {
        idCounterRepository.deleteAll().block();
        productRepository.deleteAll().block();
        userRepository.deleteAll().block();
    }

    @Test
    public void test() {
        var u1 = createTestUser("Eduardo", RegionalPriceMark.USD);
        var u2 = createTestUser("Sasha", RegionalPriceMark.RU);

        u1 = controller.register(u1).block();
        u2 = controller.register(u2).block();

        // AutoIncrement check
        Assertions.assertEquals(1, u1.getId());
        Assertions.assertEquals(2, u2.getId());

        controller.saveProduct(u1.getId(), createTestProduct("Latex glove", 300)).block();
        controller.saveProduct(u2.getId(), createTestProduct("Product", 20)).block();

        // Price check
        Assertions.assertEquals(List.of(300d, 40d), controller.getProducts(u1.getId())
                .map(ProductDto::getPrice)
                .collectList()
                .block());
        Assertions.assertEquals(List.of(150d, 20d), controller.getProducts(u2.getId())
                .map(ProductDto::getPrice)
                .collectList()
                .block());

        // No user
        try {
            controller.getProducts(3).collectList().block();
            Assertions.fail();
        } catch (IllegalArgumentException ignored) {

        }
    }
}
