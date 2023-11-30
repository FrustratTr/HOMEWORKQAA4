import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderFormTest {

    @BeforeAll
    static void setup() {
        String headless = System.getProperty("selenide.headless", "false");
        Configuration.headless = Boolean.parseBoolean(headless);
    }

    @Test
    void shouldSubmitOrderForm() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").find(exactText("Продолжить")).click();

        $("[data-test-id=order-success]")
                .shouldHave(visible)
                .shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
