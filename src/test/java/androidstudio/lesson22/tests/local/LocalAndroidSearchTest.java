package androidstudio.lesson22.tests.local;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

import androidstudio.lesson22.tests.TestBase;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LocalAndroidSearchTest extends TestBase {
    @Test
    @Tag("android_emulator")
    @DisplayName("Проверка поиска статей")
    void successfulSearchAndroidTest() {
        step("Пропуск приветственного окна", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
        step("Ввод текста в поисковую строку", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Проверка, что статьи по запросу найдены", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android_emulator")
    @DisplayName("Проверка окна на первой странице")
    void onboardingScreen2() {
        step("Открытие приложения", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia")));

    }

    @Test
    @Tag("android_emulator")
    @DisplayName("Проверка текста на второй странице")
    void continueOnboardingScreen() {
        step("Переход на вторую страницу", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
        step("Проверка текста", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore")));
    }

    @Test
    @Tag("android_emulator")
    @DisplayName("Проверка текста на третьей странице")
    void doubleContinueOnboardingScreen() {
        step("Переход на третью страницу", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Проверка текста", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Reading lists with sync")));
    }

    @Test
    @Tag("android_emulator")
    @DisplayName("Проверка текста на четвертой странице")
    void threeContinueOnboardingScreen() {
        step("Переход на четвертую страницу", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Проверка текста", () ->
                $(id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Send anonymous data")));
    }
}
