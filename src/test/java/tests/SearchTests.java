package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;


public class SearchTests extends TestBase {

    @Test
    @Tag("android")
    @DisplayName("Проверка поиска")
    void successfulSearchTest() {
        step("Ввод слова в строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Проверка, что отобразились найденные статьи", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android")
    @DisplayName("Проверка ошибки при открытии статьи")
    void openArticleWithError() {
        step("Ввод слова в строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Bus");
        });
        step("Открытие найденной статьи", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click()
        );
        step("Проверка, что при открытии статьи появилась ошибка", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text"))
                        .shouldHave(exactText("An error occurred"))
        );
    }

//    @Test
//    @Tag("ios")
//    @DisplayName("?")
//    public void searchIosTest() {
//
//        step("Поиск контента", () -> {
//            $(accessibilityId("Text Button")).click();
//            $(id("Text Input")).sendKeys("hello@browserstack.com");
//        });
//        step("Проверка результата", () -> {
//            $(id("Text Output"))
//                    .shouldHave(visible);
//        });
//    }
}